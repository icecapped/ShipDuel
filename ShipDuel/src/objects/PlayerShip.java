package objects;

import java.awt.*;
import java.awt.event.*;
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
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage(){
		return image;
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
			}
		}
	}
}
