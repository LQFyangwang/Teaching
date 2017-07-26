package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;

public class HeadFrame extends JFrame implements MouseListener{
	
	private static final long serialVersionUID = -3947833859447497256L;
	private HeadUpdateDataFrame hudFrame;
	private int count = 1;
	private String headName;
	public String getHeadName() {
		return headName;
	}
	public HeadFrame(HeadUpdateDataFrame hudFrame){
		this.hudFrame = hudFrame;
		hudFrame.setHeadFrame(this);
		setSize(380,410);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		top();
		center();
		setUndecorated(true);
		FrameDragListener darg = new FrameDragListener(this);
		addMouseListener(darg);
		addMouseMotionListener(darg);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq_icon.png"));
		setVisible(true);
	}
	private void top(){
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30,144,255));
		panel.setLayout(null);
		panel.setBounds(0, 0, 380, 35);
		JLabel bjLbl = new JLabel(new ImageIcon("images/tubiao.png"));
		bjLbl.setBounds(0, 0, 35, 23);
		panel.add(bjLbl);
		
		JLabel minLbl = new JLabel(new ImageIcon("images/min_def.png"));
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(320, 0, 30, 30);
		panel.add(minLbl);
		
		JLabel exitLbl = new JLabel(new ImageIcon("images/exit_def.png"));
		exitLbl.setBounds(350, 0, 30, 30);
		exitLbl.setName("Head_exit");
		exitLbl.addMouseListener(new ExitListener(this,hudFrame));
		panel.add(exitLbl);
		add(panel);
	}
	private void center(){
		int x = 20;
		int y = 50;
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++ ){
				JLabel headLbl = new JLabel(new ImageIcon("images/head" + count + ".png"));
				headLbl.setBounds(x,y,70,70);
				headLbl.setName("head"+count);
				headLbl.addMouseListener(this);
				add(headLbl);
				x+=90;
				count++;
			}
			x = 20;
			y+=90;
		}
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel lbl = (JLabel)e.getSource();
		String name = lbl.getName();
		for(int i = 0; i < 17; i++){
			if(name.equals("head"+i)){
				hudFrame.getHeadLbl().setIcon(new ImageIcon("images/head"+i+".png"));
				headName = "head"+i;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new HeadFrame(null);
	}
}
