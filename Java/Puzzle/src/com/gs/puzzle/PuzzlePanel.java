package com.gs.puzzle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {

	private static final long serialVersionUID = 1331577633463322939L;
	
	private BufferedImage image;
	private int rows;
	private int cols;
	
	public PuzzlePanel() {
		
	}

	public void newImage(File file) {
		image = PuzzleUtil.getImageFromFile(file);
		repaint();
	}
	
	public void newImage(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}
	
	@Override
	public void paint(Graphics g) {
		if (image != null) {
			int width = image.getWidth();
			int ratio = PuzzleUtil.getRatio(width);
			g.drawImage(image, 0, 0, width / ratio, image.getHeight() / ratio, null);
			
			BufferedImage[][] images = PuzzleUtil.getImages(image, 3, 3);
			int w = images[0][0].getWidth() / ratio;
			int h = images[0][0].getHeight() / ratio;
			int[] middle = PuzzleUtil.getMiddle(rows, cols);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					int x = j * w + j * 5;
					int y = i * h + i * 5 + 360;
					if (i == middle[0] && j == middle[1]) {
						Graphics2D gg = images[i][j].createGraphics();
						gg.setColor(Color.GREEN);
						gg.setStroke(new BasicStroke(50));
						gg.drawRect(x, y, w, h);
						
					}
					g.drawImage(images[i][j], x, y, w, h, null);
				}
			}
		} 
	}

}
