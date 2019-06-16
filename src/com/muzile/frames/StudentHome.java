package com.muzile.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.muzile.bean.Result;
import com.muzile.bean.Score;
import com.muzile.bean.User;
import com.muzile.listeners.BtnStyleListener;
import com.muzile.service.StudentService;
import com.muzile.utils.POIUtils;
import com.muzile.utils.AnalysisUtils;

@SuppressWarnings("serial")
public class StudentHome extends JFrame {

	
	private JPanel contentPane;
	private JButton indexBtn;
	private JButton searchBtn;
	private JButton resultBtn;
	private CardLayout card;
	private JPanel panel;
	private JPanel panel_2;
	private Map<String,JButton> btngroup = new HashMap<String,JButton>();
	int xx,xy;
	private JLabel lbl_name;
	private JLabel lbl_mainNum;
	private JLabel lbl_username;
	private JLabel lbl_num;
	private JLabel lbl_sex;
	private JLabel lblWelcome;
	private JPanel pnl_index;
	private JPanel panel_17;
	private JPanel panel_18;
	private StudentService service = new StudentService();
	private Object [] indexTblName = {"序号","科目名称","科目成绩","科目绩点","成绩所属学期"};
	private Object[] searchTblName = {"序号","科目名称","科目成绩","科目绩点","成绩所属学期"};
	private String date = "第1学期"; 
	private Score score = new Score();
	private User user = null;
	
