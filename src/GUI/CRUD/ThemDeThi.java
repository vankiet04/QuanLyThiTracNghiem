package GUI.CRUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import BUS.BUS_Exam;
import BUS.BUS_Questions;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Exam;
import DTO.DTO_Questions;
import DTO.DTO_Test;
import DTO.DTO_Topic;

public class ThemDeThi extends javax.swing.JDialog {
    BUS_Test testBUS = new BUS_Test();
    ArrayList<DTO_Test> listTest = new ArrayList<>();
    BUS_Questions questionBUS = new BUS_Questions();
    ArrayList<DTO_Questions> listQuestion = new ArrayList<>();
    BUS_Topic topicBUS = new BUS_Topic();
    BUS_Exam examBUS = new BUS_Exam();
    ArrayList<DTO_Exam> listExam = new ArrayList<>();
    private static GUI.Menu.QuanLyDeThi parentPanel;
    
    public ThemDeThi(java.awt.Frame parent, boolean modal, GUI.Menu.QuanLyDeThi parentPanel) {
        super(parent, modal);
        initComponents();
        this.parentPanel = parentPanel;
        this.setLocationRelativeTo(null);
        loadTestData(); // Moved data load into a separate method.
    }
    
    private void loadTestData() {
        try {
            // Load unique test codes
            jComboBox1.removeAllItems();
            listTest = testBUS.getAllData();
            Set<String> uniqueTestCodes = new HashSet<>();
            for (DTO_Test test : listTest) {
                if (uniqueTestCodes.add(test.getTestCode())) {
                    jComboBox1.addItem(test.getTestCode());
                }
            }
            // Load remaining data
            listQuestion = questionBUS.getAllData();
            listExam = examBUS.getAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 72, 170));
        jLabel7.setText("TẠO ĐỀ THI");

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Chọn code Bài Thi");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

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
        jLabel6.setText("Nhập số lượng:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Validate input
            if (jTextField1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng đề thi", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int soLuongDeThi = Integer.parseInt(jTextField1.getText().trim());
            if (soLuongDeThi <= 0 || soLuongDeThi > 10) {
                JOptionPane.showMessageDialog(this, "Số lượng đề thi phải lớn hơn 0 và nhỏ hơn hoặc bằng 10", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String testCode = jComboBox1.getSelectedItem().toString();

            int soluongdethicoma = 0;
            char maxExOrderChar = 'A' - 1;
            for (DTO_Exam exam : listExam) {
                if (exam.getTestCode().equals(testCode)) {
                    soluongdethicoma++;
                    String exOrder = exam.getExOrder();
                    if (exOrder.length() == 1 && exOrder.charAt(0) > maxExOrderChar) {
                        maxExOrderChar = exOrder.charAt(0);
                    }
                }
            }

            if (soluongdethicoma >= 10) {
                JOptionPane.showMessageDialog(this, "Số lượng đề thi đã đạt tối đa (10 đề)", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (soLuongDeThi + soluongdethicoma > 10) {
                JOptionPane.showMessageDialog(this, "Chỉ có thể tạo thêm " + (10 - soluongdethicoma) + " đề thi nữa", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                soLuongDeThi = 10 - soluongdethicoma;
            }


            // Lưu số lượng câu hỏi cần thiết cho từng topic: tpID: easy, medi, diff của test
            HashMap<Integer, int[]> topicQuestionCount = new HashMap<>();

            int soLuongCauHoiDe =0;
            int soLuongCauHoiTrungBinh =0;
            int soLuongCauHoiKho =0;
            for (DTO_Test t : listTest) {
                if (t.getTestCode().equals(testCode)) {
                    int[] count = topicQuestionCount.getOrDefault(t.getTpID(), new int[]{0, 0, 0});
                    count[0] += t.getNumEasy();    // Số câu dễ
                    count[1] += t.getNumMedium();  // Số câu trung bình
                    count[2] += t.getNumDiff();    // Số câu khó
                    soLuongCauHoiDe += t.getNumEasy();
                    soLuongCauHoiTrungBinh += t.getNumMedium();
                    soLuongCauHoiKho += t.getNumDiff();
                    topicQuestionCount.put(t.getTpID(), count);
                }
            }

            // Lưu số lượng câu hỏi hiện trên từng topic của hệ thống
            HashMap<Integer, int[]> availableQuestions = new HashMap<>();

            for (DTO_Questions question : listQuestion) {
                int topicId = question.getqTopicID();
                String level = question.getqLevel().toLowerCase();
                
                int[] available = availableQuestions.getOrDefault(topicId, new int[]{0, 0, 0});
                if (level.equals("dễ")) 
                    available[0]++;
                else if (level.equals("vừa"))
                    available[1]++;
                else if (level.equals("khó"))
                    available[2]++;
                availableQuestions.put(topicId, available);
            }

            // Kiểm tra nếu có topic nào không đủ câu hỏi
            for (Map.Entry<Integer, int[]> entry : topicQuestionCount.entrySet()) {
                int tpID = entry.getKey();
                DTO_Topic topic = topicBUS.getInfo(tpID);
                int[] required = entry.getValue();
                int[] available = availableQuestions.getOrDefault(tpID, new int[]{0, 0, 0});

                if (available[0] < required[0] || available[1] < required[1] || available[2] < required[2]) {
                    JOptionPane.showMessageDialog(this,
                            "Không đủ câu hỏi trong Topic " + topic.getTpTitle() + ":\n" +
                            "Cần: " + required[0] + " dễ, " + required[1] + " trung bình, " + required[2] + " khó\n" +
                            "Hiện có: " + available[0] + " dễ, " + available[1] + " trung bình, " + available[2] + " khó",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // lấy ra danh sách câu hỏi từng mức độ
            ArrayList<DTO_Questions> easyList = new ArrayList<>();
            ArrayList<DTO_Questions> mediumList = new ArrayList<>();
            ArrayList<DTO_Questions> diffList = new ArrayList<>();
            
            for (DTO_Questions question : listQuestion) {
                int topicId = question.getqTopicID();
                String level = question.getqLevel().toLowerCase();
            
                if (topicQuestionCount.containsKey(topicId)) {
                    if (level.equals("dễ")) 
                        easyList.add(question);
                    else if (level.equals("vừa"))
                        mediumList.add(question);
                    else if (level.equals("khó"))
                        diffList.add(question);
                }
            }

            // Tạo danh sách đề thi
            ArrayList<String> examList = new ArrayList<>();
            int successCount = 0;
            
            for (int i = 0; i < soLuongDeThi; i++) {
                java.util.Collections.shuffle(easyList);
                java.util.Collections.shuffle(mediumList);
                java.util.Collections.shuffle(diffList);
                
                ArrayList<Integer> selectedQuestionIds = new ArrayList<>();
            
                for (int j = 0; j < soLuongCauHoiDe; j++)
                    if (j < easyList.size())
                        selectedQuestionIds.add(easyList.get(j).getqID());
            
                for (int j = 0; j < soLuongCauHoiTrungBinh; j++) 
                    if (j < mediumList.size())
                        selectedQuestionIds.add(mediumList.get(j).getqID());
                
                for (int j = 0; j < soLuongCauHoiKho; j++) 
                    if (j < diffList.size())
                        selectedQuestionIds.add(diffList.get(j).getqID());
                
            
                // Chuyển danh sách câu hỏi thành chuỗi
                StringBuilder listQuestionCode = new StringBuilder("[");
                for (int j = 0; j < selectedQuestionIds.size(); j++) {
                    listQuestionCode.append(selectedQuestionIds.get(j));
                    if (j < selectedQuestionIds.size() - 1)
                        listQuestionCode.append(",");
                }
                listQuestionCode.append("]");
            
                examList.add(listQuestionCode.toString());
            }
            
            // Lưu danh sách đề thi vào database
            for (String questionList : examList) {
                DTO_Exam exam = new DTO_Exam();
                exam.setTestCode(testCode);
                exam.setExOrder(String.valueOf(++maxExOrderChar)); // Only increment here.
                exam.setExCode(testCode + exam.getExOrder());
                exam.setEx_quesIDs(questionList);
            
                int result = examBUS.insert(exam);
                if (result > 0)
                    successCount++;
                else
                    JOptionPane.showMessageDialog(this, "Lỗi khi tạo đề thi " + exam.getExCode(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                
            }
            
            // Hiển thị kết quả
            if (successCount > 0) {
                JOptionPane.showMessageDialog(this, "Đã tạo thành công " + successCount + " đề thi", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                if (parentPanel != null) {
                    ArrayList<DTO_Exam> listExam2 = examBUS.getAllData();
                    parentPanel.loadData2(listExam2);
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Không tạo được đề thi nào", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
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
            java.util.logging.Logger.getLogger(ThemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemDeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // For testing only, pass null as the UI parameter
                ThemDeThi dialog = new ThemDeThi(null, true, parentPanel);
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
