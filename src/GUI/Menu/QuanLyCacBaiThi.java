package GUI.Menu;

import ConnectDB.JDBCUtil;
import GUI.CRUD.ChiTietBaiThi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GUI.Component.pnlBaiThi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class QuanLyCacBaiThi extends JPanel {
    private ArrayList<pnlBaiThi> listPnl = new ArrayList<>();
    private GUI.GUI_MainFrm main;
    private javax.swing.JPanel pnlContent;
    
    public QuanLyCacBaiThi(GUI.GUI_MainFrm main) {
        this.main = main;
        initComponents();
        LoadBaiThi();
        
    }
    
    private void LoadBaiThi() {
        listPnl.clear();
        pnlContent.removeAll();

        try {
            Connection con = JDBCUtil.getConnectDB();
            String query = "SELECT testTitle, testLimit FROM test";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String tenBaiThi = rs.getString("testTitle");
                int soLuotLam = rs.getInt("testLimit");
                double diem = 0;

                pnlBaiThi pnl = new pnlBaiThi(tenBaiThi, soLuotLam, diem);
                pnl.setPreferredSize(new java.awt.Dimension(250, 150));
                listPnl.add(pnl);
                pnlContent.add(pnl);
                
                pnl.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        ChayBaiThi();
                    }
                });
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private void ChayBaiThi(){
        ChiTietBaiThi ctbt = new ChiTietBaiThi(this.main);
        ctbt.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlSearch = new javax.swing.JPanel();
        txtSearch2 = new com.raven.suportSwing.TextField();
        btnReset = new com.raven.suportSwing.MyButton();
        pnlListContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1744, 938));

        pnlSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearch.setPreferredSize(new java.awt.Dimension(1027, 250));

        txtSearch2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSearch2.setLabelText("Tìm bài thi");
        txtSearch2.setPreferredSize(new java.awt.Dimension(300, 50));

        btnReset.setBackground(new java.awt.Color(204, 255, 255));
        btnReset.setText("Reset");
        btnReset.setBorderColor(new java.awt.Color(153, 255, 255));
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReset.setRadius(20);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1236, Short.MAX_VALUE))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlContent = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jScrollPane1.setViewportView(pnlContent);
        
        pnlListContainer.setLayout(new BorderLayout());
        pnlListContainer.add(jScrollPane1, BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 1744, Short.MAX_VALUE)
            .addComponent(pnlListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
        );
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {

    }

    // Variables declaration - do not modify
    private com.raven.suportSwing.MyButton btnReset;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlListContainer;
    private javax.swing.JPanel pnlSearch;
    private com.raven.suportSwing.TextField txtSearch2;
    // End of variables declaration
}
