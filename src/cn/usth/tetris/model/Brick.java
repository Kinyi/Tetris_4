package cn.usth.tetris.model;

/*
 * 1.创建俄罗斯方块类 包括属性和行为 
 * 属性：长度为4的二维整型数组、位置坐标(x,y)、方块的颜色 
 * 行为：显示方块信息、左移、右移、下移、旋转
 * 2.构造函数:与类同名；不用定义返回值；不能使用return;
 * 3.完善方块类的行为（方法）
 */
import java.awt.Color;
import javax.swing.JOptionPane;

public abstract class Brick implements Cloneable {
	// (x,y)表示brick在house中的位置坐标：矩阵的左上角第0行第0列元素位置
	protected int x, y;
	// 定义4*4矩阵，在这个区域内表示一个Brick
	public int[][] BrickArray = new int[4][4];
	int RotateFlag = 0;// 旋转后的形状标识
	Color color;// Brick的颜色
	public int downStep=0;
	Brick() {
		x = 0;
		y = 3;// 初始化x,坐标
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

	// 清空BrickArray数组
	protected void clearBrick() {
		for (int i = 0; i < BrickArray.length; i++)
			for (int j = 0; j < BrickArray[i].length; j++) {
				BrickArray[i][j] = 0;
			}
	}

	// 逆时针旋转90度:原来的元素(i,j)应该是现在的(N-1-j,i),N是维度
	public void rotate() {

		int[][] temp = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[4 - 1 - j][i] = BrickArray[i][j];
			}
		}
		BrickArray = temp;

	}

	// 左移
	public void moveLeft() {
		y--;
	}

	// 右移
	public void moveRight() {
		y++;
	}

	// 下移
	public void moveDown() {
		x++;
		downStep++;
	}

	// 显示Brick属性
	public void show() {
		System.out.println("方块坐标为：(" + x + "," + y + ")");
		System.out.println("方块颜色为：" + color);
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

class IBrick extends Brick {// I形
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

class LBrick extends Brick {// L形
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

class ZBrick extends Brick {// Z形
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

class TBrick extends Brick {// T形
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

class SquareBrick extends Brick {// 方形
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
