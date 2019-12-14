package seanComponent;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedRect extends RoundRectangle2D {
	int x,y,width,height;
	int arcw,arch;
	
	public RoundedRect(int x, int y, int width, int height, int arcw,int arch) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.arcw = arcw;
		this.arch = arch;
	}

	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle(x,y,width,height);
	}

	@Override
	public double getArcWidth() {
		return arcw;
	}

	@Override
	public double getArcHeight() {
		return arch;
	}

	@Override
	public void setRoundRect(double x, double y, double w, double h, double arcWidth, double arcHeight) {
		this.x = (int)x;
		this.y = (int)y;
		this.width = (int)w;
		this.height = (int)h;
		this.arcw = (int)arcWidth;
		this.arch = (int)arcHeight;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
}
