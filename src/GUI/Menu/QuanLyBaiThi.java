/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Menu;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import DTO.DTO_Topic;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Test;
import DTO.DTO_Topic;
import GUI.GUI_MainFrm;
import GUI.CRUD.SuaBaiThi;
import GUI.CRUD.SuaDeThi;
import GUI.CRUD.ThemBaiThi;
import GUI.CRUD.XemBaiThi;

/**
 *
 * @author KIET
 */
public class QuanLyBaiThi extends javax.swing.JPanel {
        GUI.GUI_MainFrm main;

        /**
         * Creates new form QuanLyDeThi
         */
        BUS_Test busTest = new BUS_Test();
        private ArrayList<DTO_Test> listTest = busTest.getAllData();
        private DefaultTableModel modelTK;
        private int curSelect = -1;
        BUS_Topic busTopic = new BUS_Topic();

        public QuanLyBaiThi() {
                initComponents();

        }

        public void loadData(ArrayList<DTO_Test> listCur) {
                System.out.println("Load data");
                modelTK = (DefaultTableModel) jTable1.getModel();
                modelTK.setRowCount(0);
                for (DTO_Test dto : listCur) {
                        System.out.println(dto.getTestID());
                        // tách dto.getTestDate() từ chữ t là Date và Time
                        String DateStr = dto.getTestDate().toString().split("T")[0];
                        String TimeStr = dto.getTestDate().toString().split("T")[1];
                        modelTK.addRow(new Object[] { dto.getTestID(), dto.getTestCode(), dto.getTestTitle(),
                            busTopic.getNameById(dto.getTpID()), dto.getTestTime() + " phút",
                                        "Ngày:" + DateStr + " | " + "Giờ:" + TimeStr, dto.getTestLimit() + " lần" });
                }
        }

