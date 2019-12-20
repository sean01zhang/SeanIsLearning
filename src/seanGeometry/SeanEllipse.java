package seanGeometry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SeanEllipse extends Ellipse2D implements SeanShape {
	int x, y,width,height;
	
	public SeanEllipse(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
	}
	
	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle(x,y,width,height);
	}

	@Override
	public void draw(Graphics g) {
		g.fillOval(x, y, width, height);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setFrame(double x, double y, double w, double h) {
		this.x = (int)x;
		this.y = (int)y;
		this.width = (int)w;
		this.height= (int)h;
	}

}
