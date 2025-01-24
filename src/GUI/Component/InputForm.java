
package GUI.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InputForm extends JPanel{
    private JLabel lblTitle;
    private JTextField txtForm;
    private JPasswordField txtPass;
    
    public InputForm() {
         
    }
     
    public InputForm(String title) {
       this.setLayout(new GridLayout(2, 1));
       this.setBackground(Color.white);
       this.setBorder(new EmptyBorder(0, 10, 5, 10));
       this.setPreferredSize(new Dimension(400, 120));
       lblTitle = new JLabel(title);
       txtForm = new JTextField();
       txtForm.setPreferredSize(new Dimension(400,300));
       this.add(lblTitle);
       this.add(txtForm);
    }
    
    public InputForm(String title, String style) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 5, 10));
        this.setPreferredSize(new Dimension(100, 50));
        lblTitle = new JLabel(title);
        this.add(lblTitle);
        if (style.equals("password")) {
            txtPass = new JPasswordField();
            txtPass.setPreferredSize(new Dimension(400,300));
            this.add(txtPass);
        }
    }
    
     public JTextField getTxtForm() {
        return txtForm;
    }

    public void setTxtForm(JTextField txtForm) {
        this.txtForm = txtForm;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JPasswordField txtPass) {
        this.txtPass = txtPass;
    }
    
    public String getText() {
        return txtForm.getText();
    }
    
    public String getPass() {
        return txtPass.getText();
    }
    
    public void setPass(String s) {
        txtPass.setText(s);
    }

    public void setText(String content) {
        txtForm.setText(content);
    }
}
