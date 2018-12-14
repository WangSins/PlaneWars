package com.fly;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 这是一个地图类
 * 
 * @author Administrator
 * 
 */
public class Map {
	private int width;
	private int height;
	private int x;
	private int y;
	private BufferedImage mapImage;

	public Map() {
		x = 0;
		y = 0;
		width = 480;
		height = 652;
		try {
			//此段代码是用来获取程序中的图片资源
			//并转换成BufferedImage流
			//2.
			mapImage = ImageIO.read(this.getClass().getResource(
					"background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		//3.画两张图片
		g.drawImage(mapImage, x, y-height, null);
		g.drawImage(mapImage, x, y, null);
	}
	public void move(){
		y=y+2;
		if(y>height){
			y=0;
		}
	}
}
