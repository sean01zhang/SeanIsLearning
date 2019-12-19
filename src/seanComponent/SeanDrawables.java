package seanComponent;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import seanGeometry.SeanShape;

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
	int prevWidth=0;
	int prevHeight=0;
	int scaledImageWidth;
	int scaledImageHeight;
	SeanShape shapeToDraw;

	public SeanDrawables(int x,int y,int width,int height, Color c) {
		super(x,y,width,height);
		this.c = c;
		radians =0;
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(int x,int y,int width,int height) {
		super(x,y,width,height);
		c= Color.white;
		radians =0;
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(int x,int y,int width,int height,float opacity) {
		super(x,y,width,height);
		c= Color.white;
		radians =0;
		this.opacity = opacity;
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(Shape s, float opacity, Color c) {
		super(s.getBounds());
		this.c = c;
		radians =0;
		shapeToDraw = s;
		prevWidth = width;
		prevHeight= height;
	}
	
	// GETTERS AND SETTERS *******************************************
	public void setImage(Image i) {
		img = i;
		setScaledImage(i);
		imageWidth =img.getWidth(null);
		imageHeight = img.getHeight(null);
		rescaleImage();
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
	
	public Image getScaledImage() {
		return scaled;
	}

	public void setScaledImage(Image scaled) {
		this.scaled = scaled;
	}

	public int getScaledImageWidth() {
		return scaledImageWidth;
	}

	public void setScaledImageWidth(int scaledImageWidth) {
		this.scaledImageWidth = scaledImageWidth;
	}

	public int getScaledImageHeight() {
		return scaledImageHeight;
	}

	public void setScaledImageHeight(int scaledImageHeight) {
		this.scaledImageHeight = scaledImageHeight;
	}
	
	// DRAWING: *******************************************************
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(radians,rx,ry);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

		if (null == getScaledImage()) {
			g.setColor(c);
			shapeToDraw.draw(g);
		} else {
			g.drawImage(getScaledImage(), x, y,null);
		}

	}
	
	public void rescaleImage() {
		if(getScaledImage() != null) {
			if (SEAN_RESIZE == resizeMethod) {
				System.out.println("oop");
				System.out.println(getWidth() + " "+ getHeight());
				
				double imgAspect = imageWidth/(double)imageHeight;
				double frameAspect = getWidth()/getHeight();
				
				if(imgAspect > frameAspect) {
					scaledImageWidth =(int)(getHeight()*imgAspect);
					scaledImageHeight = height;
				} else {
					scaledImageWidth = width;
					scaledImageHeight = (int)(width/imgAspect);
				}
			} else {
				scaledImageWidth = width;
				scaledImageHeight = height;
			}
			
			setScaledImage(img.getScaledInstance(scaledImageWidth,scaledImageHeight, Image.SCALE_SMOOTH));
		}
	}
}
