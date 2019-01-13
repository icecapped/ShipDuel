package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(720, 900);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class Frame extends JFrame implements MouseListener {
	boolean isMainMenu;
	//fixed distances to move the "back" button image into the correct position
	int backxfix = 8;
	int backyfix = 31;
	//fixed distances to move the "rules" and "play" button into the corret position
	int xfix = 95;
	int yfix = 125;
	
	Container c;
	CardLayout cards;
	JPanel one;
	JPanel two;
	JPanel mainMenuFormat;
	JPanel backFormat;
	button play;
	button goToRules;
	button goToMainMenu;
	MFrame frame;

	public Frame() {
		super("menu");
		isMainMenu = false;
		
		frame = new MFrame("game");
		
		c = getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);

		//main menu panel
		one = new JPanel();
		one.setLayout(new BorderLayout());
		
		//rules panel
		two = new JPanel();
		two.setLayout(new BorderLayout());
		
		//JPanel used to size the main menu properly
		mainMenuFormat = new JPanel();
		mainMenuFormat.setLayout(new GridLayout(5, 4));
		
		backFormat = new JPanel();
		backFormat.setLayout(new GridLayout(1, 6));
		
		goToMainMenu = new button(new ImageIcon("src/assets/back.png"), "mainMenu");
		goToMainMenu.addMouseListener(this);
		
		goToRules = new button(new ImageIcon("src/assets/rules.png"), "rules");
		goToRules.addMouseListener(this);
		
		play = new button (new ImageIcon("src/assets/play.png"), "");
		play.addMouseListener(this);
	
		one.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.NORTH);
		one.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.EAST);
		one.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.SOUTH);
		one.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.WEST);

		one.add(mainMenuFormat, BorderLayout.CENTER);
		
		for (int i = 0; i < 12; i = i +1)
			mainMenuFormat.add(new JLabel());
		mainMenuFormat.add(goToRules);
		for (int i = 0; i < 2; i = i + 1)
			mainMenuFormat.add(new JLabel());
		mainMenuFormat.add(play);
		for (int i = 0; i < 4; i = i + 1)
			mainMenuFormat.add(new JLabel());

		two.add(backFormat, BorderLayout.NORTH);
		two.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.CENTER);
		two.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.EAST);
		two.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.SOUTH);
		two.add(new JLabel(new ImageIcon("src/assets/block.png")), BorderLayout.WEST);
		
		backFormat.add(goToMainMenu);
		for (int i = 0; i < 5; i = i + 1)
			backFormat.add(new JLabel(new ImageIcon("src/assets/bigBlock.png")));
		
		c.add(one, "mainMenu");
		c.add(two, "rules");
		
	}
	public void paint (Graphics g) {
		super.paint(g);
		g.drawImage(new ImageIcon("src/assets/CosmicSky.png").getImage(), 0, 0, null);
		
		if (!isMainMenu) {
			g.drawImage(goToRules.image.getImage() , goToRules.getX() + xfix, goToRules.getY() + yfix, null);
			g.drawImage(play.image.getImage(), play.getX() + xfix, play.getY() + yfix, null);
			
		}
		else{
			g.drawImage(goToMainMenu.image.getImage(), goToMainMenu.getX() + backxfix, goToMainMenu.getY() + backyfix, null);
		}
		isMainMenu = !isMainMenu;
	}
	public void mouseClicked(MouseEvent e) {
		
		if (e.getComponent() == goToMainMenu)
			cards.show(c, goToMainMenu.change);
		else if (e.getComponent() == goToRules)
			cards.show(c, goToRules.change);
		else {
			cards.show(c, "play");
			frame.setSize(825, 990);
			frame.setVisible(true);
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}

class button extends JPanel {
	ImageIcon image;
	String change;

	public button(ImageIcon image, String change) {
		super();

		this.change = change;
		this.image = image;
	}
}





