/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.BUS_Exam;
import BUS.BUS_Questions;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Exam;
import DTO.DTO_Questions;
import DTO.DTO_Test;
import DTO.DTO_Topic;

/**
 *
 * @author KIET
 */
public class XemDeThi extends javax.swing.JDialog {
    private DTO_Exam exam;
    private BUS_Questions busCauHoi = new BUS_Questions();
    private BUS_Exam busExam = new BUS_Exam();
    private BUS_Test busTest = new BUS_Test();
    private BUS_Topic busTopic = new BUS_Topic();
    private ArrayList<DTO_Questions> listQuestions;
    private ArrayList<DTO_Questions> selectedQuestions = new ArrayList<>();

    /**
     * Creates new form XemDeThi
     */
    public XemDeThi(java.awt.Frame parent, boolean modal, String exCode) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        // Load exam data
        loadExamData(exCode);

        // Disable text fields to prevent editing
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField3.setEditable(false);

        // Set close button action
        jButton1.addActionListener(evt -> dispose());

        // Center align text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT column
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Mã Câu Hỏi column
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Thể Loại column
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Mức Độ column

        // Set column preferred widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50); // STT - narrow
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80); // Mã Câu Hỏi
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(350); // Tên Câu Hỏi - wide
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Thể Loại
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80); // Mức Độ
    }

    private void loadExamData(String exCode) {
        try {
            // Get exam by exam code
            exam = busExam.selectById(exCode);
            if (exam == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy đề thi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                this.dispose();
                return;
            }

            // Get test info
            DTO_Test test = busTest.selectById(exam.getTestCode());
            if (test == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin bài thi!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                this.dispose();
                return;
            }

            // Set form title with exam code
            setTitle("Xem Đề Thi - Mã đề: " + exam.getExCode());

            // Set text fields with exam data and make them non-editable
            jTextField5.setText(exam.getTestCode());
            jTextField6.setText(exam.getExOrder());
            jTextField3.setText(exam.getExCode());

            // Load questions list
            listQuestions = busCauHoi.getAllData();

            // Parse selected questions from exam
            String questionsJson = exam.getEx_quesIDs();
            if (questionsJson != null && !questionsJson.isEmpty()) {
                // Remove brackets [] from the string
                questionsJson = questionsJson.substring(1, questionsJson.length() - 1);
                if (!questionsJson.isEmpty()) {
                    String[] questionIds = questionsJson.split(",");

                    // Get the questions for this exam
                    for (String idStr : questionIds) {
                        int id = Integer.parseInt(idStr.trim());
                        DTO_Questions question = findQuestionById(id);
                        if (question != null) {
                            selectedQuestions.add(question);
                        }
                    }
                }
            }

            // Update the table with selected questions
            updateSelectedQuestionsTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu đề thi: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            this.dispose();
        }
    }
      private DTO_Questions findQuestionById(int id) {
        for (DTO_Questions question : listQuestions) {
            if (question.getqID() == id) {
                return question;
            }
        }
        return null;
    }
    
    private String getTopicName(int topicId) {
        DTO_Topic topic = busTopic.getInfo(topicId);
        if (topic != null) {
            return topic.getTpTitle();
        }
        return "Chủ đề " + topicId;
    }
    
    private void updateSelectedQuestionsTable() {
        try {
            // Create a new model for the table with the correct column names
            DefaultTableModel model = new DefaultTableModel(
                    new Object[][] {},
                    new String[] { "STT", "Mã Câu Hỏi", "Tên Câu Hỏi", "Thể Loại", "Mức Độ" }) {
                // Make all cells non-editable
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            // Set the model to the table
            jTable1.setModel(model);

            // Add all selected questions to table
            int rowCount = 1;
            
            // Add questions
            for (DTO_Questions q : selectedQuestions) {
                if (q != null) {
                    // Get topic name based on topic ID
                    String topicName = getTopicName(q.getqTopicID());
                    String level = q.getqLevel().toLowerCase();
                    String difficulty;
                    
                    // Format the difficulty level
                    if (level.equals("easy") || level.equals("dễ")) {
                        difficulty = "Dễ";
                    } else if (level.equals("medium") || level.equals("vừa")) {
                        difficulty = "Trung bình";
                    } else if (level.equals("difficult") || level.equals("khó")) {
                        difficulty = "Khó";
                    } else {
                        difficulty = q.getqLevel();
                    }
                    
                    model.addRow(new Object[] { rowCount++, q.getqID(), q.getqContent(), topicName, difficulty });
                }
            }

            // Center align text in cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT column
            jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Mã Câu Hỏi column
            jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Thể Loại column
            jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Mức Độ column

            // Set column preferred widths
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);  // STT - narrow
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);  // Mã Câu Hỏi
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(350); // Tên Câu Hỏi - wide
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Thể Loại
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);  // Mức Độ
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật bảng câu hỏi: " + ex.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 589));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 72, 170));
        jLabel7.setText("XEM ĐỀ THI");

        jLabel2.setText(" ");

        jLabel5.setText(" ");

        jButton1.setBackground(new java.awt.Color(42, 72, 170));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ĐÓNG");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(102, 102, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("ExCode:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(102, 102, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("TestCode:");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(102, 102, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("ExOrder");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Câu Hỏi", "Tên câu hỏi", "Thể loại", "Mức độ"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(199, 199, 199)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(160, 160, 160))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                XemDeThi dialog = new XemDeThi(new javax.swing.JFrame(), true, "TST001A");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
