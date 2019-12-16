package seanComponent;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

public class SeanBGPanel extends JPanel {
	SeanDrawables background;
	ArrayList<SeanDrawables> foreground;
	Animations anime;
	
	public SeanBGPanel(int x, int y, int width, int height,Image bg) {
		super();
		this.setOpaque(false);
		this.setBounds(x, y, width, height);
		background = new SeanDrawables(x,y,width,height);
		background.setImage(bg);
		foreground = new ArrayList<SeanDrawables>();
		anime = new Animations(this);
	}

	public void paintComponent(Graphics g) {
		// draws bg
		background.draw(g);
		
		// draws foreground
		if(foreground.isEmpty()) {
			
		} else {
			for (SeanDrawables sd : foreground) {
				sd.draw(g);
			}
		}
	}
	
	public void resizePanel(int width, int height) {
		background.setBounds(0,0,width,height);
		background.rescaleImage();
		//
		this.setBounds(0,0,width,height);
		repaint();
	}
	
	public SeanDrawables getBG() {
		return background;
	}
	
	public Animations getAnime() {
		return anime;
	}
	
	public void addDrawables(SeanDrawables sd) {
		foreground.add(sd);
	}
	
	public SeanDrawables getDrawables(int index) {
		return foreground.get(index);
	}

}
