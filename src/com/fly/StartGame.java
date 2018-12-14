package com.fly;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.SwitchPoint;

import javax.swing.JFrame;

/**
 * 本类是游戏的开始
 * 
 * @author Administrator
 * 
 */
public class StartGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建一个窗体
		final JFrame jframe = new JFrame();
		// 设置 窗体尺寸
		jframe.setSize(431, 652);
		// 点击窗体的关闭按钮，会停止程序
		jframe.setTitle("飞机大战");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体相对于指定组件的位置，如果为null，则窗体位于屏幕正中心
		jframe.setLocationRelativeTo(null);
		final MyPanel myPanel = new MyPanel();
		// myPanel.setBackground(Color.BLUE);
		jframe.add(myPanel);
		Thread t = new Thread(myPanel);
		t.start();

		// 添加鼠标移动监听器
		jframe.addMouseMotionListener(new MouseAdapter() {// 创建鼠标监听对象并实现接口内的方法
			@Override
			public void mouseMoved(MouseEvent e) {// 负责处理鼠标移动事件
				if (myPanel.getHeroPlane().isRemove()) {
					int x = e.getX();// 获取鼠标的x坐标
					int y = e.getY();// 获取鼠标的y坐标
					// System.out.println(x + "," + y);
					if (x > 180 && x < 275 && y > 432 && y < 470) {// 如果鼠标在这个位置
						jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));// 设置鼠标
																			// 添加提示小手
					} else {
						jframe.setCursor(Cursor.getDefaultCursor());// 就不更改,使用默认光标
					}
				}
			}
		});

		// 添加鼠标监听事件
		jframe.addMouseListener(new MouseAdapter() {// 负责处理鼠标移动事件
			@Override
			// 鼠标按下 鼠标事件
			public void mousePressed(MouseEvent e) {
				if (myPanel.getHeroPlane().isRemove()) {
					// x 180y 432 w 315 h 470
					int x = e.getX(); // 获取鼠标x的位置
					int y = e.getY();// 获取鼠标y的位置
					// System.out.println(x + "," + y);
					if (x > 180 && x < 275 && y > 432 && y < 470) {// 如果鼠标在这个位置按下
						myPanel.getHeroPlane().restart();// 重新开始游戏
					}
				}
			}
		});

		jframe.addKeyListener(
		// 匿名内部类
		new KeyAdapter() {
			/**
			 * 按下某个键调用此方法
			 */
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:// 按下左键
					myPanel.getHeroPlane().setLeft(true);
					break;
				case KeyEvent.VK_RIGHT:// 按下右键
					myPanel.getHeroPlane().setRight(true);
					break;
				case KeyEvent.VK_UP:// 按下上键
					myPanel.getHeroPlane().setUp(true);
					break;
				case KeyEvent.VK_DOWN:// 按下下键
					myPanel.getHeroPlane().setDown(true);
					break;
				default:
					break;

				case KeyEvent.VK_CONTROL:// 控制
				case KeyEvent.VK_ENTER:// 进去
				case KeyEvent.VK_SPACE:// 空间
					if (myPanel.getHeroPlane().getRed() > 0
							&& !myPanel.getHeroPlane().isRemove()) {
						myPanel.getHeroPlane().setRed(
								myPanel.getHeroPlane().getRed() - 1);
						myPanel.clear();
					}
					break;
				}
			}

			/**
			 * 当按键释放时调用此方法
			 * 
			 * @param e
			 */
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					myPanel.getHeroPlane().setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:// 按下右键
					myPanel.getHeroPlane().setRight(false);
					break;
				case KeyEvent.VK_UP:// 按下上键
					myPanel.getHeroPlane().setUp(false);
					break;
				case KeyEvent.VK_DOWN:// 按下下键
					myPanel.getHeroPlane().setDown(false);
					break;
				default:
					break;
				}
			}

		});
		// 显示这个窗体
		jframe.setVisible(true);

	}

}
