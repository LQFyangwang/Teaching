package com.gs.puzzle;

import javax.swing.SwingUtilities;

public class PuzzleStarter {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new PuzzleFrame();
				
			}
			
		});
	}

}
