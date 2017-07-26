package com.gs.listener;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class FieldListener implements TextListener {

	@Override
	public void textValueChanged(TextEvent e) {
		System.out.println("text changed...");
	}

}
