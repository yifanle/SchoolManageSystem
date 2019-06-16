package com.muzile.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

public class BtnStyleListener implements ActionListener{
	
	private Map<String,JButton> btngroup;
	
	public BtnStyleListener(Map<String,JButton> btngroup){
		this.btngroup = btngroup;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		for(Entry<String,JButton> entry : btngroup.entrySet()){
			if(entry.getKey().equals(btn.getText())){
				btn.setSelected(true);
				btn.setForeground(Color.WHITE);
			}else{
				entry.getValue().setSelected(false);
				entry.getValue().setForeground(Color.BLACK);
			}
		}
		
		
	}

}
