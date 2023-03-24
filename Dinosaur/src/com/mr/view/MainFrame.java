package com.mr.view;

import java.awt.Container;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.mr.service.ScoreRecorder;
import com.mr.service.Sound;

public class MainFrame extends JFrame{
	
	public MainFrame() {
			restart();	// ��ʼ
			setBounds(340, 150, 820, 260);	// ���ú�������Ϳ��
			setTitle("���ܰɣ�С����!");	// ���ñ���
			Sound.backgroud();	// ���ñ�������
			ScoreRecorder.init();	// ��ȡ�÷ּ�¼
			addListener();		// ��Ӽ���
			setDefaultCloseOperation(EXIT_ON_CLOSE);	// �رմ���ֹͣ����
	}

	
	public void restart() {
		Container c = getContentPane(); 	// ��ȡ����������
		c.removeAll();				// ɾ���������������
		GamePanel panel = new GamePanel();	// �����µ���Ϸ���
		c.add(panel);
		addKeyListener(panel);		// ��Ӽ����¼�
		c.validate();		// ����������֤�������
	}
	
	private void addListener() {
		addWindowListener(new WindowAdapter() {	// ���Ӵ������
			public void windowClosing(WindowEvent e) {	// ����ر�ǰ����÷ּ�¼
				ScoreRecorder.saveScore();
			}
		});
	}
}
