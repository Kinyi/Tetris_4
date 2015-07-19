package cn.usth.tetris.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import cn.usth.tetris.model.Brick;
import cn.usth.tetris.model.Wall;

public class JP_Right extends JPanel{
	JButton[][] ButtonArray=new JButton[20][10];//方块下落区
	JP_Right(){
		this.setSize(205,405);
		this.setLocation(133, 20);
		//设置面板边界的显示效果
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new GridLayout(20,10));
		for(int i=0;i<20;i++)
			for(int j=0;j<10;j++){
				ButtonArray[i][j]=new JButton();
				ButtonArray[i][j].setBackground(Color.CYAN);
				ButtonArray[i][j].setEnabled(false);
				this.add(ButtonArray[i][j]);
			}
	}
	//清空游戏区
	public void clearButtonArray(){
		for(int i=0;i<20;i++)
			for(int j=0;j<10;j++){
				ButtonArray[i][j].setBackground(Color.LIGHT_GRAY);
				ButtonArray[i][j].setVisible(false);
			}
	}
	//在游戏区显示方块
	public void showBrick(Brick brick){
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				if(brick.BrickArray[i][j]==1){
					ButtonArray[i+brick.getX()][j+brick.getY()].setVisible(true);
					ButtonArray[i+brick.getX()][j+brick.getY()].setBackground(brick.getColor());
				}
			}
	}
	//在游戏区显示墙
	public void showWall(Wall wall){
		for(int i=0;i<20;i++)
			for(int j=0;j<10;j++){
				if(wall.WallArray[i][j].PosFlag==1){
					if(wall.WallArray[i][j].PosFlag==1){
						ButtonArray[i][j].setVisible(true);
						ButtonArray[i][j].setBackground(wall.WallArray[i][j].color);
					}
				}
			}
	}
}
