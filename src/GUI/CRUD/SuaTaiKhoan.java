

import BUS.BUS_User;
import DTO.DTO_User;
import javax.swing.JOptionPane;

public class SuaTaiKhoan extends javax.swing.JDialog {
    private  int curID;
    private BUS_User userBUS= new BUS_User();
    private DTO_User userDuocChon;
    private GUI.Menu.QuanLyTaiKhoan curUI;

        public SuaTaiKhoan(int idTK, GUI.Menu.QuanLyTaiKhoan UI) {
            this.curID = idTK;
            this.curUI = UI;
            userDuocChon = userBUS.getInfoByID(idTK);
            initComponents();
            this.setLocationRelativeTo(null);
            
            LoadInfoUSer();
            
        }
    private void LoadInfoUSer() {
        txtUserName.setText(userDuocChon.getUserName());
        txtPass.setText(userDuocChon.getPass());
        txtEmail.setText(userDuocChon.getEmail());
        txtFullName.setText(userDuocChon.getFullName());
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlTittle = new javax.swing.JPanel();
        lblTittle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtFullName = new com.raven.suportSwing.TextField();
        txtEmail = new com.raven.suportSwing.TextField();
        txtUserName = new com.raven.suportSwing.TextField();
        txtPass = new com.raven.suportSwing.TextField();
        btnSua = new javax.swing.JButton();

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 255));
        jLabel7.setText("THÊM CÂU HỎI");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 555));

        pnlTittle.setBackground(new java.awt.Color(255, 255, 255));

        lblTittle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(42, 72, 170));
        lblTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTittle.setText("SỬA THÔNG TIN TÀI KHOẢN");
        lblTittle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlTittleLayout = new javax.swing.GroupLayout(pnlTittle);
        pnlTittle.setLayout(pnlTittleLayout);
        pnlTittleLayout.setHorizontalGroup(
            pnlTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTittle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTittleLayout.setVerticalGroup(
            pnlTittleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTittleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtFullName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtFullName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFullName.setLabelText("Fullname");
        txtFullName.setPreferredSize(new java.awt.Dimension(450, 50));
        txtFullName.setSelectionColor(new java.awt.Color(0, 102, 255));

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setLabelText("Email");
        txtEmail.setPreferredSize(new java.awt.Dimension(450, 50));
        txtEmail.setSelectionColor(new java.awt.Color(0, 102, 255));

        txtUserName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUserName.setEnabled(false);
        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setLabelText("Username");
        txtUserName.setPreferredSize(new java.awt.Dimension(450, 50));
        txtUserName.setSelectionColor(new java.awt.Color(0, 102, 255));

        txtPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        txtPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPass.setLabelText("Password");
        txtPass.setPreferredSize(new java.awt.Dimension(450, 50));
        txtPass.setSelectionColor(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSua.setBackground(new java.awt.Color(42, 72, 170));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setBorder(null);
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlTittle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String pass = txtPass.getText().trim();
        String email = txtEmail.getText().trim();
        String fullname = txtFullName.getText().trim();
        if ( !pass.matches("^[a-zA-Z0-9]+$")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtPass.requestFocus();
            return;
        }
        if ( pass.length() < 5){
            JOptionPane.showMessageDialog(this, "Mật khẩu phải từ 5 kí tự trở lên", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtPass.requestFocus();
            return;        
        }
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;                
        }
        if (!fullname.matches("^[\\p{L}\\s]+$")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng tên", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtFullName.requestFocus();
            return;               
        }
       
        // thực hiện chỉnh sửa
        userDuocChon.setEmail(email);
        userDuocChon.setFullName(fullname);
        userDuocChon.setPass(pass);
        int res = userBUS.update(userDuocChon);
        if (res == -1){
            JOptionPane.showMessageDialog(this, "Email đã tồn tại", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;    
        }
        JOptionPane.showMessageDialog(this, "Đã sửa thành công", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
        this.curUI.loadData(userBUS.getAllData());
        this.dispose();
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel pnlTittle;
    private com.raven.suportSwing.TextField txtEmail;
    private com.raven.suportSwing.TextField txtFullName;
    private com.raven.suportSwing.TextField txtPass;
    private com.raven.suportSwing.TextField txtUserName;
    // End of variables declaration//GEN-END:variables


}
