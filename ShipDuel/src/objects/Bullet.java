package objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {

	//Drawing and physics variables
	private static final int S_SHOT_SPD = 15;
	private static final int M_SHOT_SPD = 25;
	private static final int L_SHOT_SPD = 35;
	private int ySpeed;
	private int xSpeed;
	private int x; //hit boxes
	private int y;
	private int w;
	private int h;
	private boolean direction; // false belongs to red
	private int charge;
	
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
		this.direction = direction;
		loopState = 0;
		aLoop = 0;
		charge = size;
		
		if(direction){
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
		}
		else{
			switch(size){
			case 0:
				ySpeed -= S_SHOT_SPD;
				break;
			case 1:
				ySpeed -= M_SHOT_SPD;
				break;
			case 2:
				ySpeed -= L_SHOT_SPD;
			}
		}
		
	}		
	
	public int getX(){
		return x;
	}
	public int getCharge(){
		return charge;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return image;
	}
	
	public void updateImage(){
		//There's no animation implemented as of yet, but it's a future framework
		if(!direction){
			image = new ImageIcon("src/assets/bulletr" + aLoop + ".png").getImage();
		}
		else{
			image = new ImageIcon("src/assets/bulletb" + aLoop + ".png").getImage();
		}
		loopState++;
		if(loopState >= FRAME_DELAY){
			aLoop++;
			if(aLoop >= FULL_LOOP){
				aLoop = 0;
			}
			loopState = 0;
		}
		h = image.getHeight(null);
		w = image.getWidth(null);
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
		if(x + w >= maxX){
			x = maxX - w;
			xSpeed = -xSpeed;
		}
		if(y >= maxY) return true;
		
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
