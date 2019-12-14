package seanComponent;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;

@SuppressWarnings("serial")
public class SeanDrawables extends Rectangle {
	Color c;
	Image img=null;
	Image scaled=null;
	int imageWidth;
	int imageHeight;
	double radians;
	double rx = 0;
	double ry =0;
	float opacity = (float) 1.0;


	public SeanDrawables(int x,int y,int width,int height, Color c) {
		super(x,y,width,height);
		this.c = c;
		radians =0;
	}
	
	public SeanDrawables(int x,int y,int width,int height) {
		super(x,y,width,height);
		c= Color.white;
		radians =0;
	}
	
	public SeanDrawables(int x,int y,int width,int height,float opacity) {
		super(x,y,width,height);
		c= Color.white;
		radians =0;
		this.opacity = opacity;
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
	
	public void setRadians(double rad) {
		radians = rad;
	}
	
	public double getRadians() {
		return radians;
	}
	
	public void setPivotX(double rx) {
		this.rx = rx;
	}
	
	public double getPivotX() {
		return rx;
	}
	
	public void setPivotY(double ry) {
		this.ry = ry;
	}
	
	public double getRotationY() {
		return ry;
	}
	
	public float getOpacity() {
		return opacity;
	}
	
	public void setOpacity(float o) {
		opacity =o;
	}
	
	// DRAWING: *******************************************************
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(radians,rx,ry);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		if (null == scaled) {
			g.setColor(c);
			g.fillRect(x,y,width,height);
		} else {
			g.drawImage(scaled, -(scaled.getWidth(null)-width)/2, 
						-((scaled.getHeight(null)-height)/2), null);
		}
	}
}
