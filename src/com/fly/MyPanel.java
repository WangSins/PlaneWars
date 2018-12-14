package com.fly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
	// 这是一个地图背景对象
	private Map map = new Map();
	// 这是一个飞机对象
	private HeroPlane heroPlane = new HeroPlane();
	private Bullet bullet = new Bullet(heroPlane);
	// 集合的长度是可变的，里面可以存储任意类型
	// <E>：泛型，就是把集合内存储的元素类型约束一下，存储的为这个泛型类型的
	private int bulletCount = 0;// 计数
	private int bulletStep = 4;// 控制子弹的稀疏程度
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int NPCCount = 0;// 计数
	private int NPCStep = 50;// 控制敌机的稀疏程度
	private int smallNPC = 0;// 小飞机计数
	private int middleNPC = 1;// 中飞机计数
	private int bigNPC = 2;// 大飞机计数
	//
	static int timer = 0;
	static int timer2 = 0;
	static int blue = 0;
	static int green = 0;
	private BufferedImage imgstart;// 开始图片
	private BufferedImage en;// 炸弹图片
	public static int score = 0; // 累计分数
	private int toolsStep = 200;
	private int toolsCount = 0;
	private ArrayList<NPC> npcs = new ArrayList<NPC>();
	private ArrayList<Tools> tools = new ArrayList<Tools>();

	public MyPanel() {
		try {
			imgstart = ImageIO.read(getClass().getResource("imgstart.png"));
			en = ImageIO.read(getClass().getResource("en.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		// 画地图
		map.paint(g);
		// 画英雄飞机
		heroPlane.paint(g);
		if (!heroPlane.isRemove()) {
			addBullet();
			// 数组的长度 .length 集合是 .size()
			for (int i = 0; i < bullets.size(); i++) {
				// System.out.println();
				bullets.get(i).paint(g);
			}
		}
		addNPC();
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).paint(g);
		}
		addTools();
		for (int i = 0; i < tools.size(); i++) {
			tools.get(i).paint(g);
		}
		// 绘制炸弹
		if (heroPlane.getRed() > 0) {
			g.drawImage(en, 16, 580, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", Font.BOLD, 18));
			g.drawString("x" + heroPlane.getRed(), 50, 600);
		}

		if (heroPlane.isRemove()) {// 如果飞机移除 出现重新开始
			g.drawImage(imgstart, 130, 400, this);
		}
		// 记分
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 14));
		g.drawString("Score:" + score * 100, 20, 35);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (heroPlane.getBlue() > 0) {
				heroPlane.setTimer(++timer);
				if (heroPlane.getTimer() >= 600) {
					heroPlane.setTimer(timer = 0);
					heroPlane.setBlue(blue = 0);
				}
			}
			if (heroPlane.getGreen() > 0) {
				heroPlane.setTimer(++timer2);
				if (heroPlane.getTimer() >= 300) {
					heroPlane.setTimer(timer2 = 0);
					heroPlane.setGreen(green = 0);
				}
			}

			map.move();
			heroPlane.move(npcs);

			for (int i = 0; i < bullets.size(); i++) {
				// System.out.println();
				bullets.get(i).move(bullets, npcs);
			}
			for (int i = 0; i < npcs.size(); i++) {
				npcs.get(i).move(npcs);
			}
			for (int i = 0; i < tools.size(); i++) {
				tools.get(i).move(tools, heroPlane);
			}
			// 更新一个界面
			repaint();
		}
	}

	public void addBullet() {
		// 每走一次计数加1
		bulletCount++;
		// 加一个控制 使这个运行bulletStep次在子弹集合（弹夹）中增加一个
		if (bulletCount % bulletStep == 0) {
			if (heroPlane.getBlue() == 0) {
				Bullet bullet = new Bullet(heroPlane);
				bullets.add(bullet);
			} else {
				Bullet bullet = new Bullet(heroPlane);
				bullet.setX(heroPlane.getX() + 5);
				bullets.add(bullet);

				Bullet bullet2 = new Bullet(heroPlane);
				bullet2.setX(heroPlane.getX() + 50);
				bullets.add(bullet2);
			}
		}

	}

	public void addNPC() {
		// 每生产5个小飞机会生产出一个中飞机
		// 每生产5个中飞机回生产一个大飞机
		NPCCount++;
		if (NPCCount % NPCStep == 0) {
			// 生产一个小敌机
			NPC npc = new NPC(0);
			// 把小敌机添加到集合中
			npcs.add(npc);
			// 如果生产一个小敌机，smallNPC+1
			smallNPC++;
			// 判断smallNPC与5余数是否为0
			if (smallNPC % 5 == 0) {
				// 生成一个中敌机
				NPC npc1 = new NPC(1);
				npcs.add(npc1);
				// 中敌机的计数单位
				middleNPC++;
			}
			// 判断小敌机的技术是否是5的倍数并且中敌机的计数是否同时满足
			if (smallNPC % 5 == 0 && middleNPC % 5 == 0) {
				NPC npc2 = new NPC(2);
				npcs.add(npc2);
				smallNPC = middleNPC = 0;
				NPCCount = 0;
			}
		}

	}

	public void addTools() {
		toolsCount++;
		if (toolsCount % toolsStep == 0) {
			Tools t = new Tools();
			tools.add(t);
		}

	}

	// 清除敌机
	void clear() {
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).hit = true;
			npcs.get(i).setHitCount(10);
		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public HeroPlane getHeroPlane() {
		return heroPlane;
	}

	public void setHeroPlane(HeroPlane heroPlane) {
		this.heroPlane = heroPlane;
	}

}