        public QuanLyBaiThi(GUI_MainFrm main) {
                this.main = main;
                initComponents();
                System.out.println("Load dat1a");

                loadData(listTest);
                System.out.println("Load dat2a");

                jTable1.setDefaultEditor(Object.class, null);
                jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                                if (!e.getValueIsAdjusting()) {
                                        int selectRow = jTable1.getSelectedRow();
                                        if (selectRow != -1)
                                                curSelect = Integer
                                                                .parseInt(jTable1.getValueAt(selectRow, 0).toString());
                                }
                        }

                });

                // căn giữa
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                jTable1.setDefaultRenderer(String.class, centerRenderer);
                jTable1.setDefaultRenderer(Integer.class, centerRenderer);
                jTable1.setDefaultRenderer(Double.class, centerRenderer);
                jTable1.setDefaultRenderer(Float.class, centerRenderer);
                jTable1.setDefaultRenderer(Long.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);
                jTable1.setDefaultRenderer(Boolean.class, centerRenderer);
                jTable1.setDefaultRenderer(Short.class, centerRenderer);
                jTable1.setDefaultRenderer(Byte.class, centerRenderer);
                jTable1.setDefaultRenderer(Character.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);
                jTable1.setDefaultRenderer(Object.class, centerRenderer);

        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtSearch = new com.raven.suportSwing.TextField();
        myButton2 = new com.raven.suportSwing.MyButton();
        lblSearch = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        scrollBarCustom1 = new com.raven.suportSwing.ScrollBarCustom();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setLabelText("Tìm theo tên or mã");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        myButton2.setBackground(new java.awt.Color(204, 255, 255));
        myButton2.setText("Reset");
        myButton2.setBorderColor(new java.awt.Color(153, 255, 255));
        myButton2.setRadius(20);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(255, 51, 0));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-75.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-85.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel3.setText("Thêm");

        jLabel5.setText("Sửa");

        jLabel6.setText("Xóa");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-view-75.png"))); // NOI18N

        jLabel8.setText("Xem chi tiết");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-edit-75.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    jLabel7MousePressed(evt);
                }
            });

        jButton2.setText("Xuất");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-export-excel-75.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel6)
                        .addGap(62, 62, 62)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(19, 19, 19)
                        .addComponent(jButton2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đề thi", "Mã code", "Tên đề thi", "Chủ đề", "Thời gian làm", "Ngày bắt đầu", "Số lượt làm", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(300, 300, 300))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(1710, Short.MAX_VALUE)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách bài thi");
        
        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã đề thi");
        headerRow.createCell(1).setCellValue("Mã code");
        headerRow.createCell(2).setCellValue("Tên đề thi");
        headerRow.createCell(3).setCellValue("Chủ đề");
        headerRow.createCell(4).setCellValue("Thời gian làm");
        headerRow.createCell(5).setCellValue("Ngày thi");
        headerRow.createCell(6).setCellValue("Giờ thi");
        headerRow.createCell(7).setCellValue("Số lượt làm");
        headerRow.createCell(8).setCellValue("Số câu dễ");
        headerRow.createCell(9).setCellValue("Số câu trung bình");
        headerRow.createCell(10).setCellValue("Số câu khó");
        
        // Create data rows
        int rowNum = 1;
        for (DTO_Test test : listTest) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(test.getTestID());
            row.createCell(1).setCellValue(test.getTestCode());
            row.createCell(2).setCellValue(test.getTestTitle());
            
            // Get topic name from topic ID
            String topicName = "";
            DTO_Topic topic = busTopic.getInfo(test.getTpID());
            if (topic != null) {
                topicName = topic.getTpTitle();
            }
            row.createCell(3).setCellValue(topicName);
            
            row.createCell(4).setCellValue(test.getTestTime() + " phút");
            
            // Split date and time
            String[] dateTime = test.getTestDate().toString().split("T");
            row.createCell(5).setCellValue(dateTime[0]); // Date
            row.createCell(6).setCellValue(dateTime[1]); // Time
            
            row.createCell(7).setCellValue(test.getTestLimit());
            row.createCell(8).setCellValue(test.getNumEasy());
            row.createCell(9).setCellValue(test.getNumMedium());
            row.createCell(10).setCellValue(test.getNumDiff());
        }
        
        // Auto size columns
        for (int i = 0; i < 11; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Create file chooser for saving
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx"));
        fileChooser.setSelectedFile(new File("Danh_sach_bai_thi.xlsx"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                file = new File(filePath + ".xlsx");
            }
            
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                workbook.write(outputStream);
                JOptionPane.showMessageDialog(this, 
                    "Xuất file Excel bài thi thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        workbook.close();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Lỗi khi xuất file Excel: " + e.getMessage(), 
            "Lỗi", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton2ActionPerformed

        private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtSearchFocusGained

        }// GEN-LAST:event_txtSearchFocusGained

        private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtSearchActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_txtSearchActionPerformed

        private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchKeyReleased
                // TODO add your handling code here:
                String searchText = txtSearch.getText().trim();

                if (searchText.isEmpty()) {
                        lblSearch.setText("");
                        loadData(busTest.getAllData());
                        return;
                }

                ArrayList<DTO_Test> searchResults = busTest.searchData(searchText);

                if (searchResults.isEmpty()) {
                        lblSearch.setText("Không tìm thấy kết quả!");
                        lblSearch.setForeground(new java.awt.Color(255, 51, 0));
                } else {
                        lblSearch.setText("Đã tìm thấy " + searchResults.size() + " kết quả");
                        lblSearch.setForeground(new java.awt.Color(0, 102, 0));
                }

                // Update the table with search results
                loadData(searchResults);

        }// GEN-LAST:event_txtSearchKeyReleased

        private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_myButton2ActionPerformed

                txtSearch.setText("");

                lblSearch.setText("");

                listTest = busTest.getAllData();
                loadData(listTest);

        }// GEN-LAST:event_myButton2ActionPerformed

        private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel2MousePressed
                // TODO add your handling code here:
                GUI.CRUD.ThemBaiThi themBaiThi = new GUI.CRUD.ThemBaiThi(main, true);
                themBaiThi.setLocationRelativeTo(null);
                themBaiThi.setVisible(true);
                listTest = busTest.getAllData();
                loadData(listTest);

        }// GEN-LAST:event_jLabel2MousePressed

        private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel4MousePressed
                if (curSelect != -1) {
                        DTO_Test selectedTest = null;
                        for (DTO_Test test : listTest) {
                                if (test.getTestID() == curSelect) {
                                        selectedTest = test;
                                        break;
                                }
                        }

                        if (selectedTest != null) {
                                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                                                this,
                                                "Bạn có chắc muốn xóa bài thi " + selectedTest.getTestTitle() + "?",
                                                "Xác nhận xóa",
                                                javax.swing.JOptionPane.YES_NO_OPTION);

                                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                                        // Set status to 0 instead of actually deleting
                                        selectedTest.setTestStatus(0);
                                        int result = busTest.update(selectedTest);

                                        if (result > 0) {
                                                javax.swing.JOptionPane.showMessageDialog(
                                                                this,
                                                                "Xóa bài thi thành công",
                                                                "Thông báo",
                                                                javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                                listTest = busTest.getAllData();
                                                loadData(listTest);
                                        } else {
                                                javax.swing.JOptionPane.showMessageDialog(
                                                                this,
                                                                "Xóa bài thi thất bại",
                                                                "Lỗi",
                                                                javax.swing.JOptionPane.ERROR_MESSAGE);
                                        }
                                }
                        } else {
                                javax.swing.JOptionPane.showMessageDialog(
                                                this,
                                                "Không tìm thấy thông tin bài thi",
                                                "Lỗi",
                                                javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                        javax.swing.JOptionPane.showMessageDialog(
                                        this,
                                        "Vui lòng chọn bài thi cần xóa",
                                        "Thông báo",
                                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
        }// GEN-LAST:event_jLabel4MousePressed

       private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {
    if (curSelect != -1) {
        // Get the selected test by ID
        DTO_Test selectedTest = null;
        for (DTO_Test test : listTest) {
            if (test.getTestID() == curSelect) {
                selectedTest = test;
                break;
            }
        }

        if (selectedTest != null) {
            // Open the SuaDeThi dialog
            GUI.CRUD.SuaBaiThi suaBaiThi = new GUI.CRUD.SuaBaiThi(main, true, selectedTest);
                                suaBaiThi.setLocationRelativeTo(null); // Center on screen
                                suaBaiThi.setVisible(true);

                                // Reload data after dialog closes to reflect any changes
                                loadData(busTest.getAllData());
        } else {
            JOptionPane.showMessageDialog(this,
                "Không tìm thấy thông tin bài thi",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this,
            "Vui lòng chọn bài thi cần sửa đề thi",
            "Thông báo",
            JOptionPane.INFORMATION_MESSAGE);
    }
}// GEN-LAST:event_jLabel15MousePressed

     
        private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel1MousePressed\
                if (curSelect != -1) {
                        // Get the selected test by ID
                        DTO_Test selectedTest = null;
                        for (DTO_Test test : listTest) {
                                if (test.getTestID() == curSelect) {
                                        selectedTest = test;
                                        break;
                                }
                        }

                        if (selectedTest != null) {
                                GUI.CRUD.SuaBaiThi suaBaiThi = new GUI.CRUD.SuaBaiThi(main, true, selectedTest);
                                suaBaiThi.setLocationRelativeTo(null); // Center on screen
                                suaBaiThi.setVisible(true);

                                // Reload data after dialog closes to reflect any changes
                                loadData(busTest.getAllData());
                        } else {
                                javax.swing.JOptionPane.showMessageDialog(this,
                                                "Không tìm thấy thông tin bài thi",
                                                "Lỗi",
                                                javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                        javax.swing.JOptionPane.showMessageDialog(this,
                                        "Vui lòng chọn bài thi cần sửa",
                                        "Thông báo",
                                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }

        }// GEN-LAST:event_jLabel1MousePressed

        private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {
                if (curSelect != -1) {
                        // Get the selected test by ID
                        DTO_Test selectedTest = null;
                        for (DTO_Test test : listTest) {
                                if (test.getTestID() == curSelect) {
                                        selectedTest = test;
                                        break;
                                }
                        }

                        if (selectedTest != null) {
                                // Create and show the XemBaiThi dialog instead of SuaBaiThi
                                XemBaiThi xemBaiThi = new XemBaiThi(main, true, selectedTest);
                                xemBaiThi.setLocationRelativeTo(null); // Center on screen
                                xemBaiThi.setVisible(true);
                                // No need to reload data after viewing since nothing can be changed
                        } else {
                                javax.swing.JOptionPane.showMessageDialog(this,
                                                "Không tìm thấy thông tin bài thi",
                                                "Lỗi",
                                                javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                        javax.swing.JOptionPane.showMessageDialog(this,
                                        "Vui lòng chọn bài thi cần xem",
                                        "Thông báo",
                                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblSearch;
    private com.raven.suportSwing.MyButton myButton2;
    private com.raven.suportSwing.ScrollBarCustom scrollBarCustom1;
    private com.raven.suportSwing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
