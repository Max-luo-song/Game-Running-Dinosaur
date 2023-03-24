package com.mr.modle;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import com.mr.view.BackgroundImage;
import java.awt.image.BufferedImage;

public class Obstacle {

	public int x, y; // ��������
	public BufferedImage image;
	private BufferedImage stone;    // ʯͷͼƬ
	private BufferedImage cacti;	// ������ͼƬ
	private int speed;		// �ƶ��ٶ�
	public Obstacle() {
		try {
			stone = ImageIO.read(new File("image/ʯͷ.png"));
			cacti = ImageIO.read(new File("image/������.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Random r = new Random();  // �����������
		if (r.nextInt(2) == 0) {  // Ϊ0��������
			image = cacti;
		}
		else { 					  // Ϊ1��ʯͷ
			image = stone; 
		}
		x = 800;          // ��ʼ������
		y = 200 - image.getHeight(); // ������
		speed = BackgroundImage.SPEED; // �ƶ��ٶ��뱳��ͬ��
		
	}
	
	// �ƶ�
	public void move() {
		x -= speed;
	}
	
	// ����
	public boolean isLive() {
		if (x <= -image.getWidth()) {		// ����Ƴ�����Ϸ����
			return false;					// ����
		}
		return true;						// ���
	}
	
	// �߽����
	public Rectangle getBounds() {
		if (image == cacti) {
			return new Rectangle(x+7, y, 15, image.getHeight()); // ���������Ʊ߽�
		}
		return new Rectangle(x+5, y+4, 23, 21);   // ����ʯͷ�߽�
	}

}
