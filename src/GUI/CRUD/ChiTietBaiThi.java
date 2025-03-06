package GUI.CRUD;

import BUS.BUS_Exam;
import BUS.BUS_Log;
import BUS.BUS_Result;
import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Exam;
import DTO.DTO_Log;
import DTO.DTO_Result;
import DTO.DTO_Test;
import DTO.DTO_Topic;
import GUI.Component.LamBaiThi;
import GUI.Component.MenuTaskBar;
import GUI.Component.pnlKetQua;
import GUI.Menu.QuanLyCacBaiThi;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class ChiTietBaiThi extends javax.swing.JFrame {
    private GUI.GUI_MainFrm main;
    private BUS.BUS_Test testBUS = new BUS_Test();
    private BUS_Exam examBUS =  new BUS_Exam();
    private BUS_Topic topicBUS =  new BUS_Topic();
    private BUS_Log logBUS = new BUS_Log();
    private BUS_Result resBUS = new BUS_Result();
    private DTO_Test baiThi;
    private ArrayList<DTO_Exam> listDeThi;
    private DefaultTableModel modelDeThi;
    private MenuTaskBar menuTask;
    private int luotBaiThi, luotDaLam, check=0;

    public ChiTietBaiThi(GUI.GUI_MainFrm main, MenuTaskBar menuTask, String testCode) {
        this.baiThi= testBUS.selectById(testCode);
        this.menuTask = menuTask;
        this.listDeThi = examBUS.getAllWithTestCode(testCode);
        this.main = main;
        initComponents();
        this.setLocationRelativeTo(null);
        new Thread(() -> {
                LoadData();
                SwingUtilities.invokeLater(() -> KiemTraTinhTrangLamBai(testCode));
            }).start();

    }
    
private void LoadData() {
    // load giao diện bên trái
    DTO_Topic topic = topicBUS.getInfo(baiThi.getTpID());
    this.luotBaiThi = baiThi.getTestLimit();
    this.luotDaLam = resBUS.CountResult(this.baiThi.getTestCode(), main.user.getUserID());
    lblTitle.setText(baiThi.getTestTitle());
    lblTopic.setText("Chủ đề: " + topic.getTpTitle());
    lblNumQuest.setText("Số lượng câu hỏi: " + String.valueOf(baiThi.getNumDiff() + baiThi.getNumEasy() + baiThi.getNumMedium()));
    lblTime.setText("Thời gian: " + String.valueOf(baiThi.getTestTime()));
    lblLuotBaiThi.setText("Số lượt thi của bài: " + String.valueOf(this.luotBaiThi));
    lblLuotLam.setText("Số lượt đã làm: " + String.valueOf(this.luotDaLam));

    modelDeThi = new DefaultTableModel(new Object[]{"STT", "ExOrder", "Mã Đề Thi"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    tblDeThi.setModel(modelDeThi);
    modelDeThi.setRowCount(0);
    int i = 1;
    for (DTO_Exam cur : listDeThi)
        modelDeThi.addRow(new Object[]{i++, cur.getExOrder(), cur.getExCode(), baiThi.getTestLimit()});
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    for (i = 0; i < modelDeThi.getColumnCount(); i++)
        tblDeThi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    // ktra số lần làm
    if (this.luotDaLam >= this.luotBaiThi)
        check = 1;

    // load giao diện bên phải
    ArrayList<DTO_Result> listRes = resBUS.getAllData(this.main.user.getUserID(), this.baiThi.getTestCode());

    // Use BoxLayout for pnlListKQ
    pnlListKQ.setLayout(new javax.swing.BoxLayout(pnlListKQ, javax.swing.BoxLayout.Y_AXIS));
    pnlListKQ.removeAll(); // clear previous components if needed

    for (int j = 0; j < listRes.size(); j++) {
        pnlKetQua pnl = new pnlKetQua(listRes.get(j), this.baiThi);
        pnl.setPreferredSize(new java.awt.Dimension(300, 160));
        pnl.setMaximumSize(new java.awt.Dimension(300, 160));
        pnl.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        pnlListKQ.add(pnl);
        if (j < listRes.size() - 1) {
            pnlListKQ.add(javax.swing.Box.createVerticalStrut(10));
        }
    }

    // Thêm JScrollPane để bao bọc pnlListKQ
    JScrollPane scrollPane = new JScrollPane(pnlListKQ);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    // Thêm scrollPane vào jPanel6 thay vì pnlListKQ trực tiếp
    jPanel6.setLayout(new BorderLayout());
    jPanel6.removeAll(); // Xóa các thành phần cũ trong jPanel6
    jPanel6.add(lblTitle2, BorderLayout.NORTH);
    jPanel6.add(scrollPane, BorderLayout.CENTER);

    jPanel6.revalidate();
    jPanel6.repaint();
}

    
    
    private void KiemTraTinhTrangLamBai(String testCode){
        int userId = this.main.user.getUserID();
        ArrayList<DTO_Exam> listExam = examBUS.getAllWithTestCode(testCode);
        for(DTO_Exam cur : listExam){
            DTO_Log lastLog = logBUS.layLogMoiNhat(userId, cur.getExCode());
            if (lastLog != null) {
                if ("confirm".equals(lastLog.getLogContent()))
                    continue;
                LocalDateTime curDateTime = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime lastLogTime = LocalDateTime.parse(lastLog.getLogDate(), format);
                DTO_Test test = testBUS.selectById(testCode);
                LocalDateTime timeKetThuc = lastLogTime.plusMinutes(test.getTestTime());
                
                if (curDateTime.isBefore(timeKetThuc)) {
                    int choice = JOptionPane.showConfirmDialog(this, "Đề thi " + cur.getExCode() + " vẫn còn thời gian làm bài", "Tiếp tục bài thi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if( choice == JOptionPane.OK_OPTION){
                        LamBaiThi lambai = new LamBaiThi(this.main, this.menuTask, cur.getExCode(), baiThi);
                        this.dispose();
                        main.changePages(lambai);
                    }
                    else
                        this.dispose();
                } else {
                    // Xử lý bài thi đã hết giờ
                    String curTime = curDateTime.format(format);
                    DTO_Log logCur = logBUS.layLogMoiNhat(userId, cur.getExCode());
                    DTO_Result res = new DTO_Result(userId, cur.getExCode(), logCur.getLogContent(), curTime);
                    resBUS.insert(res, this.baiThi.getNumQuest());
                    logBUS.updateLogToConfirm(userId, cur.getExCode());
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
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
        lblLuotBaiThi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTitle2 = new javax.swing.JLabel();
        pnlListKQ = new javax.swing.JPanel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 750));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1100, 650));

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

        tblDeThi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDeThi.setForeground(new java.awt.Color(0, 0, 0));
        tblDeThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, "a"},
                {"2", null, "b"},
                {"3", null, "b"},
                {"4", null, "c"},
                {"5", null, "3"},
                {"6", null, "d"},
                {"7", null, "e"},
                {"8", null, "f"},
                {"9", null, "g"},
                {"10", null, "h"}
            },
            new String [] {
                "STT", "ExOrder", "Mã Đề Thi"
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
        lblLuotLam.setText("Số lượt đã làm:");

        lblNumQuest.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNumQuest.setForeground(new java.awt.Color(0, 0, 0));
        lblNumQuest.setText("Số lượng câu hỏi:");

        lblTopic.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTopic.setForeground(new java.awt.Color(0, 0, 0));
        lblTopic.setText("Chủ đề:");

        lblLuotBaiThi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLuotBaiThi.setForeground(new java.awt.Color(0, 0, 0));
        lblLuotBaiThi.setText("Số lượt thi của bài:");

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTopic)
                            .addComponent(lblNumQuest)
                            .addComponent(lblTime))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLuotBaiThi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLuotLam)
                        .addGap(118, 118, 118))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLuotLam)
                    .addComponent(lblLuotBaiThi)))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Danh sách các mã đề có thể chọn làm");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 650));

        lblTitle2.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle2.setForeground(new java.awt.Color(42, 72, 170));
        lblTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle2.setText("Kết Quả Các Lần Thi");
        lblTitle2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle2.setPreferredSize(new java.awt.Dimension(216, 70));

        pnlListKQ.setBackground(new java.awt.Color(255, 255, 255));
        pnlListKQ.setPreferredSize(new java.awt.Dimension(500, 600));

        javax.swing.GroupLayout pnlListKQLayout = new javax.swing.GroupLayout(pnlListKQ);
        pnlListKQ.setLayout(pnlListKQLayout);
        pnlListKQLayout.setHorizontalGroup(
            pnlListKQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlListKQLayout.setVerticalGroup(
            pnlListKQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pnlListKQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListKQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(btnLamBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamBaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamBaiMousePressed
        // TODO add your handling code here:
        if( check != 1){
            int selectedRow = tblDeThi.getSelectedRow();
            if (selectedRow != -1) {
                String exCode = tblDeThi.getValueAt(selectedRow, 2).toString();
                LamBaiThi lambai = new LamBaiThi(this.main, this.menuTask, exCode, baiThi);
                this.dispose();
                main.changePages(lambai);
            } else 
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã đề thi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }else
                JOptionPane.showMessageDialog(this, "Số lượt làm đề thi đã hết", "Thông báo", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_btnLamBaiMousePressed

    private void btnLamBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamBaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamBaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamBai;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLuotBaiThi;
    private javax.swing.JLabel lblLuotLam;
    private javax.swing.JLabel lblNumQuest;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTopic;
    private javax.swing.JPanel pnlListKQ;
    private javax.swing.JTable tblDeThi;
    // End of variables declaration//GEN-END:variables
}
