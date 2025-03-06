/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.util.ArrayList;
import java.util.Collections;

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
public class SuaDeThi extends javax.swing.JDialog {
    private DTO_Exam exam;
    private BUS_Questions busCauHoi = new BUS_Questions();
    private BUS_Exam busExam = new BUS_Exam();
    private BUS_Test busTest = new BUS_Test();
    private ArrayList<DTO_Questions> listQuestions;
    private ArrayList<Integer> selectedEasyQuestions = new ArrayList<>();
    private ArrayList<Integer> selectedMediumQuestions = new ArrayList<>();
    private ArrayList<Integer> selectedHardQuestions = new ArrayList<>();
     GUI.Menu.QuanLyDeThi parentPanel;
    /**
     * Creates new form SuaDeThi
     */
    public SuaDeThi(java.awt.Frame parent, boolean modal, String exCode, GUI.Menu.QuanLyDeThi parentPanel) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.parentPanel = parentPanel;

        // Load exam data
        loadExamData(exCode);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT column
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Mã Câu Hỏi column

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50); // STT - narrow
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100); // Mã Câu Hỏi
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(400); // Tên Câu Hỏi - wide
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Mức Độ
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
    
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
    
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
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
            setTitle("Sửa Đề Thi - Mã đề: " + exam.getExCode());

            // Set text fields with exam data and make them non-editable
            jTextField5.setText(exam.getTestCode());
            jTextField5.setEditable(false);

            jTextField6.setText(exam.getExOrder());
            jTextField6.setEditable(false);

            jTextField3.setText(exam.getExCode());
            jTextField3.setEditable(false);

            // Load questions list
            listQuestions = busCauHoi.getAllData();

            // Set labels with question counts
            jLabel1.setText("Câu hỏi dễ: (" + test.getNumEasy() + " câu)");
            jLabel3.setText("Câu hỏi trung bình: (" + test.getNumMedium() + " câu)");
            jLabel4.setText("Câu hỏi khó: (" + test.getNumDiff() + " câu)");

            String questionsJson = exam.getEx_quesIDs();
            if (questionsJson != null && !questionsJson.isEmpty()) {
                questionsJson = questionsJson.substring(1, questionsJson.length() - 1);
                if (!questionsJson.isEmpty()) {
                    String[] questionIds = questionsJson.split(",");

                    for (String idStr : questionIds) {
                        int id = Integer.parseInt(idStr.trim());
                        DTO_Questions question = findQuestionById(id);
                        if (question != null) {
                            String level = question.getqLevel().toLowerCase();
                            if (level.equals("easy") || level.equals("dễ")) {
                                selectedEasyQuestions.add(id);
                            } else if (level.equals("medium") || level.equals("vừa")) {
                                selectedMediumQuestions.add(id);
                            } else if (level.equals("difficult") || level.equals("khó")) {
                                selectedHardQuestions.add(id);
                            }
                        }
                    }
                }
            }

            populateComboboxes();

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

    private void populateComboboxes() {
        // Get test info to filter questions by topic
        DTO_Test test = busTest.selectById(exam.getTestCode());
        int topicId = test.getTpID();

        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
        jComboBox3.removeAllItems();

        for (DTO_Questions question : listQuestions) {
            if (question.getqTopicID() == topicId) {
                String level = question.getqLevel().toLowerCase();
                String item = question.getqID() + " - " + question.getqContent();

                if (item.length() > 50) {
                    item = item.substring(0, 47) + "...";
                }

                if (level.equals("easy") || level.equals("dễ")) {
                    jComboBox1.addItem(item);
                } else if (level.equals("medium") || level.equals("vừa")) {
                    jComboBox2.addItem(item);
                } else if (level.equals("difficult") || level.equals("khó")) {
                    jComboBox3.addItem(item);
                }
            }
        }

        updateSelectedQuestionsTable();
    }
