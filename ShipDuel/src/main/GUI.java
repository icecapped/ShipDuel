package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(720, 945);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

class Frame extends JFrame implements MouseListener {
	static final Image BG_IMAGE = new ImageIcon("src/assets/CosmicSky.png").getImage();
	static final Image TITLE = new ImageIcon("src/assets/title.png").getImage();
	static final Image RULES = new ImageIcon("src/assets/rulesImg.png").getImage();
	static final ImageIcon BLOCK = new ImageIcon("src/assets/block.png");
	
	boolean isMainMenu = false;
	Container c;
	
	CardLayout cards;
	JPanel mainMenu;
	JPanel rules;
	JPanel mainMenuFormat;
	JPanel backFormat;
	
	button play;
	button goToRules;
	button goToMainMenu;
	
	MFrame frame;

	public Frame() {
		super("menu");

		c = getContentPane();
		
		cards = new CardLayout();
		mainMenu = new JPanel();
		rules = new JPanel();
		mainMenuFormat = new JPanel();
		backFormat = new JPanel();
		
		goToMainMenu = new button(new ImageIcon("src/assets/back.png"), "mainMenu");
		goToRules = new button(new ImageIcon("src/assets/rules.png"), "rules");
		play = new button (new ImageIcon("src/assets/play.png"), "");
	
		goToMainMenu.addMouseListener(this);
		goToRules.addMouseListener(this);
		play.addMouseListener(this);
		
		setLayouts();
		
		populateMainMenuFormat();
		mainMenu.add(mainMenuFormat, BorderLayout.CENTER);
		
		populateBackFormat();
		rules.add(backFormat, BorderLayout.NORTH);
		
		c.add(mainMenu, "mainMenu");
		c.add(rules, "rules");
		
	}
	public void setLayouts() {
		c.setLayout(cards);
		
		mainMenu.setLayout(new BorderLayout());
		rules.setLayout(new BorderLayout());
		mainMenuFormat.setLayout(new GridLayout(5, 4));
		backFormat.setLayout(new GridLayout(1, 6));
	}
	
	public void populateMainMenuFormat() {
		for (int i = 0; i < 12; i = i +1)
			mainMenuFormat.add(new JLabel());
		mainMenuFormat.add(goToRules);
		
		for (int i = 0; i < 2; i = i + 1)
			mainMenuFormat.add(new JLabel());
		mainMenuFormat.add(play);
		
		for (int i = 0; i < 4; i = i + 1)
			mainMenuFormat.add(new JLabel());
	}
	
	public void populateBackFormat() {
		backFormat.add(goToMainMenu);
		for (int i = 0; i < 5; i = i + 1)
			backFormat.add(new JLabel(BLOCK));		
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		g.drawImage(BG_IMAGE, 0, 0, null);
		if (!isMainMenu) {
			g.drawImage(TITLE, 32, 100, null);
			g.drawImage(goToRules.image, 3, 580, null);
			g.drawImage(play.image, 538, 580, null);
		}
		else{
			g.drawImage(goToMainMenu.image, 3, 28, null);
			g.drawImage(RULES, 150, 150, null);
		}
		isMainMenu = !isMainMenu;
	}
	public void mouseClicked(MouseEvent e) {
		
		if (e.getComponent() == goToMainMenu) {
			cards.show(c, goToMainMenu.change);
			repaint();
		}
		else if (e.getComponent() == goToRules) {
			cards.show(c, goToRules.change);
			repaint();
		}
		else {
			frame = new MFrame("game");
			frame.setSize(870, 970);
			frame.setResizable(false);
			frame.setVisible(true);
		}
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
	Image image;
	String change;

	public button(ImageIcon image, String change) {
		super();

		this.change = change;
		this.image = image.getImage();
	}
}
