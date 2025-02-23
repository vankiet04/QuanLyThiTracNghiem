/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Component;


public class CauHoiThi extends javax.swing.JPanel {
        

    public CauHoiThi(String title, String cauHoi, String A, String B, String C, String D) {
        initComponents();
        buttonGroup1.add(btnDapAnA);
        buttonGroup1.add(btnDapAnB);
        buttonGroup1.add(btnDapAnC);
        buttonGroup1.add(btnDapAnD);
        
        txtTitle.setText(title);
        txtCauHoi.setText(cauHoi);
        btnDapAnA.setText(A);
        btnDapAnB.setText(B);
        btnDapAnC.setText(C);
        btnDapAnD.setText(D);
        
    }
    
    public CauHoiThi() {
        initComponents();
        buttonGroup1.add(btnDapAnA);
        buttonGroup1.add(btnDapAnB);
        buttonGroup1.add(btnDapAnC);
        buttonGroup1.add(btnDapAnD);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnDapAnA = new javax.swing.JRadioButton();
        btnDapAnB = new javax.swing.JRadioButton();
        btnDapAnC = new javax.swing.JRadioButton();
        btnDapAnD = new javax.swing.JRadioButton();
        txtTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCauHoi = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(550, 350));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 168));

        btnDapAnA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDapAnA.setText("Câu a");

        btnDapAnB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDapAnB.setText("Câu b");

        btnDapAnC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDapAnC.setText("Câu C");

        btnDapAnD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDapAnD.setText("Câu D");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDapAnA)
                    .addComponent(btnDapAnB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDapAnC)
                    .addComponent(btnDapAnD))
                .addGap(289, 289, 289))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDapAnA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDapAnC, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDapAnD)
                    .addComponent(btnDapAnB))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        txtTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(0, 0, 0));
        txtTitle.setText("Câu xx");

        txtCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        txtCauHoi.setColumns(20);
        txtCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCauHoi.setForeground(new java.awt.Color(0, 0, 0));
        txtCauHoi.setRows(5);
        txtCauHoi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCauHoi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCauHoi.setEnabled(false);
        txtCauHoi.setOpaque(false);
        txtCauHoi.setPreferredSize(new java.awt.Dimension(252, 100));
        txtCauHoi.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(txtCauHoi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnDapAnA;
    private javax.swing.JRadioButton btnDapAnB;
    private javax.swing.JRadioButton btnDapAnC;
    private javax.swing.JRadioButton btnDapAnD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtCauHoi;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}
