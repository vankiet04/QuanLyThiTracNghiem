package GUI.CRUD;

import DTO.DTO_Topic;
import BUS.BUS_Topic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThemChuDe extends javax.swing.JDialog {

    private BUS_Topic busTopic = new BUS_Topic(); // Business logic layer

    // Components declaration
    private JPanel pnlTitle, pnlContent;
    private JLabel lblTitle, lblTopicName, lblTopicID, lblParentTopic;
    private JTextField txtTopicName, txtTopicID;
    private JComboBox<String> cbParentTopic;
    private JButton btnAdd;

    public ThemChuDe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadParentTopics(); // Populate parent topics in ComboBox
        pack(); // Adjust size to fit components
        setLocationRelativeTo(null); // Center dialog on screen
        setVisible(true); // Show dialog
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Title Panel
        pnlTitle = new JPanel();
        pnlTitle.setBackground(new Color(42, 72, 170));
        lblTitle = new JLabel("CHỦ ĐỀ MỚI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.add(lblTitle, BorderLayout.CENTER);

        // Content Panel
        pnlContent = new JPanel();
        pnlContent.setBackground(Color.WHITE);
        pnlContent.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Topic ID (Auto-generated)
        lblTopicID = new JLabel("Mã chủ đề:");
        txtTopicID = new JTextField();
        txtTopicID.setEditable(false); // Auto-generated ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlContent.add(lblTopicID, gbc);
        gbc.gridx = 1;
        pnlContent.add(txtTopicID, gbc);

        // Topic Name
        lblTopicName = new JLabel("Tên chủ đề:");
        txtTopicName = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlContent.add(lblTopicName, gbc);
        gbc.gridx = 1;
        pnlContent.add(txtTopicName, gbc);

        // Parent Topic
        lblParentTopic = new JLabel("Chủ đề cha:");
        cbParentTopic = new JComboBox<>();
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlContent.add(lblParentTopic, gbc);
        gbc.gridx = 1;
        pnlContent.add(cbParentTopic, gbc);

        // Add Button
        btnAdd = new JButton("Thêm");
        btnAdd.setBackground(new Color(42, 72, 170));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnAdd.addActionListener(evt -> btnAddActionPerformed(evt));
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        pnlContent.add(btnAdd, gbc);

        // Add Panels to Dialog
        setLayout(new BorderLayout());
        add(pnlTitle, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void loadParentTopics() {
        ArrayList<DTO_Topic> topics = busTopic.getAllData();

        if (topics != null) {
            cbParentTopic.removeAllItems(); // Clear existing items
            for (DTO_Topic topic : topics) {
                cbParentTopic.addItem(topic.getTpTitle() + " (ID: " + topic.getTpID() + ")");
            }
            cbParentTopic.addItem("None"); // Option for no parent
            cbParentTopic.setSelectedItem("None");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to load parent topics.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            cbParentTopic.addItem("None");
            cbParentTopic.setSelectedItem("None");
        }
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        String topicName = txtTopicName.getText().trim();

        if (topicName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Tên chủ đề không được để trống.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int parentID = -1; // Default: no parent
        if (!cbParentTopic.getSelectedItem().toString().equals("None")) {
            String selectedItem = (String) cbParentTopic.getSelectedItem();
            parentID = Integer.parseInt(selectedItem.substring(selectedItem.indexOf("ID: ") + 4).replace(")", ""));
        }

        DTO_Topic newTopic = new DTO_Topic(0, topicName, parentID, 1); // Default status: active
       
       int result=busTopic.insert(newTopic); 
       if(result==0){
	JOptionPane.showMessageDialog(this,"Thêm chủ đề thành công.","Thông báo",JOptionPane.INFORMATION_MESSAGE);
    this.dispose();
       }
       else{
    JOptionPane.showMessageDialog(this,"Thêm chủ đề thất bại.","Lỗi",JOptionPane.ERROR_MESSAGE);
       }
    }
}
