package GUI.Component;

import DTO.DTO_Result;
import DTO.DTO_Test;
import com.raven.suportSwing.RoundedBorder;
import javax.swing.JLayeredPane;
import java.awt.Color;

public class pnlKetQua extends JLayeredPane {

    public pnlKetQua(DTO_Result res, DTO_Test baithi) {
        initComponents();
        lblMaBaiThi.setText("Mã bài thi: " + res.getExCode());
        lblDiemThi.setText("Điểm thi: " + res.getRsMask());
        lblTime.setText("Thời gian nộp: " + res.getRsDate());
        int dapan = (int) Math.round(res.getRsMask() / 10 * baithi.getNumQuest());
        lblNumDapAn.setText("Số đáp án đúng: " + dapan);
        lblNumCauHoi.setText("Số lượng câu hỏi: " + baithi.getNumQuest());
        testpnl1.setBorder(new RoundedBorder(10, new Color(42, 74, 170))); // Thay đổi màu viền và bo tròn góc
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        testpnl1 = new com.raven.suportSwing.PanelBorder();
        lblDiemThi = new javax.swing.JLabel();
        lblMaBaiThi = new javax.swing.JLabel();
        lblNumCauHoi = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblNumDapAn = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        testpnl1.setBackground(new java.awt.Color(255, 255, 255));
        testpnl1.setMaximumSize(new java.awt.Dimension(300, 50));
        testpnl1.setPreferredSize(new java.awt.Dimension(300, 50));

        lblDiemThi.setBackground(new java.awt.Color(255, 255, 255));
        lblDiemThi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDiemThi.setForeground(new java.awt.Color(42, 74, 170));
        lblDiemThi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiemThi.setText("Điểm thi:");
        lblDiemThi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblMaBaiThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaBaiThi.setForeground(new java.awt.Color(0, 0, 0));
        lblMaBaiThi.setText("Mã đề thi:");

        lblNumCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNumCauHoi.setForeground(new java.awt.Color(0, 0, 0));
        lblNumCauHoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumCauHoi.setText("Số lượng câu hỏi:");
        lblNumCauHoi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 0));
        lblTime.setText("Thời gian nộp bài:");

        lblNumDapAn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNumDapAn.setForeground(new java.awt.Color(0, 0, 0));
        lblNumDapAn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumDapAn.setText("Số đáp án đúng:");
        lblNumDapAn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout testpnl1Layout = new javax.swing.GroupLayout(testpnl1);
        testpnl1.setLayout(testpnl1Layout);
        testpnl1Layout.setHorizontalGroup(
            testpnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testpnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testpnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaBaiThi)
                    .addComponent(lblTime)
                    .addComponent(lblNumDapAn)
                    .addComponent(lblNumCauHoi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, testpnl1Layout.createSequentialGroup()
                .addComponent(lblDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        testpnl1Layout.setVerticalGroup(
            testpnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testpnl1Layout.createSequentialGroup()
                .addComponent(lblDiemThi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaBaiThi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumDapAn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumCauHoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setLayer(testpnl1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testpnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testpnl1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDiemThi;
    private javax.swing.JLabel lblMaBaiThi;
    private javax.swing.JLabel lblNumCauHoi;
    private javax.swing.JLabel lblNumDapAn;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel testpnl1;
    // End of variables declaration//GEN-END:variables
}