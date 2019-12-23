package seanGeometry;

import java.awt.Graphics;
import java.awt.Shape;

public interface SeanShape extends Shape {
	public abstract void draw(Graphics g);
	
	public abstract void setBounds(int x, int y, int width,int height);
	
	public abstract void setLocation(int x, int y);
	
	public abstract void setSize(int w, int h);
}
