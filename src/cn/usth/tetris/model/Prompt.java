package cn.usth.tetris.model;

import java.awt.Color;

import cn.usth.tetris.view.*;

public class Prompt {
	long score = 0;// �÷�
	int totalLines = 0;// ����������
	int currentLines = 0;// ��ǰ��������������
	int level = 1;// ��ǰ����
	int delay = 500;// ��ǰBrick�����ٶ�
	Brick brick;

	public void clear(){
		score = 0;
		totalLines = 0;
		delay = 500;
		brick=null;
	}
	public long getScore() {
		return score;
	}

	public void setScore(int lines) {
		switch (lines) {
		case 1:
			score = score + 10;
			break;
		case 2:
			score = score + 25;
			break;
		case 3:
			score = score + 40;
			break;
		case 4:
			score = score + 60;
			break;
		}

	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines += totalLines;
	}

	public int getCurrentLines() {
		return currentLines;
	}

	public void setCurrentLines(int currentLines) {
		this.currentLines = currentLines;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Brick getBrick() {
		return brick;
	}

	// �������һ��brick
	public void setBrick() {
		brick = BrickFactory.getBrick();
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
}