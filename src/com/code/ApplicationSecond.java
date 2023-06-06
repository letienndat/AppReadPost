package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationSecond extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 650;
    private static final String sourceImage = "/com/image/";
    private JLayeredPane panelAll;
    private JLayeredPane panelHead;
    private JLayeredPane panelLogo_Head;
    private JLabel labelLogo_Head;
    private JLayeredPane panelTrangChu_Head;
    private JLabel labelTrangChu_Head;
    private JLayeredPane panelAccount_Head;
    private JLabel labelAccount_Head;
    private JLayeredPane panelNganCachTren;
    private String tenTK_DangNhap;
    private JLayeredPane panelTrangChu_Body;
    private JLayeredPane panelDuoi;
    private JLabel labelBanQuyen;
    private JLabel labelTieuDeTrangChu_Body;
    private JLabel labelTieuDeBaiVietTrangChu_Body;
    private JLabel labelThoiGianBaiVietTrangChu_Body;
    private JLabel labelBaiViet1TrangChu_Body;
    private JLabel labelBaiViet2TrangChu_Body;
    private JLayeredPane panelTuyChonAccount;
    private String kiemTraMau = "TrangChu";
    private JLabel labelThongTinCaNhan_TuyChonAccount_Body;
    private JLabel labelDoiMatKhau_TuyChonAccount_Body;
    private JLabel labelDangXuat_TuyChonAccount_Body;
    private String kiemTraMau_TuyChonAccount_Body = "";
    private JLayeredPane panelThongTinCaNhan_Body;
    private JLayeredPane panelDoiMatKhau_Body;
    private JLabel labelTieuDe_ThongTinCaNhan_Body;
    private JLabel labelTenTaiKhoan_ThongTinCaNhan_Body;
    private JLabel labelEmail_ThongTinCaNhan_Body;
    private JTextField textFieldEmail_ThongTinCaNhan_Body;
    private JLabel labelKiemTraEmail_ThongTinCaNhan_Body;
    private JLabel labelSoDienThoai_ThongTinCaNhan_Body;
    private JTextField textFieldSoDienThoai_ThongTinCaNhan_Body;
    private JLabel labelKiemTraSoDienThoai_ThongTinCaNhan_Body;
    private JLabel labelDiaChi_ThongTinCaNhan_Body;
    private JTextField textFieldDiaChi_ThongTinCaNhan_Body;
    private JLabel labelKiemTraDiaChi_ThongTinCaNhan_Body;
    private JButton buttonCapNhat_ThongTinCaNhan;
    private JLabel labelTieuDe_DoiMatKhau_Body;
    private JLabel labelMatKhauHienTai_DoiMatKhau_Body;
    private JPasswordField passwordFieldMatKhauHienTai_DoiMatKhau_Body;
    private JLabel labelKiemTraMatKhauHienTai_DoiMatKhau_Body;
    private JLabel labelMatKhauMoi1_DoiMatKhau_Body;
    private JPasswordField passwordFieldMatKhauMoi1_DoiMatKhau_Body;
    private JLabel labelKiemTraMatKhauMoi1_DoiMatKhau_Body;
    private JLabel labelMatKhauMoi2_DoiMatKhau_Body;
    private JPasswordField passwordFieldMatKhauMoi2_DoiMatKhau_Body;
    private JLabel labelKiemTraMatKhauMoi2_DoiMatKhau_Body;
    private JButton buttonCapNhat_DoiMatKhau;
    private JLabel labelKiemTraDoiMatKhau_DoiMatKhau;
    private Connection connection;
    private Statement statement;

    public ApplicationSecond(String tenTK_DangNhap, Connection connection, Statement statement) {
        super("RosieNguyen [letienndat]");
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "title.png")).getImage());
        setResizable(false);
        getContentPane().setBackground(new Color(232, 230, 230));
        setLocationRelativeTo(null);
        this.tenTK_DangNhap = tenTK_DangNhap;
        this.connection = connection;
        this.statement = statement;

        init();

        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(null);
    }

    public void init() {
        initAll();
        initHead();
        initBody();
        initDuoi();
        initAccount_TuyChon();
        startTrangChu();
    }

    public void initAll() {
        panelAll = new JLayeredPane();
        panelAll.setBounds(0, 0, WIDTH, HEIGHT);
        panelAll.setBackground(new Color(87, 75, 75));
        add(panelAll);
    }

    public void initHead() {
        panelHead = new JLayeredPane();
        panelHead.setBounds(0, 0, WIDTH, 60);
        panelHead.setOpaque(true);
        panelHead.setBackground(new Color(255, 255, 255));
        panelHead.setLayout(null);
        panelHead.setVisible(true);

        panelLogo_Head = new JLayeredPane();
        panelLogo_Head.setBackground(new Color(255, 255, 255));
        panelLogo_Head.setBounds(50, 0, 110, 60);
        panelLogo_Head.setLayout(new BorderLayout());

        labelLogo_Head = new JLabel("Rosie Nguyen");
        labelLogo_Head.setFont(new Font("Raphtalia", Font.PLAIN, 25));
        panelLogo_Head.add(labelLogo_Head);
        panelHead.add(panelLogo_Head);

        panelTrangChu_Head = new JLayeredPane();
        panelTrangChu_Head.setBackground(new Color(255, 255, 255));
        panelTrangChu_Head.setBounds(220, 6, 70, 54);
        panelTrangChu_Head.setLayout(new BorderLayout());

        labelTrangChu_Head = new JLabel("TRANG CHỦ");
        labelTrangChu_Head.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        labelTrangChu_Head.setHorizontalAlignment(JLabel.CENTER);
        labelTrangChu_Head.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startTrangChu();
                endTaiKhoan();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!kiemTraMau.equals("TrangChu")) {
                    labelTrangChu_Head.setForeground(new Color(236, 115, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau.equals("TrangChu")) {
                    labelTrangChu_Head.setForeground(new Color(0, 0, 0));
                }
            }
        });
        labelTrangChu_Head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelTrangChu_Head.add(labelTrangChu_Head, BorderLayout.CENTER);

        panelHead.add(panelTrangChu_Head);

        panelAccount_Head = new JLayeredPane();
        panelAccount_Head.setBackground(new Color(255, 255, 255));
        panelAccount_Head.setBounds(950, 6, /*tenTK_DangNhap.length() * 12 - 30*/ 7 * tenTK_DangNhap.length() + 20, 54);
        panelAccount_Head.setLayout(new BorderLayout());

        labelAccount_Head = new JLabel(tenTK_DangNhap);
        labelAccount_Head.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_default.png")));
        labelAccount_Head.setHorizontalAlignment(JLabel.CENTER);
        labelAccount_Head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelAccount_Head.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_orange.png")));
                labelAccount_Head.setForeground(new Color(236, 115, 10));
                panelTuyChonAccount.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau.equals("TuyChonTaiKhoan")) {
                    labelAccount_Head.setForeground(new Color(0, 0, 0));
                    labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_default.png")));
                }
                panelTuyChonAccount.setVisible(false);
            }
        });
        panelAccount_Head.add(labelAccount_Head, BorderLayout.CENTER);

        panelHead.add(panelAccount_Head);

        panelAll.add(panelHead, Integer.valueOf(0));

        panelNganCachTren = new JLayeredPane();
        panelNganCachTren.setBackground(new Color(170, 166, 166));
        panelNganCachTren.setOpaque(true);
        panelNganCachTren.setBounds(0, 60, WIDTH, 1);
        panelNganCachTren.setLayout(null);

        panelAll.add(panelNganCachTren, Integer.valueOf(0));
    }

    public void initBody() {
        initTrangChu_Body();
        initThongTinTaiKhoan_Body();
        initDoiMatKhau_Body();
    }

    public void initTrangChu_Body() {
        panelTrangChu_Body = new JLayeredPane();
        panelTrangChu_Body.setBounds(0, 61, WIDTH, 550 - 61);
        panelTrangChu_Body.setOpaque(true);
        panelTrangChu_Body.setBackground(new Color(255, 255, 255));
        panelTrangChu_Body.setLayout(null);
        panelTrangChu_Body.setVisible(true);

        labelTieuDeTrangChu_Body = new JLabel("<HTML><U>Rosie Nguyễn</U></HTML>");
        labelTieuDeTrangChu_Body.setFont(new Font("Cambria", Font.PLAIN, 23));
        labelTieuDeTrangChu_Body.setBounds(WIDTH / 2 - 75, 20, 136, 30);
        labelTieuDeTrangChu_Body.setHorizontalAlignment(JLabel.CENTER);
        labelTieuDeTrangChu_Body.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelTieuDeTrangChu_Body.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelTieuDeTrangChu_Body.setForeground(new Color(236, 115, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelTieuDeTrangChu_Body.setForeground(new Color(0, 0, 0));
            }
        });
        panelTrangChu_Body.add(labelTieuDeTrangChu_Body);

        labelTieuDeBaiVietTrangChu_Body = new JLabel("Làm thế nào để thay đổi ai đó");
        labelTieuDeBaiVietTrangChu_Body.setFont(new Font("Cambria", Font.PLAIN, 21));
        labelTieuDeBaiVietTrangChu_Body.setBounds(WIDTH / 2 - 135, 60, 270, 30);
        labelTieuDeBaiVietTrangChu_Body.setHorizontalAlignment(JLabel.CENTER);
        labelTieuDeBaiVietTrangChu_Body.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelTrangChu_Body.add(labelTieuDeBaiVietTrangChu_Body);

        labelThoiGianBaiVietTrangChu_Body = new JLabel("Đã đăng 11/09/2022 bởi attn110601");
        labelThoiGianBaiVietTrangChu_Body.setFont(new Font("Cambria", Font.PLAIN, 13));
        labelThoiGianBaiVietTrangChu_Body.setBounds(0, 88, WIDTH, 30);
        labelThoiGianBaiVietTrangChu_Body.setForeground(new Color(118, 113, 113));
        labelThoiGianBaiVietTrangChu_Body.setHorizontalAlignment(JLabel.CENTER);
        panelTrangChu_Body.add(labelThoiGianBaiVietTrangChu_Body);

        labelBaiViet1TrangChu_Body = new JLabel("<HTML>Nhà mình đang ở tại Wisconsin này là một ngôi nhà cộng đồng gồm các bạn người Mỹ có truyền thống Do Thái. Phần lớn các bạn trong nhà đều ăn chay, dù không phải người theo Do Thái giáo nào cũng ăn chay. Các bạn trong nhà mình ăn chay vì nhiều lý do, nâng cao sức khoẻ, bảo vệ môi trường, phản đối cách đối xử của ngành công nghiệp chăn nuôi tại Mỹ đối với động vật…. Cách để vô ở nhà này cũng hơi lạ chút. Vòng đầu sẽ là nộp đơn đăng ký thành viên, nêu rõ lý do muốn ở đó. Sau đó xếp lịch ghé tới nhà, ăn chung với mọi người trong nhà 3 bữa tối để làm quen và hỏi chuyện nhau. Nếu cả nhà cùng vote đồng ý thì sẽ dọn vào ở. Hồi mình đăng ký vô thì do Covid nên là thay chuyện ăn tối bằng phỏng vấn online.");
        labelBaiViet1TrangChu_Body.setFont(new Font("Cambria", Font.PLAIN, 17));
        labelBaiViet1TrangChu_Body.setBounds(100, 125, WIDTH - 200, 135);
        panelTrangChu_Body.add(labelBaiViet1TrangChu_Body);

        labelBaiViet2TrangChu_Body = new JLabel("<HTML>Nhà này giống như một cộng đồng thu nhỏ. Tụi mình góp tiền vô quỹ chung cho việc ăn uống sinh hoạt mỗi tháng, đồ ăn đa phần là organic. Các đồ đạc nội thất, đồ nhà bếp có sẵn thì dùng chung (trừ đồ riêng của ai tự mua thì người đó giữ). Công việc trong nhà từ nấu ăn, dọn dẹp phòng bếp, lau quét cầu thang, lau chùi tủ lạnh, sắp xếp phòng ăn, phòng khách, phòng đọc sách, không gian sinh hoạt chung, phân loại rác, đến bảo trì ngôi nhà gỗ cả trăm năm tuổi này đều được phân công đều ra từng người. Nhà có lịch trực nhật mỗi ngày và hệ thống tính điểm trực nhật cụ thể, mỗi người đều chịu trách nhiệm bình đẳng như nhau để giữ cho ngôi nhà chạy tốt. Mỗi tuần sẽ có một cuộc họp nhà, nơi mọi người cùng thảo luận về những vấn đề trong cuộc sống chung, ví dụ như sửa chữa hoặc thay thế đồ đạc chung, tìm thành viên mới cho nhà, các vấn đề về an toàn và vệ sinh, các hoạt động văn hoá giáo dục để tăng tính kết nối, hoặc thay đổi những thoả thuận, luật lệ sẵn có. Ai cũng có quyền đóng góp ý kiến, và mọi quyết định được đưa ra đều dựa trên sự đồng thuận của số đông. Nghe thì hơi phức tạp, nhưng khi ở thì có nhiều điều thú vị. Và để một căn nhà lớn nhiều người ở chung hoạt động trơn tru suôn sẻ thì cần có sự hợp tác và chung sức...</HTML>");
        labelBaiViet2TrangChu_Body.setFont(new Font("Cambria", Font.PLAIN, 17));
        labelBaiViet2TrangChu_Body.setBounds(100, 263, WIDTH - 200, 190);
        panelTrangChu_Body.add(labelBaiViet2TrangChu_Body);

        panelAll.add(panelTrangChu_Body, Integer.valueOf(0));
    }

    public void initAccount_TuyChon() {
        panelTuyChonAccount = new JLayeredPane();
        panelTuyChonAccount.setOpaque(true);
        panelTuyChonAccount.setBounds(950 + (7 * tenTK_DangNhap.length() + 20) / 2 - 135 / 2, 60, 135, 110);
        panelTuyChonAccount.setBackground(new Color(248, 248, 248));
        panelTuyChonAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelTuyChonAccount.setVisible(true);
                labelAccount_Head.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_orange.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelTuyChonAccount.setVisible(false);
                labelAccount_Head.setForeground(new Color(0, 0, 0));
                labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_default.png")));
            }
        });
        panelTuyChonAccount.setLayout(null);
        panelTuyChonAccount.setVisible(false);

        labelThongTinCaNhan_TuyChonAccount_Body = new JLabel("• Thông tin cá nhân");
        labelThongTinCaNhan_TuyChonAccount_Body.setFont(new Font("Inter", Font.PLAIN, 11));
        labelThongTinCaNhan_TuyChonAccount_Body.setBounds(10, 15, 108, 15);
        labelThongTinCaNhan_TuyChonAccount_Body.setHorizontalAlignment(JLabel.LEFT);
        labelThongTinCaNhan_TuyChonAccount_Body.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelThongTinCaNhan_TuyChonAccount_Body.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                labelThongTinCaNhan_TuyChonAccount_Body.setForeground(new Color(236, 115, 10));
                panelThongTinCaNhan_Body.setVisible(true);
                panelDoiMatKhau_Body.setVisible(false);
                panelTuyChonAccount.setVisible(false);
                labelTrangChu_Head.setForeground(new Color(0, 0, 0));
                kiemTraMau = "TuyChonTaiKhoan";
                kiemTraMau_TuyChonAccount_Body = "ThongTinCaNhan";
                labelDoiMatKhau_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                labelDangXuat_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                khoiPhucQuenMatKhau();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelThongTinCaNhan_TuyChonAccount_Body.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_orange.png")));
                panelTuyChonAccount.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau_TuyChonAccount_Body.equals("ThongTinCaNhan")) {
                    labelThongTinCaNhan_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                }
            }
        });
        panelTuyChonAccount.add(labelThongTinCaNhan_TuyChonAccount_Body);

        labelDoiMatKhau_TuyChonAccount_Body = new JLabel("• Đổi mật khẩu");
        labelDoiMatKhau_TuyChonAccount_Body.setFont(new Font("Inter", Font.PLAIN, 11));
        labelDoiMatKhau_TuyChonAccount_Body.setBounds(10, 45, 85, 15);
        labelDoiMatKhau_TuyChonAccount_Body.setHorizontalAlignment(JLabel.LEFT);
        labelDoiMatKhau_TuyChonAccount_Body.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelDoiMatKhau_TuyChonAccount_Body.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                labelDoiMatKhau_TuyChonAccount_Body.setForeground(new Color(236, 115, 10));
                panelDoiMatKhau_Body.setVisible(true);
                panelThongTinCaNhan_Body.setVisible(false);
                panelTuyChonAccount.setVisible(false);
                labelTrangChu_Head.setForeground(new Color(0, 0, 0));
                kiemTraMau = "TuyChonTaiKhoan";
                kiemTraMau_TuyChonAccount_Body = "DoiMatKhau";
                labelThongTinCaNhan_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                labelDangXuat_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                khoiPhucThongTinCaNhan();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelDoiMatKhau_TuyChonAccount_Body.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_orange.png")));
                panelTuyChonAccount.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau_TuyChonAccount_Body.equals("DoiMatKhau")) {
                    labelDoiMatKhau_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                }
            }
        });
        panelTuyChonAccount.add(labelDoiMatKhau_TuyChonAccount_Body);

        labelDangXuat_TuyChonAccount_Body = new JLabel("• Đăng xuất");
        labelDangXuat_TuyChonAccount_Body.setFont(new Font("Inter", Font.PLAIN, 11));
        labelDangXuat_TuyChonAccount_Body.setBounds(10, 75, 65, 15);
        labelDangXuat_TuyChonAccount_Body.setHorizontalAlignment(JLabel.LEFT);
        labelDangXuat_TuyChonAccount_Body.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelDangXuat_TuyChonAccount_Body.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(() -> {
                    new ApplicatonFirst(tenTK_DangNhap);
                    dispose();
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelDangXuat_TuyChonAccount_Body.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setForeground(new Color(236, 115, 10));
                labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_orange.png")));
                panelTuyChonAccount.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau_TuyChonAccount_Body.equals("DangXuat")) {
                    labelDangXuat_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
                }
            }
        });
        panelTuyChonAccount.add(labelDangXuat_TuyChonAccount_Body);

        panelAll.add(panelTuyChonAccount, Integer.valueOf(2));
    }

    public void initThongTinTaiKhoan_Body() {
        panelThongTinCaNhan_Body = new JLayeredPane();
        panelThongTinCaNhan_Body.setBounds(0, 61, WIDTH, 550 - 61);
        panelThongTinCaNhan_Body.setOpaque(true);
        panelThongTinCaNhan_Body.setBackground(new Color(255, 255, 255));
        panelThongTinCaNhan_Body.setLayout(null);
        panelThongTinCaNhan_Body.setVisible(false);

        labelTieuDe_ThongTinCaNhan_Body = new JLabel("Thông tin cá nhân");
        labelTieuDe_ThongTinCaNhan_Body.setFont(new Font("Cambria", Font.PLAIN, 21));
        labelTieuDe_ThongTinCaNhan_Body.setBounds(0, 30, WIDTH, 30);
        labelTieuDe_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.CENTER);
        panelThongTinCaNhan_Body.add(labelTieuDe_ThongTinCaNhan_Body);

        labelTenTaiKhoan_ThongTinCaNhan_Body = new JLabel("Tên đăng nhập:   " + tenTK_DangNhap);
        labelTenTaiKhoan_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelTenTaiKhoan_ThongTinCaNhan_Body.setBounds(420, 120, 400, 30);
        panelThongTinCaNhan_Body.add(labelTenTaiKhoan_ThongTinCaNhan_Body);

        labelEmail_ThongTinCaNhan_Body = new JLabel("Email:   ");
        labelEmail_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelEmail_ThongTinCaNhan_Body.setBounds(420, 165, 100, 30);
        labelEmail_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.RIGHT);
        panelThongTinCaNhan_Body.add(labelEmail_ThongTinCaNhan_Body);

        textFieldEmail_ThongTinCaNhan_Body = new JTextField(50);
        textFieldEmail_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        textFieldEmail_ThongTinCaNhan_Body.setBounds(520, 165, 300, 30);
        textFieldEmail_ThongTinCaNhan_Body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (kiemTraEmail_ThongTinCaNhan_Body() == -1) {
                        labelKiemTraEmail_ThongTinCaNhan_Body.setText("Không đúng định dạng Email!");
                    } else if (kiemTraEmail_ThongTinCaNhan_Body() == 1) {
                        capNhatEmail_ThongTinCaNhan();
                    }

                    if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == -1) {
                        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("Không đúng định dạng số điện thoại!");
                    } else if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == 1) {
                        capNhatSoDienThoai_ThongTinCaNhan();
                    }

                    if (kiemTraDiaChi_ThongTinCaNhan_Body() == 1) {
                        capNhatDiaChi_ThongTinCaNhan();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraEmail_ThongTinCaNhan_Body.setText("");
                }
            }
        });
        panelThongTinCaNhan_Body.add(textFieldEmail_ThongTinCaNhan_Body);

        labelKiemTraEmail_ThongTinCaNhan_Body = new JLabel("");
        labelKiemTraEmail_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelKiemTraEmail_ThongTinCaNhan_Body.setBounds(830, 165, 250, 30);
        labelKiemTraEmail_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.LEFT);
        panelThongTinCaNhan_Body.add(labelKiemTraEmail_ThongTinCaNhan_Body);

        labelSoDienThoai_ThongTinCaNhan_Body = new JLabel("Số điện thoại:   ");
        labelSoDienThoai_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelSoDienThoai_ThongTinCaNhan_Body.setBounds(420, 215, 100, 30);
        labelSoDienThoai_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.RIGHT);
        panelThongTinCaNhan_Body.add(labelSoDienThoai_ThongTinCaNhan_Body);

        textFieldSoDienThoai_ThongTinCaNhan_Body = new JTextField(50);
        textFieldSoDienThoai_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        textFieldSoDienThoai_ThongTinCaNhan_Body.setBounds(520, 215, 300, 30);
        textFieldSoDienThoai_ThongTinCaNhan_Body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (kiemTraEmail_ThongTinCaNhan_Body() == -1) {
                        labelKiemTraEmail_ThongTinCaNhan_Body.setText("Không đúng định dạng Email!");
                    } else if (kiemTraEmail_ThongTinCaNhan_Body() == 1) {
                        capNhatEmail_ThongTinCaNhan();
                    }

                    if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == -1) {
                        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("Không đúng định dạng số điện thoại!");
                    } else if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == 1) {
                        capNhatSoDienThoai_ThongTinCaNhan();
                    }

                    if (kiemTraDiaChi_ThongTinCaNhan_Body() == 1) {
                        capNhatDiaChi_ThongTinCaNhan();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("");
                }
            }
        });
        panelThongTinCaNhan_Body.add(textFieldSoDienThoai_ThongTinCaNhan_Body);

        labelKiemTraSoDienThoai_ThongTinCaNhan_Body = new JLabel("");
        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setBounds(830, 215, 250, 30);
        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.LEFT);
        panelThongTinCaNhan_Body.add(labelKiemTraSoDienThoai_ThongTinCaNhan_Body);

        labelDiaChi_ThongTinCaNhan_Body = new JLabel("Địa chỉ:   ");
        labelDiaChi_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelDiaChi_ThongTinCaNhan_Body.setBounds(420, 265, 100, 30);
        labelDiaChi_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.RIGHT);
        panelThongTinCaNhan_Body.add(labelDiaChi_ThongTinCaNhan_Body);

        textFieldDiaChi_ThongTinCaNhan_Body = new JTextField(50);
        textFieldDiaChi_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        textFieldDiaChi_ThongTinCaNhan_Body.setBounds(520, 265, 300, 30);
        textFieldDiaChi_ThongTinCaNhan_Body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (kiemTraEmail_ThongTinCaNhan_Body() == -1) {
                        labelKiemTraEmail_ThongTinCaNhan_Body.setText("Không đúng định dạng Email!");
                    } else if (kiemTraEmail_ThongTinCaNhan_Body() == 1) {
                        capNhatEmail_ThongTinCaNhan();
                    }

                    if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == -1) {
                        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("Không đúng định dạng số điện thoại!");
                    } else if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == 1) {
                        capNhatSoDienThoai_ThongTinCaNhan();
                    }

                    if (kiemTraDiaChi_ThongTinCaNhan_Body() == 1) {
                        capNhatDiaChi_ThongTinCaNhan();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDiaChi_ThongTinCaNhan_Body.setText("");
                }
            }
        });
        panelThongTinCaNhan_Body.add(textFieldDiaChi_ThongTinCaNhan_Body);

        dienThongTinCaNhan();

        labelKiemTraDiaChi_ThongTinCaNhan_Body = new JLabel("");
        labelKiemTraDiaChi_ThongTinCaNhan_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelKiemTraDiaChi_ThongTinCaNhan_Body.setBounds(830, 265, 250, 30);
        labelKiemTraDiaChi_ThongTinCaNhan_Body.setHorizontalAlignment(JLabel.LEFT);
        panelThongTinCaNhan_Body.add(labelKiemTraDiaChi_ThongTinCaNhan_Body);

        buttonCapNhat_ThongTinCaNhan = new JButton("CẬP NHẬT");
        buttonCapNhat_ThongTinCaNhan.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        buttonCapNhat_ThongTinCaNhan.setBounds(WIDTH / 2 - 50, 350, 100, 30);
        buttonCapNhat_ThongTinCaNhan.setHorizontalAlignment(JLabel.RIGHT);
        buttonCapNhat_ThongTinCaNhan.setBackground(new Color(0, 0, 0));
        buttonCapNhat_ThongTinCaNhan.setForeground(new Color(255, 255, 255));
        buttonCapNhat_ThongTinCaNhan.setHorizontalAlignment(JButton.CENTER);
        buttonCapNhat_ThongTinCaNhan.setBorderPainted(false);
        buttonCapNhat_ThongTinCaNhan.setFocusPainted(false);
        buttonCapNhat_ThongTinCaNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonCapNhat_ThongTinCaNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonCapNhat_ThongTinCaNhan.setBackground(new Color(236, 115, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonCapNhat_ThongTinCaNhan.setBackground(new Color(0, 0, 0));
            }
        });
        buttonCapNhat_ThongTinCaNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kiemTraEmail_ThongTinCaNhan_Body() == -1) {
                    labelKiemTraEmail_ThongTinCaNhan_Body.setText("Không đúng định dạng Email!");
                } else if (kiemTraEmail_ThongTinCaNhan_Body() == 1) {
                    capNhatEmail_ThongTinCaNhan();
                }

                if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == -1) {
                    labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("Không đúng định dạng số điện thoại!");
                } else if (kiemTraSoDienThoai_ThongTinCaNhan_Body() == 1) {
                    capNhatSoDienThoai_ThongTinCaNhan();
                }

                if (kiemTraDiaChi_ThongTinCaNhan_Body() == 1) {
                    capNhatDiaChi_ThongTinCaNhan();
                }
            }
        });
        panelThongTinCaNhan_Body.add(buttonCapNhat_ThongTinCaNhan);

        panelAll.add(panelThongTinCaNhan_Body, Integer.valueOf(1));
    }

    public void initDoiMatKhau_Body() {
        panelDoiMatKhau_Body = new JLayeredPane();
        panelDoiMatKhau_Body.setBounds(0, 61, WIDTH, 550 - 61);
        panelDoiMatKhau_Body.setOpaque(true);
        panelDoiMatKhau_Body.setBackground(new Color(255, 255, 255));
        panelDoiMatKhau_Body.setLayout(null);
        panelDoiMatKhau_Body.setVisible(false);

        labelTieuDe_DoiMatKhau_Body = new JLabel("Đổi mật khẩu");
        labelTieuDe_DoiMatKhau_Body.setFont(new Font("Cambria", Font.PLAIN, 21));
        labelTieuDe_DoiMatKhau_Body.setBounds(0, 30, WIDTH, 30);
        labelTieuDe_DoiMatKhau_Body.setHorizontalAlignment(JLabel.CENTER);
        panelDoiMatKhau_Body.add(labelTieuDe_DoiMatKhau_Body);

        labelMatKhauHienTai_DoiMatKhau_Body = new JLabel("Mật khẩu hiện tại:   ");
        labelMatKhauHienTai_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelMatKhauHienTai_DoiMatKhau_Body.setBounds(300, 120, 170, 30);
        labelMatKhauHienTai_DoiMatKhau_Body.setHorizontalAlignment(JLabel.RIGHT);
        panelDoiMatKhau_Body.add(labelMatKhauHienTai_DoiMatKhau_Body);

        passwordFieldMatKhauHienTai_DoiMatKhau_Body = new JPasswordField(50);
        passwordFieldMatKhauHienTai_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        passwordFieldMatKhauHienTai_DoiMatKhau_Body.setBounds(470, 120, 300, 30);
        passwordFieldMatKhauHienTai_DoiMatKhau_Body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhauTrungKhop_DoiMatKhau()) {
                    labelKiemTraMatKhauHienTai_DoiMatKhau_Body.setText("Trùng khớp!");
                } else {
                    labelKiemTraMatKhauHienTai_DoiMatKhau_Body.setText("Không trùng khớp!");
                }
                if (labelKiemTraMatKhauHienTai_DoiMatKhau_Body.getText().equals("Trùng khớp!") &&
                        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.getText().equals("Thỏa mãn!")) {
                    buttonCapNhat_DoiMatKhau.setEnabled(true);
                } else {
                    buttonCapNhat_DoiMatKhau.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDoiMatKhau_DoiMatKhau.setText("");
                }
            }
        });
        panelDoiMatKhau_Body.add(passwordFieldMatKhauHienTai_DoiMatKhau_Body);

        labelKiemTraMatKhauHienTai_DoiMatKhau_Body = new JLabel("");
        labelKiemTraMatKhauHienTai_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelKiemTraMatKhauHienTai_DoiMatKhau_Body.setBounds(780, 120, 250, 30);
        labelKiemTraMatKhauHienTai_DoiMatKhau_Body.setHorizontalAlignment(JLabel.LEFT);
        panelDoiMatKhau_Body.add(labelKiemTraMatKhauHienTai_DoiMatKhau_Body);

        labelMatKhauMoi1_DoiMatKhau_Body = new JLabel("Mật khẩu mới:   ");
        labelMatKhauMoi1_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelMatKhauMoi1_DoiMatKhau_Body.setBounds(300, 165, 170, 30);
        labelMatKhauMoi1_DoiMatKhau_Body.setHorizontalAlignment(JLabel.RIGHT);
        panelDoiMatKhau_Body.add(labelMatKhauMoi1_DoiMatKhau_Body);

        passwordFieldMatKhauMoi1_DoiMatKhau_Body = new JPasswordField(50);
        passwordFieldMatKhauMoi1_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        passwordFieldMatKhauMoi1_DoiMatKhau_Body.setBounds(470, 165, 300, 30);
        passwordFieldMatKhauMoi1_DoiMatKhau_Body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhauMoi1_QuenMatKhau() == 0) {
                    labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setText("Yêu cầu A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự!");
                    if (kiemTraMatKhauMoi2_QuenMatKhau() != 0) {
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Mật khẩu không trùng khớp!");
                    }
                } else if (kiemTraMatKhauMoi1_QuenMatKhau() == -1) {
                    labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setText("Trùng mật khẩu hiện tại!");
                    if (kiemTraMatKhauMoi2_QuenMatKhau() == 1) {
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Thỏa mãn!");
                    }
                    if (kiemTraMatKhauMoi2_QuenMatKhau() == -1) {
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Mật khẩu không trùng khớp!");
                    }
                } else if (kiemTraMatKhauMoi1_QuenMatKhau() == 1) {
                    labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setText("Thỏa mãn!");
                    if (kiemTraMatKhauMoi2_QuenMatKhau() == 1) {
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Thỏa mãn!");
                    }
                    if (kiemTraMatKhauMoi2_QuenMatKhau() == -1) {
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Mật khẩu không trùng khớp!");
                    }
                }
                if (labelKiemTraMatKhauHienTai_DoiMatKhau_Body.getText().equals("Trùng khớp!") &&
                        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.getText().equals("Thỏa mãn!")) {
                    buttonCapNhat_DoiMatKhau.setEnabled(true);
                } else {
                    buttonCapNhat_DoiMatKhau.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDoiMatKhau_DoiMatKhau.setText("");
                }
            }
        });
        panelDoiMatKhau_Body.add(passwordFieldMatKhauMoi1_DoiMatKhau_Body);

        labelKiemTraMatKhauMoi1_DoiMatKhau_Body = new JLabel("");
        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setBounds(780, 165, 250, 30);
        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setHorizontalAlignment(JLabel.LEFT);
        panelDoiMatKhau_Body.add(labelKiemTraMatKhauMoi1_DoiMatKhau_Body);

        labelMatKhauMoi2_DoiMatKhau_Body = new JLabel("Nhập lại mật khẩu mới:   ");
        labelMatKhauMoi2_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelMatKhauMoi2_DoiMatKhau_Body.setBounds(300, 210, 170, 30);
        labelMatKhauMoi2_DoiMatKhau_Body.setHorizontalAlignment(JLabel.RIGHT);
        panelDoiMatKhau_Body.add(labelMatKhauMoi2_DoiMatKhau_Body);

        passwordFieldMatKhauMoi2_DoiMatKhau_Body = new JPasswordField(50);
        passwordFieldMatKhauMoi2_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        passwordFieldMatKhauMoi2_DoiMatKhau_Body.setBounds(470, 210, 300, 30);
        passwordFieldMatKhauMoi2_DoiMatKhau_Body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhauMoi2_QuenMatKhau() == 0) {
                    labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Không được bỏ trống!");
                } else if (kiemTraMatKhauMoi2_QuenMatKhau() == -1) {
                    labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Mật khẩu không trùng khớp!");
                } else if (kiemTraMatKhauMoi2_QuenMatKhau() == 1) {
                    labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("Thỏa mãn!");
                }
                if (labelKiemTraMatKhauHienTai_DoiMatKhau_Body.getText().equals("Trùng khớp!") &&
                        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.getText().equals("Thỏa mãn!")) {
                    buttonCapNhat_DoiMatKhau.setEnabled(true);
                } else {
                    buttonCapNhat_DoiMatKhau.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDoiMatKhau_DoiMatKhau.setText("");
                }
            }
        });
        panelDoiMatKhau_Body.add(passwordFieldMatKhauMoi2_DoiMatKhau_Body);

        labelKiemTraMatKhauMoi2_DoiMatKhau_Body = new JLabel("");
        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 13));
        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setBounds(780, 210, 250, 30);
        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setHorizontalAlignment(JLabel.LEFT);
        panelDoiMatKhau_Body.add(labelKiemTraMatKhauMoi2_DoiMatKhau_Body);

        buttonCapNhat_DoiMatKhau = new JButton("CẬP NHẬT");
        buttonCapNhat_DoiMatKhau.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        buttonCapNhat_DoiMatKhau.setBounds(WIDTH / 2 - 50, 290, 100, 30);
        buttonCapNhat_DoiMatKhau.setHorizontalAlignment(JLabel.RIGHT);
        buttonCapNhat_DoiMatKhau.setBackground(new Color(0, 0, 0));
        buttonCapNhat_DoiMatKhau.setForeground(new Color(255, 255, 255));
        buttonCapNhat_DoiMatKhau.setHorizontalAlignment(JButton.CENTER);
        buttonCapNhat_DoiMatKhau.setBorderPainted(false);
        buttonCapNhat_DoiMatKhau.setFocusPainted(false);
        buttonCapNhat_DoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonCapNhat_DoiMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (labelKiemTraMatKhauHienTai_DoiMatKhau_Body.getText().equals("Trùng khớp!") &&
                        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.getText().equals("Thỏa mãn!")) {
                    buttonCapNhat_DoiMatKhau.setBackground(new Color(236, 115, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (labelKiemTraMatKhauHienTai_DoiMatKhau_Body.getText().equals("Trùng khớp!") &&
                        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.getText().equals("Thỏa mãn!")) {
                    buttonCapNhat_DoiMatKhau.setBackground(new Color(0, 0, 0));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                doiMatKhau_DoiMatKhau();
            }
        });
        buttonCapNhat_DoiMatKhau.setEnabled(false);
        panelDoiMatKhau_Body.add(buttonCapNhat_DoiMatKhau);

        labelKiemTraDoiMatKhau_DoiMatKhau = new JLabel("");
        labelKiemTraDoiMatKhau_DoiMatKhau.setFont(new Font("Cambria", Font.PLAIN, 13));
        labelKiemTraDoiMatKhau_DoiMatKhau.setBounds(0, 360, WIDTH, 20);
        labelKiemTraDoiMatKhau_DoiMatKhau.setHorizontalAlignment(JLabel.CENTER);
        panelDoiMatKhau_Body.add(labelKiemTraDoiMatKhau_DoiMatKhau);

        panelAll.add(panelDoiMatKhau_Body, Integer.valueOf(1));
    }

    public void initDuoi() {
        panelDuoi = new JLayeredPane();
        panelDuoi.setOpaque(true);
        panelDuoi.setBackground(new Color(232, 230, 230));
        panelDuoi.setBounds(0, 550, WIDTH, HEIGHT - 550);
        panelDuoi.setLayout(null);

        labelBanQuyen = new JLabel("Copyright of letienndat");
        labelBanQuyen.setFont(new Font("Inter", Font.PLAIN, 10));
        labelBanQuyen.setBounds(0, 20, WIDTH, 20);
        labelBanQuyen.setHorizontalAlignment(JLabel.CENTER);
        panelDuoi.add(labelBanQuyen);

        panelAll.add(panelDuoi, Integer.valueOf(0));
    }

    public void startTrangChu() {
        labelTrangChu_Head.setForeground(new Color(236, 115, 10));
        panelTrangChu_Body.setVisible(true);
        kiemTraMau = "TrangChu";
    }

    public void endTrangChu() {
        labelTrangChu_Head.setForeground(new Color(0, 0, 0));
        panelTrangChu_Body.setVisible(false);
    }

    public void startTaiKhoan() {
        labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_orange.png")));
        labelAccount_Head.setForeground(new Color(236, 115, 10));
        kiemTraMau = "TenTK";
        panelTuyChonAccount.setVisible(true);
    }

    public void endTaiKhoan() {
        labelAccount_Head.setIcon(new ImageIcon(ApplicationSecond.class.getResource(sourceImage + "account_default.png")));
        labelAccount_Head.setForeground(new Color(0, 0, 0));
        labelThongTinCaNhan_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
        labelDoiMatKhau_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
        labelDangXuat_TuyChonAccount_Body.setForeground(new Color(0, 0, 0));
        panelThongTinCaNhan_Body.setVisible(false);
        panelDoiMatKhau_Body.setVisible(false);
        kiemTraMau_TuyChonAccount_Body = "";
        khoiPhucThongTinCaNhan();
        khoiPhucQuenMatKhau();
    }

    public int kiemTraEmail_ThongTinCaNhan_Body() {
        if (textFieldEmail_ThongTinCaNhan_Body.getText().length() == 0) {
            return 1;
        }
        if (!textFieldEmail_ThongTinCaNhan_Body.getText().matches("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$")) {
            return -1;
        }
        return 1;
    }

    public int kiemTraSoDienThoai_ThongTinCaNhan_Body() {
        if (textFieldSoDienThoai_ThongTinCaNhan_Body.getText().length() == 0) {
            return 1;
        }
        if (!textFieldSoDienThoai_ThongTinCaNhan_Body.getText().matches("^[0-9]{9,15}$")) {
            return -1;
        }
        return 1;
    }

    public int kiemTraDiaChi_ThongTinCaNhan_Body() {
        return 1;
    }

    public void dienThongTinCaNhan() {
        try {
            String sql = "SELECT Email, SoDienThoai, DiaChi FROM ThongTinCaNhan WHERE TenTK = '" + this.tenTK_DangNhap + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            textFieldEmail_ThongTinCaNhan_Body.setText(resultSet.getString("Email"));
            textFieldSoDienThoai_ThongTinCaNhan_Body.setText(resultSet.getString("SoDienThoai"));
            textFieldDiaChi_ThongTinCaNhan_Body.setText(resultSet.getString("DiaChi"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void capNhatEmail_ThongTinCaNhan() {
        try {
            String sql = "UPDATE ThongTinCaNhan SET Email = '" + textFieldEmail_ThongTinCaNhan_Body.getText() + "' WHERE TenTK = '" + this.tenTK_DangNhap + "'";
            statement.executeUpdate(sql);
            labelKiemTraEmail_ThongTinCaNhan_Body.setText("Đã cập nhật thông tin!");
        } catch (SQLException e) {
            labelKiemTraEmail_ThongTinCaNhan_Body.setText("Đã có lỗi khi cập nhật thông tin!");
            e.printStackTrace();
        }
    }

    public void capNhatSoDienThoai_ThongTinCaNhan() {
        try {
            String sql = "UPDATE ThongTinCaNhan SET SoDienThoai = '" + textFieldSoDienThoai_ThongTinCaNhan_Body.getText() + "' WHERE TenTK = '" + this.tenTK_DangNhap + "'";
            statement.executeUpdate(sql);
            labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("Đã cập nhật thông tin!");
        } catch (SQLException e) {
            labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("Đã có lỗi khi cập nhật thông tin!");
            e.printStackTrace();
        }
    }

    public void capNhatDiaChi_ThongTinCaNhan() {
        try {
            String sql = "UPDATE ThongTinCaNhan SET DiaChi = '" + textFieldDiaChi_ThongTinCaNhan_Body.getText() + "' WHERE TenTK = '" + this.tenTK_DangNhap + "'";
            statement.executeUpdate(sql);
            labelKiemTraDiaChi_ThongTinCaNhan_Body.setText("Đã cập nhật thông tin!");
        } catch (SQLException e) {
            labelKiemTraDiaChi_ThongTinCaNhan_Body.setText("Đã có lỗi khi cập nhật thông tin!");
            e.printStackTrace();
        }
    }

    public void khoiPhucThongTinCaNhan() {
        labelKiemTraEmail_ThongTinCaNhan_Body.setText("");
        labelKiemTraSoDienThoai_ThongTinCaNhan_Body.setText("");
        labelKiemTraDiaChi_ThongTinCaNhan_Body.setText("");
    }

    public void khoiPhucQuenMatKhau() {
        passwordFieldMatKhauHienTai_DoiMatKhau_Body.setText("");
        passwordFieldMatKhauMoi1_DoiMatKhau_Body.setText("");
        passwordFieldMatKhauMoi2_DoiMatKhau_Body.setText("");
        labelKiemTraMatKhauHienTai_DoiMatKhau_Body.setText("");
        labelKiemTraMatKhauMoi1_DoiMatKhau_Body.setText("");
        labelKiemTraMatKhauMoi2_DoiMatKhau_Body.setText("");
        labelKiemTraDoiMatKhau_DoiMatKhau.setText("");
    }

    public boolean kiemTraMatKhauTrungKhop_DoiMatKhau() {
        try {
            String sql = "SELECT MatKhau FROM taikhoan_matkhau WHERE TenTK = '" + this.tenTK_DangNhap + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            if (resultSet.getString("MatKhau").equals(passwordFieldMatKhauHienTai_DoiMatKhau_Body.getText()))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int kiemTraMatKhauMoi1_QuenMatKhau() {
        if (!passwordFieldMatKhauMoi1_DoiMatKhau_Body.getText().matches("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")) {
            return 0; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự
        }
        if (passwordFieldMatKhauMoi1_DoiMatKhau_Body.getText().equals(passwordFieldMatKhauHienTai_DoiMatKhau_Body.getText())) {
            return -1; // Trùng mật khẩu hiện tại
        }
        return 1; // Mật khẩu 1 thỏa mãn
    }

    public int kiemTraMatKhauMoi2_QuenMatKhau() {
        if (passwordFieldMatKhauMoi2_DoiMatKhau_Body.getText().length() == 0) {
            return 0; // Bỏ trống mật khẩu 2
        }
        if (kiemTraMatKhauMoi1_QuenMatKhau() == 1) {
            if (passwordFieldMatKhauMoi2_DoiMatKhau_Body.getText().equals(passwordFieldMatKhauMoi1_DoiMatKhau_Body.getText())) {
                return 1; // Mật khẩu 2 trùng khớp với mật khẩu 1
            }
        }
        return -1; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự hoặc không trùng khớp với mật khẩu 1
    }

    public void doiMatKhau_DoiMatKhau() {
        try {
            String sql = "UPDATE taikhoan_matkhau SET MatKhau = '" + passwordFieldMatKhauMoi1_DoiMatKhau_Body.getText() + "' WHERE TenTK = '" + this.tenTK_DangNhap + "'";
            statement.executeUpdate(sql);
            labelKiemTraDoiMatKhau_DoiMatKhau.setText("Đổi mật khẩu thành công!");
        } catch (SQLException e) {
            labelKiemTraDoiMatKhau_DoiMatKhau.setText("Đổi mật khẩu không thành công!");
            e.printStackTrace();
        }
    }
}
