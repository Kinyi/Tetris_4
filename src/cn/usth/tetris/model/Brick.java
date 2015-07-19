package cn.usth.tetris.model;

/*
 * 1.��������˹������ �������Ժ���Ϊ 
 * ���ԣ�����Ϊ4�Ķ�ά�������顢λ������(x,y)���������ɫ 
 * ��Ϊ����ʾ������Ϣ�����ơ����ơ����ơ���ת
 * 2.���캯��:����ͬ�������ö��巵��ֵ������ʹ��return;
 * 3.���Ʒ��������Ϊ��������
 */
import java.awt.Color;
import javax.swing.JOptionPane;

public abstract class Brick implements Cloneable {
	// (x,y)��ʾbrick��house�е�λ�����꣺��������Ͻǵ�0�е�0��Ԫ��λ��
	protected int x, y;
	// ����4*4��������������ڱ�ʾһ��Brick
	public int[][] BrickArray = new int[4][4];
	int RotateFlag = 0;// ��ת�����״��ʶ
	Color color;// Brick����ɫ
	public int downStep=0;
	Brick() {
		x = 0;
		y = 3;// ��ʼ��x,����
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// ���BrickArray����
	protected void clearBrick() {
		for (int i = 0; i < BrickArray.length; i++)
			for (int j = 0; j < BrickArray[i].length; j++) {
				BrickArray[i][j] = 0;
			}
	}

	// ��ʱ����ת90��:ԭ����Ԫ��(i,j)Ӧ�������ڵ�(N-1-j,i),N��ά��
	public void rotate() {

		int[][] temp = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[4 - 1 - j][i] = BrickArray[i][j];
			}
		}
		BrickArray = temp;

	}

	// ����
	public void moveLeft() {
		y--;
	}

	// ����
	public void moveRight() {
		y++;
	}

	// ����
	public void moveDown() {
		x++;
		downStep++;
	}

	// ��ʾBrick����
	public void show() {
		System.out.println("��������Ϊ��(" + x + "," + y + ")");
		System.out.println("������ɫΪ��" + color);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(BrickArray[i][j] + " ");
			}
			System.out.println();
		}
	}

	public Object clone() {
		try {
			// return super.clone();
			Brick temp = (Brick) super.clone();
			temp.BrickArray = new int[BrickArray.length][BrickArray.length];
			for (int i = 0; i < BrickArray.length; i++)
				for (int j = 0; j < BrickArray.length; j++) {
					temp.BrickArray[i][j] = BrickArray[i][j];
				}
			return temp;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

class IBrick extends Brick {// I��
	IBrick() {
		color = Color.RED;
		BrickArray[0][2] = 1;
		BrickArray[1][2] = 1;
		BrickArray[2][2] = 1;
		BrickArray[3][2] = 1;
	}

	@Override
	public void rotate() {
		this.clearBrick();
		switch (RotateFlag) {
		case 0:
			RotateFlag = 1;
			BrickArray[1][0] = 1;
			BrickArray[1][1] = 1;
			BrickArray[1][2] = 1;
			BrickArray[1][3] = 1;
			break;
		case 1:
			RotateFlag = 0;
			BrickArray[0][1] = 1;
			BrickArray[1][1] = 1;
			BrickArray[2][1] = 1;
			BrickArray[3][1] = 1;
			break;
		}
	}
}

class LBrick extends Brick {// L��
	LBrick() {
		color = Color.BLUE;
		BrickArray[0][1] = 1;
		BrickArray[1][1] = 1;
		BrickArray[2][1] = 1;
		BrickArray[2][2] = 1;
	}

	@Override
	public void rotate() {
		this.clearBrick();
		switch (RotateFlag) {
		case 0:
			RotateFlag = 1;
			BrickArray[2][0] = 1;
			BrickArray[2][1] = 1;
			BrickArray[2][2] = 1;
			BrickArray[1][2] = 1;
			break;
		case 1:
			RotateFlag = 2;
			BrickArray[0][1] = 1;
			BrickArray[0][2] = 1;
			BrickArray[1][2] = 1;
			BrickArray[2][2] = 1;
			break;
		case 2:
			RotateFlag = 3;
			BrickArray[1][0] = 1;
			BrickArray[1][1] = 1;
			BrickArray[1][2] = 1;
			BrickArray[2][0] = 1;
			break;
		case 3:
			RotateFlag = 0;
			BrickArray[0][1] = 1;
			BrickArray[1][1] = 1;
			BrickArray[2][1] = 1;
			BrickArray[2][2] = 1;
			break;
		}
	}
}

class ZBrick extends Brick {// Z��
	ZBrick() {
		color = Color.GREEN;
		BrickArray[1][1] = 1;
		BrickArray[1][2] = 1;
		BrickArray[2][2] = 1;
		BrickArray[2][3] = 1;
	}

	@Override
	public void rotate() {
		this.clearBrick();
		this.clearBrick();
		switch (RotateFlag) {
		case 0:
			RotateFlag = 1;
			BrickArray[0][2] = 1;
			BrickArray[1][1] = 1;
			BrickArray[1][2] = 1;
			BrickArray[2][1] = 1;
			break;
		case 1:
			RotateFlag = 0;
			BrickArray[1][1] = 1;
			BrickArray[1][2] = 1;
			BrickArray[2][2] = 1;
			BrickArray[2][3] = 1;
			break;
		}
	}
}

class TBrick extends Brick {// T��
	TBrick() {
		color = Color.PINK;
		BrickArray[1][2] = 1;
		BrickArray[2][1] = 1;
		BrickArray[2][2] = 1;
		BrickArray[2][3] = 1;
	}

	@Override
	public void rotate() {
		this.clearBrick();
		switch (RotateFlag) {
		case 0:
			RotateFlag = 1;
			BrickArray[0][2] = 1;
			BrickArray[1][1] = 1;
			BrickArray[1][2] = 1;
			BrickArray[2][2] = 1;
			break;
		case 1:
			RotateFlag = 2;
			BrickArray[1][1] = 1;
			BrickArray[1][2] = 1;
			BrickArray[1][3] = 1;
			BrickArray[2][2] = 1;
			break;
		case 2:
			RotateFlag = 3;
			BrickArray[0][2] = 1;
			BrickArray[1][2] = 1;
			BrickArray[1][3] = 1;
			BrickArray[2][2] = 1;
			break;
		case 3:
			RotateFlag = 0;
			BrickArray[1][2] = 1;
			BrickArray[2][1] = 1;
			BrickArray[2][2] = 1;
			BrickArray[2][3] = 1;
			break;
		}
	}
}

class SquareBrick extends Brick {// ����
	SquareBrick() {
		color = Color.CYAN;
		BrickArray[1][1] = 1;
		BrickArray[1][2] = 1;
		BrickArray[2][1] = 1;
		BrickArray[2][2] = 1;
	}

	@Override
	public void rotate() {
	}
}
