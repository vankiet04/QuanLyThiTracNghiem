
package GUI;

import BUS.BUS_User;
import DTO.DTO_User;
import GUI.Component.InputForm;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class GUI_SignUp extends JFrame{
    JPanel pnlMain, pnlLogin;
    JLabel lblPc, lblTitleLogin, lblLogin, lblDangKy;
    InputForm txtUsername, txtPass, txtEmail, txtFullName;
    BUS.BUS_User BUSUser = new BUS_User();

    public GUI_SignUp() {
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(1000,800));
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("HỆ THỐNG QUẢN LÝ THI TRẮC NGHIỆM");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitComponent();
    }
    
    private void InitComponent(){
        pnlMain = new JPanel();

        // đưa hình vào
        JPanel pnlPic = new JPanel(new GridBagLayout()); 
        pnlPic.setPreferredSize(new Dimension(500, 800));
        pnlPic.setBackground(Color.WHITE);
        lblPc = new JLabel();
        lblPc.setIcon(new FlatSVGIcon("./img/login.svg"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.fill = GridBagConstraints.CENTER; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 

        pnlPic.add(lblPc, gbc);
        this.add(pnlPic, BorderLayout.EAST);
        
        // tiêu đề
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(new EmptyBorder(50, 0, 0, 0));

        pnlMain.setPreferredSize(new Dimension(500, 600));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));
        
        
        lblTitleLogin = new JLabel("ĐĂNG KÝ");
        lblTitleLogin.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 24));
        pnlMain.add(lblTitleLogin);
        // nội dung
        JPanel pnlNoiDung = new JPanel();
        pnlNoiDung.setBackground(Color.WHITE);
        
        pnlNoiDung.setLayout(new GridLayout(4, 1));
        pnlNoiDung.setBorder(new EmptyBorder(0, 0, 10, 0));
        txtUsername = new InputForm("Tên đăng nhập:");
        pnlNoiDung.add(txtUsername);
        txtPass = new InputForm("Mật khẩu:", "password");
        pnlNoiDung.add(txtPass);
        txtEmail = new InputForm("Email:");
        pnlNoiDung.add(txtEmail);
        txtFullName = new InputForm("FullName:");
        pnlNoiDung.add(txtFullName);
        pnlMain.add(pnlNoiDung);
        
        // button
        lblLogin = new JLabel("ĐĂNG KÝ");
        lblLogin.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lblLogin.setForeground(Color.white);
 
        pnlLogin = new JPanel();
        pnlLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlLogin.putClientProperty(FlatClientProperties.STYLE, "arc: 100" );
        pnlLogin.setBackground(Color.BLACK);
        pnlLogin.setPreferredSize(new Dimension(400, 50));
        pnlLogin.setLayout(new FlowLayout(1, 0, 15));
        pnlLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                ValidateSignUp();
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogin.setBackground(new Color(50,50,50));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogin.setBackground(Color.BLACK); 
            }
            
        });
        pnlLogin.add(lblLogin);
        pnlMain.add(pnlLogin);
        
        // thêm phân cách DN - DK
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(380, 20));
        separator.setForeground(Color.LIGHT_GRAY);       
        pnlMain.add(separator);
        // thêm phần đăng ký
        lblDangKy = new JLabel("Đã có tài khoản?        ");
        lblDangKy.setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
        lblDangKy.setBounds(50, 400, 300, 30);
        pnlMain.add(lblDangKy);
        
        JPanel pnlLogup = new JPanel();
        pnlLogup.putClientProperty(FlatClientProperties.STYLE, "arc: 100");
        pnlLogup.setBackground(Color.decode("#2a48aa"));
        pnlLogup.setPreferredSize(new Dimension(100, 45));
        pnlLogup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlLogup.setLayout(new FlowLayout(1,0,10));

        JLabel lblLogup = new JLabel("Đăng nhập");
        lblLogup.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lblLogup.setForeground(Color.white);

        pnlLogup.add(lblLogup);
        pnlLogup.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new GUI_Login();
                closeWindow();
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogup.setBackground(Color.decode("#5070cc"));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogup.setBackground(Color.decode("#2a48aa")); 
            }
        });
        
        txtUsername.getTxtForm().addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) 
                ValidateSignUp();
            }
        });

        txtPass.getTxtPass().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    ValidateSignUp();
            }
        });

        pnlLogup.add(lblLogup);
        pnlMain.add(pnlLogup);
        this.add(pnlMain,BorderLayout.WEST);
        this.setVisible(true);
    }
    
    public void closeWindow () {
        this.dispose();
    }
        
    private void ValidateSignUp(){
        String userName = txtUsername.getText().trim();
        String pass = txtPass.getPass().trim();
        String email = txtEmail.getText().trim();
        String fullname = txtFullName.getText().trim();
        if ( userName.isEmpty() || pass.isEmpty() || email.isEmpty() || fullname.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ( !userName.matches("^[a-zA-Z0-9]+$")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return;
        }
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
       
        // thực hiện đk
        DTO.DTO_User cur = new DTO_User(userName, pass, email, fullname);
        int res = BUSUser.insert(cur);
        if ( res == 0){
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return;   
        }
        if (res == -1){
            JOptionPane.showMessageDialog(this, "Email đã tồn tại", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;    
        }
        JOptionPane.showMessageDialog(this, "Bạn đã đăng ký thành công", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    
    

    
}
