package com.xk.qq.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.xk.qq.ui.common.MouseEnterExitIconUtil;

public class TrigonListener extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "images/triangle_over.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MouseEnterExitIconUtil.change(e, "images/triangle_def.png");
	}
	
}
