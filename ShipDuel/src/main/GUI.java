package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
		menuFrame frame = new menuFrame();
		frame.setSize(720, 945);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}