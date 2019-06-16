package com.muzile.frames;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.muzile.bean.Score;
import com.muzile.bean.Subject;
import com.muzile.bean.User;
import com.muzile.service.TeacherService;

@SuppressWarnings("serial")
public class Addframe extends JFrame {

	int xx,xy;
	
	private JPanel contentPane;
	private JTextField scoreField;
	private JLabel info;
	private TeacherService service = new TeacherService();
	private JComboBox<String> subjectCbx;
	private TeacherHome frame;
	boolean flag = false;
	String date;
	User user;
	Map<String,Subject> selectableSub = new HashMap<String,Subject>();
	String subjectName;

	/**
	 * Create the frame.
	 */
	public Addframe(String date,User user,TeacherHome frame) {
		this.user = user;
		this.date = date;
		this.frame = frame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 400, 413, 309);
		setUndecorated(true);
		setVisible(true);
		init();
	}
	
	private void init(){
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246,248,246));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("添加成绩");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(97, 89, 137));
		label.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		label.setBounds(174, 28, 92, 25);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Addframe.class.getResource("/images/icons8_add_property_25px.png")));
		lblNewLabel.setBounds(157, 24, 25, 35);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.GRAY);
		separator.setBounds(40, 58, 340, 1);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			int x,y;
			@Override
			public void mouseDragged(MouseEvent e) {
				x = e.getXOnScreen();
				y = e.getYOnScreen();
				Addframe.this.setLocation(x-xx, y-xy);
			}
		});
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 413, 25);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Addframe.this.dispose();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(Addframe.class.getResource("/images/icons8_delete_sign_25px_1.png")));
		lblNewLabel_1.setBounds(388, 0, 25, 25);
		panel.add(lblNewLabel_1);
		
		info = new JLabel("");
		info.setForeground(Color.RED);
		info.setFont(new Font("幼圆", Font.PLAIN, 12));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(47, 0, 331, 25);
		panel.add(info);
		
		scoreField = new JTextField();
		scoreField.addFocusListener(new FocusAdapter() {
			String regex = "^(\\d|[1-9]\\d|100)$";
			@Override
			public void focusLost(FocusEvent e) {
				JTextField score = (JTextField)e.getSource();
				String src =  score.getText();
				if(src.equals("")){
					info.setText("分数不能为空");
					flag = false;
				}else if(!Pattern.matches(regex, src)){
					info.setText("请输入0-100之间的数字");
					flag = false;
				}else{
					info.setText("");
					flag = true;
				}
			}
		});
		scoreField.setBackground(new Color(246,248,246));
		scoreField.setColumns(10);
		scoreField.setBorder(null);
		scoreField.setBounds(109, 184, 205, 21);
		contentPane.add(scoreField);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(109, 207, 205, 1);
		contentPane.add(separator_1);
		
		subjectCbx = new JComboBox<String>();
		subjectCbx.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cbx = (JComboBox<String>)e.getSource();
				subjectName = cbx.getSelectedItem().toString();
			}
		});
		subjectCbx.setForeground(new Color(169, 166, 185));
		subjectCbx.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		subjectCbx.setBackground(new Color(246, 248, 246));
		subjectCbx.setBounds(111, 105, 203, 25);
		List<Subject> subjects = service.getSubjects();
		for(Subject sub :subjects){
			subjectCbx.addItem(sub.getSname());
			selectableSub.put(sub.getSname(), sub);
		}
		contentPane.add(subjectCbx);
		
		JLabel label_3 = new JLabel("科目：");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(169, 166, 185));
		label_3.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 17));
		label_3.setBounds(59, 106, 51, 21);
		contentPane.add(label_3);
		
		JLabel label_2 = new JLabel("分数：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(169, 166, 185));
		label_2.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 17));
		label_2.setBounds(59, 182, 51, 21);
		contentPane.add(label_2);
		
		JButton button = new JButton("添加");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag){
					add();
				}else{
					info.setText("请正确填写信息");
				}
			}
		});
		button.setIcon(new ImageIcon(Addframe.class.getResource("/images/icons8_add_25px.png")));
		button.setForeground(new Color(97, 89, 137));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBackground(new Color(246, 248, 246));
		button.setBounds(144, 262, 122, 37);
		contentPane.add(button);
	}
	
	private void add(){
		//封装数据
		Score score = new Score();
		score.setDate(date);
		score.setScore(scoreField.getText());
		score.setSuid(selectableSub.get(subjectName).getSuid());
		score.setUid(user.getUid());
		String[] values = date.split("[^\\d]+");
		int flag = Integer.parseInt(values[1]);
		score.setFlag(flag);
		//调用service,先检查是否已经有该科目成绩,是否是同一个学期的
		if(!service.isExist(score)){
			service.add(score);
			frame.refresh();
		}else{
			JOptionPane.showMessageDialog(null, "本学期已经存在该科目成绩,您可以在修改页面修改,请勿重复添加", "警告", JOptionPane.ERROR_MESSAGE);
		}
		Addframe.this.dispose();
	}
}
