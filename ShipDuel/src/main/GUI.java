package main;

import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
		MFrame frame = new MFrame("Game");
		
		frame.setSize(700, 1000);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}