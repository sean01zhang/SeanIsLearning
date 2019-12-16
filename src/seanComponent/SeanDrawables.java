package seanComponent;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class SeanDrawables extends Rectangle {
	Color c;
	Image img=null;
	private Image scaled=null;
	int imageWidth;
	int imageHeight;
	double radians;
	double rx = 0;
	double ry =0;
	float opacity = (float) 1.0;
	int cornerRadii =0;
	int resizeMethod=0;
	final int SEAN_RESIZE = 0;
	final int STRETCH_RESIZE =1;


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
		setScaledImage(i);
		imageWidth =img.getWidth(null);
		imageHeight = img.getHeight(null);
		rescaleImage();
	}
	
	public void rescaleImage() {
		if(getScaledImage() != null) {
			if (SEAN_RESIZE == resizeMethod) {
				System.out.println("oop");
				System.out.println(getWidth() + " "+ getHeight());
				
				double imgAspect = imageWidth/(double)imageHeight;
				double frameAspect = getWidth()/getHeight();
				
				if(imgAspect > frameAspect) {
					System.out.println(getHeight() +" " + getWidth() + " "+ (int)(getHeight()/imgAspect)+" " +(getHeight()/imgAspect));
					setScaledImage(img.getScaledInstance((int)(getHeight()*imgAspect),height, Image.SCALE_SMOOTH));
				} else {
					setScaledImage(img.getScaledInstance(width,(int)(width/imgAspect), Image.SCALE_SMOOTH));
				}
			} else {
				setScaledImage(img.getScaledInstance((int)getWidth(),(int)getHeight(), Image.SCALE_SMOOTH));
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
	
	public int getCornerRadii() {
		return cornerRadii;
	}
	
	public void setCornerRadii(int cornerRadii) {
		this.cornerRadii = cornerRadii;
	}
	
	// DRAWING: *******************************************************
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(radians,rx,ry);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		if(cornerRadii !=0)
		g.setClip(new RoundedRect((int)this.getX(),(int)this.getY(),(int)getWidth(),(int)getHeight(),cornerRadii,cornerRadii));
		
		if (null == getScaledImage()) {
			g.setColor(c);
			g.fillRect(x,y,width,height);
		} else {
			if (resizeMethod == SEAN_RESIZE) {
				/*
				g.drawImage(scaled, -(scaled.getWidth(null)-width)/2, 
						-((scaled.getHeight(null)-height)/2), null);*/
				g.drawImage(getScaledImage(), x, y,null);
			} else {
				g.drawImage(getScaledImage(), x, y,null);
			}
			
		}
		
		if(cornerRadii !=0)
		g.setClip(null);
	}

	public Image getScaledImage() {
		return scaled;
	}

	public void setScaledImage(Image scaled) {
		this.scaled = scaled;
	}
}
