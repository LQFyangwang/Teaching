package com.xk.qq.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.xk.qq.ui.listener.ExitListener;
import com.xk.qq.ui.listener.FrameDragListener;
import com.xk.qq.ui.listener.MinListener;

public class SkinFrame extends JFrame {

	private static final long serialVersionUID = 4761813924990472273L;
	private MainFrame mainFrame;
	
	public SkinFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setSize(740, 500);
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

	private JLabel exitLbl;

	public JLabel getExitLbl() {
		return exitLbl;
	}

	private void top() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30,144,255));
		panel.setLayout(null);
		panel.setBounds(0, 0, 740, 35);
		
		ImageIcon tubiaoIcon = new ImageIcon("images/tubiao.png");
		JLabel tubiaoLbl = new JLabel(tubiaoIcon);
		tubiaoLbl.setBounds(10, 5, 35, 23);
		panel.add(tubiaoLbl);
		
		JLabel minLbl = new JLabel(new ImageIcon("images/min_def.png"));
		minLbl.setName("Login_min");
		minLbl.addMouseListener(new MinListener(this));
		minLbl.setBounds(680, 0, 30, 30);
		panel.add(minLbl);
		
		JLabel exitLbl = new JLabel(new ImageIcon("images/exit_def.png"));
		exitLbl.setBounds(710, 0, 30, 30);
		exitLbl.setName("Friend_exit");
		exitLbl.addMouseListener(new ExitListener(this,mainFrame));
		panel.add(exitLbl);
		
		add(panel);
	}
	
	private void center(){
		int x = 20;
		for(int i = 0; i < 6; i++){
			JLabel bjLbl = new JLabel(new ImageIcon("images/skin_background" + i + ".png")); 
			bjLbl.setBounds(x, 40, 100, 600);
			bjLbl.addMouseListener(new AlterListener(bjLbl));
			bjLbl.setName("skin"+i);
			add(bjLbl);
			x += 120;
		}
	}
	class AlterListener extends MouseAdapter{
		private JLabel lbl;
		public AlterListener(JLabel lbl) {
			this.lbl = lbl;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel)e.getSource();
			String name = lbl.getName();
			for(int i = 0; i < 6; i++){
				if(name.equals("skin"+i)){
					String icon = "images/skin_background" + i + ".png";
					mainFrame.getBjLbl().setIcon(new ImageIcon(icon));
				}
			}
		}
		
		
	}
	
}
