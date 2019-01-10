package objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {

	//Drawing and physics variables
	private static final int S_SHOT_SPD = 20;
	private static final int M_SHOT_SPD = 30;
	private static final int L_SHOT_SPD = 40;
	private int ySpeed;
	private int xSpeed;
	private int x; //hit boxes
	private int y;
	public int drawX; //drawing
	public int drawY;
	private boolean direction; // false belongs to red
	
	//Animation Variables
	private static final int FULL_LOOP = 1;
	private static final int FRAME_DELAY = 4;
	private Image image;
	private int aLoop;
	private int loopState;
	
	public Bullet(int x, int y, int initXVel, int initYVel, boolean direction, int size){
		this.x = x;
		this.y = y;
		xSpeed = initXVel;
		ySpeed = initYVel;
		switch(size){
		case 0:
			ySpeed += S_SHOT_SPD;
			break;
		case 1:
			ySpeed += M_SHOT_SPD;
			break;
		case 2:
			ySpeed += L_SHOT_SPD;
		}
		
		this.direction = direction;
		loopState = 0;
		aLoop = 0;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public Image getImage(){
		return image;
	}
	
	public void updateImage(){
		if(!direction){
			image = new ImageIcon("src/assets/bullet" + aLoop + ".png").getImage();
		}
		else{
			image = new ImageIcon("src/assets/bullet" + aLoop + ".png").getImage();
		}
		loopState++;
		if(loopState >= FRAME_DELAY){
			aLoop++;
			if(aLoop >= FULL_LOOP){
				aLoop = 0;
			}
			loopState = 0;
		}
	}
	
	public void update(){
		x += xSpeed;
		y += ySpeed;
		
		updateImage();
	}
	
	/**
	 * returns true if runs out of bounds (not wall bounce)
	 */
	public boolean wallCheck(int minX, int minY, int maxX, int maxY){
		if(x <= minX){
			x = minX;
			xSpeed = -xSpeed;
		}
		if(y <= minY) return true;
		if(x >= maxX){
			x = maxX;
			xSpeed = -xSpeed;
		}
		if(y>= maxY) return true;
		
		return false;
	}
	
	/**
	 * returns true if runs into bound
	 */
	public boolean collisionCheck(int minX, int minY, int maxX, int maxY){
		if(x >= minX && y >= minY && x <= maxX && y <= maxY){
			return true;
		}
		return false;
	}
}