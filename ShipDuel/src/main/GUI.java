package main;

import javax.swing.*;

public class GUI {
	
	static JFrame frame;
	
	/**
	 * Launch Menu
	 */
	public static void main(String[] args) {
		frame = new MenuFrame();
		frame.setSize(720, 945);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	/**
	 * Launch Game
	 */
	static void launchGame(){
		frame.dispose();
		frame = new MFrame("Game");
		
		frame.setSize(900, 980);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}