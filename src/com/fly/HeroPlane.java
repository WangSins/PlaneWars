package com.fly;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//import com.testFly.MyPanel;

public class HeroPlane {
	// 飞机的坐标
	private int x;
	private int y;
	// 飞机的大小
	private int width;
	private int height;
	// 飞机的图片数组集合，共六张
	private BufferedImage[] imgs = new BufferedImage[7];
	// 图片的索引,图片的下标
	private int index = 1;
	// 飞机的状态,销毁
	boolean destroy = false;
	// 飞机可以上下左右移动
	boolean left;// 左
	boolean right;// 右
	boolean up;// 上
	boolean down;// 下
	private boolean remove = false;// 移除工作

	private int planeCount = 0;// 计数器
	private int planeStep = 2;// 步数

	// 工具包
	private int blue=0;
	private int red=0;
	private int green=0;
	// 子弹时长计算器
	private int timer = 0;

	public HeroPlane() {
		width = 60;
		height = 76;
		x = 175;
		y = 452;
		try {
			// 利用for循环遍历的往数组中存入数据
			for (int i = 0; i < imgs.length; i++) {
				// 根据图片名的特点拼装字符串
				imgs[i] = ImageIO.read(this.getClass().getResource("plane0" + (i + 1) + ".png"));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 这是画飞机的方法
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		if (remove) {
			return;
		}
		// 根据index作为下标选择显示的是哪张飞机图片
		g.drawImage(imgs[index], x, y, null);
		planeCount++;
		if (planeCount % planeStep == 0) {
			index++;
			planeCount = 0;
		}
		if (green != 0) {
			if (index >= 3 && !destroy) {
				index = 0;
			}
		} else {
			if (index >= 3 && !destroy) {
				index = 1;
			}
		}
		if (destroy && index >= 7) {
			remove = true;
		}

	}

	/**
	 * 这是控制飞机移动的方法
	 */
	public void move(ArrayList<NPC> npcs) {
		if(destroy){
			return;
		}
		// 左移
		if (left) {
			x = x - 10;
		}
		// 右移
		if (right) {
			x = x + 10;
		}
		// 上移
		if (up) {
			y = y - 10;
		}
		// 下移
		if (down) {
			y = y + 10;
		}
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (x > 431 - width - 15) {
			x = 431 - width - 15;
		}
		if (y > 652 - height - 39) {
			y = 652 - height - 39;
		}
		// 碰撞检测
		for (int i = 0; i < npcs.size(); i++) {
			NPC npc = npcs.get(i);//
			if (x < npc.getX() + npc.getWidth() && x > npc.getX() - width) {
				if (y < npc.getY() + npc.getHeight() && y > npc.getY() - height) {
					if (green != 0) {
						npc.setDestory(true);
					} else {
						// 飞机销毁
						destroy = true;
						// npc销毁
						npc.setDestory(true);
					}

				}
			}
		}
		
	}

	// 重新开始
	public void restart() {
		y = 400;
		x = 410 / 2 - width / 2;
		index = 1;
		destroy = remove = false;
		MyPanel.score = 0;// 分数清零
		blue=red=green=0;
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

	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public int getPlaneCount() {
		return planeCount;
	}

	public void setPlaneCount(int planeCount) {
		this.planeCount = planeCount;
	}

	public int getPlaneStep() {
		return planeStep;
	}

	public void setPlaneStep(int planeStep) {
		this.planeStep = planeStep;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

}