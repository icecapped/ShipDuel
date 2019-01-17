package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objects.Button;

class menuFrame extends JFrame implements MouseListener {
	
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
	
	Button play;
	Button goToRules;
	Button goToMainMenu;
	
	MFrame frame;

	public menuFrame() {
		super("menu");

		c = getContentPane();
		
		cards = new CardLayout();
		mainMenu = new JPanel();
		rules = new JPanel();
		mainMenuFormat = new JPanel();
		backFormat = new JPanel();
		
		goToMainMenu = new Button(new ImageIcon("src/assets/back.png"), "mainMenu", 3, 27);
		goToRules = new Button(new ImageIcon("src/assets/rules.png"), "rules", 3, 580);
		play = new Button (new ImageIcon("src/assets/play.png"), "", 538, 580);
	
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
			g.drawImage(goToRules.image, goToRules.x, goToRules.y, null);
			g.drawImage(play.image, play.x, play.y, null);
		}
		else{
			g.drawImage(goToMainMenu.image, goToMainMenu.x, goToMainMenu.y, null);
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
