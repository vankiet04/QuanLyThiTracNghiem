/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Menu;
import DTO.DTO_User;
import BUS.BUS_User;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class QuanLyThongTinCaNhan extends javax.swing.JPanel {
    private DTO_User user;
    private BUS.BUS_User userBUS = new BUS_User();

    public QuanLyThongTinCaNhan(DTO_User account) {
        user = account;
        initComponents();
        LoadInfoUSer();
    }
    
    private void LoadInfoUSer() {
        txtUserName.setText(user.getUserName());
        txtEmail.setText(user.getEmail());
        txtFullName.setText(user.getFullName());
        txtPassCu.setText("");
        txtPassMoi.setText("");
    }
    
    private int  ValidateData(){
        String email = txtEmail.getText().trim();
        String fullname = txtFullName.getText().trim();
        String passCu = txtPassCu.getText().trim();
        String passMoi = txtPassMoi.getText().trim();
        if ( email.isEmpty() || fullname.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return -1;                
        }
        if (!fullname.matches("^[\\p{L}\\s]+$")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng tên", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtFullName.requestFocus();
            return -1;               
        }
        
        if (!passMoi.isEmpty() && passCu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu cũ để đổi mật khẩu", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtPassCu.requestFocus();
            return -1;
        }

        if ( !passCu.isEmpty()){
            if (!passCu.equals(user.getPass())){
                JOptionPane.showMessageDialog(this, "Mật khẩu cũ đã nhập sai", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return -1;   
            }
            if ( passMoi.length() < 5){
                JOptionPane.showMessageDialog(this, "Mật khẩu mới phải từ 5 kí tự trở lên", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                txtPassMoi.requestFocus();
                return -1;        
            }
        }
        return 1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTittle = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pnlFullName = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        pnlUserName = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        pnlEmail = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlPassCu = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtPassCu = new javax.swing.JPasswordField();
        pnlPassMoi = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtPassMoi = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N

        lblTittle.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(42, 72, 170));
        lblTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTittle.setText("Thông tin cá nhân");
        lblTittle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTittle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlFullName.setBackground(new java.awt.Color(255, 255, 255));
        pnlFullName.setPreferredSize(new java.awt.Dimension(1000, 150));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Full name");

        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFullName.setPreferredSize(new java.awt.Dimension(1200, 50));

        javax.swing.GroupLayout pnlFullNameLayout = new javax.swing.GroupLayout(pnlFullName);
        pnlFullName.setLayout(pnlFullNameLayout);
        pnlFullNameLayout.setHorizontalGroup(
            pnlFullNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFullNameLayout.createSequentialGroup()
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
        );
        pnlFullNameLayout.setVerticalGroup(
            pnlFullNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFullNameLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(pnlFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 66, 1300, 87));

        pnlUserName.setBackground(new java.awt.Color(255, 255, 255));
        pnlUserName.setPreferredSize(new java.awt.Dimension(1000, 150));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Username");

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtUserName.setEnabled(false);
        txtUserName.setPreferredSize(new java.awt.Dimension(1200, 50));

        javax.swing.GroupLayout pnlUserNameLayout = new javax.swing.GroupLayout(pnlUserName);
        pnlUserName.setLayout(pnlUserNameLayout);
        pnlUserNameLayout.setHorizontalGroup(
            pnlUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlUserNameLayout.createSequentialGroup()
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
        );
        pnlUserNameLayout.setVerticalGroup(
            pnlUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserNameLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(pnlUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 177, 1300, 87));

        pnlEmail.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmail.setPreferredSize(new java.awt.Dimension(1000, 150));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Email");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(1200, 50));

        javax.swing.GroupLayout pnlEmailLayout = new javax.swing.GroupLayout(pnlEmail);
        pnlEmail.setLayout(pnlEmailLayout);
        pnlEmailLayout.setHorizontalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlEmailLayout.createSequentialGroup()
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
        );
        pnlEmailLayout.setVerticalGroup(
            pnlEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmailLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(pnlEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 276, 1300, 87));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(42, 72, 170));
        jLabel5.setText("Đổi mật khẩu");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 375, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(42, 72, 170));
        jLabel6.setText("Chung");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 6, -1, -1));

        pnlPassCu.setBackground(new java.awt.Color(255, 255, 255));
        pnlPassCu.setPreferredSize(new java.awt.Dimension(450, 150));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Mật khẩu cũ");

        txtPassCu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlPassCuLayout = new javax.swing.GroupLayout(pnlPassCu);
        pnlPassCu.setLayout(pnlPassCuLayout);
        pnlPassCuLayout.setHorizontalGroup(
            pnlPassCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addComponent(txtPassCu)
        );
        pnlPassCuLayout.setVerticalGroup(
            pnlPassCuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPassCuLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassCu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(pnlPassCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 441, -1, 87));

        pnlPassMoi.setBackground(new java.awt.Color(255, 255, 255));
        pnlPassMoi.setPreferredSize(new java.awt.Dimension(450, 150));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Mật khẩu mới");

        txtPassMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlPassMoiLayout = new javax.swing.GroupLayout(pnlPassMoi);
        pnlPassMoi.setLayout(pnlPassMoiLayout);
        pnlPassMoiLayout.setHorizontalGroup(
            pnlPassMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addComponent(txtPassMoi, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlPassMoiLayout.setVerticalGroup(
            pnlPassMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPassMoiLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(pnlPassMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 440, -1, 87));

        jButton1.setBackground(new java.awt.Color(42, 72, 170));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cập nhật");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(250, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 560, 249, 89));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtEmail.getText().trim().equals(user.getEmail()) &&
            txtFullName.getText().trim().equals(user.getFullName()) &&
            txtPassCu.getText().trim().isEmpty() &&
            txtPassMoi.getText().trim().isEmpty())
        return;
        if (ValidateData()==1){
            int choice = JOptionPane.showConfirmDialog(null,
                "Xác nhận cập nhật thông tin mới", "Thông báo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == 0){
                user.setEmail(txtEmail.getText().trim());
                user.setFullName(txtFullName.getText().trim());
                user.setPass(txtPassMoi.getText().trim());
                int res = userBUS.update(user);
                if (res == -1){
                    JOptionPane.showMessageDialog(this, "Email đã tồn tại", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                    txtEmail.requestFocus();
                    return;
                }
                JOptionPane.showMessageDialog(this, "Đã sửa thành công", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
                LoadInfoUSer();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel pnlEmail;
    private javax.swing.JPanel pnlFullName;
    private javax.swing.JPanel pnlPassCu;
    private javax.swing.JPanel pnlPassMoi;
    private javax.swing.JPanel pnlUserName;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPassCu;
    private javax.swing.JPasswordField txtPassMoi;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
