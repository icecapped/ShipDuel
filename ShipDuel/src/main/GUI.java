package main;

import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
		MFrame frame = new MFrame("Game");
		
		frame.setSize(825, 980);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}