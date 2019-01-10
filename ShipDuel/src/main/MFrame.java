package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

class MFrame extends JFrame implements ActionListener{
	
	static GameContainer panel;
	
	public class MAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			panel.red.keyPressed(e);
			panel.blue.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			panel.red.keyReleased(e);
			panel.blue.keyReleased(e);
		}
	}
	
	MFrame(String s){
		super(s);
		panel = new GameContainer();
		this.addKeyListener(new MAdapter());
		this.setContentPane(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.dispatchEvent(e);
	}
}