private String getTopicName(int topicId) {
   
    BUS_Topic busTopic = new BUS_Topic();
    DTO_Topic topic = busTopic.getInfo(topicId);
    if (topic != null) {
        return topic.getTpTitle();
    }
    return "Chủ đề " + topicId;
}
    private void updateSelectedQuestionsTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "STT", "Mã Câu Hỏi", "Tên Câu Hỏi", "Thể Loại", "Mức Độ" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        jTable1.setModel(model);
    
        int rowCount = 1;
        for (int id : selectedEasyQuestions) {
            DTO_Questions q = findQuestionById(id);
            if (q != null) {
                // Get topic name based on topic ID
                String topicName = getTopicName(q.getqTopicID());
                model.addRow(new Object[] { rowCount++, id, q.getqContent(), topicName, "Dễ" });
            }
        }
    
        for (int id : selectedMediumQuestions) {
            DTO_Questions q = findQuestionById(id);
            if (q != null) {
                String topicName = getTopicName(q.getqTopicID());
                model.addRow(new Object[] { rowCount++, id, q.getqContent(), topicName, "Trung bình" });
            }
        }
    
        for (int id : selectedHardQuestions) {
            DTO_Questions q = findQuestionById(id);
            if (q != null) {
                // Get topic name based on topic ID
                String topicName = getTopicName(q.getqTopicID());
                model.addRow(new Object[] { rowCount++, id, q.getqContent(), topicName, "Khó" });
            }
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT column
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Mã Câu Hỏi column
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Thể Loại column
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Mức Độ column
    
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);  // STT - narrow
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);  // Mã Câu Hỏi
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(350); // Tên Câu Hỏi - wide
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Thể Loại
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);  // Mức Độ
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

        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 589));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 72, 170));
        jLabel7.setText("SỬA ĐỀ THI");

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Câu hỏi dễ: (Cần 5 câu)");

        jLabel2.setText(" ");

        jLabel5.setText(" ");

        jButton1.setBackground(new java.awt.Color(42, 72, 170));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("XÁC NHẬN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(102, 102, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("ExCode:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setBackground(new java.awt.Color(102, 102, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Câu hỏi trung bình: (Cần 5 câu)");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setBackground(new java.awt.Color(102, 102, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Câu hỏi khó: (Cần 5 câu)");

        jButton3.setBackground(new java.awt.Color(42, 72, 170));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(42, 72, 170));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(42, 72, 170));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Thêm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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

        jButton8.setBackground(new java.awt.Color(42, 72, 170));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Xóa");

        jButton9.setBackground(new java.awt.Color(42, 72, 170));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Clear");

        jButton10.setBackground(new java.awt.Color(42, 72, 170));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Ramdom");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9)
                                    .addComponent(jButton8))
                                .addGap(15, 15, 15)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton3))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton5))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                .addGap(31, 31, 31)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            // Validate that we have the required number of questions
            DTO_Test test = busTest.selectById(exam.getTestCode());
            if (selectedEasyQuestions.size() != test.getNumEasy()) {
                JOptionPane.showMessageDialog(this,
                        "Số lượng câu hỏi dễ phải là " + test.getNumEasy() + " câu.",
                        "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (selectedMediumQuestions.size() != test.getNumMedium()) {
                JOptionPane.showMessageDialog(this,
                        "Số lượng câu hỏi trung bình phải là " + test.getNumMedium() + " câu.",
                        "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (selectedHardQuestions.size() != test.getNumDiff()) {
                JOptionPane.showMessageDialog(this,
                        "Số lượng câu hỏi khó phải là " + test.getNumDiff() + " câu.",
                        "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            StringBuilder questionsJson = new StringBuilder("[");

            for (int i = 0; i < selectedEasyQuestions.size(); i++) {
                questionsJson.append(selectedEasyQuestions.get(i));
                if (i < selectedEasyQuestions.size() - 1 || !selectedMediumQuestions.isEmpty()
                        || !selectedHardQuestions.isEmpty()) {
                    questionsJson.append(",");
                }
            }

            for (int i = 0; i < selectedMediumQuestions.size(); i++) {
                questionsJson.append(selectedMediumQuestions.get(i));
                if (i < selectedMediumQuestions.size() - 1 || !selectedHardQuestions.isEmpty()) {
                    questionsJson.append(",");
                }
            }

            for (int i = 0; i < selectedHardQuestions.size(); i++) {
                questionsJson.append(selectedHardQuestions.get(i));
                if (i < selectedHardQuestions.size() - 1) {
                    questionsJson.append(",");
                }
            }

            questionsJson.append("]");

            exam.setEx_quesIDs(questionsJson.toString());

            int result = busExam.update(exam);
            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật đề thi thành công!",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
                ArrayList<DTO_Exam> listExam = busExam.getAllData();
                parentPanel.loadData2(listExam);
                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật đề thi thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        // Thêm câu hỏi khó
        if (jComboBox3.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn câu hỏi khó!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        DTO_Test test = busTest.selectById(exam.getTestCode());
        if (selectedHardQuestions.size() >= test.getNumDiff()) {
            JOptionPane.showMessageDialog(this, 
                "Đã đủ " + test.getNumDiff() + " câu hỏi khó theo yêu cầu!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        String selectedItem = jComboBox3.getSelectedItem().toString();
        int questionId = Integer.parseInt(selectedItem.split(" - ")[0]);
    
        if (selectedHardQuestions.contains(questionId)) {
            JOptionPane.showMessageDialog(this, "Câu hỏi này đã được chọn!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        selectedHardQuestions.add(questionId);
        updateSelectedQuestionsTable();
        
        populateComboboxes();
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        if (jComboBox2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn câu hỏi trung bình!", "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        DTO_Test test = busTest.selectById(exam.getTestCode());
        if (selectedMediumQuestions.size() >= test.getNumMedium()) {
            JOptionPane.showMessageDialog(this, 
                "Đã đủ " + test.getNumMedium() + " câu hỏi trung bình theo yêu cầu!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        String selectedItem = jComboBox2.getSelectedItem().toString();
        int questionId = Integer.parseInt(selectedItem.split(" - ")[0]);
    
        if (selectedMediumQuestions.contains(questionId)) {
            JOptionPane.showMessageDialog(this, "Câu hỏi này đã được chọn!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        selectedMediumQuestions.add(questionId);
        updateSelectedQuestionsTable();
        
        populateComboboxes();
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        // Add easy question
        if (jComboBox1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn câu hỏi dễ!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        DTO_Test test = busTest.selectById(exam.getTestCode());
        if (selectedEasyQuestions.size() >= test.getNumEasy()) {
            JOptionPane.showMessageDialog(this, 
                "Đã đủ " + test.getNumEasy() + " câu hỏi dễ theo yêu cầu!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    
        String selectedItem = jComboBox1.getSelectedItem().toString();
        int questionId = Integer.parseInt(selectedItem.split(" - ")[0]);
    
        if (selectedEasyQuestions.contains(questionId)) {
            JOptionPane.showMessageDialog(this, "Câu hỏi này đã được chọn!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        selectedEasyQuestions.add(questionId);
        updateSelectedQuestionsTable();
        
        populateComboboxes();
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn câu hỏi cần xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        int questionId = (int) jTable1.getValueAt(row, 1);
        String difficulty = (String) jTable1.getValueAt(row, 4); // Column index changed from 3 to 4
    
        if (difficulty.equals("Dễ")) {
            selectedEasyQuestions.remove(Integer.valueOf(questionId));
        } else if (difficulty.equals("Trung bình")) {
            selectedMediumQuestions.remove(Integer.valueOf(questionId));
        } else if (difficulty.equals("Khó")) {
            selectedHardQuestions.remove(Integer.valueOf(questionId));
        }
    
        updateSelectedQuestionsTable();
        
        populateComboboxes();
    }

    // Handler for the "Random" button to randomly select questions
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            DTO_Test test = busTest.selectById(exam.getTestCode());
            int topicId = test.getTpID();

            selectedEasyQuestions.clear();
            selectedMediumQuestions.clear();
            selectedHardQuestions.clear();

            ArrayList<DTO_Questions> easyQuestions = new ArrayList<>();
            ArrayList<DTO_Questions> mediumQuestions = new ArrayList<>();
            ArrayList<DTO_Questions> hardQuestions = new ArrayList<>();

            for (DTO_Questions question : listQuestions) {
                if (question.getqTopicID() == topicId) {
                    String level = question.getqLevel().toLowerCase();

                    if (level.equals("easy") || level.equals("dễ")) {
                        easyQuestions.add(question);
                    } else if (level.equals("medium") || level.equals("vừa")) {
                        mediumQuestions.add(question);
                    } else if (level.equals("difficult") || level.equals("khó")) {
                        hardQuestions.add(question);
                    }
                }
            }

            if (easyQuestions.size() < test.getNumEasy() ||
                    mediumQuestions.size() < test.getNumMedium() ||
                    hardQuestions.size() < test.getNumDiff()) {

                JOptionPane.showMessageDialog(this,
                        "Không đủ câu hỏi để tạo đề ngẫu nhiên!\n" +
                                "Yêu cầu: " + test.getNumEasy() + " câu dễ, " +
                                test.getNumMedium() + " câu trung bình, " +
                                test.getNumDiff() + " câu khó\n" +
                                "Hiện có: " + easyQuestions.size() + " câu dễ, " +
                                mediumQuestions.size() + " câu trung bình, " +
                                hardQuestions.size() + " câu khó",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Collections.shuffle(easyQuestions);
            Collections.shuffle(mediumQuestions);
            Collections.shuffle(hardQuestions);

            for (int i = 0; i < test.getNumEasy(); i++) {
                selectedEasyQuestions.add(easyQuestions.get(i).getqID());
            }

            for (int i = 0; i < test.getNumMedium(); i++) {
                selectedMediumQuestions.add(mediumQuestions.get(i).getqID());
            }

            for (int i = 0; i < test.getNumDiff(); i++) {
                selectedHardQuestions.add(hardQuestions.get(i).getqID());
            }

            updateSelectedQuestionsTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi tạo câu hỏi ngẫu nhiên: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Handler for the "Clear" button
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        selectedEasyQuestions.clear();
        selectedMediumQuestions.clear();
        selectedHardQuestions.clear();
        updateSelectedQuestionsTable();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuaDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SuaDeThi dialog = new SuaDeThi(new javax.swing.JFrame(), true, "DT001", null);
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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
