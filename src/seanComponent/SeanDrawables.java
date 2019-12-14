package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class SeanDrawables extends Rectangle {
	Color c;
	Image img=null;
	Image scaled=null;
	int imageWidth;
	int imageHeight;

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
	public void setImage(Image i) {
		img = i;
		scaled = i.getScaledInstance(width,height, Image.SCALE_SMOOTH);
		imageWidth =img.getWidth(null);
		imageHeight = img.getHeight(null);
	}
	
	public void rescaleImage() {
		if(scaled == null) {
			
		} else {
			double imgAspect = imageWidth/(double)imageHeight;
			double frameAspect = getWidth()/getHeight();
			
			if(imgAspect > frameAspect) {
				System.out.println(getHeight() +" " + getWidth() + " "+ (int)(getHeight()/imgAspect)+" " +(getHeight()/imgAspect));
				scaled = img.getScaledInstance((int)(getHeight()*imgAspect),height, Image.SCALE_SMOOTH);
			} else {
				scaled = img.getScaledInstance(width,(int)(width/imgAspect), Image.SCALE_SMOOTH);
			}
		}
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}
	
	
	// DRAWING: *******************************************************
	public void draw(Graphics g) {
		if (null == scaled) {
			g.setColor(c);
			g.fillRect(x,y,width,height);
		} else {
			g.drawImage(scaled, -(scaled.getWidth(null)-width)/2, 
						-((scaled.getHeight(null)-height)/2), null);
		}
	}
}
