package GUI.Component;

import GUI.GUI_Login;
import GUI.Menu.QuanLyCacBaiThi;
import GUI.Menu.QuanLyCauHoi;
import GUI.Menu.QuanLyDeThi;
import GUI.Menu.QuanLyBaiThi;
import GUI.Menu.QuanLyTaiKhoan;
import GUI.Menu.QuanLyChuDe;
import GUI.Menu.QuanLyThongKe;
import GUI.Menu.QuanLyThongTinCaNhan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MenuTaskBar extends JPanel {
    private GUI.GUI_MainFrm main;
    private DTO.DTO_User user;
    private JPanel pnlHeader, pnlCenter;
    private Color DefaultColor = Color.decode("#2a48aa");
    private JScrollPane scrollPane;
    public itemTaskbar[] listitem;
    private boolean isMenuEnabled = true; // Thêm biến boolean để kiểm tra trạng thái vô hiệu hóa

    String[][] listComponent = {
            { "Màn hình chính", "home.svg", "home" },
            { "Ngân hàng câu hỏi", "nganhangcauhoi.svg", "QuanLyCauHoi" },
            { "Chủ đề/Môn học", "monhoc.svg", "monhoc" },
            { "Đề thi", "baithi.svg", "dethi" },
            { "Bài thi", "baithi.svg", "baithi" },
            { "Quản lý tài khoản", "qly.svg", "qltaikhoan" },
            { "Cài đặt tài khoản", "account.svg", "taikhoan" },
            { "Thống kê", "thongke.svg", "thongke" },
            { "Đăng xuất", "logout.svg", "dangxuat" },
    };

    public MenuTaskBar(GUI.GUI_MainFrm main, DTO.DTO_User user) {
        this.main = main;
        this.user = user;
        KhoiTaoMenu();
    }

    public void KhoiTaoMenu() {
        pnlHeader = new JPanel();
        pnlCenter = new JPanel();
        listitem = new itemTaskbar[listComponent.length];
        this.setBackground(DefaultColor);// xanh biển
        this.setLayout(new BorderLayout(0, 0));

        pnlHeader.setPreferredSize(new Dimension(350, 150));
        pnlHeader.setBackground(DefaultColor);
        pnlHeader.setLayout(new FlowLayout(0, 0, 5));
        pnlHeader.setBorder(new EmptyBorder(5, 10, 0, 10));

        headerTaskbar header = new headerTaskbar("admin.svg", "    Xin chào " + user.getUserName());
        pnlHeader.add(header, BorderLayout.CENTER);
        this.add(pnlHeader, BorderLayout.NORTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 300));
        pnlCenter.setLayout(new FlowLayout(0, 0, 5));
        pnlCenter.setBackground(DefaultColor);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 0));
        this.add(scrollPane, BorderLayout.CENTER);

        // khởi tạo danh sách
        for (int i = 0; i < listComponent.length; i++) {
            listitem[i] = new itemTaskbar(listComponent[i][1], listComponent[i][0]);
            pnlCenter.add(listitem[i]);
        }

        listitem[0].setBackground(new Color(192, 192, 192));
        listitem[0].isSelected = true;

        for (itemTaskbar iBtn : listitem)
            iBtn.setVisible(true);

        if (!user.isIsAdmin()) {
            listitem[1].setVisible(false);
            listitem[2].setVisible(false);
            listitem[3].setVisible(false);
            listitem[4].setVisible(false);
            listitem[5].setVisible(false);
            listitem[7].setVisible(false);
        }

        for (int i = 0; i < listComponent.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                        AddHover(evt);
                    }
                }
            });
        }
        // màn hình chính
        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyCacBaiThi qlcbt = new QuanLyCacBaiThi(main, MenuTaskBar.this);
                    main.changePages(qlcbt);
                }
            }
        });

        // quản lý câu hỏi
        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyCauHoi ch = new QuanLyCauHoi(main);
                    main.changePages(ch);
                }
            }
        });
        // chủ đề / môn học
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyChuDe qlcd = new QuanLyChuDe();
                    main.changePages(qlcd);
                }
            }
        });
        // đề thi
        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyDeThi ch = new QuanLyDeThi(main);
                    main.changePages(ch);
                }
            }
        });
        // bài thi
        listitem[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyBaiThi ch = new QuanLyBaiThi(main);
                    main.changePages(ch);
                }
            }
        });

        // quản lý tài khoản
        listitem[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyTaiKhoan qltk = new QuanLyTaiKhoan();
                    main.changePages(qltk);
                }
            }
        });
        // thông tin cá nhân
        listitem[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyThongTinCaNhan qlttcn = new QuanLyThongTinCaNhan(user);
                    main.changePages(qlttcn);
                }
            }
        });
        // thống kê
        listitem[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    QuanLyThongKe thongke = new QuanLyThongKe(main, MenuTaskBar.this);
                    main.changePages(thongke);
                }
            }
        });

        // đăng xuất
        listitem[listitem.length - 1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (isMenuEnabled) { // Kiểm tra trạng thái isMenuEnabled trước khi xử lý sự kiện
                    int input = JOptionPane.showConfirmDialog(null,
                            "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (input == 0) {
                        GUI.GUI_Login login = new GUI_Login();
                        login.setVisible(true);
                        main.dispose();
                    }
                }
            }
        });
    }

    public void AddHover(MouseEvent evt) {
        for (int i = 0; i < listitem.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(new Color(192, 192, 192));
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(new Color(255, 255, 255));
            }
        }
    }

    public void UnableAllMenu() {
        isMenuEnabled = false; // Cập nhật trạng thái isMenuEnabled
        for (itemTaskbar iBtn : listitem) 
            iBtn.setEnabled(false);
        
    }

    public void EnableAllMenu() {
        isMenuEnabled = true; // Cập nhật trạng thái isMenuEnabled
        for (itemTaskbar iBtn : listitem)
            iBtn.setEnabled(true);
        
    }
}
