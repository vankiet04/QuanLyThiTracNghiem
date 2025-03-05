package GUI.Component;

import BUS.BUS_Answers;
import BUS.BUS_Exam;
import BUS.BUS_Log;
import BUS.BUS_Questions;
import BUS.BUS_Result;
import DTO.DTO_Exam;
import DTO.DTO_Answer;
import DTO.DTO_Log;
import DTO.DTO_Questions;
import DTO.DTO_Result;
import DTO.DTO_Test;
import GUI.GUI_MainFrm;
import GUI.Menu.QuanLyCacBaiThi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


import ConnectDB.JDBCUtil;
import DTO.DTO_Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class LamBaiThi extends javax.swing.JPanel {
    // support database
    private BUS_Questions questBUS = new BUS_Questions();
    private BUS_Answers answerBUS = new BUS_Answers();
    private BUS_Exam examBUS = new BUS_Exam();
    private BUS_Log logBUS = new BUS_Log();
    private BUS_Result resBUS = new BUS_Result();
    
    // support giao diện
    private JScrollPane scrollPane;
    private ArrayList<JButton> listBtn = new ArrayList<>();
    private MenuTaskBar menuTask;
    private GUI_MainFrm mainFrm;
    
    // support truy xuất, lưu trữ danh sách nội dung câu hỏi, panel
    private DTO_Exam examCur;
    private ArrayList<DTO_Questions> listCauHoi = new ArrayList<>();
    private ArrayList<CauHoiThi> danhSachCauHoi = new ArrayList<>();
    private HashMap<Integer, ArrayList<DTO_Answer>> myMap = new HashMap<>(); // qId: list Answer
    
    // support timing
    private int timeConLai; // Thời gian còn lại tính bằng giây
    private Timer timer; // Timer để đếm ngược
    
    // support lấy dữ liệu từ log mới nhất khi còn thời gian làm bài
    private HashMap<Integer, Integer> answerMap = new HashMap<>(); // qId: awID
    private HashMap<Integer, CauHoiThi> cauHoiMap = new HashMap<>(); // qID: panel câu hỏi
    private DTO_Test baithi;
    

    public LamBaiThi(GUI.GUI_MainFrm main, MenuTaskBar menuTask, String exCode, DTO_Test baithi) {
        this.baithi=baithi;
        this.mainFrm = main;
        this.menuTask = menuTask;
        initComponents();
        this.setLayout(new BorderLayout());

        // Load giao diện câu hỏi và nút
        examCur = examBUS.selectById(exCode);
        listCauHoi = questBUS.getAllData(examCur.getEx_quesIDs());
        XuLyDuLieu();
        hienThiTatCaCauHoi();
        taoNutCauHoi();
        
        // đang làm, đã làm xong
        XuLyLogNguoiDung(exCode);
        
        
        // Code liên quan đến giao diện
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
    
    private  void XuLyLogNguoiDung(String exCode){
            boolean isConfirm = logBUS.isLatestLogConfirm(mainFrm.user.getUserID(), exCode);
            ArrayList<DTO_Log> timeLine = logBUS.LayLogCuaNguoiThi(mainFrm.user.getUserID(), exCode, this.baithi.getTestTime());
            
            if (isConfirm) {
                // log cuối là confirm => Bắt đầu bài thi mới
                timeConLai = this.baithi.getTestTime() * 60;
            } else if (!timeLine.isEmpty()) {
                // Nếu chưa nộp bài hoặc còn log sau confirm => Tiếp tục bài thi
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime curDateTime = LocalDateTime.now();
                LocalDateTime timeLanDauVaoThi = LocalDateTime.parse(timeLine.get(0).getLogDate(), format);
                LocalDateTime timeKetThucBaiThi = timeLanDauVaoThi.plusMinutes(this.baithi.getTestTime());

                if (curDateTime.isBefore(timeKetThucBaiThi)) { 
                // Load log mới nhất để tiếp tục
                timeConLai = (int) Duration.between(curDateTime, timeKetThucBaiThi).toSeconds();
                    LayThongTinTuLog(timeLine.get(timeLine.size() - 1).getLogContent());
                    UpdateGiaoDien();
                }
            } else {
                // chưa làm bài nào
                timeConLai = this.baithi.getTestTime() * 60;
            }
            updateLblTime();
            CountTiming();

    }

    private void UpdateGiaoDien() {
        // Cập nhật các câu hỏi đã chọn trước đó
        for (Map.Entry<Integer, Integer> entry : answerMap.entrySet()) {
            int qID = entry.getKey();
            int awID = entry.getValue();

            if (cauHoiMap.containsKey(qID)) {
                CauHoiThi cauHoi = cauHoiMap.get(qID);
                cauHoi.setAnswerID(awID);
            }
        }

        // Cập nhật giao diện của danh sách câu hỏi
        for (int i = 0; i < danhSachCauHoi.size(); i++) {
            int qID = danhSachCauHoi.get(i).getQuestionID();

            // Nếu câu hỏi đã có đáp án được chọn, đổi màu nút câu hỏi
            if (answerMap.containsKey(qID))
                listBtn.get(i).setBackground(Color.decode("#d2d9ef"));
        }

        // Làm mới giao diện
        pnlListCauHoi.revalidate();
        pnlListCauHoi.repaint();
    }

    public void XuLyDuLieu() {
        lblThiSinh.setText(mainFrm.user.getFullName());
        mainFrm.disableMenuTaskBarItems();
        
        for (DTO_Questions ques : listCauHoi) {
            ArrayList<DTO_Answer> list = answerBUS.getAllData(ques.getqID());
            myMap.put(ques.getqID(), list);
        }
        taoCauHoi();
    }
    
    private void LayThongTinTuLog(String logContent) {
        if (logContent.isEmpty())
            return;
        
        String[] list = logContent.split(", ");
        for (String cur : list) {
            String[] item = cur.split(":");
            if (item.length == 2) {
                int qID = Integer.parseInt(item[0]);
                int awID = Integer.parseInt(item[1]);
                answerMap.put(qID, awID);
            }
        }
    }
    
    private void taoCauHoi() {
        for (int i = 0; i < listCauHoi.size(); i++) {
            DTO_Questions quest = listCauHoi.get(i);
            ArrayList<DTO_Answer> listAnswer = myMap.get(quest.getqID());
            CauHoiThi cauHoiThi = new CauHoiThi(quest.getqID(), "Câu hỏi số " + (i + 1), quest.getqContent(), listAnswer);
            danhSachCauHoi.add(cauHoiThi);
            int curIdx = i;
            // câu hỏi đã chọn đáp án => lưu log + đổi màu btn
            for (JRadioButton radioButton : cauHoiThi.getRadioButtons()) {
                radioButton.addActionListener(e -> {
                    // đổi màu
                    listBtn.get(curIdx).setBackground(Color.decode("#d2d9ef"));
                    // lưu log
                    LocalDateTime curDateTime = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String curTime = curDateTime.format(format);
                    String curChoice = quest.getqID() + ":" + cauHoiThi.getAnswerID(radioButton);
                    
                    DTO_Log curLog = new DTO_Log();
                    curLog.setLogUserID(this.mainFrm.user.getUserID());
                    curLog.setLogExCode(examCur.getExCode());
                    curLog.setLogDate(curTime);
                    curLog.setLogContent(curChoice);
                    
                    logBUS.insert(curLog);
                });
            }
        }
    }

    private void hienThiTatCaCauHoi() {
        for (CauHoiThi cauHoi : danhSachCauHoi) {
            pnlListCauHoi.add(cauHoi);
            cauHoiMap.put(cauHoi.getQuestionID(), cauHoi);
        } 
        pnlListCauHoi.revalidate();
        pnlListCauHoi.repaint();
    }

    private void hienThiCauHoi(int index) {
        CauHoiThi cauHoi = danhSachCauHoi.get(index); 

        pnlListCauHoi.revalidate();
        pnlListCauHoi.repaint();

        Rectangle bounds = SwingUtilities.convertRectangle(pnlListCauHoi, cauHoi.getBounds(), scrollPane.getViewport());
        bounds.grow(10, 10);
        scrollPane.getViewport().scrollRectToVisible(bounds);
    }

    private void taoNutCauHoi() {
        for (int i = 0; i < danhSachCauHoi.size(); i++) {
            int index = i;
            JButton btn = new JButton("Câu " + (i + 1));
            btn.setPreferredSize(new Dimension(80, 50));
            btn.addActionListener(e -> hienThiCauHoi(index));
            listBtn.add(btn);
            pnlTableCauHoi.add(btn);
        }
    }

    private void updateLblTime() {
        int minutes = timeConLai / 60;
        int seconds = timeConLai % 60;
        lblTime.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void CountTiming() {
        timer = new Timer(1000, e -> {
            timeConLai--;
            updateLblTime();
            if (timeConLai <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Thời gian làm bài đã hết!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                TinhDiemBaiThi();
            }
        });
        timer.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTieuDe = new javax.swing.JPanel();
        btnQuayLai = new javax.swing.JButton();
        lblThiSinh = new javax.swing.JLabel();
        btnNopBai = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        pnlListCauHoi = new javax.swing.JPanel();
        pnlTableCauHoi = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1477, 800));

        pnlTieuDe.setBackground(new java.awt.Color(255, 255, 255));
        pnlTieuDe.setPreferredSize(new java.awt.Dimension(1500, 100));

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

        lblThiSinh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThiSinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThiSinh.setText("Thí sinh:");

        btnNopBai.setBackground(new java.awt.Color(42, 72, 170));
        btnNopBai.setForeground(new java.awt.Color(255, 255, 255));
        btnNopBai.setText("Nộp Bài");
        btnNopBai.setPreferredSize(new java.awt.Dimension(100, 40));
        btnNopBai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNopBaiMousePressed(evt);
            }
        });
        btnNopBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNopBaiActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 0));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("Thời gian:");
        lblTime.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTime.setPreferredSize(new java.awt.Dimension(200, 80));

        javax.swing.GroupLayout pnlTieuDeLayout = new javax.swing.GroupLayout(pnlTieuDe);
        pnlTieuDe.setLayout(pnlTieuDeLayout);
        pnlTieuDeLayout.setHorizontalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTieuDeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblThiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlTieuDeLayout.setVerticalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTieuDeLayout.createSequentialGroup()
                        .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblThiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    
    
    private void btnNopBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNopBaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNopBaiActionPerformed

    private void btnQuayLaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMousePressed
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thoát bài thi ?", "Quay lại",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            QuanLyCacBaiThi pnl = new QuanLyCacBaiThi(mainFrm, menuTask);
            this.mainFrm.changePages(pnl);
            this.mainFrm.enableMenuTaskBarItems();
            if (timer != null) {
                timer.stop();
            }
        }

    }//GEN-LAST:event_btnQuayLaiMousePressed

    private void TinhDiemBaiThi(){
            LocalDateTime curDateTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String curTime = curDateTime.format(format);
            ArrayList<DTO_Log> timeLine = logBUS.LayLogCuaNguoiThi(mainFrm.user.getUserID(), examCur.getExCode(), this.baithi.getTestTime());
            DTO_Result res = new DTO_Result(this.mainFrm.user.getUserID(), examCur.getExCode(), timeLine.get(1).getLogContent(), curTime);

            resBUS.insert(res, this.baithi.getNumQuest());
            
            // hiển thị điểm
            double diem = res.getRsMask();
            int soCauDung = (int)Math.round(diem / 10.0 * baithi.getNumQuest());
           
            DecimalFormat df = new DecimalFormat("0.00");
            String formattedScore = df.format(diem);

            JOptionPane.showMessageDialog(this,
                "Điểm của bạn: " + formattedScore + "\nSố câu đúng: " + soCauDung,
                "Kết quả bài thi", JOptionPane.INFORMATION_MESSAGE);
            
            // nộp bài xong, chuyển về giao diện QuanLyCacBaiThi.
            QuanLyCacBaiThi pnl = new QuanLyCacBaiThi(mainFrm, menuTask);
            this.mainFrm.changePages(pnl);
            this.mainFrm.enableMenuTaskBarItems();
            if (timer != null) {
                timer.stop();
            }
            logBUS.updateLogToConfirm(this.mainFrm.user.getUserID(), examCur.getExCode());
    }
    
    private void btnNopBaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNopBaiMousePressed
        int input = JOptionPane.showConfirmDialog(null,
            "Bạn chắc chắn nộp bài ?", "Nộp bài",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            TinhDiemBaiThi();
        }
 
    }//GEN-LAST:event_btnNopBaiMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNopBai;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JLabel lblThiSinh;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlListCauHoi;
    private javax.swing.JPanel pnlTableCauHoi;
    private javax.swing.JPanel pnlTieuDe;
    // End of variables declaration//GEN-END:variables
}
