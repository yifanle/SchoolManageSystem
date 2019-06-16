package com.muzile.frames;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.muzile.bean.User;
import com.muzile.service.UserService;


@SuppressWarnings("serial")
public class Loginframe extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JRadioButton teacherRBtn;
	private JRadioButton studentRBtn;
	private CardLayout clt;
	private JPanel pnl_content;
	private JButton lorBtn;
	private JLabel lbel_lnfo;
	private JLabel login_info;
	private String role = "学生";
	private UserService service = new UserService();
	private User user;
	
	int xx,xy;
	boolean uflag = false;
	boolean pflag = false;
	
	
	
	public void setRole(String role) {
		this.role = role;
	}

	public JRadioButton getTeacherRBtn() {
		return teacherRBtn;
	}

	public JLabel getLogin_info() {
		return login_info;
	}

	public JLabel getLbel_lnfo() {
		return lbel_lnfo;
	}

	public JButton getLorBtn() {
		return lorBtn;
	}

	public JPanel getPnl_content() {
		return pnl_content;
	}

	public JPanel getContentPane() {
		return contentPane;
	}


	public JTextField getUsername() {
		return username;
	}


	public JPasswordField getPassword() {
		return password;
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Loginframe();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Loginframe() {
		setUndecorated(true);
		setLocationByPlatform(true);
		setSize(624, 430);
		setVisible(true);
		init();
		initDir();
	}
	
	private void init(){
		clt = new CardLayout(0, 0);
		teacherRBtn = new JRadioButton("教师");
		studentRBtn = new JRadioButton("学生");
		login_info = new JLabel("");
		login_info.setFont(new Font("幼圆", Font.PLAIN, 12));
		login_info.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_content = new JPanel();
		lbel_lnfo = new JLabel("login");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnl_lor = new JPanel();
		pnl_lor.setBackground(Color.WHITE);
		pnl_lor.setBounds(0, 295, 624, 135);
		contentPane.add(pnl_lor);
		pnl_lor.setLayout(null);
		
		lorBtn = new JButton("登录");
		lorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = new User();
				if(pflag&&uflag){
					user.setUsername(getUsername().getText().trim());
					user.setPassword(String.valueOf(getPassword().getPassword()).trim());
					user.setRole(role);
					user = service.login(user);
					if(user==null){
						login_info.setText("用户名或密码或角色不正确");
					}else{
						if(user.getRole().equals("学生")){
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										new StudentHome(user);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}else{
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										new TeacherHome(user);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}
						Loginframe.this.dispose();
					}
				}
			}
		});
		lorBtn.setForeground(Color.WHITE);
		lorBtn.setFont(new Font("幼圆", Font.PLAIN, 16));
		lorBtn.setBackground(new Color(32,149,70));
		lorBtn.setBounds(413, 37, 99, 37);
		pnl_lor.add(lorBtn);
		
		JPanel panel_2 = new JPanel();
		/**
		 * 打开注册页面
		 */
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * 打开注册窗口
				 */
				Loginframe.this.setExtendedState(ICONIFIED);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Registframe();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(120, 37, 105, 37);
		pnl_lor.add(panel_2);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Loginframe.class.getResource("/images/icons8_edit_user_32px_1.png")));
		panel_2.add(label);
		
		JLabel lblLoginregist = new JLabel("注册");
		lblLoginregist.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginregist.setFont(new Font("幼圆", Font.PLAIN, 16));
		panel_2.add(lblLoginregist);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Loginframe.class.getResource("/images/角标-2.png")));
		label_2.setBounds(0, 110, 25, 25);
		pnl_lor.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Loginframe.class.getResource("/images/角标-1.png")));
		label_3.setBounds(599, 110, 25, 25);
		pnl_lor.add(label_3);
		
		
		pnl_content.setBackground(Color.WHITE);
		pnl_content.setBounds(0, 32, 624, 263);
		contentPane.add(pnl_content);
		pnl_content.setLayout(clt);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pnl_content.add(panel, "login");
		panel.setLayout(null);
		
		lbel_lnfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbel_lnfo.setFont(new Font("Papyrus", Font.PLAIN, 35));
		lbel_lnfo.setBounds(20, 0, 110, 57);
		panel.add(lbel_lnfo);
		
		username = new JTextField();
		username.addFocusListener(new FocusAdapter() {
			JLabel info = getLogin_info();
			String regex = "[\\p{Alnum}]{1,8}";
			@Override
			public void focusLost(FocusEvent e) {
				JTextField username = (JTextField) e.getSource();
				String value = username.getText().trim();
				if(value.equals("")){
					info.setText("用户名不能为空");
					uflag = false;
				}else if(!Pattern.matches(regex, value)){
					info.setText("请输入1-8位数字、字母");
					uflag = false;
				}else{
					info.setText("");
					uflag = true;
				}
			}
		});
		username.setBounds(177, 59, 288, 26);
		panel.add(username);
		username.setColumns(10);
		username.setBorder(null);
		
		JLabel lbel_usernameIcon = new JLabel("");
		lbel_usernameIcon.setIcon(new ImageIcon(Loginframe.class.getResource("/images/icons8_contacts_32px_1.png")));
		lbel_usernameIcon.setBounds(135, 45, 32, 57);
		panel.add(lbel_usernameIcon);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(177, 86, 288, 2);
		panel.add(separator);
		
		password = new JPasswordField();
		password.addFocusListener(new FocusAdapter() {
			JLabel info = getLogin_info();
			@Override
			public void focusLost(FocusEvent e) {
				JPasswordField password = (JPasswordField) e.getSource();
				char[] values = password.getPassword();
				if(values.length<=0){
					info.setText("密码不能为空");
					pflag = false;
				}else{
					info.setText("");
					pflag = true;
				}
			}
		});
		password.setColumns(10);
		password.setBorder(null);
		password.setBounds(177, 126, 288, 26);
		panel.add(password);
		
		JLabel lbel_passwordIcon = new JLabel("");
		lbel_passwordIcon.setIcon(new ImageIcon(Loginframe.class.getResource("/images/icons8_unlock_32px_1.png")));
		lbel_passwordIcon.setBounds(135, 112, 32, 57);
		panel.add(lbel_passwordIcon);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(177, 153, 288, 2);
		panel.add(separator_1);
		
		teacherRBtn.addItemListener(new ItemListener() {
			JRadioButton teacherRBtn = getTeacherRBtn();
			public void itemStateChanged(ItemEvent e) {
				if(teacherRBtn.isSelected()){
					setRole("老师");
				}else{
					setRole("学生");
				}
				
			}
		});
		teacherRBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		teacherRBtn.setBackground(Color.WHITE);
		teacherRBtn.setBounds(212, 197, 57, 23);
		panel.add(teacherRBtn);
		
		studentRBtn.setSelected(true);
		studentRBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		studentRBtn.setBackground(Color.WHITE);
		studentRBtn.setBounds(387, 197, 57, 23);
		panel.add(studentRBtn);
		
		ButtonGroup login_group = new ButtonGroup();
		login_group.add(teacherRBtn);
		login_group.add(studentRBtn);
		
		JLabel lbel_roleIcon = new JLabel("");
		lbel_roleIcon.setIcon(new ImageIcon(Loginframe.class.getResource("/images/icons8_checked_user_filled_32px.png")));
		lbel_roleIcon.setBounds(135, 178, 32, 57);
		panel.add(lbel_roleIcon);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(32,149,70));
		separator_2.setBounds(10, 251, 604, 1);
		panel.add(separator_2);
		
		JPanel pnl_top = new JPanel();
		/**
		 * 拖拽屏幕
		 */
		pnl_top.addMouseMotionListener(new MouseMotionAdapter() {
			int x,y;
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getXOnScreen();
				y = e.getYOnScreen();
				Loginframe.this.setLocation(x-xx, y-xy);
			}
		});
		pnl_top.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				xx = e.getX();
				xy = e.getY();
			}
		});
		pnl_top.setBackground(Color.WHITE);
		pnl_top.setBounds(0, 0, 624, 32);
		contentPane.add(pnl_top);
		pnl_top.setLayout(null);
		
		/**
		 * 退出
		 */
		JLabel exitLabel = new JLabel("");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exitLabel.setIcon(new ImageIcon(Loginframe.class.getResource("/images/icons8_delete_sign_25px.png")));
		exitLabel.setBounds(590, 0, 24, 32);
		pnl_top.add(exitLabel);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Loginframe.class.getResource("/images/角标-3.png")));
		label_1.setBounds(0, 0, 25, 25);
		pnl_top.add(label_1);
		
		login_info.setForeground(Color.RED);
		login_info.setBounds(35, 0, 545, 32);
		pnl_top.add(login_info);
	}
	private void initDir(){
		String tablepath = "c:/SchoolManageSys/excel";
		String chartpath = "c:/SchoolManageSys/charts";
		
		File tableFile = new File(tablepath);
		File chartFile = new File(chartpath);
		
		if(!tableFile.exists()||!chartFile.exists()){
			tableFile.mkdirs();
			chartFile.mkdirs();
		}
		
	}
	
}
