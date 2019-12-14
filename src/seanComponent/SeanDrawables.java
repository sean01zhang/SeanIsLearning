package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class SeanDrawables extends Rectangle {
	Color c;
	Image img;

	public SeanDrawables(Rectangle hitbox) {
		super(hitbox);
		c= Color.white;
	}

	public SeanDrawables(Rectangle hitbox, Color c) {
		super(hitbox);
		this.c = c;
	}
	
	public SeanDrawables(int x,int y,int width,int height, Color c) {
		super(x,y,width,height);
		this.c = c;
	}
	
	public SeanDrawables(int x,int y,int width,int height) {
		super(x,y,width,height);
		c= Color.white;
	}
	
	// GETTERS AND SETTERS *******************************************
	public void setImage(Image i,int width,int height) {
		img = i.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	public void setImage(Image i) {
		img = i.getScaledInstance(width,height, Image.SCALE_DEFAULT);
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}
	
	
	// DRAWING: *******************************************************
	public void draw(Graphics g, int radius) {
		if (null == img) {
			g.setColor(c);
			g.fillRoundRect(x,y,width,height,radius,radius);
		} else {
			g.drawImage(img, 0, 0, null);
		}
	}

	public void draw(Graphics g) {
		if (null == img) {
			g.setColor(c);
			g.fillRect(x,y,width,height);
		} else {
			g.drawImage(img, 0, 0, null);
		}
	}
}
