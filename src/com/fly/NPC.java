package com.fly;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

//import com.testFly.MyPanel;

public class NPC {
	private int x;
	private int y;
	private int height;
	private int width;
	private BufferedImage[] images;
	private int index = 0;
	private int type;// 0 小飞机 1中飞机2大飞机
	// 飞机的状态
	boolean hit = false;// 击中
	boolean destory = false;// 销毁，飞机是否破损
	boolean remove = false;// 是否被移除
	private int hitCount=0;// 击中的次数
	private boolean TransForm = false;// 图片变换的状态
	private int bombCount = 0;// 爆炸的次数
	private int bombStep = 2;// 爆炸的速度

	// 构造函数
	// 加一个int type;然后MyPanel报错了，改报错地方，存入1
	public NPC(int type) {
		// 根据飞机类型不同数组长度也不同
		int size = 0;
		this.type = type;
		Random r = new Random();
		x = r.nextInt(380 - width);
		y = 0 -height;
		if (type == 0) {
			// 小飞机
			height = 34;
			width = 40;
			size = 5;
		}
		if (type == 1) {
			// 中飞机
			height = 70;
			width = 50;
			size = 6;
		}
		if (type == 2) {
			// 大飞机
			height = 155;
			width = 100;
			size = 9;
		}
		images = new BufferedImage[size];
		for (int i = 0; i < images.length; i++) {
			try {
				images[i] = ImageIO.read(this.getClass().getResource(
						"npc" + type + "_" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 敌机的画的方法
	public void paint(Graphics g) {
		if (!remove) {
			g.drawImage(images[index], x, y, null);
		}

		// 被击中但没有被销毁
		if (hit && !destory) {
			if (type == 0) {// 小飞机
				destory = true;
				MyPanel.score += 1;
			}
			if (type == 1) {// 中飞机
				TransForm = true;
				hitCount++;
				if (hitCount >= 3) {
					destory = true;
					MyPanel.score += 3;
				}

			}
			if (type == 2) {// 大飞机
				TransForm = true;
				hitCount++;
				if (hitCount >= 8) {
					destory = true;
					MyPanel.score += 5;
				}
			}
			hit = false;
		}

		if (destory) {// 进入销毁状态
			bombCount++;
			if (bombCount % bombStep == 0) {
				index++;// 轮显图片
				bombCount = 0;
			}
			if (index >= images.length) {
				remove = true;
			}
			return;
		}
		// 被击中后换图片的状态
		if (TransForm) {
			index++;
		}
		if (type == 1 && index >= 2) {
			index = 0;
			TransForm = false;
		}
		if (type == 2 && index >= 3) {
			index = 0;
			TransForm = false;
		}
		if (type == 2 && !hit && TransForm) {
			index++;
			if (index >= 2) {
				index = 0;
			}
		}

	}

	// 敌机的移动方法
	public void move(ArrayList<NPC> npcs) {
		// 敌机下落
		y = y + 5;
		// 越界移除
		if (y > 652) {
			npcs.remove(this);
		}
		if (remove) {
			npcs.remove(this);
		}
	}

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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public void setImages(BufferedImage[] images) {
		this.images = images;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isDestory() {
		return destory;
	}

	public void setDestory(boolean destory) {
		this.destory = destory;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public boolean isTransForm() {
		return TransForm;
	}

	public void setTransForm(boolean transForm) {
		TransForm = transForm;
	}

	public int getBombCount() {
		return bombCount;
	}

	public void setBomCount(int bomCount) {
		this.bombCount = bomCount;
	}

	public int getBombStep() {
		return bombStep;
	}

	public void setBombStep(int bombStep) {
		this.bombStep = bombStep;
	}

}
