package GUI.Component;

import BUS.BUS_Answers;
import BUS.BUS_Exam;
import BUS.BUS_Questions;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Exam;
import DTO.DTO_Answer;
import DTO.DTO_Questions;
import GUI.Component.CauHoiThi;
import GUI.GUI_Login;
import GUI.GUI_MainFrm;
import GUI.Menu.QuanLyCacBaiThi;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class LamBaiThi extends javax.swing.JPanel {
    private ArrayList<CauHoiThi> danhSachCauHoi = new ArrayList<>();
    private ArrayList<DTO_Questions> listCauHoi = new ArrayList<>();
    private JScrollPane scrollPane;
    private HashMap<Integer, ArrayList<DTO_Answer>> myMap = new HashMap<>();
    
    private MenuTaskBar menuTask;
    private GUI_MainFrm mainFrm;
    private BUS_Exam examBUS =  new BUS_Exam();
    private BUS_Questions questBUS = new BUS_Questions();
    private BUS_Answers answerBUS =  new BUS_Answers();
    private DTO_Exam examCur;
    

    public LamBaiThi(GUI.GUI_MainFrm main,MenuTaskBar menuTask , String exCode) {
        mainFrm = main;
        this.menuTask = menuTask;
        initComponents();
        this.removeAll();
        this.setLayout(new BorderLayout());
        
        // xử lý thông tin chuỗi của exam

        examCur = examBUS.selectById(exCode);
        listCauHoi = questBUS.getAllData(examCur.getEx_quesIDs());
        XuLyDuLieu();
        hienThiTatCaCauHoi();
        taoNutCauHoi();
        
        disableMenuTaskButtons();
        // code liên quan đến giao diện
        pnlListCauHoi.setLayout(new BoxLayout(pnlListCauHoi, BoxLayout.Y_AXIS));
        pnlListCauHoi.setPreferredSize(null);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(pnlListCauHoi);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(pnlTieuDe, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pnlTableCauHoi, BorderLayout.EAST);
        this.revalidate();
        this.repaint();
    }
    
    private void disableMenuTaskButtons() {
        for(int i =0; i < menuTask.listitem.length; i++)
            menuTask.listitem[i].setEnabled(false);
    }

    public void XuLyDuLieu(){
        for(DTO_Questions ques : listCauHoi){
                ArrayList<DTO_Answer> list = answerBUS.getAllData(ques.getqID());
                myMap.put(ques.getqID(), list);
        }
        taoCauHoi();
    }
        private void taoCauHoi() {
            for (int i = 0; i < listCauHoi.size(); i++){
                DTO_Questions quest = listCauHoi.get(i);
                ArrayList<DTO_Answer> listAnswer = myMap.get(quest.getqID());
                danhSachCauHoi.add(new CauHoiThi("Câu hỏi số " + (i + 1), quest.getqContent(), listAnswer));
            }

        }

    //  
    private void hienThiTatCaCauHoi() {
        for (CauHoiThi cauHoi : danhSachCauHoi) 
            pnlListCauHoi.add(cauHoi); // Thêm từng câu hỏi vào panel
        pnlListCauHoi.revalidate();
        pnlListCauHoi.repaint();
    }

    
    private void hienThiCauHoi(int index) {
        CauHoiThi cauHoi = danhSachCauHoi.get(index); 

        pnlListCauHoi.revalidate();
        pnlListCauHoi.repaint();

        // Convert the cauHoi bounds to viewport coordinate system and scroll to it
        Rectangle bounds = SwingUtilities.convertRectangle(pnlListCauHoi, cauHoi.getBounds(), scrollPane.getViewport());
        bounds.grow(10, 10);
        scrollPane.getViewport().scrollRectToVisible(bounds);
    }

    // Tạo các nút câu hỏi bên pnlTableCauHoi
    private void taoNutCauHoi() {
        for (int i = 0; i < danhSachCauHoi.size(); i++) {
            int index = i; // Use correct index
            JButton btn = new JButton("Câu " + (i + 1)); // Display text can remain (i+1)
            btn.setPreferredSize(new Dimension(80,50));
            btn.addActionListener(e -> hienThiCauHoi(index));
            pnlTableCauHoi.add(btn);
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTieuDe = new javax.swing.JPanel();
        btnQuayLai = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pnlListCauHoi = new javax.swing.JPanel();
        pnlTableCauHoi = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1477, 800));

        pnlTieuDe.setBackground(new java.awt.Color(255, 255, 255));
        pnlTieuDe.setPreferredSize(new java.awt.Dimension(1500, 100));

        btnQuayLai.setBackground(new java.awt.Color(42, 72, 170));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay lại");
        btnQuayLai.setPreferredSize(new java.awt.Dimension(100, 40));
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQuayLaiMousePressed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 0));
        lblTime.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thí sinh:");

        jButton2.setBackground(new java.awt.Color(42, 72, 170));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Nộp Bài");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTieuDeLayout = new javax.swing.GroupLayout(pnlTieuDe);
        pnlTieuDe.setLayout(pnlTieuDeLayout);
        pnlTieuDeLayout.setHorizontalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTieuDeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlTieuDeLayout.setVerticalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTieuDeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlListCauHoi.setPreferredSize(new java.awt.Dimension(1000, 800));
        pnlListCauHoi.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        pnlTableCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableCauHoi.setForeground(new java.awt.Color(0, 0, 0));
        pnlTableCauHoi.setPreferredSize(new java.awt.Dimension(400, 1000));
        pnlTableCauHoi.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlListCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTableCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlListCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                    .addComponent(pnlTableCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnQuayLaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMousePressed
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thoát bài thi ?", "Quay lại",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        // có khi sẽ phải set lại trong log
        if (input == 0) {
            QuanLyCacBaiThi pnl = new QuanLyCacBaiThi(mainFrm, menuTask);
            this.mainFrm.changePages(pnl);
        }

    }//GEN-LAST:event_btnQuayLaiMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlListCauHoi;
    private javax.swing.JPanel pnlTableCauHoi;
    private javax.swing.JPanel pnlTieuDe;
    // End of variables declaration//GEN-END:variables
}
