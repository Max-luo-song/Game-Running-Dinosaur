package com.mr.service;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat;


public class MusicPlayer implements Runnable{
		
		File soundFile; 	// �����ļ�
		Thread thread;		// ���߳�
		boolean circulate;  // �Ƿ�ѭ������

		public MusicPlayer(String filepath, boolean circulate) 
					throws FileNotFoundException {
				this.circulate = circulate;
				soundFile = new File(filepath);
				if (!soundFile.exists()) {
					throw new FileNotFoundException(filepath + "δ�ҵ�");
				}
			}
	
		// ����Ч��
	    @Override
		public void run() {
			byte[] auBuffer = new byte[1024*128]; // ����������
			do {
				AudioInputStream audioInputStream = null;
				SourceDataLine auline = null;
				try {
					// �������ļ��л�ȡ��Ƶ����
					audioInputStream = AudioSystem.getAudioInputStream(soundFile);
					AudioFormat format = audioInputStream.getFormat(); // ��ȡ��Ƶ��ʽ
					// �����ж���
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
					// ��Ƶϵͳ����ָ����������ƥ����ж���
					auline = (SourceDataLine)AudioSystem.getLine(info);
					auline.open(format); // ���ո�ʽ��Դ������
					auline.start();		// Դ�����п�ʼ��д�
					int byteCount = 0;		// ������¼��Ƶ����������������
					while (byteCount != -1) { // ����ֽ�����Ϊ1
						byteCount = audioInputStream.read(auBuffer, 0, auBuffer.length); // ����Ƶ����������128KB
						if (byteCount >= 0) { // ���������Ч
							auline.write(auBuffer, 0, byteCount); // ��Ч���ݷ���������
						}
					}
				}catch(IOException e) {
					e.printStackTrace();
				}catch(UnsupportedAudioFileException e) {
					e.printStackTrace();
				}catch(LineUnavailableException e) {
					e.printStackTrace();
				}finally {
					auline.drain();			// ���������
					auline.close();			// �ر�������
				}	
			}while(circulate);		//����ѭ���ж��Ƿ�ѭ������
		}
	
		public void play() {
			thread = new Thread(this);	//�����̶߳���
			thread.start();		// �����߳�
		}
		
		public void stop() {
			thread.stop();		// ǿ�ƹر��߳�
		}
}
