/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.CRUD;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import BUS.BUS_Test;
import DTO.DTO_Test;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.BUS_Test;
import BUS.BUS_Topic;
import DTO.DTO_Test;
import DTO.DTO_Topic;

/**
 *
 * @author KIET
 */
public class ThemBaiThi extends javax.swing.JDialog {

    /**
     * Creates new form ThemBaiThi
     */
    BUS_Topic busTopic = new BUS_Topic();
    ArrayList<DTO_Topic> listTopic = busTopic.getAllData();
    BUS_Test busTest = new BUS_Test();
    ArrayList<DTO_Test> listTest = busTest.getAllData();
    private BUS_Topic topicBUS = new BUS_Topic();
    private ArrayList<TopicEntry> topicEntries = new ArrayList<>();
    private DefaultTableModel tableModel;
    private int selectedRow = -1;

    public ThemBaiThi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Tạo model thời gian
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(model);
        jTextField1.setText("09:00:00");
        // Định dạng HH:mm:ss
        JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(editor);

        // Thêm vào frame

        jComboBox1.removeAllItems();
        for (DTO_Topic topic : listTopic) {
            jComboBox1.addItem(topic.getTpID() + " - " + topic.getTpTitle());
        }
        jComboBox1.setSelectedIndex(0);
        // Khởi tạo model cho table
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Mã chủ đề", "Tên chủ đề", "Số câu Dễ", "Số câu Trung bình", "Số câu Khó" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(tableModel);

