package cn.usth.tetris.model;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.Test;

/*定义Wall类 */
public class Wall {// 已经堆积起来的静止的bricks
	// 属性:WallBrick类类型对象数组
	public WallBrick[][] WallArray = new WallBrick[20][10];

	// 构造函数
	public Wall() {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++) {
				WallArray[i][j] = new WallBrick();
			}
	}

	// 显示wall矩阵
	public void show() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(WallArray[i][j].PosFlag + " ");
			}
			System.out.println();
		}
	}

	public void clear() {
		for (int i = 0; i < WallArray.length; i++)
			for (int j = 0; j < WallArray[i].length; j++) {
				WallArray[i][j].PosFlag = 0;
			}
	}

	// 落下的方块变为墙的一部分
	public void buildWall(Brick brick) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (brick.BrickArray[i][j] == 1) {
					WallArray[i + brick.getX()][j + brick.getY()].PosFlag = 1;
					WallArray[i + brick.getX()][j + brick.getY()].color = brick.color;
				}
			}
	}

	// 将所有满行消除
	public int removeWall() {
		int CutLineNumber = 0;
		for (int i = 1; i < 20; i++) {
			boolean isLineFull = true;
			for (int j = 0; j < 10; j++) {
				if (WallArray[i][j].PosFlag == 0) {
					isLineFull = false;
					break;
				}
			}
			if (isLineFull) {
				removeALine(WallArray, i);
				CutLineNumber++;
			}
		}
		return CutLineNumber;
	}

	// 将LineCutNum行上面的wall全部下移一行
	private void removeALine(WallBrick[][] wall, int LineCutNum) {
		for (int i = LineCutNum; i > 0; i--)
			for (int j = 0; j < 10; j++) {
				wall[i][j] = wall[i - 1][j];
			}
	}

	// 方块能否左移
	public boolean canBrickMoveLeft(Brick brick) {
		for (int i = 0; i < brick.BrickArray.length; i++)
			for (int j = 0; j < brick.BrickArray[i].length; j++) {
				// 左移后左边是否超过左边界
				if (brick.BrickArray[i][j] == 1 && (j + brick.getY() - 1) < 0) {
					return false;
				}
				// 左移后是否与墙有重叠
				if (brick.BrickArray[i][j] == 1
						&& WallArray[i + brick.getX()][j + brick.getY() - 1].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}

	// 方块能否右移
	public boolean canBrickMoveRight(Brick brick) {
		for (int i = 0; i < brick.BrickArray.length; i++)
			for (int j = 0; j < brick.BrickArray[i].length; j++) {
				// 右移后右边是否超过右边界
				if (brick.BrickArray[i][j] == 1
						&& j + brick.getY() + 1 > WallArray[i].length - 1) {// >9
					return false;
				}
				// 右移后是否与墙有重叠
				if (brick.BrickArray[i][j] == 1
						&& WallArray[i + brick.getX()][j + brick.getY() + 1].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}

	// 方块能否下移
	public boolean canBrickMoveDown(Brick brick) {
		for (int i = 0; i < brick.BrickArray.length; i++)
			for (int j = 0; j < brick.BrickArray[i].length; j++) {
				// 下移后下边是否超过下边界
				if (brick.BrickArray[i][j] == 1
						&& i + brick.getX() + 1 > WallArray.length - 1) {// >19
					return false;
				}
				// 下移后是否与墙有重叠
				if (brick.BrickArray[i][j] == 1
						&& WallArray[i + brick.getX() + 1][j + brick.getY()].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}

	// 方块能否旋转
	public boolean canBrickRotate(Brick brick) {
		Brick temp = (Brick) brick.clone();
		temp.rotate();
		// 旋转后brick在wall中的位置是否超出wall的边界
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (temp.BrickArray[i][j] == 1) {
					if (i + temp.getX() < 0 || i + temp.getX() > 19) {
						return false;
					} else if (j + temp.getY() < 0 || j + temp.getY() > 9) {
						return false;
					}
				}
			}
		// 旋转后是否与墙有重叠
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (temp.BrickArray[i][j] == 1
						&& WallArray[i + temp.getX()][j + temp.getY()].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}
}