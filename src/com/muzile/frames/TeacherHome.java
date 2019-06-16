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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.muzile.bean.Result;
import com.muzile.bean.Score;
import com.muzile.bean.User;
import com.muzile.listeners.BtnStyleListener;
import com.muzile.service.TeacherService;
import com.muzile.utils.AnalysisUtils;
import com.muzile.utils.POIUtils;

@SuppressWarnings("serial")
public class TeacherHome extends JFrame {

	private JPanel contentPane;
	private JButton indexBtn;
	private JButton searchBtn;
	private JButton resultBtn;
	private CardLayout card;
	private JPanel panel;
	private JPanel panel_2;
	private Map<String,JButton> btngroup = new HashMap<String,JButton>();
	int xx,xy,count=1;
	private User user;
	private JLabel lbl_name;
	private JLabel lbl_mainNum;
	private JLabel lbl_username;
	private JLabel lbl_num;
	private JLabel lbl_sex;
	private JLabel lblWelcome;
	private JPanel pnl_menu;
	private JPanel panel_17;
	private JPanel panel_19;
	private JTable table;
	private boolean eidtflag = false;
	private String date;
	private String stuName;
	private Map<String,User> selectableStu = new HashMap<String,User>();
	private List<Result> list4Serach;
	private String resultDate;
	
