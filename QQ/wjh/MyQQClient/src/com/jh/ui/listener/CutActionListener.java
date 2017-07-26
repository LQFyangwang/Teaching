package com.jh.ui.listener;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import com.jh.common.Constants;
import com.jh.ui.common.Cut;

public class CutActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("news")) {
			new Cut();
		} else if (action.equals("seek")) {
			if (Constants.cutName != null && !Constants.cutName.equals("")) {
				try {
					Desktop.getDesktop().open(new File("e:/workspace/MyQQClient/image/" + Constants.cutName));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
