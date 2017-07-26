package com.gs.file;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class FileCopyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -5877275012397535163L;

	public FileCopyFrame() {
		setTitle("文件copy");
		setSize(400, 100);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new FlowLayout());
		JButton btn1 = new JButton("选择源文件");
		btn1.setActionCommand("o");
		btn1.addActionListener(this);
		JButton btn2 = new JButton("选择目标路径");
		btn2.setActionCommand("d");
		btn2.addActionListener(this);
		add(btn1);
		add(btn2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private File oFile;
	@Override 
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		JFileChooser fc = new JFileChooser();
		if (action.equals("o")) {
			int result = fc.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				oFile = fc.getSelectedFile();
			}
		} else {
			int result = fc.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File dir = fc.getCurrentDirectory();
				if (oFile == null) {
					JOptionPane.showMessageDialog(this, "还没有选择源文件", "警告", JOptionPane.WARNING_MESSAGE);
				} else {
					File desFile = new File(dir.getAbsolutePath() + "/" + "copy-" +oFile.getName());
					FileCopy.copy(oFile, desFile);
					JOptionPane.showMessageDialog(this, "复制成功", "警告", JOptionPane.WARNING_MESSAGE);
					oFile = null;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FileCopyFrame();
			}
		});
	}

}
