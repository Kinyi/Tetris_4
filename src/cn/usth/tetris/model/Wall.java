package cn.usth.tetris.model;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.Test;

/*����Wall�� */
public class Wall {// �Ѿ��ѻ������ľ�ֹ��bricks
	// ����:WallBrick�����Ͷ�������
	public WallBrick[][] WallArray = new WallBrick[20][10];

	// ���캯��
	public Wall() {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 10; j++) {
				WallArray[i][j] = new WallBrick();
			}
	}

	// ��ʾwall����
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

	// ���µķ����Ϊǽ��һ����
	public void buildWall(Brick brick) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (brick.BrickArray[i][j] == 1) {
					WallArray[i + brick.getX()][j + brick.getY()].PosFlag = 1;
					WallArray[i + brick.getX()][j + brick.getY()].color = brick.color;
				}
			}
	}

	// ��������������
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

	// ��LineCutNum�������wallȫ������һ��
	private void removeALine(WallBrick[][] wall, int LineCutNum) {
		for (int i = LineCutNum; i > 0; i--)
			for (int j = 0; j < 10; j++) {
				wall[i][j] = wall[i - 1][j];
			}
	}

	// �����ܷ�����
	public boolean canBrickMoveLeft(Brick brick) {
		for (int i = 0; i < brick.BrickArray.length; i++)
			for (int j = 0; j < brick.BrickArray[i].length; j++) {
				// ���ƺ�����Ƿ񳬹���߽�
				if (brick.BrickArray[i][j] == 1 && (j + brick.getY() - 1) < 0) {
					return false;
				}
				// ���ƺ��Ƿ���ǽ���ص�
				if (brick.BrickArray[i][j] == 1
						&& WallArray[i + brick.getX()][j + brick.getY() - 1].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}

	// �����ܷ�����
	public boolean canBrickMoveRight(Brick brick) {
		for (int i = 0; i < brick.BrickArray.length; i++)
			for (int j = 0; j < brick.BrickArray[i].length; j++) {
				// ���ƺ��ұ��Ƿ񳬹��ұ߽�
				if (brick.BrickArray[i][j] == 1
						&& j + brick.getY() + 1 > WallArray[i].length - 1) {// >9
					return false;
				}
				// ���ƺ��Ƿ���ǽ���ص�
				if (brick.BrickArray[i][j] == 1
						&& WallArray[i + brick.getX()][j + brick.getY() + 1].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}

	// �����ܷ�����
	public boolean canBrickMoveDown(Brick brick) {
		for (int i = 0; i < brick.BrickArray.length; i++)
			for (int j = 0; j < brick.BrickArray[i].length; j++) {
				// ���ƺ��±��Ƿ񳬹��±߽�
				if (brick.BrickArray[i][j] == 1
						&& i + brick.getX() + 1 > WallArray.length - 1) {// >19
					return false;
				}
				// ���ƺ��Ƿ���ǽ���ص�
				if (brick.BrickArray[i][j] == 1
						&& WallArray[i + brick.getX() + 1][j + brick.getY()].PosFlag == 1) {
					return false;
				}
			}
		return true;
	}

	// �����ܷ���ת
	public boolean canBrickRotate(Brick brick) {
		Brick temp = (Brick) brick.clone();
		temp.rotate();
		// ��ת��brick��wall�е�λ���Ƿ񳬳�wall�ı߽�
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
		// ��ת���Ƿ���ǽ���ص�
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