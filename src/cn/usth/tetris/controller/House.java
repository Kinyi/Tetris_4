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
	// ��������
	Brick brick = BrickFactory.getBrick();
	Prompt prompt = new Prompt();
	Wall wall = new Wall();
	Timer timer = new Timer(prompt.getDelay(), this);

	public House() {
		// Ϊ��ǰ������¼�������
		this.addKeyListener(new keyAdp());
		// Ϊ�˵�������¼�������
		menuBar.menuItem_Start.addActionListener(this);
		menuBar.menuItem_Suspend.addActionListener(this);
		menuBar.menuItem_Exit.addActionListener(this);
		for (int i = 0; i < 10; i++) {
			menuBar.JRadioBtnMenuItem[i].addActionListener(this);
		}
	}

	// ����KeyEvent�¼�������
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
		} else if (e.getActionCommand() == "��ʼ") {
			gameBegin();
			return;
		} else if (e.getSource() == menuBar.menuItem_Suspend) {
			gameSuspend(e);
			return;
		} else if (e.getActionCommand() == "�˳�") {
			gameExit();
			return;
		} else {
			setGameLevel(e);
		}
	}

	/* ���¶�����Ʋ㷽�� */
	// ��Ϸ��ʼ
	public void gameBegin() {
		timer.start();
		menuBar.menuItem_Suspend.setText("��ͣ");
		// ����ϴ���Ϸ��¼
		wall.clear();
		prompt.clear();
		jp_Left.clearButtonArray();
		jp_Left.clearPrompt();
		jp_Right.clearButtonArray();
		// ������Ϸ���������ʾ������
		brick = BrickFactory.getBrick();
		prompt.setBrick();
		// ��ʾ��Ϸ������ʾ��
		jp_Right.showBrick(brick);
		jp_Left.showPrompt(prompt);
		jp_Left.showBrick(prompt.getBrick());
	}

	// ��Ϸ��ͣ
	public void gameSuspend(ActionEvent e) {
		if (e.getActionCommand() == "��ͣ") {
			timer.stop();
			menuBar.menuItem_Suspend.setText("����");
		} else {
			timer.start();
			menuBar.menuItem_Suspend.setText("��ͣ");
		}
	}

	// �˳���Ϸ
	public void gameExit() {
		System.exit(0);
	}

	// ������Ϸ����
	public void setGameLevel(ActionEvent e) {
		int level = Integer.parseInt(e.getActionCommand());
		prompt.setLevel(level);
		prompt.setDelay(500 - (level - 1) * 50);
		timer.setDelay(prompt.getDelay());
		jp_Left.showPrompt(prompt);
	}

	// ��������
	public void moveBrickLeft() {
		if (wall.canBrickMoveLeft(brick)) {
			brick.moveLeft();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		}
	}

	// ��������
	public void moveBrickRight() {
		if (wall.canBrickMoveRight(brick)) {
			brick.moveRight();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		}
	}

	// ��������
	public void moveBrickDown() {
		if (wall.canBrickMoveDown(brick)) {
			brick.moveDown();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		} else {
			if (isGameOver()) {
				JOptionPane.showMessageDialog(this, "��Ϸ����!");
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

	// ������ת
	public void brickRotate() {
		if (wall.canBrickRotate(brick)) {
			brick.rotate();
			jp_Right.clearButtonArray();
			jp_Right.showWall(wall);
			jp_Right.showBrick(brick);
		}
	}

	// ��Ϸ�Ƿ����
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
