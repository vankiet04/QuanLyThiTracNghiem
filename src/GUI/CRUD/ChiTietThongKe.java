
package GUI.CRUD;

import BUS.BUS_Exam;
import BUS.BUS_Result;
import BUS.BUS_Test;
import DTO.DTO_Result;
import DTO.DTO_Test;
import GUI.Component.MenuTaskBar;
import GUI.Menu.QuanLyThongKe;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class ChiTietThongKe extends javax.swing.JPanel {
    private GUI.GUI_MainFrm main;
    private MenuTaskBar menuTask;
    private BUS.BUS_Test testBUS = new BUS_Test();
    private BUS_Exam examBUS = new BUS_Exam();
    private BUS_Result resBUS = new BUS_Result();
    private DTO_Test baiThi;
    
    private ArrayList<DTO_Result> listRes = new ArrayList<>();
    private DefaultTableModel modelTK;
    public ChiTietThongKe(GUI.GUI_MainFrm main, MenuTaskBar menuTask, String testCode) {
        this.baiThi= testBUS.selectById(testCode);
        listRes= resBUS.getAllData(testCode);
        this.main = main;
        this.menuTask = menuTask;
        initComponents();
        loadData();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnQuayLai = new javax.swing.JButton();
        pnlThongTinChung = new javax.swing.JPanel();
        lblBaiThi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSoNguoiThamGia = new javax.swing.JLabel();
        lblSoLuotLamBai = new javax.swing.JLabel();
        lblSoLuongDat = new javax.swing.JLabel();
        lblSoLuongChuaDat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListThi = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1477, 800));

        btnQuayLai.setBackground(new java.awt.Color(42, 72, 170));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay lại");
        btnQuayLai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuayLai.setPreferredSize(new java.awt.Dimension(100, 40));
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQuayLaiMousePressed(evt);
            }
        });

        pnlThongTinChung.setBackground(new java.awt.Color(255, 255, 255));

        lblBaiThi.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblBaiThi.setForeground(new java.awt.Color(42, 72, 170));
        lblBaiThi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBaiThi.setText("Bài thi");
        lblBaiThi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Số người tham gia làm bài:");
        jLabel2.setMaximumSize(new java.awt.Dimension(230, 50));
        jLabel2.setMinimumSize(new java.awt.Dimension(230, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(230, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Tổng lượt làm bài:");
        jLabel3.setMaximumSize(new java.awt.Dimension(160, 50));
        jLabel3.setMinimumSize(new java.awt.Dimension(160, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(160, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Số lượng thí sinh đạt (>=5): ");
        jLabel4.setMaximumSize(new java.awt.Dimension(245, 50));
        jLabel4.setMinimumSize(new java.awt.Dimension(245, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(245, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Số lượng thí sinh chưa đạt (<5): ");
        jLabel5.setMaximumSize(new java.awt.Dimension(280, 50));
        jLabel5.setMinimumSize(new java.awt.Dimension(280, 50));
        jLabel5.setPreferredSize(new java.awt.Dimension(280, 50));

        lblSoNguoiThamGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoNguoiThamGia.setForeground(new java.awt.Color(42, 72, 170));
        lblSoNguoiThamGia.setText("0");

        lblSoLuotLamBai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoLuotLamBai.setForeground(new java.awt.Color(42, 72, 170));
        lblSoLuotLamBai.setText("0");

        lblSoLuongDat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoLuongDat.setForeground(new java.awt.Color(0, 255, 0));
        lblSoLuongDat.setText("0");

        lblSoLuongChuaDat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoLuongChuaDat.setForeground(new java.awt.Color(255, 0, 0));
        lblSoLuongChuaDat.setText("0");

        javax.swing.GroupLayout pnlThongTinChungLayout = new javax.swing.GroupLayout(pnlThongTinChung);
        pnlThongTinChung.setLayout(pnlThongTinChungLayout);
        pnlThongTinChungLayout.setHorizontalGroup(
            pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBaiThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSoNguoiThamGia))
                    .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSoLuotLamBai)))
                .addGap(161, 161, 161)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSoLuongDat))
                    .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoLuongChuaDat)))
                .addContainerGap(471, Short.MAX_VALUE))
        );
        pnlThongTinChungLayout.setVerticalGroup(
            pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinChungLayout.createSequentialGroup()
                .addComponent(lblBaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuongDat)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoNguoiThamGia))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuotLamBai)
                    .addComponent(lblSoLuongChuaDat))
                .addGap(0, 64, Short.MAX_VALUE))
        );

        tblListThi.setBackground(new java.awt.Color(255, 255, 255));
        tblListThi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblListThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "ExCode", "User FullName", "Ngày Nộp Bài", "Điểm"
            }
        ));
        jScrollPane1.setViewportView(tblListThi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlThongTinChung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlThongTinChung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMousePressed

            QuanLyThongKe pnl = new QuanLyThongKe(this.main, menuTask);
            this.main.changePages(pnl);

    }//GEN-LAST:event_btnQuayLaiMousePressed

    public void loadData(){
        int count = resBUS.CountResult(this.baiThi.getTestCode());
        // load data
        this.lblBaiThi.setText(this.baiThi.getTestTitle());
        this.lblSoLuotLamBai.setText(String.valueOf(count));
        
        
        // load bảng
        modelTK = (DefaultTableModel) tblListThi.getModel();
        modelTK.setRowCount(0);
        
        int num=1;
        for(DTO_Result cur : listRes)
                modelTK.addRow(new Object[]{num++, cur.getExCode(), this.main.user.getFullName(), cur.getRsDate(), cur.getRsMask()});
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < modelTK.getColumnCount(); i++)
            tblListThi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); 
        tblListThi.setDefaultEditor(Object.class, null);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBaiThi;
    private javax.swing.JLabel lblSoLuongChuaDat;
    private javax.swing.JLabel lblSoLuongDat;
    private javax.swing.JLabel lblSoLuotLamBai;
    private javax.swing.JLabel lblSoNguoiThamGia;
    private javax.swing.JPanel pnlThongTinChung;
    private javax.swing.JTable tblListThi;
    // End of variables declaration//GEN-END:variables
}
