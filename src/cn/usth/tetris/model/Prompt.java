package cn.usth.tetris.model;

import java.awt.Color;

import cn.usth.tetris.view.*;

public class Prompt {
	long score = 0;// 得分
	int totalLines = 0;// 消除总行数
	int currentLines = 0;// 当前级别下消除行数
	int level = 1;// 当前级别
	int delay = 500;// 当前Brick下落速度
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

	// 随机生成一个brick
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