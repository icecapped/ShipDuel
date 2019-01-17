import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class icswork {
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(720, 990);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class Frame extends JFrame implements MouseListener {
	int screen;
	int backxfix = 8;
	int backyfix = 31;
	int xfix = 95;
	int yfix = 125;
	Container c;
	CardLayout cards;
	JPanel one;
	JPanel two;
	JPanel mainMenuSplit;
	JPanel backSplit;
	button play;
	button mainMenu;
	button rules;

	public Frame() {
		super("yeet");
		screen = 2;
		
		c = getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);

		one = new JPanel();
		one.setLayout(new BorderLayout());
		
		two = new JPanel();
		two.setLayout(new BorderLayout());
		
		mainMenuSplit = new JPanel();
		mainMenuSplit.setLayout(new GridLayout(5, 4));
		
		backSplit = new JPanel();
		backSplit.setLayout(new GridLayout(1, 6));
		
		rules = new button(new ImageIcon("back.png"), "mainMenu");
		rules.addMouseListener(this);
		rules.setBackground(Color.RED);
		
		one.add(backSplit, BorderLayout.NORTH);
		one.add(new JLabel(new ImageIcon("block.png")), BorderLayout.CENTER);
		one.add(new JLabel(new ImageIcon("block.png")), BorderLayout.EAST);
		one.add(new JLabel(new ImageIcon("block.png")), BorderLayout.SOUTH);
		one.add(new JLabel(new ImageIcon("block.png")), BorderLayout.WEST);
		
		backSplit.add(rules);
		for (int i = 0; i < 5; i = i + 1)
			backSplit.add(new JLabel(new ImageIcon("bigBlock.png")));
		
		mainMenu = new button(new ImageIcon("rules.png"), "rules");
		mainMenu.addMouseListener(this);
		mainMenu.setBackground(Color.BLUE);
		
		play = new button (new ImageIcon("play.png"), "rules");
		play.addMouseListener(this);
		play.setBackground(Color.GREEN);
		
		two.add(new JLabel(new ImageIcon("block.png")), BorderLayout.NORTH);
		two.add(new JLabel(new ImageIcon("block.png")), BorderLayout.EAST);
		two.add(new JLabel(new ImageIcon("block.png")), BorderLayout.SOUTH);
		two.add(new JLabel(new ImageIcon("block.png")), BorderLayout.WEST);

		two.add(mainMenuSplit, BorderLayout.CENTER);
		
		for (int i = 0; i < 12; i = i +1)
			mainMenuSplit.add(new JLabel());
		mainMenuSplit.add(mainMenu);
		for (int i = 0; i < 2; i = i + 1)
			mainMenuSplit.add(new JLabel());
		mainMenuSplit.add(play);
		for (int i = 0; i < 4; i = i + 1)
			mainMenuSplit.add(new JLabel());
		
		c.add(one, "rules");
		c.add(two, "mainMenu");
	}
	public void paint (Graphics g) {
		super.paint(g);
		
		g.drawImage(new ImageIcon("background.png").getImage(), 0, 0, null);
		
		if (screen == 1) {
			g.drawImage(mainMenu.image.getImage() , mainMenu.getX() + xfix, mainMenu.getY() + yfix, null);
			g.drawImage(play.image.getImage(), play.getX() + xfix, play.getY() + yfix, null);
			screen = 2;
		}
		else {
			g.drawImage(rules.image.getImage(), rules.getX() + backxfix, rules.getY() + backyfix, null);
			screen = 1;
		}
	}
	public void mouseClicked(MouseEvent e) {
		
		if (e.getComponent() == rules)
			cards.show(c, rules.change);
		else
			cards.show(c, mainMenu.change);
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





