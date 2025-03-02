
package GUI.CRUD;

import BUS.BUS_Exam;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Exam;
import DTO.DTO_Test;
import DTO.DTO_Topic;
import GUI.Component.LamBaiThi;
import GUI.Component.MenuTaskBar;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class ChiTietBaiThi extends javax.swing.JFrame {
    private GUI.GUI_MainFrm main;
    private BUS.BUS_Test testBUS = new BUS_Test();
    private BUS_Exam examBUS =  new BUS_Exam();
    private BUS_Topic topicBUS =  new BUS_Topic();
    private DTO_Test baiThi;
    private ArrayList<DTO_Exam> listDeThi;
    private DefaultTableModel modelDeThi;
    private MenuTaskBar menuTask;

    public ChiTietBaiThi(GUI.GUI_MainFrm main, MenuTaskBar menuTask, String testCode) {
        this.baiThi= testBUS.selectById(testCode);
        this.menuTask = menuTask;
        this.listDeThi = examBUS.getAllWithTestCode(testCode);
        this.main = main;
        initComponents();
        this.setLocationRelativeTo(null);
        LoadData();
    }
    
    private void LoadData(){
        DTO_Topic topic = topicBUS.getInfo(baiThi.getTpID());
        lblTitle.setText(baiThi.getTestTitle());
        lblTopic.setText("Chủ đề: " + topic.getTpTitle());
        lblNumQuest.setText("Số lượng câu hỏi: " + String.valueOf(baiThi.getNumDiff() + baiThi.getNumEasy() + baiThi.getNumMedium()));
        lblTime.setText("Thời gian: " + String.valueOf(baiThi.getTestTime()));
        lblLuotLam.setText("Lượt làm: " + String.valueOf(baiThi.getTestLimit()));
        
        modelDeThi = new DefaultTableModel(new Object[]{"STT", "ExOrder", "Mã Đề Thi", "Số Lượt Thi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tblDeThi.setModel(modelDeThi);
        modelDeThi.setRowCount(0);
        int i=1;
        for(DTO_Exam cur : listDeThi)
                modelDeThi.addRow(new Object[]{i++, cur.getExOrder(), cur.getExCode(), baiThi.getTestLimit()});
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (i = 0; i < modelDeThi.getColumnCount(); i++)
            tblDeThi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); 
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnLamBai = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeThi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblLuotLam = new javax.swing.JLabel();
        lblNumQuest = new javax.swing.JLabel();
        lblTopic = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(580, 650));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(586, 650));

        btnLamBai.setBackground(new java.awt.Color(255, 102, 102));
        btnLamBai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamBai.setForeground(new java.awt.Color(255, 255, 255));
        btnLamBai.setText("Bắt Đầu Làm");
        btnLamBai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLamBai.setPreferredSize(new java.awt.Dimension(120, 50));
        btnLamBai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLamBaiMousePressed(evt);
            }
        });
        btnLamBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamBaiActionPerformed(evt);
            }
        });

        tblDeThi.setBackground(new java.awt.Color(255, 255, 255));
        tblDeThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDeThi.setForeground(new java.awt.Color(0, 0, 0));
        tblDeThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, "a", "3"},
                {"2", null, "b", "3"},
                {"3", null, "b", "3"},
                {"4", null, "c", "3"},
                {"5", null, "3", "3"},
                {"6", null, "d", "3"},
                {"7", null, "e", "3"},
                {"8", null, "f", "3"},
                {"9", null, "g", "3"},
                {"10", null, "h", "3"}
            },
            new String [] {
                "STT", "ExOrder", "Mã Đề Thi", "Số Lượt Thi"
            }
        ));
        tblDeThi.setPreferredSize(new java.awt.Dimension(225, 330));
        jScrollPane1.setViewportView(tblDeThi);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(42, 72, 170));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Bài thi môn abcxyz");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 0));
        lblTime.setText("Thời gian:");

        lblLuotLam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLuotLam.setForeground(new java.awt.Color(0, 0, 0));
        lblLuotLam.setText("Số lượt làm:");

        lblNumQuest.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNumQuest.setForeground(new java.awt.Color(0, 0, 0));
        lblNumQuest.setText("Số lượng câu hỏi:");

        lblTopic.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTopic.setForeground(new java.awt.Color(0, 0, 0));
        lblTopic.setText("Chủ đề:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTopic)
                    .addComponent(lblNumQuest)
                    .addComponent(lblTime)
                    .addComponent(lblLuotLam))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTopic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumQuest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLuotLam))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Danh sách các mã đề có thể chọn làm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(btnLamBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamBaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamBaiMousePressed
        // TODO add your handling code here:
        int selectedRow = tblDeThi.getSelectedRow();
        if (selectedRow != -1) {
            String exCode = tblDeThi.getValueAt(selectedRow, 2).toString();
            LamBaiThi lambai = new LamBaiThi(this.main, this.menuTask, exCode);
            this.dispose();
            main.changePages(lambai);
        } else 
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã đề thi.", "Thông báo", JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_btnLamBaiMousePressed

    private void btnLamBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamBaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamBaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamBai;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLuotLam;
    private javax.swing.JLabel lblNumQuest;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTopic;
    private javax.swing.JTable tblDeThi;
    // End of variables declaration//GEN-END:variables
}
