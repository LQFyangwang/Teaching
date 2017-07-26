package com.gs.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = -8153506578750980360L;
	
	private BufferedImage[] images;
	private int[][] map;
	private int currentLevel;
	private String levelInfo;
	private Stack<GameStep> maps;
	
	public GamePanel() {
		
	}
	
	public GamePanel(int level) {
		maps = new Stack<GameStep>();
		images = GameUtil.getImages();
		map = GameUtil.getLevel(level);
		currentLevel = level;
		levelInfo = "第"  + level + "关"; 
		setFocusable(true); // 设置是否可以获取焦点
		requestFocus(); // 请求焦点
		addKeyListener(this);
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void focus() {
		setFocusable(true); // 设置是否可以获取焦点
		requestFocus(); // 请求焦点
	}
	
	/**
	 * 根据之前已经获取的关卡地图和图片把相应的关卡绘制出来
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		for (int row = 0; row < Constants.TOTAL; row++) {
			for (int col = 0; col < Constants.TOTAL; col++) {
				g.setFont(new Font("楷体", Font.BOLD, 48));
				g.setColor(Color.GREEN);
				g.drawString(levelInfo, 240, 60);
				int imageIndex = map[row][col];
				g.drawImage(images[imageIndex], col * Constants.IMAGE_SIZE, row * Constants.IMAGE_SIZE, 
						Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, null);
			}
		}
	}
	
	/**
	 * 重新选择关卡后，需要重新设置GamePanel中的当前关卡数，重新获得当前关卡所对应的地图，重新设置显示的关卡信息
	 * 再调用repaint方法，repaint会自动调用update，update自动调用paint方法
	 * @param level
	 */
	public void chooseLevel(int level) {
		maps.clear();
		if (level <= 0) {
			level = Constants.LEVEL_1;
		} else if (level >= 12) {
			level = Constants.LEVEL_11;
		}
		map = GameUtil.getLevel(level);
		currentLevel = level;
		levelInfo = "第"  + level + "关"; 
		currentSpriteIsOnDestination = false;
		repaint();
		focus();
	}
	
	// 当前的精灵是否在目标位置上
	// 每次人移动的时候，都需要判断当前人的位置是否是在目标位置上，如果是，则此位置应该变成目标位置
	// 如果否，则不用变成目标位置
	// 如果人移到的下一个位置是目标位置，或者 目标箱子，则意味着当前人在目标位置上，此值为true，否则值变成false
	private boolean currentSpriteIsOnDestination = false; 
	private void move(int direction, int row1, int col1, int row2, int col2, int row3, int col3) {
		/**
		 * 1、 前一个位置是房子
		 * 	什么都不做
		 * 2、前一个位置是箱子，其下一个位置是箱子
		 * 	什么都不做
		 * 3、前一个位置是箱子，其下一个位置是目标箱子
		 * 	什么都不做
		 * 4、前一个位置是目标箱子，其下一个位置是箱子
		 * 	什么都不做
		 * 5、前一个位置是目标箱子，其下一个位置是目标箱子
		 * 	什么都不做
		 * 
		 * 6、前一个位置是箱子，其下一个位置是目标位置
		 * 	把箱子移到目标位置，箱子需要变成目标箱子，人往前移，人原先的位置如果不是目标位置，则变成草地
		 * 7、前一个位置是箱子，其下一个位置是草地
		 * 	把箱子移动下一个位置，箱子的位置变成草地，人往前移，人原先的位置如果不是目标位置，则变成草地
		 * 8、前一个位置是目标箱子，其下一个位置 是目标位置
		 * 	把该目标箱子移到下一个位置，原先目标箱子的位置变成目标位置，人往前走，人原先的位置如果不是目标位置，则变成草地
		 * 9、前一个位置 是目标箱子，其下一个位置是草地
		 * 	把目标箱子移动到下一个位置，把目标箱子的位置改成目标位置，人往前走，人原先的位置如果不是目标位置，则变成草地
		 * 10、前一个位置是目标位置
		 * 	记录该目标位置的行与列，人移动到该目标位置
		 * 11、前一个位置是草地
		 * 	人直接走
		 */
		if (map[row2][col2] == GameUtil.HOUSE
				|| (map[row2][col2] == GameUtil.BOX && map[row3][col3] == GameUtil.BOX)
				|| (map[row2][col2] == GameUtil.BOX && map[row3][col3] == GameUtil.DES_BOX)
				|| (map[row2][col2] == GameUtil.DES_BOX && map[row3][col3] == GameUtil.BOX)
				|| (map[row2][col2] == GameUtil.DES_BOX && map[row3][col3] == GameUtil.DES_BOX)) {
			
		} else {
			GameStep gameStep = new GameStep();
			gameStep.setMap(copyArray());
			gameStep.setCurrentSpriteIsOnDestination(currentSpriteIsOnDestination);
			maps.push(gameStep);
			if (map[row2][col2] == GameUtil.BOX && map[row3][col3] == GameUtil.DESTINATION) { // 6
				map[row3][col3] = GameUtil.DES_BOX; // 移动箱子到目标位置
				map[row2][col2] = direction; // 人移动到下一个位置
				if (currentSpriteIsOnDestination) {
					map[row1][col1] = GameUtil.DESTINATION;
					currentSpriteIsOnDestination = false;
				} else {
					map[row1][col1] = GameUtil.GRASS;
				}
			} else if (map[row2][col2] == GameUtil.BOX && map[row3][col3] == GameUtil.GRASS) { // 7
				map[row3][col3] = GameUtil.BOX;
				map[row2][col2] = direction;
				if (currentSpriteIsOnDestination) {
					map[row1][col1] = GameUtil.DESTINATION;
					currentSpriteIsOnDestination = false;
				} else {
					map[row1][col1] = GameUtil.GRASS;
				}
			} else if (map[row2][col2] == GameUtil.DES_BOX && map[row3][col3] == GameUtil.DESTINATION) { // 8
				map[row3][col3] = GameUtil.DES_BOX;
				map[row2][col2] = direction;
				if (currentSpriteIsOnDestination) {
					map[row1][col1] = GameUtil.DESTINATION;
				} else {
					map[row1][col1] = GameUtil.GRASS;
				}
				currentSpriteIsOnDestination = true;
			} else if (map[row2][col2] == GameUtil.DES_BOX && map[row3][col3] == GameUtil.GRASS) { // 9
				map[row3][col3] = GameUtil.BOX;
				map[row2][col2] = direction;
				if (currentSpriteIsOnDestination) {
					map[row1][col1] = GameUtil.DESTINATION;
				} else {
					map[row1][col1] = GameUtil.GRASS;
				}
				currentSpriteIsOnDestination = true;
			} else if (map[row2][col2] == GameUtil.DESTINATION) { // 10
				map[row2][col2] = direction;
				if (currentSpriteIsOnDestination) {
					map[row1][col1] = GameUtil.DESTINATION;
				} else {
					map[row1][col1] = GameUtil.GRASS;
				}
				currentSpriteIsOnDestination = true;
			} else if (map[row2][col2] == GameUtil.GRASS) { // 11
				map[row2][col2] = direction;
				if (currentSpriteIsOnDestination) {
					map[row1][col1] = GameUtil.DESTINATION;
					currentSpriteIsOnDestination = false;
				} else {
					map[row1][col1] = GameUtil.GRASS;
				}
			}
		}
		
	}
	
	private void move(int direction) {
		int[] sprite = GameUtil.getSprite(map);
		int row = sprite[0];
		int col = sprite[1];
		switch(direction) {
		case KeyEvent.VK_UP:
			move(GameUtil.SPRITE_UP, row, col, row - 1, col, row - 2, col);		
			break;
		case KeyEvent.VK_DOWN:
			move(GameUtil.SPRITE_DOWN, row, col, row + 1, col, row + 2, col);
			break;
		case KeyEvent.VK_LEFT:
			move(GameUtil.SPRITE_LEFT, row, col, row, col - 1, row, col - 2);
			break;
		case KeyEvent.VK_RIGHT:
			move(GameUtil.SPRITE_RIGHT, row, col, row, col + 1, row, col + 2);
			break;
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP:// TODO 向上走
			move(KeyEvent.VK_UP);
			break;
		case KeyEvent.VK_DOWN:// TODO 向下走
			move(KeyEvent.VK_DOWN);
			break;
		case KeyEvent.VK_LEFT:// TODO 向左走
			move(KeyEvent.VK_LEFT);
			break;
		case KeyEvent.VK_RIGHT:// TODO 向右走
			move(KeyEvent.VK_RIGHT);
			break;
		}
		if (isWin()) {
			int result = JOptionPane.showConfirmDialog(getParent(), "是否继续下一关？", "恭喜您！", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				chooseLevel(getCurrentLevel() + 1);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	private boolean isWin() {
		for (int i = 0; i < Constants.TOTAL; i++) {
			for (int j = 0; j < Constants.TOTAL; j++) {
				if (map[i][j] == GameUtil.BOX) {
					return false;
				}
			}
		}
		return true;
	}
	
	private int[][] copyArray() {
		int[][] originalMap = new int[Constants.TOTAL][Constants.TOTAL];
		for (int i = 0; i < Constants.TOTAL; i++) {
			for (int j = 0; j < Constants.TOTAL; j++) {
				originalMap[i][j] = map[i][j];
			}
		}
		return originalMap;
	}
	
	public void previousStep() {
		if (!maps.empty()) { // 判断stack栈是否还有数据
			GameStep step = maps.pop();
			map = step.getMap();
			currentSpriteIsOnDestination = step.isCurrentSpriteIsOnDestination();
			repaint();
		}
		focus();
	}

}
