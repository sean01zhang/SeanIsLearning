package seanComponent;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

public class SeanBGPanel extends JPanel {
	SeanDrawables background;
	Queue<SeanDrawables> foreground;
	
	public SeanBGPanel(int x, int y, int width, int height,Image bg) {
		super();
		this.setOpaque(false);
		this.setBounds(x, y, width, height);
		background = new SeanDrawables(this.getBounds());
		background.setImage(bg);
		foreground = new LinkedList<>();
	}

	public void paintComponent(Graphics g) {
		// draws bg
		background.draw(g);
		
		// draws foreground
		if(foreground.isEmpty()) {
			
		} else {
			for (SeanDrawables sd :(SeanDrawables[])foreground.toArray()) {
				sd.draw(g);
			}
		}
	}
	
	public void resizePanel(int width, int height) {
		background.setBounds(0,0,width,height);
		background.rescaleImage();
		this.setBounds(0,0,width,height);
		repaint();
	}

}
