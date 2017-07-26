package com.gs.puzzle;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;

public class PuzzleFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5098168930790350690L;
	
	private PuzzlePanel puzzlePanel;
	
	public PuzzleFrame() {
		setSize(700, 700);
		setTitle("∆¥Õº”Œœ∑");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		puzzlePanel = new PuzzlePanel();
		add(puzzlePanel, BorderLayout.CENTER);
		initWidgets();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initWidgets() {
		JPanel panel = new JPanel();
		JButton chooseImage = new JButton("—°‘ÒÕº∆¨");
		chooseImage.setActionCommand("chooseImage");
		chooseImage.addActionListener(this);
		panel.add(chooseImage);
		add(chooseImage, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("chooseImage")) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle(Constants.CHOOSER_TITLE);
			chooser.setFileFilter(new FileFilter() {

				@Override
				public boolean accept(File f) {
					return PuzzleUtil.isRigthImageType(f.getName());
				}

				@Override
				public String getDescription() {
					return null;
				}
				
			});
			int result = chooser.showOpenDialog(PuzzleFrame.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				puzzlePanel.newImage(chooser.getSelectedFile());
				puzzlePanel.newImage(3, 3);
				validate();
				repaint();
			}
		}
	}

}
