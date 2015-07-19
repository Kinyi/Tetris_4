package cn.usth.tetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JMenuBar{
	TetrisFrame frame;
	// ���塰�ļ����˵�
	JMenu menu_file = new JMenu("�ļ�");
	// ���塰�ļ����˵���"��ʼ"
	public JMenuItem menuItem_Start = new JMenuItem("��ʼ");
	// ���塰�ļ����˵���"��ͣ"
	public JMenuItem menuItem_Suspend = new JMenuItem("��ͣ");
	// ���塰�ļ����˵���"�˳�"
	public JMenuItem menuItem_Exit = new JMenuItem("�˳�");
	// ���塰ѡ��˵�
	JMenu menu_Opinion = new JMenu("ѡ��");
	JMenu menu_Initial = new JMenu("��ʼ��ˮƽ");
	// ���尴ť��
	ButtonGroup buttonGroup = new ButtonGroup();
	// ����JRadioButtonMenuItem����
	public JRadioButtonMenuItem[] JRadioBtnMenuItem = new JRadioButtonMenuItem[10];;

	Menu() {
		// ����10��JRadioButtonMenuItem������JRadioBtnMenuItem����
		for (int i = 0; i < 10; i++) {
			JRadioBtnMenuItem[i] = new JRadioButtonMenuItem(String
					.valueOf((i + 1)));
			buttonGroup.add(JRadioBtnMenuItem[i]);// ʵ��10����ťֻ��ѡ��һ��
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
