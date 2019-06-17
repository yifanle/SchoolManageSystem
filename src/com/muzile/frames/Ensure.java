package com.muzile.frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Ensure extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Ensure(Component c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(c);
		setSize(450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240,242,240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("文件导出成功\r\n");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(80, 54, 284, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblcschoolmanagesys = new JLabel("文件路径为：C:/SchoolManageSys\r\n");//这里路径应该是动态的，为了省脑子，直接写死了
		lblcschoolmanagesys.setForeground(Color.RED);
		lblcschoolmanagesys.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		lblcschoolmanagesys.setHorizontalAlignment(SwingConstants.CENTER);
		lblcschoolmanagesys.setBounds(80, 125, 284, 45);
		contentPane.add(lblcschoolmanagesys);
		
		JLabel label = new JLabel("确认");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ensure.this.dispose();
			}
		});
		label.setForeground(Color.MAGENTA);
		label.setBackground(Color.CYAN);
		label.setFont(new Font("幼圆", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(191, 229, 65, 29);
		contentPane.add(label);
	}
}
