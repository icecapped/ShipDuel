package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import objects.*;

class GameContainer extends JPanel implements ActionListener{
	
	static final Image BG_IMAGE = new ImageIcon("src/assets/CosmicSky.png").getImage();
	static final Image WALL_IMAGE_L = new ImageIcon("src/assets/Wall0.png").getImage();
	static final Image WALL_IMAGE_R = new ImageIcon("src/assets/Wall1.png").getImage();
	static final Image P1 = new ImageIcon("src/assets/p1.png").getImage();
	static final Image P2 = new ImageIcon("src/assets/p2.png").getImage();
	
	int pAreaX;
	int pAreaY;
	int pAreaW;
	int pAreaH;
	
	int numWidth;
	int blueWins;
	int redWins;
	int frameSize;
	
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
		pAreaH = 945;
		
		numWidth = 18;
		blueWins = 0;
		redWins = 0;
		frameSize = 825;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(BG_IMAGE, 50, 0, this);
		g.drawImage(WALL_IMAGE_L, 0, 0, this);
		g.drawImage(WALL_IMAGE_R, pAreaX + pAreaW - 10, 0, this);
		
		g.drawImage(P1, frameSize - numWidth, (int)(pAreaH / 4) - 42, null);
		g.drawImage(P2, frameSize - numWidth, (int)(pAreaH * 0.75) - 42, null);
		
		if (blueWins >= 10) {
			g.drawImage(new ImageIcon("src/assets/" + blueWins / 10 + ".png").getImage(), frameSize - numWidth, (int)(pAreaH / 4), null);
		}
		g.drawImage(new ImageIcon("src/assets/" + blueWins % 10 + ".png").getImage(), frameSize, (int)(pAreaH / 4), null);
		
		if (redWins >= 10) {
			g.drawImage(new ImageIcon("src/assets/" + redWins / 10 + ".png").getImage(), frameSize - numWidth, (int)(pAreaH * 0.75), null);
		}
		g.drawImage(new ImageIcon("src/assets/" + redWins % 10 + ".png").getImage(), frameSize, (int)(pAreaH * 0.75), null);
		//drawing middle boundary
		g.setColor(Color.GRAY);
		g.fillRect(50, pAreaH / 2 - 1, 720, 3);
		
		for(int i = 0; i < red.getBullets().size(); i++){
			g.drawImage(red.getBullets().get(i).getImage(), red.getBullets().get(i).getX(), red.getBullets().get(i).getY(), this);
		}
		for(int i = 0; i < blue.getBullets().size(); i++){
			g.drawImage(blue.getBullets().get(i).getImage(), blue.getBullets().get(i).getX(), blue.getBullets().get(i).getY(), this);

		}
		
		g.drawImage(red.getImage(), red.getX(), red.getY(), this);
		g.drawImage(blue.getImage(), blue.getX(), blue.getY(), this);
		g.drawImage(red.getHealthBar(), red.getX() + red.w + 5, red.getY() + 10, this);
		g.drawImage(blue.getHealthBar(), blue.getX() - 10, blue.getY() + 25, this);
		
		//paint(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	/*public void paint(Graphics g) {
	}*/

	void update(){
		red.update();
		blue.update();
		red.wallCheck(pAreaX, pAreaH / 2, pAreaW + pAreaX, pAreaH + pAreaY);
		blue.wallCheck(pAreaX, pAreaY, pAreaW + pAreaX, pAreaH / 2 + pAreaY);
		
		//bullet out of bounds destructor
		red.bulletBounce(pAreaX, pAreaY, pAreaW + pAreaX, pAreaH + pAreaY);
		blue.bulletBounce(pAreaX, pAreaY, pAreaW + pAreaX, pAreaH + pAreaY);
		
		//hit check
		if(red.checkShot(blue)){
			System.out.println("Red wins!");
			blue = new PlayerShip(380, 275, true);
			red = new PlayerShip(380, 755, false);
			redWins = redWins + 1;
		}
		if(blue.checkShot(red)){
			System.out.println("Blue wins!");
			blue = new PlayerShip(380, 275, true);
			red = new PlayerShip(380, 755, false);
			blueWins = blueWins + 1;
		}
		
		repaint(0, 0, 0, getWidth(), getHeight());
	}
	
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