        // Handler khi chọn một dòng trong bảng
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < topicEntries.size()) {
                    TopicEntry selected = topicEntries.get(selectedRow);

                    // Chọn topic trong combobox
                    for (int i = 0; i < jComboBox1.getItemCount(); i++) {
                        String item = jComboBox1.getItemAt(i);
                        int id = Integer.parseInt(item.split(" - ")[0]);
                        if (id == selected.getTopicId()) {
                            jComboBox1.setSelectedIndex(i);
                            break;
                        }
                    }

                    // Hiển thị số lượng câu hỏi
                    jTextField7.setText(String.valueOf(selected.getNumEasy()));
                    jTextField8.setText(String.valueOf(selected.getNumMedium()));
                    jTextField9.setText(String.valueOf(selected.getNumHard()));
                }
            }
        });

        // custom ô jDateChooser1 với dịnh dạng 2025-02-18
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setDate(Calendar.getInstance().getTime());
        jDateChooser1.setMinSelectableDate(Calendar.getInstance().getTime());
        jDateChooser1.setMaxSelectableDate(null);

        // custom ô jSpinField1 với 08:00:00

        // Căn giữa nội dung các cột trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Áp dụng cho tất cả các cột
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Điều chỉnh độ rộng của các cột
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60); // Mã chủ đề
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150); // Tên chủ đề
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100); // Số câu Dễ
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(130); // Số câu Trung bình
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100); // Số câu Khó
        // Thêm vào cuối constructor ThemBaiThi ngay sau phần code cấu hình bảng

        // Thêm listener cho combobox để reset các trường nhập khi chọn chủ đề mới
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Reset các trường nhập số lượng câu hỏi
                jTextField7.setText("");
                jTextField8.setText("");
                jTextField9.setText("");
            }
        });
    }

    private void updateTable() {
        tableModel.setRowCount(0);

        for (TopicEntry entry : topicEntries) {
            tableModel.addRow(new Object[] {
                    entry.getTopicId(),
                    entry.getTopicName(),
                    entry.getNumEasy(),
                    entry.getNumMedium(),
                    entry.getNumHard()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinField2 = new com.toedter.components.JSpinField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 589));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 72, 170));
        jLabel7.setText("THÊM BÀI THI");

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Chọn chủ đề");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText(" ");

        jLabel5.setText(" ");

        jButton1.setBackground(new java.awt.Color(42, 72, 170));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("XÁC NHẬN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(102, 102, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nhập thời lượng thi: (phút)");

        jComboBox1.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setBackground(new java.awt.Color(102, 102, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Nhập lượt thi:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(102, 102, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Nhập mã code:");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(102, 102, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Nhập tên đề thi:");

        jLabel13.setBackground(new java.awt.Color(102, 102, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Nhập số câu dễ:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(102, 102, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Nhập số câu trung bình:");

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(102, 102, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Nhập số câu khó:");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(102, 102, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Nhập thời gian thi (giờ:phút:giây):");

        jLabel10.setBackground(new java.awt.Color(102, 102, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Chọn ngày thi (năm-tháng-ngày):");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton2.setText("Chọn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null }
                },
                new String[] {
                        "STT", "Mã Thể Loại", "Tên Thể Loại", "Số Câu Dễ", "Số Câu Trung Bình", "Số Câu Khó"
                }));
        jScrollPane1.setViewportView(jTable1);

        jButton4.setBackground(new java.awt.Color(42, 72, 170));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Sửa ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(42, 72, 170));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Xóa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(42, 72, 170));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Thêm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(389, 389, 389)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(351, 351, 351)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField8,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField9,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addComponent(jComboBox1,
                                                                javax.swing.GroupLayout.Alignment.LEADING, 0,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 313,
                                                                Short.MAX_VALUE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jDateChooser1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel10,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextField5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                309,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel11,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                309,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                309,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                309,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel1Layout
                                                                                        .createSequentialGroup()
                                                                                        .addGap(4, 4, 4)
                                                                                        .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                197,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(jButton2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                102,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jLabel9,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        309,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(4, 4, 4))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(38, 38, 38)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 309,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(298, 298, 298)
                                                .addComponent(jLabel7))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(228, 228, 228)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton4)
                                        .addComponent(jButton5)
                                        .addComponent(jButton6))
                                .addGap(600, 600, 600)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel2)
                                                                .addComponent(jLabel5))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel12)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextField6,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel9)
                                                .addGap(8, 8, 8)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jTextField1)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel10)
                                                .addGap(8, 8, 8)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 765,
                                javax.swing.GroupLayout.PREFERRED_SIZE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // Kiểm tra nếu chưa có chủ đề nào được thêm
        if (topicEntries.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm ít nhất một chủ đề", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String testCode = jTextField5.getText().trim();
            String testTitle = jTextField6.getText().trim();
            int testTime = Integer.parseInt(jTextField3.getText().trim());
            int testLimit = Integer.parseInt(jTextField2.getText().trim());

            if (testCode.isEmpty() || testTitle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã bài thi và Tên bài thi", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (testTime <= 0 || testLimit <= 0) {
                JOptionPane.showMessageDialog(this, "Thời gian và số lượt thi phải lớn hơn 0", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            java.util.Date selectedDate = jDateChooser1.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày thi", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            // Phân tích giờ từ jTextField1 (định dạng HH:MM:SS)
            String timeStr = jTextField1.getText().trim();
            try {
                String[] timeParts = timeStr.split(":");
                if (timeParts.length == 3) {
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
                    calendar.set(Calendar.SECOND, Integer.parseInt(timeParts[2]));
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập thời gian theo định dạng HH:MM:SS", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thời gian theo định dạng HH:MM:SS", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Instant instant = calendar.toInstant();
            LocalDateTime testDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            BUS_Test testBUS = new BUS_Test();
            ArrayList<DTO_Test> existingTests = testBUS.getAllData();
            for (DTO_Test existingTest : existingTests) {
                if (existingTest.getTestCode().equals(testCode)) {
                    JOptionPane.showMessageDialog(this, "Mã bài thi đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int successCount = 0;

            for (TopicEntry entry : topicEntries) {
                DTO_Test test = new DTO_Test(
                        0, // ID tự động tạo
                        testCode,
                        testTitle,
                        entry.getTopicId(),
                        testTime,
                        entry.getNumEasy(),
                        entry.getNumMedium(),
                        entry.getNumHard(),
                        testLimit,
                        testDate,
                        1 // Status = 1 (active)
                );

                int result = testBUS.insert(test);
                if (result > 0) {
                    successCount++;
                }
            }

            // Hiển thị kết quả
            if (successCount > 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Đã thêm thành công " + successCount + " bài thi cho " + topicEntries.size() + " chủ đề",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Thêm bài thi thất bại. Vui lòng kiểm tra lại thông tin.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JDialog timeDialog = new JDialog(this, "Chọn Giờ:Phút:Giây", true);
        timeDialog.setLayout(new BorderLayout());
        timeDialog.setSize(300, 150);
        timeDialog.setLocationRelativeTo(this);

        // Create time model with default time of 09:00:00
        SpinnerDateModel timeModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(timeModel);

        // Format spinner to display HH:mm:ss
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        // Set initial value to 09:00:00
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        timeModel.setValue(calendar.getTime());
        JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        spinnerPanel.add(timeSpinner);
        timeDialog.add(spinnerPanel, BorderLayout.CENTER);
        spinnerPanel.add(timeSpinner);
        timeDialog.add(spinnerPanel, BorderLayout.CENTER);

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            // Get selected time
            java.util.Date selectedTime = (java.util.Date) timeSpinner.getValue();

            // Format time as HH:mm:ss
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String formattedTime = formatter.format(selectedTime);

            // Update the text field
            jTextField1.setText(formattedTime);

            // Close dialog
            timeDialog.dispose();
        });

        // Create panel for the OK button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        timeDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog
        timeDialog.setVisible(true);
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chủ đề để sửa", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            // Lấy thông tin từ các trường nhập
            int numEasy = Integer.parseInt(jTextField7.getText().trim());
            int numMedium = Integer.parseInt(jTextField8.getText().trim());
            int numHard = Integer.parseInt(jTextField9.getText().trim());

            // Kiểm tra giá trị hợp lệ
            if (numEasy < 0 || numMedium < 0 || numHard < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng câu hỏi không được âm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra tổng số câu hỏi phải > 0
            if (numEasy + numMedium + numHard <= 0) {
                JOptionPane.showMessageDialog(this, "Phải có ít nhất 1 câu hỏi", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật dữ liệu
            TopicEntry oldEntry = topicEntries.get(selectedRow);
            TopicEntry newEntry = new TopicEntry(
                    oldEntry.getTopicId(),
                    oldEntry.getTopicName(),
                    numEasy,
                    numMedium,
                    numHard);

            topicEntries.set(selectedRow, newEntry);
            updateTable();

            JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin chủ đề", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số lượng câu hỏi", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chủ đề để xóa", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa chủ đề này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            topicEntries.remove(selectedRow);
            updateTable();
            selectedRow = -1;

            // Reset các trường
            jTextField7.setText("0");
            jTextField8.setText("0");
            jTextField9.setText("0");

            JOptionPane.showMessageDialog(this, "Đã xóa chủ đề khỏi danh sách", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }// GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Lấy topic được chọn từ combo box
            String selectedItem = jComboBox1.getSelectedItem().toString();
            int topicId = Integer.parseInt(selectedItem.split(" - ")[0]);
            String topicName = selectedItem.substring(selectedItem.indexOf(" - ") + 3);

            // Lấy số lượng câu hỏi từ các text field
            int numEasy = Integer.parseInt(jTextField7.getText().trim());
            int numMedium = Integer.parseInt(jTextField8.getText().trim());
            int numHard = Integer.parseInt(jTextField9.getText().trim());

            // Kiểm tra giá trị hợp lệ
            if (numEasy < 0 || numMedium < 0 || numHard < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng câu hỏi không được âm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra tổng số câu hỏi phải > 0
            if (numEasy + numMedium + numHard <= 0) {
                JOptionPane.showMessageDialog(this, "Phải có ít nhất 1 câu hỏi", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy thông tin chủ đề từ CSDL để kiểm tra quan hệ cha-con
            DTO_Topic selectedTopic = busTopic.getInfo(topicId);
            if (selectedTopic == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin chủ đề", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra xem chủ đề đã được thêm chưa
            for (int i = 0; i < topicEntries.size(); i++) {
                if (topicEntries.get(i).getTopicId() == topicId) {
                    // Nếu chủ đề đã tồn tại, hiển thị thông báo
                    JOptionPane.showMessageDialog(this,
                            "Chủ đề này đã tồn tại trong bảng.\nNếu muốn thay đổi, hãy chọn chủ đề và sửa số lượng câu hỏi.",
                            "Chủ đề đã tồn tại",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Kiểm tra quan hệ cha-con giữa các chủ đề
                DTO_Topic existingTopic = busTopic.getInfo(topicEntries.get(i).getTopicId());
                if (existingTopic != null) {
                    // Kiểm tra nếu chủ đề mới là cha của chủ đề đã có trong bảng
                    if (existingTopic.getTpParent() == topicId) {
                        JOptionPane.showMessageDialog(this,
                                "Chủ đề \"" + topicName + "\" là cha của chủ đề \"" + topicEntries.get(i).getTopicName()
                                        + "\" đã có trong bảng.\n" +
                                        "Không thể thêm cả cha và con vào cùng bài thi.",
                                "Quan hệ cha-con không hợp lệ",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Kiểm tra nếu chủ đề mới là con của chủ đề đã có trong bảng
                    if (selectedTopic.getTpParent() == existingTopic.getTpID()) {
                        JOptionPane.showMessageDialog(this,
                                "Chủ đề \"" + topicName + "\" là con của chủ đề \"" + topicEntries.get(i).getTopicName()
                                        + "\" đã có trong bảng.\n" +
                                        "Không thể thêm cả cha và con vào cùng bài thi.",
                                "Quan hệ cha-con không hợp lệ",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            // Thêm chủ đề mới vào danh sách
            TopicEntry entry = new TopicEntry(topicId, topicName, numEasy, numMedium, numHard);
            topicEntries.add(entry);

            // Cập nhật bảng
            updateTable();

            // Reset các trường nhập liệu
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");

            JOptionPane.showMessageDialog(this,
                    "Đã thêm chủ đề \"" + topicName + "\" vào danh sách",
                    "Thêm thành công",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số lượng câu hỏi", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemBaiThi dialog = new ThemBaiThi(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.components.JSpinField jSpinField2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
// Thêm vào đầu class ThemBaiThi sau các khai báo biến

// Lớp lưu trữ thông tin cho mỗi mục trong bảng chủ đề
class TopicEntry {
    private int topicId;
    private String topicName;
    private int numEasy;
    private int numMedium;
    private int numHard;

    public TopicEntry(int topicId, String topicName, int numEasy, int numMedium, int numHard) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.numEasy = numEasy;
        this.numMedium = numMedium;
        this.numHard = numHard;
    }

    // Getters
    public int getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getNumEasy() {
        return numEasy;
    }

    public int getNumMedium() {
        return numMedium;
    }

    public int getNumHard() {
        return numHard;
    }
}