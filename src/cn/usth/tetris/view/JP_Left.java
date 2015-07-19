package cn.usth.tetris.view;

import java.awt.*;

import javax.swing.*;

import cn.usth.tetris.model.*;

public class JP_Left extends JPanel{
	JLabel gradeLbl=new JLabel();//�ȼ���ǩ
	JLabel scoreLbl=new JLabel();//�÷ֱ�ǩ
	JLabel lineNumLbl=new JLabel();//���б�ǩ
	JButton[][] ButtonArray=new JButton[4][4];//������ʾ��
	JP_Left(){
		//��������С
		this.setSize(80,240);
		//��������ڸ������е�����
		this.setLocation(25,20);
		//�������߽����ʾЧ��
		this.setBorder(BorderFactory.createEtchedBorder());;
		//���õ�ǰ��岼������
		this.setLayout(new BorderLayout());
		//����һ��������JP_Up
		JPanel JP_Up=new JPanel();
		setJP_Up(JP_Up);
		//��JP_Up���õ������ı���
		this.add(JP_Up,BorderLayout.NORTH);
		//����һ��������JP_Down
		JPanel JP_Down=new JPanel();
		setJP_Down(JP_Down);
		//��JP_Up���õ��������м�
		this.add(JP_Down,BorderLayout.CENTER);
	}
	void setJP_Up(JPanel panel){
		//����һ����񲼾ֶ���
		GridLayout GridLay=new GridLayout(8,1);
		//���øò��ֵ�ˮƽ���
		GridLay.setVgap(3);
		panel.setLayout(GridLay);
		panel.add(new JLabel("�ȼ���"));
		panel.add(gradeLbl);
		panel.add(new JLabel("�÷֣�"));
		panel.add(scoreLbl);
		panel.add(new JLabel("������"));
		panel.add(lineNumLbl);
		panel.add(new JLabel("��һ����"));
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
	//�����ʾ��(������������ʾ����
	public void clearPrompt(){
		gradeLbl.setText("1");//�ȼ���ǩ
		scoreLbl.setText("0");//�÷ֱ�ǩ
		lineNumLbl.setText("0");//���б�ǩ
	}
	
	//��շ�����ʾ��
	public void clearButtonArray(){
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				ButtonArray[i][j].setBackground(Color.LIGHT_GRAY);
				ButtonArray[i][j].setVisible(false);
			}
	}
	//��ʾ��ʾ��(������������ʾ����
	public void showPrompt(Prompt prompt){
		gradeLbl.setText(prompt.getLevel()+"");//�ȼ���ǩ
		scoreLbl.setText(prompt.getScore()+"");//�÷ֱ�ǩ
		lineNumLbl.setText(prompt.getTotalLines()+"");//���б�ǩ
	}
	//����ʾ����ʾ������ʾ��
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

