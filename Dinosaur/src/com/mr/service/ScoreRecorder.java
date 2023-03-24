package com.mr.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class ScoreRecorder {
	private static final String SCOREFILE = "data/source";	// �ɼ���¼�ļ�
	private static int scores[] = new int[3];	// ���ڴ���ǰ����
	
	public static void init() {
		File f = new File(SCOREFILE);	// ������¼�ļ�
		if (!f.exists()) {
			try {
				f.createNewFile();		// �������ļ�
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;						// ֹͣ����
		}
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(f);	// �ļ��ֽ�������
			isr = new InputStreamReader(fis);	// �ֽ���ת�ַ���
			br = new BufferedReader(isr);	// �����ַ���
			String value = br.readLine();	// ��ȡһ��
			if (!(value == null || "".equals(value))) {	// �ǿվͷָ��ַ���
				String vs[] = value.split(",");
				if (vs.length < 3) {	// С��3�����0
					Arrays.fill(scores, 0);
				}
				else {
					for (int i=0; i<3; i++) {
						scores[i] = Integer.parseInt(vs[i]);	// ����¼�ļ���ֵ������ǰ����
					}
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {		// ���ιر���
			try {
				br.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveScore() {
		String value = scores[0] + "," + scores[1] + "," + scores[2]; // ƴ�ӵ÷�����
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(SCOREFILE); // �ļ��ֽ������
			osw = new OutputStreamWriter(fos);		// �ֽ���ת�ַ���
			bw = new BufferedWriter(osw);			// �����ַ���
			bw.write(value);					// д��ƴ�Ӻ���ַ���
			bw.flush();							// �ַ���ˢ��
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {			// ���ιر���
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void addNewScore(int score) {
		int tmp[] = Arrays.copyOf(scores, 4);	// ��������Ϊ4����ʱ���飬����֮ǰ��3�Σ����1��
		tmp[3] = score;		// �·��������ĸ�
		Arrays.sort(tmp);	// ��ʱ������������
		scores = Arrays.copyOfRange(tmp, 1, 4);	// ���������scores
	}
	
	static public int[] getScores() {
		return scores;
	}
}
