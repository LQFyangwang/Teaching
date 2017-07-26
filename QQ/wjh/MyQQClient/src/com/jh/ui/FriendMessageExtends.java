package com.jh.ui;

import java.awt.Color;

import com.jh.bean.Account;
import com.jh.common.Constants;

public class FriendMessageExtends extends FriendMessageFrame {

	private static final long serialVersionUID = -3851318770072491533L;

	public FriendMessageExtends(Account account) {
		super(account);
		super.addBtn.setEnabled(false);
		super.addBtn.setBackground(new Color(0, 0, 0, 0));
		Constants.isFriendMessageExtendsOpen = false;
	}

}
