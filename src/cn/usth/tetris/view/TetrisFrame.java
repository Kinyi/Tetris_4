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
		// �ڴ�������Ӳ˵���
		this.setJMenuBar(menuBar);
		// ��ȡ����Ĭ���������
		// Container container=frame.getContentPane();
		JPanel container = (JPanel) getContentPane();
		// ���õ�ǰ���Ϊ�ղ��֣�û�в��֣�����������ʹ��setSize(),setLocation()
		container.setLayout(null);
		container.add(jp_Left);
		container.add(jp_Right);
		setFrame();
	}

	public static void main(String[] args) {
		TetrisFrame frame = new House();
		// �ڴ�������Ӳ˵���
		frame.setJMenuBar(frame.menuBar);
		// ��ȡ����Ĭ���������
		// Container container=frame.getContentPane();
		JPanel container = (JPanel) frame.getContentPane();
		// ���õ�ǰ���Ϊ�ղ��֣�û�в��֣�����������ʹ��setSize(),setLocation()
		container.setLayout(null);
		container.add(frame.jp_Left);
		container.add(frame.jp_Right);
		frame.setFrame();
	}

	void setFrame() {
		setTitle("����˹����");
		setSize(370, 500);
		setResizable(false);
		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setFocusable(true);
	}

}
