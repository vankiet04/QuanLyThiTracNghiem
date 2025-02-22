package GUI.Menu;

import BUS.BUS_Topic;
import DTO.DTO_Topic;
import GUI.CRUD.ThemChuDe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QuanLyChuDe extends javax.swing.JPanel {

    private BUS_Topic busTopic = new BUS_Topic(); // Business logic layer
    private DefaultTableModel tableModel; // Table model for the topic list

    public QuanLyChuDe() {
        initComponents();
        loadTopicData(); // Load data into the table
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTopics = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblTopics.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Title", "Parent Topic", "Status"}
        ));
        jScrollPane1.setViewportView(tblTopics);

        btnAdd.setText("Add Topic");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        // Layout setup
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAdd)
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void loadTopicData() {
        tableModel = (DefaultTableModel) tblTopics.getModel();
        tableModel.setRowCount(0); // Clear existing rows

        ArrayList<DTO_Topic> topics = busTopic.getAllData();

        if (topics != null) {
            for (DTO_Topic topic : topics) {
                String parentTitle = (topic.getTpParent() == -1) 
                                    ? "None" 
                                    : busTopic.getInfo(topic.getTpParent()).getTpTitle();
                String status = (topic.getTpStatus() == 1) ? "Active" : "Inactive";
                tableModel.addRow(new Object[]{topic.getTpID(), topic.getTpTitle(), parentTitle, status});
            }
        }
    }

    private void btnAddActionPerformed(ActionEvent evt) {
        // Open the Add Topic dialog
        ThemChuDe dialog = new ThemChuDe(new JFrame(), true);
        dialog.setVisible(true);

        // Refresh data after adding a topic
        loadTopicData();
    }

    // Variables declaration
    private javax.swing.JButton btnAdd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTopics;
}
