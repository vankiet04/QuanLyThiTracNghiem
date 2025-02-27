package GUI.Menu;

import BUS.BUS_Topic;
import DTO.DTO_Topic;
import GUI.CRUD.ThemChuDe;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QuanLyChuDe extends JPanel {

    private BUS_Topic busTopic = new BUS_Topic();
    private DefaultTableModel tableModel;
    private JButton btnAdd;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable tblTopics;

    public QuanLyChuDe() {
        initComponents();
        loadTopicData();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tblTopics = new JTable();
        btnAdd = new JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblTopics.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Title", "Parent Topic", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        });
        jScrollPane1.setViewportView(tblTopics);

        btnAdd.setText("Add Topic");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnAdd)
                                                .addGap(0, 0, Short.MAX_VALUE)))
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
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void loadTopicData() {
        SwingUtilities.invokeLater(() -> {
            tableModel = (DefaultTableModel) tblTopics.getModel();
            tableModel.setRowCount(0); // Clear existing rows

            ArrayList<DTO_Topic> topics = busTopic.getAllData();

            if (topics != null) {
                for (DTO_Topic topic : topics) {
                    // Display parent ID directly
                    String parentTitle = String.valueOf(topic.getTpParent());
                    String status = (topic.getTpStatus() == 1) ? "Active" : "Inactive";

                    // Add topic data to the table
                    tableModel.addRow(new Object[]{topic.getTpID(), topic.getTpTitle(), parentTitle, status});
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load topic data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void btnAddActionPerformed(ActionEvent evt) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        ThemChuDe dialog = new ThemChuDe(parentFrame, true);
        dialog.setVisible(true);
        loadTopicData(); // Refresh the table after adding a topic
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.add(new QuanLyChuDe());
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
