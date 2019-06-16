package com.muzile.frames;

import java.awt.Color;
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
public class Registframe extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JTextField schoolNum;
	private JTextField nameField;
	private JPasswordField repassword;
	private JLabel regist_info;
	private JRadioButton female;
	private JRadioButton student;
	private String sex = "男";
	private String role = "老师";
	private UserService service = new UserService();
	int xx,xy;
	boolean uflag = false,pflag = false,rflag = false ,sflag = false ,nflag = false;
	

	
	public JRadioButton getFemale() {
		return female;
	}

	public JRadioButton getStudent() {
		return student;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public JTextField getUsername() {
		return username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public JTextField getSchoolNum() {
		return schoolNum;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JPasswordField getRepassword() {
		return repassword;
	}

	public JLabel getRegist_info() {
		return regist_info;
	}

	public Registframe() {
		setUndecorated(true);
		setLocationByPlatform(true);
		setSize(451, 656);
		setVisible(true);
		init();
		
	}

	private void init() {
		regist_info = new JLabel("");
		regist_info.setFont(new Font("幼圆", Font.PLAIN, 12));
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			int x,y;
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getXOnScreen();
				y = e.getYOnScreen();
				Registframe.this.setLocation(x-xx, y-xy);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		panel.setBounds(0, 0, 451, 54);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbel_exit = new JLabel("");
		lbel_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Registframe.this.dispose();
			}
		});
		lbel_exit.setIcon(new ImageIcon(Registframe.class.getResource("/images/icons8_delete_sign_25px.png")));
		lbel_exit.setBounds(424, 0, 27, 30);
		panel.add(lbel_exit);
		
		JLabel lbel_info = new JLabel("regist");
		lbel_info.setFont(new Font("Papyrus", Font.PLAIN, 35));
		lbel_info.setHorizontalAlignment(SwingConstants.CENTER);
		lbel_info.setBounds(172, 0, 103, 44);
		panel.add(lbel_info);
		
		JLabel lbel_left_top = new JLabel("");
		lbel_left_top.setIcon(new ImageIcon(Registframe.class.getResource("/images/角标-3.png")));
		lbel_left_top.setBounds(0, 0, 25, 25);
		panel.add(lbel_left_top);
		
		username = new JTextField();
		username.addFocusListener(new FocusAdapter() {
			JLabel info = getRegist_info();
			String regex = "[\\p{Alnum}]{1,8}";
			@Override
			public void focusLost(FocusEvent e) {
				JTextField username = (JTextField) e.getSource();
				String value = username.getText();
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
		username.setColumns(10);
		username.setBorder(null);
		username.setBounds(109, 94, 288, 26);
		contentPane.add(username);
		
		JLabel label_username = new JLabel("账号：");
		label_username.setFont(new Font("幼圆", Font.PLAIN, 14));
		label_username.setBounds(67, 94, 42, 32);
		contentPane.add(label_username);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(109, 121, 288, 2);
		contentPane.add(separator);
		
		password = new JPasswordField();
		password.addFocusListener(new FocusAdapter() {
			JLabel info = getRegist_info();
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
		password.setBounds(109, 149, 288, 26);
		contentPane.add(password);
		
		JLabel label_password = new JLabel("密码：");
		label_password.setFont(new Font("幼圆", Font.PLAIN, 14));
		label_password.setBounds(67, 149, 42, 32);
		contentPane.add(label_password);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(109, 176, 288, 2);
		contentPane.add(separator_1);
		
		schoolNum = new JTextField();
		schoolNum.addFocusListener(new FocusAdapter() {
			JLabel info = getRegist_info();
			String regex = "[\\d]+";
			@Override
			public void focusLost(FocusEvent e) {
				JTextField schoolNum = (JTextField) e.getSource();
				String num = schoolNum.getText();
				if(num.equals("")){
					info.setText("学号不能为空");
					sflag = false;
				}else if(!Pattern.matches(regex, num)){
					info.setText("请至少输入1位数字");
					sflag = false;
				}else{
					info.setText("");
					sflag = true;
				}
			}
		});
		schoolNum.setColumns(10);
		schoolNum.setBorder(null);
		schoolNum.setBounds(109, 270, 288, 26);
		contentPane.add(schoolNum);
		
		JLabel label_shcoolNum = new JLabel("学号：");
		label_shcoolNum.setFont(new Font("幼圆", Font.PLAIN, 14));
		label_shcoolNum.setBounds(67, 270, 42, 32);
		contentPane.add(label_shcoolNum);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.GRAY);
		separator_2.setBounds(109, 297, 288, 2);
		contentPane.add(separator_2);
		
		nameField = new JTextField();
		nameField.addFocusListener(new FocusAdapter() {
			JLabel info = getRegist_info();
			@Override
			public void focusLost(FocusEvent e) {
				JTextField nameField = (JTextField) e.getSource();
				String realName = nameField.getText();
				if(realName.equals("")){
					info.setText("姓名不能为空");
					nflag = false;
				}else{
					info.setText("");
					nflag = true;
				}
			}
		});
		nameField.setColumns(10);
		nameField.setBorder(null);
		nameField.setBounds(109, 332, 288, 26);
		contentPane.add(nameField);
		
		JLabel label_name = new JLabel("姓名：");
		label_name.setFont(new Font("幼圆", Font.PLAIN, 14));
		label_name.setBounds(67, 332, 42, 32);
		contentPane.add(label_name);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.GRAY);
		separator_3.setBounds(109, 359, 288, 2);
		contentPane.add(separator_3);
		
		JLabel label_sex = new JLabel("性别：");
		label_sex.setFont(new Font("幼圆", Font.PLAIN, 14));
		label_sex.setBounds(67, 404, 42, 26);
		contentPane.add(label_sex);
		
		
		JRadioButton male = new JRadioButton("男");
		male.setSelected(true);
		male.setFont(new Font("幼圆", Font.PLAIN, 14));
		male.setBackground(Color.WHITE);
		male.setBounds(158, 406, 57, 23);
		contentPane.add(male);
		
		female = new JRadioButton("女");
		female.addItemListener(new ItemListener() {
			JRadioButton female = getFemale();
			public void itemStateChanged(ItemEvent e) {
				if(female.isSelected()){
					setSex("女");
				}else{
					setSex("男");
				}
			}
		});
		female.setFont(new Font("幼圆", Font.PLAIN, 14));
		female.setBackground(Color.WHITE);
		female.setBounds(311, 407, 57, 23);
		contentPane.add(female);
		
		ButtonGroup sex_group = new ButtonGroup();
		sex_group.add(male);
		sex_group.add(female);
		
		JLabel label_role = new JLabel("角色：");
		label_role.setFont(new Font("幼圆", Font.PLAIN, 14));
		label_role.setBounds(67, 473, 42, 26);
		contentPane.add(label_role);
		
		student = new JRadioButton("学生");
		student.addItemListener(new ItemListener() {
			JRadioButton student = getStudent();
			public void itemStateChanged(ItemEvent e) {
				if(student.isSelected()){
					setRole("学生");
				}else{
					setRole("教师");
				}
			}
		});
		student.setFont(new Font("幼圆", Font.PLAIN, 14));
		student.setBackground(Color.WHITE);
		student.setBounds(311, 475, 57, 23);
		contentPane.add(student);
		
		JRadioButton teacher = new JRadioButton("教师");
		teacher.setSelected(true);
		teacher.setFont(new Font("幼圆", Font.PLAIN, 14));
		teacher.setBackground(Color.WHITE);
		teacher.setBounds(158, 474, 57, 23);
		contentPane.add(teacher);
		
		ButtonGroup role_group = new ButtonGroup();
		role_group.add(student);
		role_group.add(teacher);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.LIGHT_GRAY);
		separator_4.setBounds(45, 64, 381, 2);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.LIGHT_GRAY);
		separator_5.setBounds(45, 562, 381, 2);
		contentPane.add(separator_5);
		
		JButton registBtn = new JButton("注册");
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * 注册并判断
				 * boolean uflag = false,pflag = false,rflag = false ,sflag = false ,nflag = false;
				 */
				if(uflag&&pflag&&rflag&&sflag&&nflag){
					User user = new User();
					//封装user
					user.setName(getNameField().getText());
					user.setNum(getSchoolNum().getText());
					user.setUsername(getUsername().getText());
					user.setPassword(String.valueOf(getPassword().getPassword()));
					user.setSex(sex);
					user.setRole(role);
					//注册
					if(service.regist(user)){
						Registframe.this.dispose();
					}else{
						regist_info.setText("注册失败");
					}
				}
			}
		});
		registBtn.setForeground(Color.WHITE);
		registBtn.setFont(new Font("幼圆", Font.PLAIN, 16));
		registBtn.setBackground(new Color(32, 149, 70));
		registBtn.setBounds(180, 584, 99, 37);
		contentPane.add(registBtn);
		
		JLabel lbel_left_bottom = new JLabel("");
		lbel_left_bottom.setIcon(new ImageIcon(Registframe.class.getResource("/images/角标-2.png")));
		lbel_left_bottom.setBounds(0, 631, 25, 25);
		contentPane.add(lbel_left_bottom);
		
		JLabel lbel_right_bottom = new JLabel("");
		lbel_right_bottom.setIcon(new ImageIcon(Registframe.class.getResource("/images/角标-1.png")));
		lbel_right_bottom.setBounds(426, 631, 25, 25);
		contentPane.add(lbel_right_bottom);
		
		JLabel label = new JLabel("确认密码：");
		label.setFont(new Font("幼圆", Font.PLAIN, 14));
		label.setBounds(39, 210, 70, 32);
		contentPane.add(label);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.GRAY);
		separator_6.setBounds(109, 237, 288, 2);
		contentPane.add(separator_6);
		
		repassword = new JPasswordField();
		repassword.addFocusListener(new FocusAdapter() {
			JLabel info = getRegist_info();
			JPasswordField password = getPassword();
			@Override
			public void focusLost(FocusEvent e) {
				JPasswordField repassword = (JPasswordField) e.getSource();
				String passwordStr = String.valueOf(password.getPassword());
				String repasswordStr = String.valueOf(repassword.getPassword());
				
				if((passwordStr.equals("")||repasswordStr.equals(""))||!(passwordStr.equals(repasswordStr))){
					info.setText("两次密码不一致");
					rflag = false;
				}else{
					info.setText("");
					rflag = true;
				}
			}
		});
		repassword.setColumns(10);
		repassword.setBorder(null);
		repassword.setBounds(109, 210, 288, 26);
		contentPane.add(repassword);
		
		regist_info.setForeground(Color.RED);
		regist_info.setHorizontalAlignment(SwingConstants.CENTER);
		regist_info.setBounds(45, 541, 381, 15);
		contentPane.add(regist_info);
	}
}
