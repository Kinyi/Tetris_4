package cn.usth.tetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JMenuBar{
	TetrisFrame frame;
	// 定义“文件”菜单
	JMenu menu_file = new JMenu("文件");
	// 定义“文件”菜单项"开始"
	public JMenuItem menuItem_Start = new JMenuItem("开始");
	// 定义“文件”菜单项"暂停"
	public JMenuItem menuItem_Suspend = new JMenuItem("暂停");
	// 定义“文件”菜单项"退出"
	public JMenuItem menuItem_Exit = new JMenuItem("退出");
	// 定义“选项”菜单
	JMenu menu_Opinion = new JMenu("选项");
	JMenu menu_Initial = new JMenu("初始化水平");
	// 定义按钮组
	ButtonGroup buttonGroup = new ButtonGroup();
	// 定义JRadioButtonMenuItem数组
	public JRadioButtonMenuItem[] JRadioBtnMenuItem = new JRadioButtonMenuItem[10];;

	Menu() {
		// 创建10个JRadioButtonMenuItem并存入JRadioBtnMenuItem数组
		for (int i = 0; i < 10; i++) {
			JRadioBtnMenuItem[i] = new JRadioButtonMenuItem(String
					.valueOf((i + 1)));
			buttonGroup.add(JRadioBtnMenuItem[i]);// 实现10个按钮只能选中一个
			menu_Initial.add(JRadioBtnMenuItem[i]);
		}
		setMenuBar();
	}
	Menu(TetrisFrame frame){
		this();
		this.frame=frame;
	}

	void setMenuBar() {
		this.add(menu_file);
		this.add(menu_Opinion);
		menu_file.add(menuItem_Start);
		menu_file.add(menuItem_Suspend);
		menu_file.add(menuItem_Exit);
		menu_Opinion.add(menu_Initial);
		JRadioBtnMenuItem[0].setSelected(true);
	}


}
