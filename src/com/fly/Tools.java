package com.fly;
/**
 * 工具类（奖励类）
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tools {
	private int x;
	private int y;
	private int height;
	private int width;
	private BufferedImage  images;
	private int type;
	public Tools(){
		//定义一个随机数作为类型
		Random r = new Random();
		type=r.nextInt(3);
		//设置一个数组存储工具图片名
		String[] str = { "blue.png", "red.png","green.png" };
		try {
			images = ImageIO.read(this.getClass().getResource(str [type]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = r.nextInt(400 - width);
		y = 0 - height;
		width=images.getWidth();
		height=images.getHeight();
	}
	public void paint(Graphics g) {
		g.drawImage(images, x, y, null);

	}
	
	public void move(ArrayList<Tools> tools ,HeroPlane heroPlane) {
		y = y+7;
		// 边界判断
		if (y > 652) {
			tools.remove(this);
		}
		// 碰撞检测
		if (x > heroPlane.getX() - width && x < heroPlane.getX() + heroPlane.getWidth()) {
			if (y > heroPlane.getY() - height && y < heroPlane.getY() + heroPlane.getHeight()) {
				// 碰撞之后判断道具
				if (type == 0) {
					heroPlane.setBlue(heroPlane.getBlue()+1);
				}
				if (type == 1) {
					heroPlane.setRed(heroPlane.getRed()+1);
				}
				if (type == 2){
					heroPlane.setGreen(heroPlane.getGreen()+1);
				}
				// 碰撞之后 移除道具
				tools.remove(this);
			}
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
	public BufferedImage getImages() {
		return images;
	}
	public void setImages(BufferedImage images) {
		this.images = images;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
