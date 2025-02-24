package GUI.CRUD;

import BUS.BUS_Topic;
import DTO.DTO_Topic;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class ThemChuDe extends JDialog {

    private BUS_Topic busTopic = new BUS_Topic();
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JButton btnThem;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel pnlTittle;
    private JComboBox<String> jComboBoxParent;
    private JLabel lblTittle;
    private com.raven.suportSwing.TextField txtTopicName;
    private com.raven.suportSwing.TextField txtTopicID;

    public ThemChuDe(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadParentTopics();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        pnlTittle = new JPanel();
        lblTittle = new JLabel();
        jPanel2 = new JPanel();
        txtTopicName = new com.raven.suportSwing.TextField();
        txtTopicID = new com.raven.suportSwing.TextField();
        jComboBoxParent = new JComboBox<>();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        btnThem = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Title Panel
        pnlTittle.setBackground(new Color(255, 255, 255));
        lblTittle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTittle.setForeground(new Color(42, 72, 170));
        lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTittle.setText("CHỦ ĐỀ MỚI");
        pnlTittle.add(lblTittle);

        // Input Panel
        jPanel2.setBackground(new Color(255, 255, 255));

        txtTopicName.setLabelText("Tên chủ đề");
        txtTopicID.setEditable(false); // Auto-generated ID
        txtTopicID.setLabelText("Mã chủ đề");
        txtTopicID.setEnabled(false);  // Make it greyed out
        txtTopicID.setFocusable(false); // Prevent focus

        jLabel1.setText("Tên chủ đề:");
        jLabel2.setText("Mã chủ đề:");
        jLabel3.setText("Chủ đề cha:");

        btnThem.setBackground(new Color(42, 72, 170));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnThem.setForeground(new Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(this::btnThemActionPerformed);

         GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTittle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtTopicName, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(txtTopicID, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxParent, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTittle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTopicName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTopicID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxParent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addContainerGap())
        );

        pack();
    }

    private void loadParentTopics() {
        ArrayList<DTO_Topic> topics = busTopic.getAllData();
        if (topics != null) {
            jComboBoxParent.removeAllItems();
            jComboBoxParent.addItem("None"); //ID 0
            for (DTO_Topic topic : topics) {
                jComboBoxParent.addItem(topic.getTpTitle() + " (ID: " + topic.getTpID() + ")");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Failed to load parent topics.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnThemActionPerformed(ActionEvent evt) {
        String topicName = txtTopicName.getText().trim();
        if (topicName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên chủ đề không được để trống.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int parentID = 0; // Default: no parent (ID 0)
        if (!jComboBoxParent.getSelectedItem().toString().equals("None")) {
            String selectedItem = (String) jComboBoxParent.getSelectedItem();
            try {
                parentID = Integer.parseInt(selectedItem.substring(selectedItem.indexOf("ID: ") + 4).replace(")", ""));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid Parent ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        DTO_Topic newTopic = new DTO_Topic(0, topicName, parentID, 1); // Default status: active
        int result = busTopic.insert(newTopic);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Chủ đề mới đã được thêm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (result == 0) {
            JOptionPane.showMessageDialog(this, "Chủ đề đã tồn tại.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm chủ đề thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ThemChuDe dialog = new ThemChuDe(new JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
}
