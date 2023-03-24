package com.mr.view;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BackgroundImage {
	public BufferedImage image;		// ����ͼƬ
	private BufferedImage image1, image2;	// ����������ͼƬ
	private Graphics2D g;		// ����ͼƬ�Ļ�ͼ����
	public int x1, x2;			// ��������ͼƬ������
	public static final int SPEED = 4;	// �����ٶ�

	public BackgroundImage() {
		try {
			image1 = ImageIO.read(new File("image/����.png"));
			image2 = ImageIO.read(new File("image/����.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		// ��ͼƬ800x300
		image = new BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();	// ��ȡ��ͼƬ��ͼ����
		x1 = 0;				// ��һ��ͼ��ʼ������Ϊ0
		x2 = 800;			// �ڶ���ͼ��ʼ������Ϊ800
		g.drawImage(image1, x1, 0, null);
	}

	public void roll() {
		x1 -= SPEED;	// ��һ������
		x2 -= SPEED;	// �ڶ�������
		if (x1 <= -800) {	// �ѳ���Ļ�ص��Ҳ�
			x1 = 800;
		}
		if (x2 <= -800) {
			x2 = 800;
		}
		g.drawImage(image1, x1, 0, null);		// ��ͼƬ�л�������ͼƬ
		g.drawImage(image2, x2, 0, null);
	}
}



