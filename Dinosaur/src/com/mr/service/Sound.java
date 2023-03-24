package com.mr.service;

import java.io.FileNotFoundException;

public class Sound {
	static final String DIR = "music/";		// �����ļ���
	static final String BACKGROUD = "background.wav";	// ��������
	static final String JUMP = "jump.wav";		// ��Ծ��Ч
	static final String HIT = "hit.wav";		// ײ����Ч
	
	private static void play(String file, boolean circulate) {
		try {
			MusicPlayer player = new MusicPlayer(file, circulate);	// ����������
			player.play();	// ��ʼ����
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// ������Ծ��Ч
	static public void jump() {
		play(DIR+JUMP, false);
	}
	
	// ����ײ����Ч
	static public void hit() {
		play(DIR+HIT, false);
	}
	
	// ���ű�������
	static public void backgroud() {
		play(DIR+BACKGROUD, true);
	}
}
