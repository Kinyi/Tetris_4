package cn.usth.tetris.view;

import java.awt.*;

import javax.swing.*;

import cn.usth.tetris.model.*;

public class JP_Left extends JPanel{
	JLabel gradeLbl=new JLabel();//等级标签
	JLabel scoreLbl=new JLabel();//得分标签
	JLabel lineNumLbl=new JLabel();//消行标签
	JButton[][] ButtonArray=new JButton[4][4];//方块提示区
	JP_Left(){
		//设置面板大小
		this.setSize(80,240);
		//设置面板在父容器中的坐标
		this.setLocation(25,20);
		//设置面板边界的显示效果
		this.setBorder(BorderFactory.createEtchedBorder());;
		//设置当前面板布局类型
		this.setLayout(new BorderLayout());
		//定义一个面板对象JP_Up
		JPanel JP_Up=new JPanel();
		setJP_Up(JP_Up);
		//将JP_Up放置到父面板的北面
		this.add(JP_Up,BorderLayout.NORTH);
		//定义一个面板对象JP_Down
		JPanel JP_Down=new JPanel();
		setJP_Down(JP_Down);
		//将JP_Up放置到父面板的中间
		this.add(JP_Down,BorderLayout.CENTER);
	}
	void setJP_Up(JPanel panel){
		//创建一个表格布局对象
		GridLayout GridLay=new GridLayout(8,1);
		//设置该布局的水平间隔
		GridLay.setVgap(3);
		panel.setLayout(GridLay);
		panel.add(new JLabel("等级："));
		panel.add(gradeLbl);
		panel.add(new JLabel("得分："));
		panel.add(scoreLbl);
		panel.add(new JLabel("行数："));
		panel.add(lineNumLbl);
		panel.add(new JLabel("下一个："));
	}
	void setJP_Down(JPanel panel){
		GridLayout gridLay=new GridLayout(4,4);
		panel.setLayout(gridLay);
		panel.setSize(20,20);
		panel.setLocation(10, 10);
		panel.setBorder(BorderFactory.createEtchedBorder());
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				ButtonArray[i][j]=new JButton();
				ButtonArray[i][j].setBackground(Color.cyan);
				ButtonArray[i][j].setEnabled(false);
				panel.add(ButtonArray[i][j]);
			}
	}
	//清空提示区(不包括方块提示区）
	public void clearPrompt(){
		gradeLbl.setText("1");//等级标签
		scoreLbl.setText("0");//得分标签
		lineNumLbl.setText("0");//消行标签
	}
	
	//清空方块提示区
	public void clearButtonArray(){
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				ButtonArray[i][j].setBackground(Color.LIGHT_GRAY);
				ButtonArray[i][j].setVisible(false);
			}
	}
	//显示提示区(不包括方块提示区）
	public void showPrompt(Prompt prompt){
		gradeLbl.setText(prompt.getLevel()+"");//等级标签
		scoreLbl.setText(prompt.getScore()+"");//得分标签
		lineNumLbl.setText(prompt.getTotalLines()+"");//消行标签
	}
	//在提示区显示方块提示区
	public void showBrick(Brick brick){
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				if(brick.BrickArray[i][j]==1){
					ButtonArray[i][j].setVisible(true);
					ButtonArray[i][j].setBackground(brick.getColor());
				}
			}
	}
}

