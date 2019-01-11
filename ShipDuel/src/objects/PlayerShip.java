package objects;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class PlayerShip {
	
	//Drawing and physics variables
	private static final int MAX_SPD = 10;
	private static final int MIN_SPD = -10;
	private int x;
	private int y;
	private int xa;
	private int ya;
	private int xs;
	private int ys;
	private boolean color;
	public int w;
	public int h;
	
	//Animation Variables
	private static final int FULL_LOOP = 3;
	private static final int FRAME_DELAY = 6;
	private Image image;
	private int aLoop; //# of animation steps
	private int loopState; //# of frames between steps
	
	//Keypress variables
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean shoot;
	
	//Shooting
	private static final int CHARGE_TIME = 20;
	private ArrayList<Bullet> bullets; 
	private int shotCharge; // 0 to 2
	private int shotLoop;
	
	/**
	 * false is red, true is blue
	 */
	public PlayerShip(int x, int y, boolean color){
		this.x = x;
		this.y = y;
		xa = 0;
		ya = 0;
		xs = 0;
		ys = 0;
		this.color = color;
		aLoop = 0;
		loopState = 0;
		up = false;
		down = false;
		left = false;
		right = false;
		bullets = new ArrayList<>();
		
		updateImage();
	}
	
	public void updateImage(){
		if(!color){
			image = new ImageIcon("src/assets/red" + aLoop + ".png").getImage();
		}
		else{
			image = new ImageIcon("src/assets/blue" + aLoop + ".png").getImage();
		}
		loopState++;
		if(loopState >= FRAME_DELAY){
			aLoop++;
			if(aLoop >= FULL_LOOP){
				aLoop = 0;
			}
			loopState = 0;
		}

		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	
	public void update(){
		xa = 0;
		ya = 0;
		
		if(left) xa -= 1;
		if(right) xa += 1;
		if(up) ya -= 1;
		if(down) ya += 1;
		
		xs += xa;
		ys += ya;
		
		if(xs > 0 && loopState % 2 == 0) xs -= 1;
		if(xs < 0 && loopState % 2 == 0) xs += 1;
		if(ys > 0 && loopState % 2 == 0) ys -= 1;
		if(ys < 0 && loopState % 2 == 0) ys += 1; 
		
		xs = Math.min(xs, MAX_SPD);
		ys = Math.min(ys, MAX_SPD);
		xs = Math.max(xs, MIN_SPD);
		ys = Math.max(ys, MIN_SPD);
		
		
		x += xs;
		y += ys;
		
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).update();
			
		}
		
		if(shoot) updateCharge();
		updateImage();
	}
	
	public void wallCheck(int minX, int minY, int maxX, int maxY){
		if(x <= minX){
			x = minX;
			xs = -xs * 3 / 2;
		}
		if(y <= minY){
			y = minY;
			ys = -ys * 3 / 2;
		}
		if(x + w >= maxX){
			x = maxX - w;
			xs = -xs * 3 / 2;
		}
		if(y + h >= maxY){
			y = maxY - h;
			ys = -ys * 3 / 2;
		}
	}
	
	//Called while charging
	//TODO: SHOW SHOT IS CHARGING (whileshotLoop != 0 && shotCharge != 0)
	public void updateCharge(){
		shotLoop++;
		if(shotLoop >= CHARGE_TIME){
			shotCharge++;
			shotLoop = 0;
		}
		if(shotCharge >= 2){
			shotLoop = 0;
		}
	}
	//Called after shot is fired
	public void resetCharge(){
		shotLoop = 0;
		shotCharge = 0;
	}
	
	//Called to fire shot
	public void shootCharge(){
		bullets.add(new Bullet(x + w / 2 - 7, y + h / 2, xs / 2, ys / 2, color, shotCharge));
		resetCharge();
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
	
	public ArrayList<Bullet> getBullets(){
		return bullets;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(!color){
			switch(key){
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_SPACE:
				shoot = true;
			}
		}
		else{
			switch(key){
			case KeyEvent.VK_A:
				left = true;
				break;
			case KeyEvent.VK_D:
				right = true;
				break;
			case KeyEvent.VK_S:
				down = true;
				break;
			case KeyEvent.VK_W:
				up = true;
				break;
			case KeyEvent.VK_Q:
				shoot = true;
			}
		}

	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(!color){
			switch(key){
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_SPACE:
				shoot = false;	
				shootCharge();
			}
		}
		else{
			switch(key){
			case KeyEvent.VK_A:
				left = false;
				break;
			case KeyEvent.VK_D:
				right = false;
				break;
			case KeyEvent.VK_S:
				down = false;
				break;
			case KeyEvent.VK_W:
				up = false;
				break;
			case KeyEvent.VK_Q:
				shoot = false;	
				shootCharge();
			}
		}
	}
}