	public StudentHome(User user) {
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1308, 800);
		setUndecorated(true);
		setVisible(true);
		init();
		load();
		getIndexTbl();
		getSearchTbl(false);
	}
	
	private void load(){
		lbl_name.setText(user.getName());
		lbl_mainNum.setText(user.getNum());
		lbl_username.setText(user.getUsername());
		lbl_num.setText(user.getNum());
		lbl_sex.setText(user.getSex());
		lblWelcome.setText("Welcom back,"+user.getName());
	}
	
	private void init(){
		contentPane = new JPanel();
		panel_17 = new JPanel();
		panel_18 = new JPanel();
		resultBtn = new JButton("成绩总结");
		resultBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel_2, "result");
			}
		});
		indexBtn = new JButton("主页");
		indexBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRepaint(panel_17);
				getIndexTbl();
				card.show(panel_2, "index");
			}
		});
		searchBtn = new JButton("成绩查询");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel_2, "search");
			}
		});
		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btngroup.put(indexBtn.getText(), indexBtn);
		btngroup.put(searchBtn.getText(), searchBtn);
		btngroup.put(resultBtn.getText(), resultBtn);
		
		BtnStyleListener btnStyle = new BtnStyleListener(btngroup);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel.setBackground(new Color(250,250,250));
		panel.setBounds(0, 24, 220, 776);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_super_mario_50px.png")));
		lblNewLabel.setBounds(79, 51, 50, 50);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(61, 117, 50, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblId = new JLabel("学号：");
		lblId.setFont(new Font("幼圆", Font.PLAIN, 15));
		lblId.setBounds(61, 145, 50, 15);
		panel.add(lblId);
		
		lbl_name = new JLabel("李逸凡");
		lbl_name.setForeground(Color.RED);
		lbl_name.setFont(new Font("幼圆", Font.PLAIN, 15));
		lbl_name.setBounds(100, 117, 88, 15);
		panel.add(lbl_name);
		
		lbl_mainNum = new JLabel("2702170313");
		lbl_mainNum.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lbl_mainNum.setBounds(100, 145, 120, 15);
		panel.add(lbl_mainNum);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(10, 178, 200, 1);
		panel.add(separator);
		
		
		indexBtn.addActionListener(btnStyle);
		indexBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		indexBtn.setSelected(true);
		indexBtn.setIcon(new ImageIcon(StudentHome.class.getResource("/images/moren.png")));
		indexBtn.setRolloverIcon(new ImageIcon(StudentHome.class.getResource("/images/按钮.png")));
		indexBtn.setSelectedIcon(new ImageIcon(StudentHome.class.getResource("/images/按钮.png")));
		indexBtn.setBorder(null);
		indexBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		indexBtn.setBounds(10, 297, 200, 30);
		panel.add(indexBtn);
		
		searchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		searchBtn.addActionListener(btnStyle);
		searchBtn.setIcon(new ImageIcon(StudentHome.class.getResource("/images/moren.png")));
		searchBtn.setRolloverIcon(new ImageIcon(StudentHome.class.getResource("/images/按钮.png")));
		searchBtn.setSelectedIcon(new ImageIcon(StudentHome.class.getResource("/images/按钮.png")));
		searchBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		searchBtn.setBounds(10, 350, 200, 30);
		panel.add(searchBtn);
		
		resultBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		resultBtn.addActionListener(btnStyle);
		resultBtn.setIcon(new ImageIcon(StudentHome.class.getResource("/images/moren.png")));
		resultBtn.setRolloverIcon(new ImageIcon(StudentHome.class.getResource("/images/按钮.png")));
		resultBtn.setSelectedIcon(new ImageIcon(StudentHome.class.getResource("/images/按钮.png")));
		resultBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		resultBtn.setBorderPainted(false);
		resultBtn.setBorder(null);
		resultBtn.setBounds(10, 403, 200, 30);
		panel.add(resultBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			int x,y;
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getXOnScreen();
				y = e.getYOnScreen();
				StudentHome.this.setLocation(x-xx, y-xy);
			}
		});
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1308, 25);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_delete_sign_25px_1.png")));
		lblNewLabel_4.setBounds(1283, 0, 25, 26);
		panel_1.add(lblNewLabel_4);
		
		JComboBox<String> dateCbx = new JComboBox<String>();
		panelRepaint(panel_17);
		for(int i = 1;i<=8;i++){
			dateCbx.addItem("第"+i+"学期");
		}
		
		card = new CardLayout(0, 0);
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(219, 24, 1089, 776);
		contentPane.add(panel_2);
		panel_2.setLayout(card);
		
		pnl_index = new JPanel();
		pnl_index.setBackground(new Color(246, 248, 246));
		panel_2.add(pnl_index, "index");
		pnl_index.setLayout(null);
		
		lblWelcome = new JLabel("Welcome back,李逸凡");
		lblWelcome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblWelcome.setForeground(new Color(97, 89, 137));
		lblWelcome.setBounds(79, 21, 208, 25);
		pnl_index.add(lblWelcome);
		
		JLabel label = new JLabel("下面是您的基本信息");
		label.setForeground(new Color(169, 166, 185));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label.setBounds(63, 75, 160, 15);
		pnl_index.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(76, 174, 189, 124);
		pnl_index.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(229, 207, 230));
		panel_7.setBounds(0, 0, 189, 19);
		panel_3.add(panel_7);
		
		lbl_username = new JLabel("muzile");
		lbl_username.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lbl_username.setForeground(new Color(93, 85, 128));
		lbl_username.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_username.setBounds(0, 58, 189, 22);
		panel_3.add(lbl_username);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(329, 174, 189, 124);
		pnl_index.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(229, 207, 230));
		panel_8.setBounds(0, 0, 189, 19);
		panel_4.add(panel_8);
		
		lbl_num = new JLabel("2702170313");
		lbl_num.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_num.setForeground(new Color(93, 85, 128));
		lbl_num.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lbl_num.setBounds(0, 58, 189, 22);
		panel_4.add(lbl_num);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(579, 174, 189, 124);
		pnl_index.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(229, 207, 230));
		panel_9.setBounds(0, 0, 189, 19);
		panel_5.add(panel_9);
		
		lbl_sex = new JLabel("男");
		lbl_sex.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sex.setForeground(new Color(93, 85, 128));
		lbl_sex.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lbl_sex.setBounds(0, 57, 189, 22);
		panel_5.add(lbl_sex);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(828, 174, 189, 124);
		pnl_index.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(229, 207, 230));
		panel_10.setBounds(0, 0, 189, 19);
		panel_6.add(panel_10);
		
		JLabel label_6 = new JLabel("学生");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(new Color(93, 85, 128));
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_6.setBounds(0, 57, 189, 22);
		panel_6.add(label_6);
		
		JLabel lblNewLabel_5 = new JLabel("系统ID");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(97, 89, 137));
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_5.setBounds(133, 139, 65, 25);
		pnl_index.add(lblNewLabel_5);
		
		JLabel label_1 = new JLabel("学号");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(97, 89, 137));
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_1.setBounds(388, 139, 65, 25);
		pnl_index.add(label_1);
		
		JLabel label_2 = new JLabel("性别");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(97, 89, 137));
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_2.setBounds(637, 139, 65, 25);
		pnl_index.add(label_2);
		
		JLabel label_3 = new JLabel("角色");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(97, 89, 137));
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_3.setBounds(891, 139, 65, 25);
		pnl_index.add(label_3);
		
		JLabel label_7 = new JLabel("下面是您最新录入的考试结果");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(new Color(169, 166, 185));
		label_7.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_7.setBounds(63, 353, 224, 15);
		pnl_index.add(label_7);
		
		panel_17.setBackground(Color.WHITE);
		panel_17.setBounds(77, 378, 940, 377);
		panel_17.setLayout(new BorderLayout(0, 0));
		pnl_index.add(panel_17);
		
		JPanel pnl_search = new JPanel();
		pnl_search.setBackground(new Color(246, 248, 246));
		panel_2.add(pnl_search, "search");
		pnl_search.setLayout(null);
		
		JLabel label_8 = new JLabel("成绩查询");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(new Color(97, 89, 137));
		label_8.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		label_8.setBounds(63, 21, 108, 25);
		pnl_search.add(label_8);
		
		JLabel label_9 = new JLabel("请选择哪一场考试的成绩");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(new Color(169, 166, 185));
		label_9.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_9.setBounds(74, 77, 170, 15);
		pnl_search.add(label_9);
		
		dateCbx.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cbx = (JComboBox<String>) e.getSource();
				date = (String) cbx.getSelectedItem();
