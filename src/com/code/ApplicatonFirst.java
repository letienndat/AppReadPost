package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ApplicatonFirst extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/" + InfoConnectDatabase.DATABASE_NAME + "?characterEncoding=utf8";
    private static final String USER = InfoConnectDatabase.USENAME;
    private static final String PASSWORD = InfoConnectDatabase.PASSWORD;
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 600;
    private static final String sourceImage = "/com/image/";
    private JPanel panelHead;
    private JPanel panelLogo_Head;
    private JLabel labelLogo_Head;
    private JPanel panelDangNhap_Head;
    private JLabel labelDangNhap_Head;
    private JPanel panelDangKy_Head;
    private JLabel labelDangKy_Head;
    private JPanel panelNganCachTren_DangNhap;
    private JPanel panelDangNhap_Body;
    private JLabel labelTieuDeDangNhap_Body;
    private JLabel labelTieuDeTenTaiKhoan_DangNhap;
    private JTextField textFieldTenTK_DangNhap;
    private JLabel labelKiemTraTenTK_DangNhap;
    private JLabel labelTieuDeMatKhau_DangNhap;
    private JPasswordField textFieldMatKhau_DangNhap;
    private JLabel labelKiemTraMatKhau_DangNhap;
    private JCheckBox checkBoxHienMatKhau;
    private JButton buttonDangNhap_DangNhap;
    private JLabel labelQuenMatKhau_DangNhap;
    private JPanel panelDuoi;
    private JLabel labelBanQuyen;
    private JPanel panelDangKy_Body;
    private JLabel labelTieuDeDangKy_Body;
    private JLabel labelTieuDeTenTaiKhoan_DangKy;
    private JTextField textFieldTenTK_DangKy;
    private JLabel labelKiemTraTenTK_DangKy;
    private JLabel labelTieuDeMatKhau1_DangKy;
    private JPasswordField textFieldMatKhau1_DangKy;
    private JLabel labelKiemTraMatKhau1_DangKy;
    private JLabel labelTieuDeMatKhau2_DangKy;
    private JPasswordField textFieldMatKhau2_DangKy;
    private JLabel labelKiemTraMatKhau2_DangKy;
    private JButton buttonDangKy_DangKy;
    private JLabel labelKiemTraDangKy;
    private JPanel panelQuenMatKhau_Body;
    private JLabel labelTieuDeQuenMatKhau_Body;
    private JLabel labelTieuDeTenTaiKhoan_QuenMatKhau;
    private JTextField textFieldTenTK_QuenMatKhau;
    private JLabel labelKiemTraTenTK_QuenMatKhau;
    private JButton buttonQuenMatKhau_QuenMatKhau;
    private JLabel labelKiemTraQuenMatKhau;
    private String kiemTraMau;
    private Connection connection;
    private Statement statement;
    private String tenTK;

    public ApplicatonFirst(String tenTK) {
        super("RosieNguyen [letienndat]");
        setIconImage(new ImageIcon(ApplicatonFirst.class.getResource(sourceImage + "title.png")).getImage());
        setSize(1100, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            connectSQL();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối tới máy chủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.tenTK = tenTK;
        init();

        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(null);
        setVisible(true);
    }

    public void init() {
        initHead();
        initBody();
        initDuoi();
        startDangNhap();
        endQuenMatKhau();
    }

    public void initHead() {
        panelHead = new JPanel();
        panelHead.setBackground(new Color(255, 255, 255));
        panelHead.setBounds(0, 0, 1100, 60);
        panelHead.setLayout(null);

        panelLogo_Head = new JPanel();
        panelLogo_Head.setBackground(new Color(255, 255, 255));
        panelLogo_Head.setBounds(50, 0, 110, 60);
        panelLogo_Head.setLayout(new BorderLayout());

        labelLogo_Head = new JLabel("Rosie Nguyen");
        labelLogo_Head.setFont(new Font("Raphtalia", Font.PLAIN, 25));
        labelLogo_Head.setBounds(15, 20, 80, 15);
        panelLogo_Head.add(labelLogo_Head);
        panelHead.add(panelLogo_Head);

        panelDangNhap_Head = new JPanel();
        panelDangNhap_Head.setBackground(new Color(255, 255, 255));
        panelDangNhap_Head.setBounds(WIDTH - 250, 0, 70, 60);
        panelDangNhap_Head.setLayout(new BorderLayout());

        labelDangNhap_Head = new JLabel("ĐĂNG NHẬP");
        labelDangNhap_Head.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        labelDangNhap_Head.setHorizontalAlignment(JLabel.CENTER);
        labelDangNhap_Head.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startDangNhap();
                endDangKy();
                endQuenMatKhau();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!kiemTraMau.equals("DangNhap")) {
                    labelDangNhap_Head.setForeground(new Color(236, 115, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau.equals("DangNhap")) {
                    labelDangNhap_Head.setForeground(new Color(0, 0, 0));
                }
            }
        });
        panelDangNhap_Head.add(labelDangNhap_Head, BorderLayout.CENTER);

        panelHead.add(panelDangNhap_Head);

        panelDangKy_Head = new JPanel();
        panelDangKy_Head.setBackground(new Color(255, 255, 255));
        panelDangKy_Head.setBounds(WIDTH - 165, 0, 70, 60);
        panelDangKy_Head.setLayout(new BorderLayout());

        labelDangKy_Head = new JLabel("ĐĂNG KÝ");
        labelDangKy_Head.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        labelDangKy_Head.setHorizontalAlignment(JLabel.CENTER);
        labelDangKy_Head.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startDangKy();
                endDangNhap();
                endQuenMatKhau();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!kiemTraMau.equals("DangKy")) {
                    labelDangKy_Head.setForeground(new Color(236, 115, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!kiemTraMau.equals("DangKy")) {
                    labelDangKy_Head.setForeground(new Color(0, 0, 0));
                }
            }
        });
        panelDangKy_Head.add(labelDangKy_Head, BorderLayout.CENTER);

        panelHead.add(panelDangKy_Head);

        labelDangNhap_Head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelDangKy_Head.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(panelHead);

        panelNganCachTren_DangNhap = new JPanel();
        panelNganCachTren_DangNhap.setBackground(new Color(170, 166, 166));
        panelNganCachTren_DangNhap.setBounds(0, 60, WIDTH, 1);
        panelNganCachTren_DangNhap.setLayout(null);

        add(panelNganCachTren_DangNhap);
    }

    public void initBody() {
        initBodyDangNhap();
        initBodyDangKy();
        initBodyQuenMatKhau();
    }

    public void initDuoi() {
        panelDuoi = new JPanel();
        panelDuoi.setBackground(new Color(232, 230, 230));
        panelDuoi.setBounds(0, 500, WIDTH, HEIGHT - 500);
        panelDuoi.setLayout(null);

        labelBanQuyen = new JLabel("Copyright of letienndat");
        labelBanQuyen.setFont(new Font("Inter", Font.PLAIN, 10));
        labelBanQuyen.setBounds(0, 20, WIDTH, 20);
        labelBanQuyen.setHorizontalAlignment(JLabel.CENTER);
        panelDuoi.add(labelBanQuyen);

        add(panelDuoi);
    }

    public void initBodyDangNhap() {
        panelDangNhap_Body = new JPanel();
        panelDangNhap_Body.setBackground(new Color(255, 255, 255));
        panelDangNhap_Body.setBounds(0, 61, WIDTH, 440);
        panelDangNhap_Body.setLayout(null);

        labelTieuDeDangNhap_Body = new JLabel("Chào mừng bạn quay trở lại, hãy đăng nhập");
        labelTieuDeDangNhap_Body.setFont(new Font("Cambria", Font.PLAIN, 18));
        labelTieuDeDangNhap_Body.setBounds(0, 60, WIDTH, 30);
        labelTieuDeDangNhap_Body.setHorizontalAlignment(JLabel.CENTER);
        panelDangNhap_Body.add(labelTieuDeDangNhap_Body);

        labelTieuDeTenTaiKhoan_DangNhap = new JLabel("Tên đăng nhập");
        labelTieuDeTenTaiKhoan_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelTieuDeTenTaiKhoan_DangNhap.setBounds(WIDTH / 2 - 350 / 2, 125, 100, 20);
        labelTieuDeTenTaiKhoan_DangNhap.setHorizontalAlignment(JLabel.LEFT);
        panelDangNhap_Body.add(labelTieuDeTenTaiKhoan_DangNhap);

        textFieldTenTK_DangNhap = new JTextField(30);
        textFieldTenTK_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textFieldTenTK_DangNhap.setBounds(WIDTH / 2 - 350 / 2, 145, 350, 35);
        textFieldTenTK_DangNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraTenTK_DangNhap.setText("");
                    if (!textFieldMatKhau_DangNhap.getText().equals("")) {
                        labelKiemTraMatKhau_DangNhap.setText("");
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (kiemTraTenTK_DangNhap() == 0) {
                        labelKiemTraTenTK_DangNhap.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                        }
                    } else if (kiemTraTenTK_DangNhap() == -1) {
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (kiemTraMatKhau_DangNhap() != 0) {
                            labelKiemTraTenTK_DangNhap.setText("Tên đăng nhập không tồn tại!");
                        }
                    } else if (kiemTraTenTK_DangNhap() == 1) {
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (kiemTraMatKhau_DangNhap() == -1) {
                            labelKiemTraMatKhau_DangNhap.setText("Sai mật khẩu!");
                        } else if (kiemTraMatKhau_DangNhap() == 1) {
                            labelKiemTraTenTK_DangNhap.setText("Thành công!");
                            labelKiemTraMatKhau_DangNhap.setText("Thành công!");
                            dispose();
                            EventQueue.invokeLater(() -> {
                                new ApplicationSecond(textFieldTenTK_DangNhap.getText(), connection, statement).setVisible(true);
                            });
                        }
                    }
                }
            }
        });
        panelDangNhap_Body.add(textFieldTenTK_DangNhap);

        labelKiemTraTenTK_DangNhap = new JLabel("");
        labelKiemTraTenTK_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelKiemTraTenTK_DangNhap.setBounds(WIDTH / 2 + 350 / 2 + 10, 155, 300, 20);
        labelKiemTraTenTK_DangNhap.setHorizontalAlignment(JLabel.LEFT);
        panelDangNhap_Body.add(labelKiemTraTenTK_DangNhap);

        labelTieuDeMatKhau_DangNhap = new JLabel("Mật khẩu");
        labelTieuDeMatKhau_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelTieuDeMatKhau_DangNhap.setBounds(WIDTH / 2 - 350 / 2, 185, 100, 20);
        labelTieuDeMatKhau_DangNhap.setHorizontalAlignment(JLabel.LEFT);
        panelDangNhap_Body.add(labelTieuDeMatKhau_DangNhap);

        textFieldMatKhau_DangNhap = new JPasswordField(30);
        textFieldMatKhau_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textFieldMatKhau_DangNhap.setBounds(WIDTH / 2 - 350 / 2, 205, 350, 35);
        textFieldMatKhau_DangNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER && e.getKeyChar() != KeyEvent.VK_SPACE) {
                    labelKiemTraMatKhau_DangNhap.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (kiemTraTenTK_DangNhap() == 0) {
                        labelKiemTraTenTK_DangNhap.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                        }
                    } else if (kiemTraTenTK_DangNhap() == -1) {
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (kiemTraMatKhau_DangNhap() != 0) {
                            labelKiemTraTenTK_DangNhap.setText("Tên đăng nhập không tồn tại!");
                        }
                    } else if (kiemTraTenTK_DangNhap() == 1) {
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (kiemTraMatKhau_DangNhap() == -1) {
                            labelKiemTraMatKhau_DangNhap.setText("Sai mật khẩu!");
                        } else if (kiemTraMatKhau_DangNhap() == 1) {
                            labelKiemTraTenTK_DangNhap.setText("Thành công!");
                            labelKiemTraMatKhau_DangNhap.setText("Thành công!");
                            dispose();
                            EventQueue.invokeLater(() -> {
                                new ApplicationSecond(textFieldTenTK_DangNhap.getText(), connection, statement).setVisible(true);
                            });
                        }
                    }
                }
            }
        });
        panelDangNhap_Body.add(textFieldMatKhau_DangNhap);

        labelKiemTraMatKhau_DangNhap = new JLabel("");
        labelKiemTraMatKhau_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelKiemTraMatKhau_DangNhap.setBounds(WIDTH / 2 + 350 / 2 + 10, 215, 300, 20);
        labelKiemTraMatKhau_DangNhap.setHorizontalAlignment(JLabel.LEFT);
        panelDangNhap_Body.add(labelKiemTraMatKhau_DangNhap);

        checkBoxHienMatKhau = new JCheckBox("Hiện mật khẩu");
        checkBoxHienMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        checkBoxHienMatKhau.setBackground(new Color(255, 255, 255));
        checkBoxHienMatKhau.setBounds(WIDTH / 2 - 350 / 2 - 3, 248, 103, 20);
        checkBoxHienMatKhau.setFocusPainted(false);
        checkBoxHienMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBoxHienMatKhau.addItemListener(new ItemListener() {
            public char temp = textFieldMatKhau_DangNhap.getEchoChar();

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textFieldMatKhau_DangNhap.setEchoChar((char) 0);
                } else {
                    textFieldMatKhau_DangNhap.setEchoChar(temp);
                }
            }
        });
        checkBoxHienMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkBoxHienMatKhau.setForeground(new Color(236, 115, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkBoxHienMatKhau.setForeground(new Color(0, 0, 0));
            }
        });
        panelDangNhap_Body.add(checkBoxHienMatKhau);

        labelQuenMatKhau_DangNhap = new JLabel("Quên mật khẩu");
        labelQuenMatKhau_DangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelQuenMatKhau_DangNhap.setBounds(635, 248, 85, 20);
        labelQuenMatKhau_DangNhap.setHorizontalAlignment(JLabel.RIGHT);
        labelQuenMatKhau_DangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelQuenMatKhau_DangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startQuenMatKhau();
                endDangKy();
                endDangNhap();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelQuenMatKhau_DangNhap.setForeground(new Color(236, 115, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelQuenMatKhau_DangNhap.setForeground(new Color(0, 0, 0));
            }
        });
        panelDangNhap_Body.add(labelQuenMatKhau_DangNhap);

        buttonDangNhap_DangNhap = new JButton("ĐĂNG NHẬP");
        buttonDangNhap_DangNhap.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        buttonDangNhap_DangNhap.setBackground(new Color(0, 0, 0));
        buttonDangNhap_DangNhap.setForeground(new Color(255, 255, 255));
        buttonDangNhap_DangNhap.setBounds(WIDTH / 2 - 150 / 2, 330, 150, 30);
        buttonDangNhap_DangNhap.setHorizontalAlignment(JButton.CENTER);
        buttonDangNhap_DangNhap.setBorderPainted(false);
        buttonDangNhap_DangNhap.setFocusPainted(false);
        buttonDangNhap_DangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDangNhap_DangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kiemTraTenTK_DangNhap() == 0) {
                    labelKiemTraTenTK_DangNhap.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                    if (kiemTraMatKhau_DangNhap() == 0) {
                        labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                    }
                } else if (kiemTraTenTK_DangNhap() == -1) {
                    if (kiemTraMatKhau_DangNhap() == 0) {
                        labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                    } else if (kiemTraMatKhau_DangNhap() != 0) {
                        labelKiemTraTenTK_DangNhap.setText("Tên đăng nhập không tồn tại!");
                    }
                } else if (kiemTraTenTK_DangNhap() == 1) {
                    if (kiemTraMatKhau_DangNhap() == 0) {
                        labelKiemTraMatKhau_DangNhap.setText("Không được bỏ trống!");
                    } else if (kiemTraMatKhau_DangNhap() == -1) {
                        labelKiemTraMatKhau_DangNhap.setText("Sai mật khẩu!");
                    } else if (kiemTraMatKhau_DangNhap() == 1) {
                        labelKiemTraTenTK_DangNhap.setText("Thành công!");
                        labelKiemTraMatKhau_DangNhap.setText("Thành công!");
                        dispose();
                        EventQueue.invokeLater(() -> {
                            new ApplicationSecond(textFieldTenTK_DangNhap.getText(), connection, statement).setVisible(true);
                        });
                    }
                }
            }
        });
        buttonDangNhap_DangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonDangNhap_DangNhap.setBackground(new Color(236, 115, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonDangNhap_DangNhap.setBackground(new Color(0, 0, 0));
            }
        });
        panelDangNhap_Body.add(buttonDangNhap_DangNhap);

        add(panelDangNhap_Body);
    }

    public void initBodyDangKy() {
        panelDangKy_Body = new JPanel();
        panelDangKy_Body.setBackground(new Color(255, 255, 255));
        panelDangKy_Body.setBounds(0, 61, WIDTH, 440);
        panelDangKy_Body.setLayout(null);

        labelTieuDeDangKy_Body = new JLabel("Hãy tạo một tài khoản để sử dụng");
        labelTieuDeDangKy_Body.setFont(new Font("Cambria", Font.PLAIN, 18));
        labelTieuDeDangKy_Body.setBounds(0, 60, WIDTH, 30);
        labelTieuDeDangKy_Body.setHorizontalAlignment(JLabel.CENTER);
        panelDangKy_Body.add(labelTieuDeDangKy_Body);

        labelTieuDeTenTaiKhoan_DangKy = new JLabel("Tên đăng nhập");
        labelTieuDeTenTaiKhoan_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelTieuDeTenTaiKhoan_DangKy.setBounds(WIDTH / 2 - 350 / 2, 125, 100, 20);
        labelTieuDeTenTaiKhoan_DangKy.setHorizontalAlignment(JLabel.LEFT);
        panelDangKy_Body.add(labelTieuDeTenTaiKhoan_DangKy);

        textFieldTenTK_DangKy = new JTextField(30);
        textFieldTenTK_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textFieldTenTK_DangKy.setBounds(WIDTH / 2 - 350 / 2, 145, 350, 35);
        textFieldTenTK_DangKy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraTaiKhoan_DangKy() == 0) {
                    labelKiemTraTenTK_DangKy.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTaiKhoan_DangKy() == -1) {
                    labelKiemTraTenTK_DangKy.setText("Tên đăng nhập đã được sử dụng!");
                } else {
                    labelKiemTraTenTK_DangKy.setText("Thỏa mãn!");
                }
                if (labelKiemTraTenTK_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    buttonDangKy_DangKy.setEnabled(true);
                } else {
                    buttonDangKy_DangKy.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDangKy.setText("");
                }
            }
        });
        panelDangKy_Body.add(textFieldTenTK_DangKy);

        labelKiemTraTenTK_DangKy = new JLabel("");
        labelKiemTraTenTK_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelKiemTraTenTK_DangKy.setBounds(WIDTH / 2 + 350 / 2 + 10, 155, 300, 20);
        labelKiemTraTenTK_DangKy.setHorizontalAlignment(JLabel.LEFT);
        panelDangKy_Body.add(labelKiemTraTenTK_DangKy);

        labelTieuDeMatKhau1_DangKy = new JLabel("Mật khẩu");
        labelTieuDeMatKhau1_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelTieuDeMatKhau1_DangKy.setBounds(WIDTH / 2 - 350 / 2, 185, 100, 20);
        labelTieuDeMatKhau1_DangKy.setHorizontalAlignment(JLabel.LEFT);
        panelDangKy_Body.add(labelTieuDeMatKhau1_DangKy);

        textFieldMatKhau1_DangKy = new JPasswordField(30);
        textFieldMatKhau1_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textFieldMatKhau1_DangKy.setBounds(WIDTH / 2 - 350 / 2, 205, 350, 35);
        textFieldMatKhau1_DangKy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhau1_DangKy() == 0) {
                    labelKiemTraMatKhau1_DangKy.setText("Yêu cầu A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự!");
                    if (kiemTraMatKhau2_DangKy() != 0) {
                        labelKiemTraMatKhau2_DangKy.setText("Mật khẩu không trùng khớp!");
                    }
                } else if (kiemTraMatKhau1_DangKy() == 1) {
                    labelKiemTraMatKhau1_DangKy.setText("Thỏa mãn!");
                    if (kiemTraMatKhau2_DangKy() == 1) {
                        labelKiemTraMatKhau2_DangKy.setText("Thỏa mãn!");
                    }
                    if (kiemTraMatKhau2_DangKy() == -1) {
                        labelKiemTraMatKhau2_DangKy.setText("Mật khẩu không trùng khớp!");
                    }
                }
                if (kiemTraTaiKhoan_DangKy() == 0) {
                    labelKiemTraTenTK_DangKy.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTaiKhoan_DangKy() == -1) {
                    labelKiemTraTenTK_DangKy.setText("Tên đăng nhập đã được sử dụng!");
                } else {
                    labelKiemTraTenTK_DangKy.setText("Thỏa mãn!");
                }
                if (labelKiemTraTenTK_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    buttonDangKy_DangKy.setEnabled(true);
                } else {
                    buttonDangKy_DangKy.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDangKy.setText("");
                }
            }
        });
        panelDangKy_Body.add(textFieldMatKhau1_DangKy);

        labelKiemTraMatKhau1_DangKy = new JLabel("");
        labelKiemTraMatKhau1_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelKiemTraMatKhau1_DangKy.setBounds(WIDTH / 2 + 350 / 2 + 10, 215, 300, 20);
        labelKiemTraMatKhau1_DangKy.setHorizontalAlignment(JLabel.LEFT);
        panelDangKy_Body.add(labelKiemTraMatKhau1_DangKy);

        labelTieuDeMatKhau2_DangKy = new JLabel("Nhập lại mật khẩu");
        labelTieuDeMatKhau2_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelTieuDeMatKhau2_DangKy.setBounds(WIDTH / 2 - 350 / 2, 245, 100, 20);
        labelTieuDeMatKhau2_DangKy.setHorizontalAlignment(JLabel.LEFT);
        panelDangKy_Body.add(labelTieuDeMatKhau2_DangKy);

        textFieldMatKhau2_DangKy = new JPasswordField(30);
        textFieldMatKhau2_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textFieldMatKhau2_DangKy.setBounds(WIDTH / 2 - 350 / 2, 265, 350, 35);
        textFieldMatKhau2_DangKy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhau2_DangKy() == 0) {
                    labelKiemTraMatKhau2_DangKy.setText("Không được bỏ trống!");
                } else if (kiemTraMatKhau2_DangKy() == -1) {
                    labelKiemTraMatKhau2_DangKy.setText("Mật khẩu không trùng khớp!");
                } else if (kiemTraMatKhau2_DangKy() == 1) {
                    labelKiemTraMatKhau2_DangKy.setText("Thỏa mãn!");
                }
                if (kiemTraTaiKhoan_DangKy() == 0) {
                    labelKiemTraTenTK_DangKy.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTaiKhoan_DangKy() == -1) {
                    labelKiemTraTenTK_DangKy.setText("Tên đăng nhập đã được sử dụng!");
                } else {
                    labelKiemTraTenTK_DangKy.setText("Thỏa mãn!");
                }
                if (labelKiemTraTenTK_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    buttonDangKy_DangKy.setEnabled(true);
                } else {
                    buttonDangKy_DangKy.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraDangKy.setText("");
                }
            }
        });
        panelDangKy_Body.add(textFieldMatKhau2_DangKy);

        labelKiemTraMatKhau2_DangKy = new JLabel("");
        labelKiemTraMatKhau2_DangKy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelKiemTraMatKhau2_DangKy.setBounds(WIDTH / 2 + 350 / 2 + 10, 275, 300, 20);
        labelKiemTraMatKhau2_DangKy.setHorizontalAlignment(JLabel.LEFT);
        panelDangKy_Body.add(labelKiemTraMatKhau2_DangKy);

        buttonDangKy_DangKy = new JButton("ĐĂNG KÝ");
        buttonDangKy_DangKy.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        buttonDangKy_DangKy.setBackground(new Color(0, 0, 0));
        buttonDangKy_DangKy.setForeground(new Color(255, 255, 255));
        buttonDangKy_DangKy.setBounds(WIDTH / 2 - 150 / 2, 345, 150, 30);
        buttonDangKy_DangKy.setHorizontalAlignment(JButton.CENTER);
        buttonDangKy_DangKy.setFocusPainted(false);
        buttonDangKy_DangKy.setBorderPainted(false);
        buttonDangKy_DangKy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDangKy_DangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaiKhoanToSQL();
                labelKiemTraDangKy.setText("Tạo tài khoản thành công!");
            }
        });
        buttonDangKy_DangKy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (labelKiemTraTenTK_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    buttonDangKy_DangKy.setBackground(new Color(236, 115, 10));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (labelKiemTraTenTK_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        labelKiemTraMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    buttonDangKy_DangKy.setBackground(new Color(0, 0, 0));
                }
            }
        });
        panelDangKy_Body.add(buttonDangKy_DangKy);

        labelKiemTraDangKy = new JLabel("");
        labelKiemTraDangKy.setFont(new Font("Cambria", Font.PLAIN, 13));
        labelKiemTraDangKy.setBounds(0, 390, WIDTH, 20);
        labelKiemTraDangKy.setHorizontalAlignment(JLabel.CENTER);
        panelDangKy_Body.add(labelKiemTraDangKy);

        panelDangKy_Body.setVisible(false);

        add(panelDangKy_Body);
    }

    public void initBodyQuenMatKhau() {
        panelQuenMatKhau_Body = new JPanel();
        panelQuenMatKhau_Body.setBackground(new Color(255, 255, 255));
        panelQuenMatKhau_Body.setBounds(0, 61, WIDTH, 440);
        panelQuenMatKhau_Body.setLayout(null);

        labelTieuDeQuenMatKhau_Body = new JLabel("Quên mật khẩu, hãy sử dụng tên đăng nhập...", JLabel.CENTER);
        labelTieuDeQuenMatKhau_Body.setFont(new Font("Cambria", Font.PLAIN, 18));
        labelTieuDeQuenMatKhau_Body.setBounds(0, 60, WIDTH, 30);
        panelQuenMatKhau_Body.add(labelTieuDeQuenMatKhau_Body);

        labelTieuDeTenTaiKhoan_QuenMatKhau = new JLabel("Tên đăng nhập");
        labelTieuDeTenTaiKhoan_QuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelTieuDeTenTaiKhoan_QuenMatKhau.setBounds(WIDTH / 2 - 350 / 2, 125, 100, 20);
        labelTieuDeTenTaiKhoan_QuenMatKhau.setHorizontalAlignment(JLabel.LEFT);
        panelQuenMatKhau_Body.add(labelTieuDeTenTaiKhoan_QuenMatKhau);

        textFieldTenTK_QuenMatKhau = new JTextField(30);
        textFieldTenTK_QuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textFieldTenTK_QuenMatKhau.setBounds(WIDTH / 2 - 350 / 2, 145, 350, 35);
        textFieldTenTK_QuenMatKhau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraTenTK_QuenMatKhau() == 0) {
                    labelKiemTraTenTK_QuenMatKhau.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    labelKiemTraTenTK_QuenMatKhau.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                labelKiemTraQuenMatKhau.setText("");
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (kiemTraTenTK_QuenMatKhau() == -1) {
                        labelKiemTraQuenMatKhau.setText("Tên đăng nhập không tồn tại!");
                    } else if (kiemTraTenTK_QuenMatKhau() == 1) {
                        String sql = "SELECT MatKhau FROM taikhoan_matkhau WHERE TenTK = '" + textFieldTenTK_QuenMatKhau.getText() + "'";
                        try {
                            ResultSet resultSet = statement.executeQuery(sql);
                            resultSet.next();
                            labelKiemTraQuenMatKhau.setText("Mật khẩu của bạn là: " + resultSet.getString("MatKhau"));
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    }
                }
            }
        });
        panelQuenMatKhau_Body.add(textFieldTenTK_QuenMatKhau);

        labelKiemTraTenTK_QuenMatKhau = new JLabel("");
        labelKiemTraTenTK_QuenMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelKiemTraTenTK_QuenMatKhau.setBounds(WIDTH / 2 + 350 / 2 + 10, 155, 300, 20);
        labelKiemTraTenTK_QuenMatKhau.setHorizontalAlignment(JLabel.LEFT);
        panelQuenMatKhau_Body.add(labelKiemTraTenTK_QuenMatKhau);

        buttonQuenMatKhau_QuenMatKhau = new JButton("TÌM MẬT KHẨU");
        buttonQuenMatKhau_QuenMatKhau.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        buttonQuenMatKhau_QuenMatKhau.setBackground(new Color(0, 0, 0));
        buttonQuenMatKhau_QuenMatKhau.setForeground(new Color(255, 255, 255));
        buttonQuenMatKhau_QuenMatKhau.setBounds(WIDTH / 2 - 150 / 2, 215, 150, 30);
        buttonQuenMatKhau_QuenMatKhau.setHorizontalAlignment(JButton.CENTER);
        buttonQuenMatKhau_QuenMatKhau.setBorderPainted(false);
        buttonQuenMatKhau_QuenMatKhau.setFocusPainted(false);
        buttonQuenMatKhau_QuenMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonQuenMatKhau_QuenMatKhau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (kiemTraTenTK_QuenMatKhau() == 0) {
                    labelKiemTraTenTK_QuenMatKhau.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTenTK_QuenMatKhau() == -1) {
                    labelKiemTraTenTK_QuenMatKhau.setText("Tên đăng nhập không tồn tại!");
                } else if (kiemTraTenTK_QuenMatKhau() == 1) {
                    String sql = "SELECT MatKhau FROM taikhoan_matkhau WHERE TenTK = '" + textFieldTenTK_QuenMatKhau.getText() + "'";
                    try {
                        ResultSet resultSet = statement.executeQuery(sql);
                        resultSet.next();
                        labelKiemTraQuenMatKhau.setText("Mật khẩu của bạn là: " + resultSet.getString("MatKhau"));
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonQuenMatKhau_QuenMatKhau.setBackground(new Color(236, 115, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonQuenMatKhau_QuenMatKhau.setBackground(new Color(0, 0, 0));
            }
        });
        panelQuenMatKhau_Body.add(buttonQuenMatKhau_QuenMatKhau);

        labelKiemTraQuenMatKhau = new JLabel("");
        labelKiemTraQuenMatKhau.setFont(new Font("Cambria", Font.PLAIN, 13));
        labelKiemTraQuenMatKhau.setBounds(0, 285, WIDTH, 20);
        labelKiemTraQuenMatKhau.setHorizontalAlignment(JLabel.CENTER);
        panelQuenMatKhau_Body.add(labelKiemTraQuenMatKhau);

        add(panelQuenMatKhau_Body);
    }

    public void startDangNhap() {
        panelDangNhap_Body.setVisible(true);
        labelDangNhap_Head.setForeground(new Color(236, 115, 10));
        textFieldTenTK_DangNhap.setText(this.tenTK);
        textFieldMatKhau_DangNhap.setText("");
        labelKiemTraTenTK_DangNhap.setText("");
        labelKiemTraMatKhau_DangNhap.setText("");
        kiemTraMau = "DangNhap";
    }

    public void endDangNhap() {
        panelDangNhap_Body.setVisible(false);
        labelDangNhap_Head.setForeground(new Color(0, 0, 0));
    }

    public void startDangKy() {
        panelDangKy_Body.setVisible(true);
        labelDangKy_Head.setForeground(new Color(236, 115, 10));
        textFieldTenTK_DangKy.setText("");
        textFieldMatKhau1_DangKy.setText("");
        textFieldMatKhau2_DangKy.setText("");
        labelKiemTraTenTK_DangKy.setText("");
        labelKiemTraMatKhau1_DangKy.setText("");
        labelKiemTraMatKhau2_DangKy.setText("");
        buttonDangKy_DangKy.setEnabled(false);
        labelKiemTraDangKy.setText("");
        kiemTraMau = "DangKy";
    }

    public void endDangKy() {
        panelDangKy_Body.setVisible(false);
        labelDangKy_Head.setForeground(new Color(0, 0, 0));
    }

    public void startQuenMatKhau() {
        panelQuenMatKhau_Body.setVisible(true);
        kiemTraMau = "";
    }

    public void endQuenMatKhau() {
        textFieldTenTK_QuenMatKhau.setText("");
        labelKiemTraQuenMatKhau.setText("");
        labelKiemTraTenTK_QuenMatKhau.setText("");
        panelQuenMatKhau_Body.setVisible(false);
    }

    public void connectSQL() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS taikhoan_matkhau (" +
                    "TenTK VARCHAR(100) NOT NULL, " +
                    "MatKhau VARCHAR(100), " +
                    "PRIMARY KEY (TenTK))";
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException();
        }
    }

    public boolean kiemTraTaiKhoanTonTai(String s) {
        try {
            String sql = "SELECT TenTK FROM taikhoan_matkhau WHERE TenTK = " + "'" + s + "'";
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) return true; // Tài khoản có tồn tại trong hệ thống
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Tài khoản không tồn tại trong hệ thống
    }

    public int kiemTraTaiKhoan_DangKy() {
        if (!textFieldTenTK_DangKy.getText().matches("^[a-z0-9_-]{3,16}$")) {
            return 0; // Không thỏa mãn a-z, 0-9, -, _, từ 3 đến 16 ký tự
        } else if (kiemTraTaiKhoanTonTai(textFieldTenTK_DangKy.getText())) {
            return -1; // Tên đăng nhập đã được sử dụng
        }
        return 1; // Tên đăng nhập thỏa mãn
    }

    public int kiemTraMatKhau1_DangKy() {
        if (!textFieldMatKhau1_DangKy.getText().matches("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")) {
            return 0; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự
        }
        return 1; // Mật khẩu 1 thỏa mãn
    }

    public int kiemTraMatKhau2_DangKy() {
        if (textFieldMatKhau2_DangKy.getText().length() == 0) {
            return 0; // Bỏ trống mật khẩu 2
        }
        if (kiemTraMatKhau1_DangKy() == 1) {
            if (textFieldMatKhau2_DangKy.getText().equals(textFieldMatKhau1_DangKy.getText())) {
                return 1; // Mật khẩu 2 trùng khớp với mật khẩu 1
            }
        }
        return -1; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự hoặc không trùng khớp với mật khẩu 1
    }

    public void addTaiKhoanToSQL() {
        try {
            String sql = "INSERT INTO taikhoan_matkhau VALUES ('" + textFieldTenTK_DangKy.getText() + "', '" + textFieldMatKhau1_DangKy.getText() + "')";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS ThongTinCaNhan (" +
                    "TenTK VARCHAR(100)," +
                    "Email VARCHAR(100)," +
                    "SoDienThoai VARCHAR(15)," +
                    "DiaChi VARCHAR(200)" +
                    ")";
            statement.executeUpdate(sql);
            sql = "INSERT INTO ThongTinCaNhan VALUES ('" + textFieldTenTK_DangKy.getText() + "', '', '', '')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int kiemTraTenTK_DangNhap() {
        if (!textFieldTenTK_DangNhap.getText().matches("^[a-z0-9_-]{3,16}$")) {
            return 0; // Không thỏa mãn a-z, 0-9, -, _, từ 3 đến 16 ký tự
        }
        if (kiemTraTaiKhoanTonTai(textFieldTenTK_DangNhap.getText())) {
            return 1; // Tên đăng nhập tồn tại
        }
        return -1; // Tên đăng nhập không tồn tại
    }

    public int kiemTraMatKhau_DangNhap() {
        if (textFieldMatKhau_DangNhap.getText().length() == 0) {
            return 0; // Bỏ trống mật khẩu
        }
        if (kiemTraTenTK_DangNhap() == 1) {
            try {
                String sql = "SELECT MatKhau FROM taikhoan_matkhau WHERE TenTK = '" + textFieldTenTK_DangNhap.getText() + "'";
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                if (resultSet.getString("MatKhau").equals(textFieldMatKhau_DangNhap.getText())) {
                    return 1; // Mật khẩu trùng khớp
                }
                return -1; // Sai mật khẩu
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -2; // Sai tên đăng nhập
    }

    public int kiemTraTenTK_QuenMatKhau() {
        if (!textFieldTenTK_QuenMatKhau.getText().matches("^[a-z0-9_-]{3,16}$")) {
            return 0; // Không thỏa mãn a-z, 0-9, -, _, từ 3 đến 16 ký tự
        }
        if (!kiemTraTaiKhoanTonTai(textFieldTenTK_QuenMatKhau.getText())) {
            return -1; // Tên đăng nhập không tồn tại
        }
        return 1; // Tên đăng nhập tồn tại
    }
}