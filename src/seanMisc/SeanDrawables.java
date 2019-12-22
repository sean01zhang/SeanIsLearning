package seanMisc;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import seanGeometry.SeanRoundedRect;
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
	int resizeMethod=0;
	final int SEAN_RESIZE = 0;
	final int STRETCH_RESIZE =1;
	int prevWidth=0;
	int prevHeight=0;
	int scaledImageWidth;
	int scaledImageHeight;
	private SeanShape shapeToDraw;

	public SeanDrawables(SeanShape s) {
		super(s.getBounds());
		c = Color.white;
		this.setShapeToDraw(s);
		radians =0;
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(int x,int y,int width,int height) {
		super(x,y,width,height);
		setShapeToDraw(new SeanRoundedRect(x,y,width,height,0,0));
		c= Color.white;
		radians =0;
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(int x,int y,int width,int height,float opacity) {
		super(x,y,width,height);
		setShapeToDraw(new SeanRoundedRect(x,y,width,height,0,0));
		c= Color.white;
		radians =0;
		this.opacity = opacity;
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(SeanShape s, float opacity, Color c) {
		super(s.getBounds());
		this.c = c;
		radians =0;
		setShapeToDraw(s);
		prevWidth = width;
		prevHeight= height;
	}
	
	public SeanDrawables(int x,int y,int width,int height,float opacity,Image i) {
		super(x,y,width,height);
		c= Color.white;
		radians =0;
		this.opacity = opacity;
		prevWidth = width;
		prevHeight= height;
		setImage(i);
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
	
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		resize();
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		resize();
	}
	
	// DRAWING: *******************************************************
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(radians,rx,ry);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

		if (null == getScaledImage()) {
			g.setColor(c);
			getShapeToDraw().draw(g);
		} else {
			g.drawImage(getScaledImage(), x, y,null);
		}
		
		g2d.rotate(-radians,rx,ry);
	}
	
	public void resize() {
		if(img != null) {
			rescaleImage();
		} else {
			getShapeToDraw().setBounds(x, y, (int)getWidth(), (int)getHeight());
		}
	}
	
	public void rescaleImage() {
		if(getScaledImage() != null) {
			if (SEAN_RESIZE == resizeMethod) {	
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

	public SeanShape getShapeToDraw() {
		return shapeToDraw;
	}

	public void setShapeToDraw(SeanShape shapeToDraw) {
		this.shapeToDraw = shapeToDraw;
	}
}
