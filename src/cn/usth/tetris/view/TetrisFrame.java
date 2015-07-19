package cn.usth.tetris.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import cn.usth.tetris.controller.House;
import cn.usth.tetris.controller.House;

public class TetrisFrame extends JFrame {
	protected Menu menuBar = new Menu();
	protected JP_Left jp_Left = new JP_Left();
	protected JP_Right jp_Right = new JP_Right();

	public TetrisFrame() {
		// 在窗口中添加菜单条
		this.setJMenuBar(menuBar);
		// 获取窗口默认内容面板
		// Container container=frame.getContentPane();
		JPanel container = (JPanel) getContentPane();
		// 设置当前面板为空布局（没有布局），这样可以使用setSize(),setLocation()
		container.setLayout(null);
		container.add(jp_Left);
		container.add(jp_Right);
		setFrame();
	}

	public static void main(String[] args) {
		TetrisFrame frame = new House();
		// 在窗口中添加菜单条
		frame.setJMenuBar(frame.menuBar);
		// 获取窗口默认内容面板
		// Container container=frame.getContentPane();
		JPanel container = (JPanel) frame.getContentPane();
		// 设置当前面板为空布局（没有布局），这样可能使用setSize(),setLocation()
		container.setLayout(null);
		container.add(frame.jp_Left);
		container.add(frame.jp_Right);
		frame.setFrame();
	}

	void setFrame() {
		setTitle("俄罗斯方块");
		setSize(370, 500);
		setResizable(false);
		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setFocusable(true);
	}

}
