/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.ArrayList;
import DTO.DTO_Answer;
import DTO.DTO_Questions;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import BUS.BUS_Answers;
import BUS.BUS_Questions;

/**
 *
 * @author KIET
 */
public class ThemCauHoi extends javax.swing.JDialog {

        /**
         * Creates new form ThemNhaCungCap
         */
        String imgQuestion = "";
        String imgAnswer = "";
        ArrayList<DTO_Answer> listAnswer = new ArrayList<DTO_Answer>();
        int cur_ans_index = -1;
        BUS.BUS_Questions questionBUS = new BUS_Questions();
        BUS.BUS_Answers answerBUS = new BUS_Answers();

        public ThemCauHoi(java.awt.Frame parent, boolean modal) {

                initComponents();
        }

        public ThemCauHoi() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                               // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private ThemCauHoi(JFrame jFrame, boolean b) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                               // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public void loadListAnswer() {
                try {
                        String[] columns = { "Lựa chọn", "Nội dung lựa chọn", "Hình ảnh", "Đáp án" };
                        DefaultTableModel model = new DefaultTableModel(columns, 0) {
                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                        if (columnIndex == 3) {
                                                return Boolean.class;
                                        }
                                        return super.getColumnClass(columnIndex);
                                }
                        };

                        model.addTableModelListener(new TableModelListener() {
                                @Override
                                public void tableChanged(TableModelEvent e) {
                                        if (e.getColumn() == 3) {
                                                int row = e.getFirstRow();
                                                Boolean checked = (Boolean) model.getValueAt(row, 3);
                                                if (checked) {
                                                        for (int i = 0; i < model.getRowCount(); i++) {
                                                                if (i != row) {
                                                                        model.setValueAt(false, i, 3);
                                                                }
                                                        }
                                                }
                                                // update cur_ans_index
                                                if (checked) {
                                                        cur_ans_index = row;
                                                } else {
                                                        cur_ans_index = -1;
                                                }
                                                // update isright in list answer
                                                for (int i = 0; i < listAnswer.size(); i++) {
                                                        DTO_Answer answer = listAnswer.get(i);
                                                        if (i == row) {
                                                                answer.setRight(1);
                                                        } else {
                                                                answer.setRight(0);
                                                        }
                                                }
                                        }
                                }
                        });

                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                        jTable1.setModel(model);

                        for (int i = 0; i < jTable1.getColumnCount(); i++) {
                                if (i != 3) {
                                        jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                                }
                        }

                        for (int i = 0; i < listAnswer.size(); i++) {
                                DTO_Answer answer = listAnswer.get(i);
                                Object[] rowData = {
                                                i + 1,
                                                answer.getContent(),
                                                answer.getImage(),
                                                false
                                };
                                model.addRow(rowData);
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel7 = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jTextField1 = new javax.swing.JTextField();
                jTextField3 = new javax.swing.JTextField();
                jButton2 = new javax.swing.JButton();
                jButton3 = new javax.swing.JButton();
                jButton4 = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jLabel2 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jButton1 = new javax.swing.JButton();
                jLabel6 = new javax.swing.JLabel();
                jComboBox1 = new javax.swing.JComboBox<>();
                jLabel8 = new javax.swing.JLabel();
                jComboBox2 = new javax.swing.JComboBox<>();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(0, 204, 255));
                jLabel7.setText("THÊM CÂU HỎI");

                jLabel1.setBackground(new java.awt.Color(102, 102, 255));
                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel1.setText("Chọn chủ đề");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel3.setText("Nhập câu trả lời (văn bản)");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel4.setText("Danh sách câu trả lời đã nhập");

