package com.gs.puzzle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

public class PuzzleUtil {

	public static boolean isRigthImageType(String fileName) {
		return fileName.endsWith(Constants.IMAGE_BMP) || fileName.endsWith(Constants.IMAGE_JPG)
				|| fileName.endsWith(Constants.IMAGE_PNG);
	}

	public static BufferedImage getImageFromFile(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println(Constants.ERROR_READING_IMAGE);
			e.printStackTrace();
		}
		return image;
	}

	public static int getRatio(int width) {
		return width / 360;
	}

	public static BufferedImage[][] getImages(BufferedImage image, int rows, int cols) {
		BufferedImage[][] images = new BufferedImage[rows][cols];
		List<BufferedImage> imageList = new ArrayList<BufferedImage>();
		int desWidth = image.getWidth() / cols;
		int desHeight = image.getHeight() / rows;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				BufferedImage img = image.getSubimage(j * desWidth, i * desHeight, desWidth, desHeight);
				imageList.add(img);
				Collections.shuffle(imageList);
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				images[i][j] = imageList.get(getIndex(i, j, cols));
			}
		}
		return images;
	}
	
	public static int getIndex(int row, int col, int cols) {
		return row * cols + col;
	}
	
	public static int[] getMiddle(int rows, int cols) {
		int middleIndex = rows * cols / 2;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (getIndex(i, j, cols) == middleIndex) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	
}
