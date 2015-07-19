package cn.usth.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import cn.usth.tetris.model.*;
import cn.usth.tetris.view.*;

public class House extends TetrisFrame implements ActionListener {
	// 定义属性
	Brick brick = BrickFactory.getBrick();
	Prompt prompt = new Prompt();
	Wall wall = new Wall();
	Timer timer = new Timer(prompt.getDelay(), this);

	public House() {
		// 为当前类添加事件监听器
		this.addKeyListener(new keyAdp());
		// 为菜单项添加事件监听器
		menuBar.menuItem_Start.addActionListener(this);
		menuBar.menuItem_Suspend.addActionListener(this);
		menuBar.menuItem_Exit.addActionListener(this);
		for (int i = 0; i < 10; i++) {
			menuBar.JRadioBtnMenuItem[i].addActionListener(this);
		}
	}

	// 定义KeyEvent事件剪裁类
	class keyAdp extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				moveBrickLeft();
				break;
			case KeyEvent.VK_RIGHT:
				moveBrickRight();
				break;
			case KeyEvent.VK_DOWN:
				moveBrickDown();
				break;
			case KeyEvent.VK_UP:
				brickRotate();
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			moveBrickDown();
			return;
		} else if (e.getActionCommand() == "开始") {
			gameBegin();
			return;
		} else if (e.getSource() == menuBar.menuItem_Suspend) {
			gameSuspend(e);
			return;
		} else if (e.getActionCommand() == "退出") {
			gameExit();
			return;
		} else {
			setGameLevel(e);
		}
	}

	/* 以下定义控制层方法 */
	// 游戏开始
	public void gameBegin() {
		timer.start();
		menuBar.menuItem_Suspend.setText("暂停");
		// 清空上次游戏记录
		wall.clear();
		prompt.clear();
		jp_Left.clearButtonArray();
		jp_Left.clearPrompt();
		jp_Right.clearButtonArray();
		// 创建游戏区方块和提示区方块
		brick = BrickFactory.getBrick();
		prompt.setBrick();
		// 显示游戏区和提示区
		jp_Right.showBrick(brick);
		jp_Left.showPrompt(prompt);
		jp_Left.showBrick(prompt.getBrick());
	}

	// 游戏暂停
	public void gameSuspend(ActionEvent e) {
		if (e.getActionCommand() == "暂停") {
			timer.stop();
			menuBar.menuItem_Suspend.setText("继续");
		} else {
			timer.start();
			menuBar.menuItem_Suspend.setText("暂停");
		}
	}

	// 退出游戏
	public void gameExit() {
		System.exit(0);
	}

	// 设置游戏级别
	public void setGameLevel(ActionEvent e) {
		int level = Integer.parseInt(e.getActionCommand());
		prompt.setLevel(level);
		prompt.setDelay(500 - (level - 1) * 50);
		timer.setDelay(prompt.getDelay());
		jp_Left.showPrompt(prompt);
	}

	// 方块左移
	public void moveBrickLeft() {
		if (wall.canBrickMoveLeft(brick)) {
			brick.moveLeft();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		}
	}

	// 方块右移
	public void moveBrickRight() {
		if (wall.canBrickMoveRight(brick)) {
			brick.moveRight();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		}
	}

	// 方块下移
	public void moveBrickDown() {
		if (wall.canBrickMoveDown(brick)) {
			brick.moveDown();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		} else {
			if (isGameOver()) {
				JOptionPane.showMessageDialog(this, "游戏结束!");
				timer.stop();
			} else {
				wall.buildWall(brick);
				int lines = 0;
				lines = wall.removeWall();
				brick = prompt.getBrick();
				prompt.setBrick();
				jp_Left.clearButtonArray();
				jp_Right.clearButtonArray();
				jp_Left.showBrick(prompt.getBrick());
				jp_Right.showWall(wall);
				jp_Right.showBrick(brick);
			}

		}
	}

	// 方块旋转
	public void brickRotate() {
		if (wall.canBrickRotate(brick)) {
			brick.rotate();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		}
	}

	// 游戏是否结束
	public boolean isGameOver() {
		if (brick.downStep == 0) {
			if (!wall.canBrickMoveDown(brick)) {
				return true;
			}
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (brick.BrickArray[i][j] == 1) {
						if (wall.WallArray[i + brick.getX()]
						                   [j + brick.getY()].PosFlag == 1) {
							return true;
						}
					}
				}
		}
		return false;
	}
}
