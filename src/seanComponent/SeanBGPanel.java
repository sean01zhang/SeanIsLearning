package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

import seanMisc.Animations;
import seanMisc.SeanDrawableForeground;
import seanMisc.SeanDrawables;

public class SeanBGPanel extends SeanComponent {
	SeanDrawables background;
	ArrayList<SeanDrawableForeground> foreground;
	Animations anime;
	
	public SeanBGPanel(int x, int y, int width, int height,Image bg) {
		super();
		this.setOpaque(false);
		this.setBounds(x, y, width, height);
		// how to center:
		background = new SeanDrawables(x,y,width,height,1.0f,bg);
		background.setLocation((background.getScaledImage().getWidth(null)-width)/-2,
				(background.getScaledImage().getHeight(null)-height)/-2);
		
		foreground = new ArrayList<SeanDrawableForeground>();
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
		background.setSize(width, height);
		background.rescaleImage();
		background.setLocation((background.getScaledImageWidth()-width)/-2,
				(background.getScaledImageHeight()-height)/-2);
		
		for (SeanDrawableForeground sd : foreground) {
			sd.setBounds(sd.getPreferredBounds(getX(), getY(), getWidth(), getHeight()));
			sd.rescaleImage();
		}
		
		this.setBounds(0,0,width,height);
		repaint();
	}
	
	public SeanDrawables getBG() {
		return background;
	}
	
	public Animations getAnime() {
		return anime;
	}
	
	public void addDrawables(SeanDrawableForeground sd) {
		foreground.add(sd);
	}
	
	public SeanDrawables getDrawables(int index) {
		return foreground.get(index);
	}

	@Override
	public void setTextColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getTextColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBoundsModified(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
