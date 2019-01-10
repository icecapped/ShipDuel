package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import objects.*;

class GameContainer extends JPanel implements ActionListener{
	
	static final Image BG_IMAGE = new ImageIcon("src/assets/CosmicSky.png").getImage();
	
	int pAreaX;
	int pAreaY;
	int pAreaW;
	int pAreaH;
	
	Timer tick;
	int tickrate = 16;
	PlayerShip red;
	PlayerShip blue;
	
	public GameContainer() {
		//addKeyListener(new MAdapter());
		red = new PlayerShip(380, 755, false);
		blue = new PlayerShip(380, 275, true);
		tick = new Timer(tickrate, this);
		tick.start();
		
		pAreaX = 50;
		pAreaY = 0;
		pAreaW = 720;
		pAreaH = 990;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(BG_IMAGE, 50, 0, this);
		
		//draw side boundaries
		g.fillRect(0, 0, 50, 1020);
		g.fillRect(pAreaW + 50,  0,  50, 1020);
		
		//drawing middle boundary
		g.setColor(Color.GRAY);
		g.fillRect(50, 500, 720, 10);
		
		g.drawImage(red.getImage(), red.getX(), red.getY(), this);
		g.drawImage(blue.getImage(), blue.getX(), blue.getY(), this);
		
		//paint(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	/*public void paint(Graphics g) {
	}*/

	void update(){
		red.update();
		blue.update();
		red.wallCheck(pAreaX, pAreaY, pAreaW + pAreaX, pAreaH + pAreaY);
		blue.wallCheck(pAreaX, pAreaY, pAreaW + pAreaX, pAreaH + pAreaY);
		repaint(0, 0, 0, getWidth(), getHeight());
	}
	
	public void actionPerformed(ActionEvent e) {
		update();
	}
}