                jTextField1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextField1ActionPerformed(evt);
                        }
                });

                jButton2.setText("Thêm hình");
                jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jButton2MousePressed(evt);
                        }
                });

                jButton3.setText("Thêm hình");
                jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jButton3MousePressed(evt);
                        }
                });

                jButton4.setText("Thêm câu trả lời");
                jButton4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton4ActionPerformed(evt);
                        }
                });

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Lựa chọn", "Nội dung lựa chọn", "Hình ảnh", "Đáp án"
                                }));
                jScrollPane1.setViewportView(jTable1);

                jLabel2.setText(" ");

                jLabel5.setText(" ");

                jButton1.setText("Thêm câu hỏi");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton1ActionPerformed(evt);
                        }
                });

                jLabel6.setBackground(new java.awt.Color(102, 102, 255));
                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel6.setText("Nhập nội dung câu hỏi (văn bản)");

                jComboBox1.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                jLabel8.setBackground(new java.awt.Color(102, 102, 255));
                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel8.setText("Chọn độ khó");

                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dễ", "Vừa ", "Khó" }));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel7)
                                                                .addGap(425, 425, 425))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(467, 467, 467)
                                                                .addComponent(jButton1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(99, 99, 99)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel4,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                270,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createSequentialGroup()
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jLabel1,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                111,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jComboBox1,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                128,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                50,
                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jLabel8,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(jComboBox2,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                146,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jTextField1,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                320,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jButton3)
                                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                320,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                197,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jLabel5,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                320,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                false)
                                                                                                                                                .addComponent(jLabel3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                225,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jTextField3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                321,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addComponent(jButton2)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(jButton4,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                103,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                13,
                                                                                                                                Short.MAX_VALUE)))
                                                                .addGap(96, 96, 96))
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(109, 109, 109)
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                320,
                                                                                                Short.MAX_VALUE)
                                                                                .addGap(617, 617, 617))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                52,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(43, 43, 43)
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                38,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTextField3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                40,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButton3)
                                                                                .addComponent(jButton2)
                                                                                .addComponent(jButton4))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(jLabel5))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel8))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jComboBox1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jComboBox2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                356,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(35, 35, 35)
                                                                .addComponent(jButton1)
                                                                .addContainerGap(41, Short.MAX_VALUE))
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(125, 125, 125)
                                                                                .addComponent(jLabel6)
                                                                                .addContainerGap(658,
                                                                                                Short.MAX_VALUE))));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField1ActionPerformed

        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
                // TODO add your handling code here:
                // them cau tra loi
                try {
                        String content = jTextField3.getText();
                        String image = imgAnswer;

                        if (content.isEmpty() && image.isEmpty()) {
                                JOptionPane.showMessageDialog(this,
                                                "Vui lòng nhập nội dung câu trả lời hoặc chọn hình ảnh");
                                return;
                        }

                        DTO_Answer answer = new DTO_Answer();
                        answer.setContent(content);
                        answer.setImage(image);
                        listAnswer.add(answer);
                        loadListAnswer();
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi thêm câu trả lời: " + ex.getMessage());
                }
        }// GEN-LAST:event_jButton4ActionPerformed

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
                // TODO add your handling code here:
                if (listAnswer.size() < 2 || listAnswer.size() > 6) {
                        JOptionPane.showMessageDialog(this, "Số lượng câu trả lời phải từ 2 đến 6");
                        return;
                }
                DTO_Questions question = new DTO_Questions();
                question.setqContent(jTextField1.getText().toString());
                question.setqPictures(imgQuestion);
                String level = jComboBox2.getSelectedItem().toString();
                question.setqLevel(level);
                question.setqTopicID(1);
                if (questionBUS.insert(question) > 0) {
                        JOptionPane.showMessageDialog(this, "Thêm câu hỏi thành công");
                        int largestID = questionBUS.getLargestID();
                        // add to ansswer
                        for (int i = 0; i < listAnswer.size(); i++) {
                                DTO_Answer answer = listAnswer.get(i);
                                answer.setQuestionId(largestID);
                                answerBUS.insert(answer);
                        }
                        this.dispose();
                } else {
                        JOptionPane.showMessageDialog(this, "Thêm câu hỏi thất bại");
                }

        }// GEN-LAST:event_jButton1ActionPerformed

        private void jButton3MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton3MousePressed
                // TODO add your handling code here: them hinh cau hoi
                try {

                        JFileChooser chooser = new JFileChooser();

                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                        "Image Files", "jpg", "jpeg", "gif");
                        chooser.setFileFilter(filter);

                        int result = chooser.showOpenDialog(this);

                        if (result == JFileChooser.APPROVE_OPTION) {
                                File selectedFile = chooser.getSelectedFile();

                                File imgDir = new File("src/img");
                                if (!imgDir.exists()) {
                                        imgDir.mkdirs();
                                }

                                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                String extension = selectedFile.getName()
                                                .substring(selectedFile.getName().lastIndexOf("."));
                                String newFileName = "img_" + timestamp + extension;

                                File destFile = new File(imgDir, newFileName);
                                Files.copy(selectedFile.toPath(), destFile.toPath(),
                                                StandardCopyOption.REPLACE_EXISTING);

                                String relativePath = "src/img/" + newFileName;
                                jLabel2.setText(newFileName);
                                imgQuestion = relativePath;
                        }
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi copy file: " + ex.getMessage());
                }
        }// GEN-LAST:event_jButton3MousePressed

        private void jButton2MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton2MousePressed
                // TODO add your handling code here: them hinh cau tra loi
                try {

                        JFileChooser chooser = new JFileChooser();

                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                        "Image Files", "jpg", "jpeg", "gif");
                        chooser.setFileFilter(filter);

                        int result = chooser.showOpenDialog(this);

                        if (result == JFileChooser.APPROVE_OPTION) {
                                File selectedFile = chooser.getSelectedFile();

                                File imgDir = new File("src/img");
                                if (!imgDir.exists()) {
                                        imgDir.mkdirs();
                                }

                                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                String extension = selectedFile.getName()
                                                .substring(selectedFile.getName().lastIndexOf("."));
                                String newFileName = "img_" + timestamp + extension;

                                File destFile = new File(imgDir, newFileName);
                                Files.copy(selectedFile.toPath(), destFile.toPath(),
                                                StandardCopyOption.REPLACE_EXISTING);

                                String relativePath = "src/img/" + newFileName;
                                jLabel2.setText(newFileName);
                                imgAnswer = relativePath;
                        }
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi copy file: " + ex.getMessage());
                }

        }// GEN-LAST:event_jButton2MousePressed

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
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(ThemCauHoi.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(ThemCauHoi.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(ThemCauHoi.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(ThemCauHoi.class.getName()).log(
                                        java.util.logging.Level.SEVERE, null,
                                        ex);
                }
                // </editor-fold>
                // </editor-fold>

                /* Create and display the dialog */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                ThemCauHoi dialog = new ThemCauHoi(new javax.swing.JFrame(), true);
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
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        private javax.swing.JComboBox<String> jComboBox1;
        private javax.swing.JComboBox<String> jComboBox2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField3;
        // End of variables declaration//GEN-END:variables
}