//				String[] values = value.split("[^\\d]+");
//				date = "第"+values[1]+"学期";
				panelRepaint(panel_18);
				getSearchTbl(false);
			}
		});
		dateCbx.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dateCbx.setForeground(new Color(169, 166, 185));
		dateCbx.setBackground(new Color(246,248,246));
		dateCbx.setBounds(84, 114, 160, 25);
		pnl_search.add(dateCbx);
		
		JLabel label_10 = new JLabel("下面是您该学期的成绩情况");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(new Color(169, 166, 185));
		label_10.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 15));
		label_10.setBounds(74, 201, 205, 15);
		pnl_search.add(label_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(229, 207, 230));
		panel_11.setBounds(84, 238, 950, 10);
		pnl_search.add(panel_11);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_search_property_25px.png")));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(271, 190, 25, 37);
		pnl_search.add(lblNewLabel_7);
		
		JButton outBtn = new JButton("一键导出");
		outBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] titles = {"序号","科目名称","成绩","学分","成绩所属学期"};
				score.setDate(date);
				POIUtils.createExcelFile(titles, service.getListByDate(user, score, false));
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Ensure(StudentHome.this);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		outBtn.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_ms_excel_25px.png")));
		outBtn.setBackground(new Color(246,248,246));
		outBtn.setForeground(new Color(97, 89, 137));
		outBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		outBtn.setBounds(912, 179, 122, 37);
		pnl_search.add(outBtn);
		
		panel_18.setBackground(Color.WHITE);
		panel_18.setBounds(84, 248, 950, 481);
		panel_18.setLayout(new BorderLayout(0, 0));
		pnl_search.add(panel_18);
		
		JButton button = new JButton("按成绩排序");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRepaint(panel_18);
				getSearchTbl(true);
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_generic_sorting_filled_15px.png")));
		button.setForeground(new Color(97, 89, 137));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBackground(new Color(246, 248, 246));
		button.setBounds(306, 197, 131, 25);
		pnl_search.add(button);
		
		JPanel pnl_result = new JPanel();
		pnl_result.setBackground(new Color(246,248,246));
		panel_2.add(pnl_result, "result");
		pnl_result.setLayout(null);
		
		JLabel label_11 = new JLabel("成绩总结与分析");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(new Color(97, 89, 137));
		label_11.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		label_11.setBounds(69, 21, 161, 25);
		pnl_result.add(label_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_12.setBounds(148, 223, 235, 412);
		pnl_result.add(panel_12);
		panel_12.setLayout(null);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(0, 0, 235, 140);
		panel_12.add(panel_14);
		panel_14.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_line_chart_120px_3.png")));
		lblNewLabel_8.setBounds(56, 10, 120, 95);
		panel_14.add(lblNewLabel_8);
		
		JLabel label_12 = new JLabel("平均成绩趋势图");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setForeground(new Color(159, 156, 175));
		label_12.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_12.setBounds(39, 115, 158, 15);
		panel_14.add(label_12);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(33, 150, 179, 2);
		separator_1.setForeground(new Color(97, 89, 137));
		panel_12.add(separator_1);
		
		JButton resultB1 = new JButton("查看");
		resultB1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				btn.setBackground(new Color(153,132,220));
				btn.setBounds(67, 248,101, 46);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				btn.setBackground(new Color(153,132,172));
				btn.setBounds(68, 249, 99, 44);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				averageScoreAnalysis();
			}
		});
		resultB1.setFont(new Font("幼圆", Font.PLAIN, 14));
		resultB1.setBounds(68, 249, 99, 44);
		resultB1.setBorder(null);
		resultB1.setBackground(new Color(153,132,172));
		panel_12.add(resultB1);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		panel_13.setBounds(692, 223, 235, 412);
		pnl_result.add(panel_13);
		panel_13.setLayout(null);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_15.setBounds(0, 0, 235, 140);
		panel_13.add(panel_15);
		panel_15.setLayout(null);
		
		JLabel label_13 = new JLabel("");
		label_13.setIcon(new ImageIcon(StudentHome.class.getResource("/images/icons8_pie_chart_110px.png")));
		label_13.setBounds(62, 0, 110, 115);
		panel_15.add(label_13);
		
		JLabel label_14 = new JLabel("不及格科目占比");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(new Color(159, 156, 175));
		label_14.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_14.setBounds(47, 115, 149, 15);
		panel_15.add(label_14);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(97, 89, 137));
		separator_2.setBounds(31, 150, 179, 2);
		panel_13.add(separator_2);
		
		JButton resultB2 = new JButton("查看");
		resultB2.setFont(new Font("幼圆", Font.PLAIN, 14));
		resultB2.setBorder(null);
		resultB2.setBackground(new Color(153, 132, 172));
		resultB2.setBounds(68, 249, 99, 44);
		resultB2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				btn.setBackground(new Color(153,132,220));
				btn.setBounds(67, 248,101, 46);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				btn.setBackground(new Color(153,132,172));
				btn.setBounds(68, 249, 99, 44);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				passRateAnalysis();
			}
		});
		panel_13.add(resultB2);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(229, 207, 230));
		panel_16.setBounds(148, 179, 779, 10);
		pnl_result.add(panel_16);
		
		JLabel label_16 = new JLabel("点击查看即可查看成绩分析");
		label_16.setForeground(new Color(169, 166, 185));
		label_16.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_16.setBounds(90, 77, 185, 15);
		pnl_result.add(label_16);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBounds(90, 102, 171, 2);
		pnl_result.add(separator_3);
		
	}
	
	private void getIndexTbl(){
		JTable table;
		List<Result> list = service.resList(user);
		if(list.size()>0){
			String[][] data = new String[list.size()][5];
			for(int i=0;i<list.size();i++){
				data[i][0]=String.valueOf(list.get(i).getSid());
				data[i][1]=list.get(i).getSname();
				data[i][2]=list.get(i).getScore();
				data[i][3]=list.get(i).getGpa();
				data[i][4]=list.get(i).getDate();
			}
			table = new JTable(data,indexTblName);
			table.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
			table.setForeground(new Color(97, 89, 137));
			table.setEnabled(false);
			table.getTableHeader().setReorderingAllowed(false);
			/*table.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					int column = table.getSelectedColumn();
					int row = table.getSelectedRow();
					System.out.println(row+"行，"+column+"列");
				}
			});*/
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.getViewport().setBackground(Color.WHITE);
			panel_17.add(scrollPane,BorderLayout.CENTER);
		}else{
			table = new JTable();
			table.setBounds(76, 406, 941, 349);
			panel_17.add(table,BorderLayout.CENTER);
		}
	}
	
	private void getSearchTbl(Boolean isOrderByScore){
		JTable table;
		score.setDate(date);
		List<Result> list = service.getListByDate(user, score,isOrderByScore);
		if(list.size()>0){
			String[][] data = new String[list.size()][5];
			for(int i=0;i<list.size();i++){
				data[i][0]=String.valueOf(list.get(i).getSid());
				data[i][1]=list.get(i).getSname();
				data[i][2]=list.get(i).getScore();
				data[i][3]=list.get(i).getGpa();
				data[i][4]=list.get(i).getDate();
				table = new JTable(data,searchTblName);
				table.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
				table.setForeground(new Color(97, 89, 137));
				table.getTableHeader().setReorderingAllowed(false);
				table.setEnabled(false);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.getViewport().setBackground(Color.WHITE);
				panel_18.add(scrollPane,BorderLayout.CENTER);
			}
		}
			else{
				table = new JTable();
				table.setBounds(76, 406, 941, 349);
				panel_18.add(table,BorderLayout.CENTER);
			}
	}
	
	private void panelRepaint(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.repaint();
	}
	
	public void averageScoreAnalysis(){
		//获得总共学期数
		int semesters = service.getMaxDate(user);
		//分别查询每个学期的总成绩，并计算平均成绩后封装到Map中
		List<Double> scoreList = new ArrayList<Double>();
		if(semesters>0){
			for(int i=1;i<=semesters;i++){
				//score.flag域
				score.setFlag(i);
				double result = service.getAverageScore(user, score);
				scoreList.add(result);
			}
		}
		String path = "";
		try {
			path = AnalysisUtils.creatLineChartImage("平均成绩","分数","学期",scoreList);
			new Charts("分析",path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void passRateAnalysis(){
		double result = service.getPassRate(user);
		
		String path = "";
		try {
			path = AnalysisUtils.creatPieChartImage("fail rate", result);
			new Charts("分析",path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