	private TeacherService service = new TeacherService();
	
	
	/**
	 * Create the frame.
	 */
	public TeacherHome(User user) {
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1308, 800);
		setUndecorated(true);
		setVisible(true);
		init();
		//加载数据库
		load();
		initTable4Index();
		initTable4Search();
		
	}
	
	private void init(){
		card = new CardLayout(0, 0);
		contentPane = new JPanel();
		panel_17 = new JPanel();
		panel_19 = new JPanel();
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
				initTable4Index();
				card.show(panel_2, "index");
			}
		});
		searchBtn = new JButton("学生成绩表");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelRepaint(panel_19);
				initTable4Search();
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
		lblNewLabel.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_super_mario_50px.png")));
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
		lbl_name.setBounds(100, 117, 120, 15);
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
		indexBtn.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/moren.png")));
		indexBtn.setRolloverIcon(new ImageIcon(TeacherHome.class.getResource("/images/按钮.png")));
		indexBtn.setSelectedIcon(new ImageIcon(TeacherHome.class.getResource("/images/按钮.png")));
		indexBtn.setBorder(null);
		indexBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		indexBtn.setBounds(10, 297, 200, 30);
		panel.add(indexBtn);
		
		searchBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		searchBtn.addActionListener(btnStyle);
		searchBtn.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/moren.png")));
		searchBtn.setRolloverIcon(new ImageIcon(TeacherHome.class.getResource("/images/按钮.png")));
		searchBtn.setSelectedIcon(new ImageIcon(TeacherHome.class.getResource("/images/按钮.png")));
		searchBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		searchBtn.setBounds(10, 350, 200, 30);
		panel.add(searchBtn);
		
		resultBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		resultBtn.addActionListener(btnStyle);
		resultBtn.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/moren.png")));
		resultBtn.setRolloverIcon(new ImageIcon(TeacherHome.class.getResource("/images/按钮.png")));
		resultBtn.setSelectedIcon(new ImageIcon(TeacherHome.class.getResource("/images/按钮.png")));
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
				TeacherHome.this.setLocation(x-xx, y-xy);
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
		lblNewLabel_4.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_delete_sign_25px_1.png")));
		lblNewLabel_4.setBounds(1283, 0, 25, 26);
		panel_1.add(lblNewLabel_4);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(219, 24, 1089, 776);
		contentPane.add(panel_2);
		panel_2.setLayout(card);
		
		JPanel pnl_index = new JPanel();
		pnl_index.setBackground(new Color(246, 248, 246));
		panel_2.add(pnl_index, "index");
		pnl_index.setLayout(null);
		
		lblWelcome = new JLabel("Welcome back,李逸凡");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblWelcome.setForeground(new Color(97, 89, 137));
		lblWelcome.setBounds(63, 21, 208, 25);
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
		
		JLabel label_6 = new JLabel("教师");
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
		
		JLabel label_7 = new JLabel("下面是所有学生的信息");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(new Color(169, 166, 185));
		label_7.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_7.setBounds(63, 353, 160, 15);
		pnl_index.add(label_7);
		
		panel_17.setBackground(Color.WHITE);
		panel_17.setBounds(76, 388, 941, 357);
		pnl_index.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
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
		
		JComboBox<String> dateCbx = new JComboBox<String>();
		dateCbx.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cbx = (JComboBox<String>)e.getSource();
				date = (String) cbx.getSelectedItem();
				panelRepaint(panel_19);
				initTable4Search();
			}
		});
		dateCbx.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		dateCbx.setForeground(new Color(169, 166, 185));
		for(int i = 1;i<=8;i++){
			dateCbx.addItem("第"+i+"学期");
		}
		dateCbx.setBackground(new Color(246,248,246));
		dateCbx.setBounds(84, 114, 160, 25);
		pnl_search.add(dateCbx);
		
		JLabel label_10 = new JLabel("同学的成绩情况");
		label_10.setForeground(new Color(169, 166, 185));
		label_10.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 15));
		label_10.setBounds(215, 201, 108, 15);
		pnl_search.add(label_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(229, 207, 230));
		panel_11.setBounds(84, 238, 950, 10);
		pnl_search.add(panel_11);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_search_property_25px.png")));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(321, 191, 25, 37);
		pnl_search.add(lblNewLabel_7);
		
		JLabel label_5 = new JLabel("下面是");
		label_5.setForeground(new Color(169, 166, 185));
		label_5.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 15));
		label_5.setBounds(84, 202, 45, 15);
		pnl_search.add(label_5);
		
		JComboBox<String> stuNameCbx = new JComboBox<String>();
		stuNameCbx.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cbx = (JComboBox<String>)e.getSource();
				stuName = (String) cbx.getSelectedItem();
				panelRepaint(panel_19);
				initTable4Search();
			}
		});
		stuNameCbx.setForeground(new Color(169, 166, 185));
		stuNameCbx.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuNameCbx.setBackground(new Color(246, 248, 246));
		stuNameCbx.setBounds(129, 197, 85, 25);
		List<User> nameList = service.getStuNameList();
		for(User name : nameList){
			stuNameCbx.addItem(name.getName());
			selectableStu.put(name.getName(), name);
		}
		pnl_search.add(stuNameCbx);
		
		JLabel lbl_menu = new JLabel("");
		lbl_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(count%2==1){
					pnl_menu.setVisible(true);
				}else{
					pnl_menu.setVisible(false);
				}
				count++;
			}
		});
		lbl_menu.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_menu_filled_25px.png")));
		lbl_menu.setBounds(1009, 203, 25, 25);
		pnl_search.add(lbl_menu);
		
		pnl_menu = new JPanel();
		pnl_menu.setBackground(new Color(246,248,246));
		pnl_menu.setBounds(878, 114, 155, 79);
		pnl_menu.setVisible(false);
		pnl_menu.setLayout(null);
		pnl_search.add(pnl_menu);
		
		JButton button = new JButton("一键导出");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] title = {"序号","科目名称","科目成绩","科目学分","成绩所属学期"};
				POIUtils.createExcelFile(title, list4Serach,date,stuName);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Ensure(TeacherHome.this);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				pnl_menu.setVisible(false);
				count++;
			}
		});
		button.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_ms_excel_25px.png")));
		button.setForeground(new Color(97, 89, 137));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBackground(new Color(246, 248, 246));
		button.setBounds(0, 0, 155, 27);
		pnl_menu.add(button);
		
		JButton button_1 = new JButton("添加成绩");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new Addframe(date,selectableStu.get(stuName),TeacherHome.this);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				pnl_menu.setVisible(false);
				count++;
			}
		});
		button_1.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_add_25px.png")));
		button_1.setForeground(new Color(97, 89, 137));
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_1.setBackground(new Color(246, 248, 246));
		button_1.setBounds(0, 26, 155, 27);
		pnl_menu.add(button_1);
		
		JButton button_2 = new JButton("修改成绩");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eidtflag = true;
				pnl_menu.setVisible(false);
				count++;
			}
		});
		button_2.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_edit_property_25px.png")));
		button_2.setForeground(new Color(97, 89, 137));
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_2.setBackground(new Color(246, 248, 246));
		button_2.setBounds(0, 52, 155, 27);
		pnl_menu.add(button_2);
		
		panel_19.setBackground(Color.WHITE);
		panel_19.setBounds(84, 250, 950, 478);
		pnl_search.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JLabel btn_del = new JLabel("");
		btn_del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int) table.getValueAt(row, 0);
				int option = JOptionPane.showConfirmDialog(null, "数据删除后将无法恢复,是否要继续？", "确认", JOptionPane.YES_NO_OPTION);
				if(option==0){
					service.delete(id);
					panelRepaint(panel_19);
					initTable4Search();
				}
			}
		});
		btn_del.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_delete_25px.png")));
		btn_del.setBounds(974, 203, 25, 25);
		pnl_search.add(btn_del);
		
		JLabel label_15 = new JLabel("");
		label_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(eidtflag){
					int rowCount = table.getRowCount();
					for(int i =0;i<rowCount;i++){
						String checkStr = table.getValueAt(i, 2).toString();
						if(!Pattern.matches("^(\\d|[1-9]\\d|100)$", checkStr)){
							JOptionPane.showMessageDialog(null, "格式错误！", "警告", JOptionPane.ERROR_MESSAGE);
							panelRepaint(panel_19);
							initTable4Search();
							return;
						}
					}
					int option = JOptionPane.showConfirmDialog(null, "保存后旧的数据将被覆盖,是否要继续？", "确认", JOptionPane.YES_NO_OPTION);
					if(option==0){
						eidtflag = false;
						pnl_menu.setVisible(false);
						count++;
						//更新数据
						updata();
						panelRepaint(panel_19);
						initTable4Search();
					}else{
						panelRepaint(panel_19);
						initTable4Search();
					}
				}
			}
		});
		label_15.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_save_25px.png")));
		label_15.setBounds(939, 203, 25, 25);
		pnl_search.add(label_15);
		
		JPanel pnl_result = new JPanel();
		pnl_result.setBackground(new Color(246,248,246));
		panel_2.add(pnl_result, "result");
		pnl_result.setLayout(null);
		
		JLabel label_11 = new JLabel("成绩总结与分析");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(new Color(97, 89, 137));
		label_11.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		label_11.setBounds(76, 21, 161, 25);
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
		lblNewLabel_8.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_bar_chart_110px.png")));
		lblNewLabel_8.setBounds(64, 10, 110, 105);
		panel_14.add(lblNewLabel_8);
		
		JLabel label_12 = new JLabel("成绩排名情况");
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
				Score score = new Score();
				score.setDate(resultDate);
				List<User> list = service.getAnalysis4Rank(score);
				try {
					String path = AnalysisUtils.creatBarChartImage("成绩排名情况", "成绩", "姓名", list);
					System.out.println(resultDate);
					new Charts("分析",path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
		label_13.setIcon(new ImageIcon(TeacherHome.class.getResource("/images/icons8_pie_chart_110px.png")));
		label_13.setBounds(62, 0, 110, 115);
		panel_15.add(label_13);
		
		JLabel label_14 = new JLabel("不及格人数占比");
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
				Score score = new Score();
				score.setDate(resultDate);
				double passrate = service.getPassRate(score);
				try {
					String path = AnalysisUtils.creatPieChartImage("pass rate", passrate);
					System.out.println(resultDate);
					new Charts("分析",path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_13.add(resultB2);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(229, 207, 230));
		panel_16.setBounds(148, 179, 779, 10);
		pnl_result.add(panel_16);
		
		JLabel label_16 = new JLabel("点击查看即可查看成绩分析");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setForeground(new Color(169, 166, 185));
		label_16.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_16.setBounds(76, 70, 203, 15);
		pnl_result.add(label_16);
		
		JLabel label_4 = new JLabel("请先选择要总结的学期");
		label_4.setForeground(new Color(169, 166, 185));
		label_4.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
		label_4.setBounds(766, 102, 161, 15);
		pnl_result.add(label_4);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cbx = (JComboBox<String>)e.getSource();
				resultDate = cbx.getSelectedItem().toString();
			}
		});
		for(int i = 1;i<=8;i++){
			comboBox.addItem("第"+i+"学期");
		}
		comboBox.setForeground(new Color(169, 166, 185));
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		comboBox.setBackground(new Color(246, 248, 246));
		comboBox.setBounds(767, 129, 160, 25);
		pnl_result.add(comboBox);
	}
	
	private void load(){
		lbl_name.setText(user.getName());
		lbl_mainNum.setText(user.getNum());
		lbl_username.setText(user.getUsername());
		lbl_num.setText(user.getNum());
		lbl_sex.setText(user.getSex());
		lblWelcome.setText("Welcom back,"+user.getName());
	}
	
	private void initTable4Index(){
		DefaultTableModel model = null;
		JTable table = null;
		String[] columnNames = {"序号","姓名","性别","学号","账号","挂科科目数"};
		List<User> list = service.getUserList4Teacher();
		if(list.size()>0){
			model = new DefaultTableModel(columnNames,0){
				@Override
				public boolean isCellEditable(int row,int column){
					return false;
				}
			};
			for(User user : list){
				Object[] rowData = {user.getUid(),user.getName(),user.getSex(),user.getNum(),user.getUsername(),user.getFsn()};
				model.addRow(rowData);
			}
		}
		if(model!=null){
			table = new JTable(model);
			table.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
			table.setForeground(new Color(97, 89, 137));
			table.setEnabled(true);
			table.getTableHeader().setReorderingAllowed(false);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.getViewport().setBackground(Color.WHITE);
			panel_17.add(scrollPane,BorderLayout.CENTER);
		}
	}
	
	private void initTable4Search(){
		DefaultTableModel model = null;
		Score score = new Score();
		User searchUser = new User();
		score.setDate(date);
		if(selectableStu.get(stuName)!=null){
			searchUser.setUid(selectableStu.get(stuName).getUid());
			String[] columnNames = {"序号","科目名称","科目成绩","科目学","成绩所属学期"};
			list4Serach = service.getStuScore(searchUser, score);
			if(list4Serach.size()>0){
				model = new DefaultTableModel(columnNames,0){
					@Override
					public boolean isCellEditable(int row,int column){
						if(column!=2)
							return false;
						return eidtflag;
					}
				};
				for(Result res : list4Serach){
					Object[] rowData = {res.getSid(),res.getSname(),res.getScore(),res.getGpa(),res.getDate()};
					model.addRow(rowData);
				}
			}
			if(model!=null){
				table = new JTable(model);
				table.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 14));
				table.setForeground(new Color(97, 89, 137));
				table.setEnabled(true);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.getViewport().setBackground(Color.WHITE);
				panel_19.add(scrollPane,BorderLayout.CENTER);
			}
		}
	}
	
	private void panelRepaint(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.repaint();
	}
	
	private void updata(){
		int rowCount = table.getRowCount();
		List<Result> list = new ArrayList<Result>();
		for(int i = 0;i<rowCount ;i++){
			Result res = new Result();
			res.setSid(Integer.parseInt(table.getValueAt(i, 0).toString()));
			res.setScore(table.getValueAt(i, 2).toString());
			list.add(res);
		}
		
		service.update(list);
	}
	
	public void refresh(){
		panelRepaint(panel_19);
		initTable4Search();
	}
}
