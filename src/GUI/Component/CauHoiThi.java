package GUI.Component;

import DTO.DTO_Answer;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;

public class CauHoiThi extends javax.swing.JPanel {

    public CauHoiThi(String title, String cauHoi, ArrayList<DTO_Answer> listAnswer) {
        initComponents();
        txtTitle.setText(title);
        txtCauHoi.setText(cauHoi);
        taoRadioButton(listAnswer);
    }
    
    public CauHoiThi() {
        initComponents();
    }

    private void taoRadioButton(ArrayList<DTO_Answer> listAnswer) {
        ButtonGroup group = new ButtonGroup();
        pnlAnswer.setLayout(new java.awt.GridLayout(0, 2)); // Sử dụng GridLayout với 2 cột
        for (DTO_Answer answer : listAnswer) {
            JRadioButton radioButton = new JRadioButton(answer.getContent());
            radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Đặt kích thước phông chữ lên 14
            group.add(radioButton);
            pnlAnswer.add(radioButton);
        }
        pnlAnswer.revalidate();
        pnlAnswer.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlAnswer = new javax.swing.JPanel();
        txtTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCauHoi = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(550, 300));

        pnlAnswer.setBackground(new java.awt.Color(255, 255, 255));
        pnlAnswer.setPreferredSize(new java.awt.Dimension(550, 168));

        javax.swing.GroupLayout pnlAnswerLayout = new javax.swing.GroupLayout(pnlAnswer);
        pnlAnswer.setLayout(pnlAnswerLayout);
        pnlAnswerLayout.setHorizontalGroup(
            pnlAnswerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        pnlAnswerLayout.setVerticalGroup(
            pnlAnswerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        txtTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(0, 0, 0));
        txtTitle.setText("Câu xx");

        txtCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        txtCauHoi.setColumns(20);
        txtCauHoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCauHoi.setForeground(new java.awt.Color(0, 0, 0));
        txtCauHoi.setRows(5);
        txtCauHoi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCauHoi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCauHoi.setEnabled(false);
        txtCauHoi.setOpaque(true); // Đặt màu nền của JTextArea
        txtCauHoi.setLineWrap(true); // Tắt thanh cuộn ngang
        txtCauHoi.setWrapStyleWord(true); // Tắt thanh cuộn ngang
        txtCauHoi.setPreferredSize(new java.awt.Dimension(252, 100));
        txtCauHoi.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(txtCauHoi);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Tắt thanh cuộn dọc
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Tắt thanh cuộn ngang

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAnswer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pnlAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlAnswer;
    private javax.swing.JTextArea txtCauHoi;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}