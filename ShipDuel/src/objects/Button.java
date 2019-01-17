package objects;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Button extends JPanel {
	public Image image;
	public String change;
	public int x;
	public int y;

	public Button(ImageIcon image, String change, int x, int y) {
		super();

		this.change = change;
		this.image = image.getImage();
		this.x = x;
		this.y = y;
	}
}
