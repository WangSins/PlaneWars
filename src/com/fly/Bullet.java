package com.fly;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bullet {
	private int x;
	private int y;
	// 大小
	private int width;
	private int height;
	// 图片资源数组
	private BufferedImage[] imgs = new BufferedImage[2];
	private int index = 0;
	private HeroPlane heroplane;

	// 构造方法
	public Bullet(HeroPlane heroPlane) {
		this.heroplane = heroPlane;
		;
		height = 14;
		width = 6;
		x = heroPlane.getX() + (heroPlane.getWidth() / 2 - width / 2);
		y = heroPlane.getY();
		try {
			imgs[0] = ImageIO.read(this.getClass().getResource("bullet.png"));
			imgs[1] = ImageIO.read(this.getClass().getResource("bullet1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		// 判断飞机的bule是否为0
		if (heroplane.getBlue() != 0) {
			index = 1;
		}
		// 绘制子弹
		g.drawImage(imgs[index], x, y, null);
	}

	// 移动的方法
	public void move(ArrayList<Bullet> bullets, ArrayList<NPC> npcs) {
		// 处理子弹移动
		y = y - 10;
		// 处理子弹越界
		if (y < 0) {
			bullets.remove(this);
		}
		// 子弹碰撞检测
		if (y < -height) {
			bullets.remove(this);
		}
		for (int i = 0; i < npcs.size(); i++) {
			NPC npc = npcs.get(i);
			if (y < npc.getY() + npc.getHeight() && y > npc.getY() - height) {
				if (x < npc.getX() + npc.getWidth() && x > npc.getX() - width) {
					//当前子弹销毁
					bullets.remove(this);
					//通知敌机击中
					npc.setHit(true);
				}
			}
		}
		
	}

	// get和set方法
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage[] getImgs() {
		return imgs;
	}

	public void setImgs(BufferedImage[] imgs) {
		this.imgs = imgs;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Bullet [x=" + x + ", y=" + y + ", width=" + width + ", height="
				+ height + ", imgs=" + Arrays.toString(imgs) + ", index="
				+ index + "]";
	}

}
