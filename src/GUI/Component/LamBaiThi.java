package GUI.Component;

import BUS.BUS_Exam;
import BUS.BUS_Questions;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Exam;
import DTO.DTO_Questions;
import GUI.Component.CauHoiThi;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class LamBaiThi extends javax.swing.JPanel {

    //test data
    private ArrayList<CauHoiThi> danhSachCauHoi = new ArrayList<>();
    private ArrayList<DTO_Questions> listCauHoi = new ArrayList<>();
    private JScrollPane scrollPane;
    
    
    private BUS_Exam examBUS =  new BUS_Exam();
    private BUS_Questions questBUS = new BUS_Questions();
    private DTO_Exam examCur;
    

    public LamBaiThi(GUI.GUI_MainFrm main, String exCode) {
        initComponents();
        this.removeAll();
        this.setLayout(new BorderLayout());
        
        // xử lý thông tin chuỗi của exam
        examCur = examBUS.selectById(exCode);
        System.out.println(examCur.getEx_quesIDs());
        listCauHoi = questBUS.getAllData(examCur.getEx_quesIDs());
        
        taoCauHoi();
        hienThiTatCaCauHoi();
        taoNutCauHoi();
        
        
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

    // Lấy từ BUS của list đề thi lên
    private void taoCauHoi() {
        for (int i = 0; i < 50; i++)
            // ex_quesIDs => có id => DTO, quest : qContent: đề bài, qID => answer: gom theo qID => tạo button 
            // câu hỏi, đề bài, các lựa chọn
            danhSachCauHoi.add(new CauHoiThi("Câu hỏi số " + (i + 1), "Đây là đề bài", "Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D"));
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pnlListCauHoi = new javax.swing.JPanel();
        pnlTableCauHoi = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1477, 800));

        pnlTieuDe.setBackground(new java.awt.Color(255, 255, 255));
        pnlTieuDe.setPreferredSize(new java.awt.Dimension(1500, 100));

        jButton1.setBackground(new java.awt.Color(42, 72, 170));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Quay lại");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Thời gian: tik tak");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 50));

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
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlTieuDeLayout.setVerticalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlListCauHoi;
    private javax.swing.JPanel pnlTableCauHoi;
    private javax.swing.JPanel pnlTieuDe;
    // End of variables declaration//GEN-END:variables
}
