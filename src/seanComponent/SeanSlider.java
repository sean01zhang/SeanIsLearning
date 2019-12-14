package seanComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class SeanSlider extends JComponent implements MouseListener, MouseMotionListener{
	
	Point compCoords = new Point();
	
	SeanDrawables bg;
	SeanDrawables in;
	String type; //circular, straight, wavy, 2D (ex. a map), etc.
	int max;
	int min;
	int currentValue;
	
	public SeanSlider(SeanDrawables bg, SeanDrawables in, int max, int min, String type){
		compCoords = null;
		this.bg = bg;
		this.in = in;
		this.type = type;
		this.max = max;
		this.min = min;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setOpaque(false);
		this.setSize(200, 200);
		this.setLocation(20, 20);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// turn on anti-alias mode
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		bg.draw(g);
		in.draw(g);
		System.out.println(in.getX() + ", " + in.getY());
	}
	
	public void setValue(int value){
		currentValue = value;
	}
	
	public void setMin(int min){
		this.min = min;
	}
	
	public void setInLocation(int x, int y){
		int modX;
		int modY;
		if(x >= bg.getMaxX()){
			modX = (int)bg.getMaxX() - (int)in.getMaxX();
		} else if (x <= bg.getMinX()){
			modX = (int)bg.getMinX() - (int)in.getMinX();
		} else {
			modX = x;
		}
		if(y >= bg.getMaxY()){
			modY = (int)bg.getMaxY() - (int)in.getMaxY();
		} else if (y <= bg.getMinY()){
			modY = (int)bg.getMinY() - (int)in.getMinY();
		} else {
			modY = y;
		}
		in.setLocation(modX, modY);
	}
	
	public void setBgLocation(int x, int y){
		bg.setLocation(x, y);
	}
	
	public void setMax(int max){
		this.max = max;
	}
	
	public int getValue(){
		return currentValue;
	}

	//Mouse Listener Stuffs *************************************************
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("clicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		compCoords = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		compCoords = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//Point currCoords = e.getLocationOnScreen();
		if(in.width < e.getX() && in.width > e.getX()){
			setInLocation(e.getX() - (int)(in.width/2), e.getY() - (int)(in.height/2));
		} else {
			
		}
		
		repaint();
		System.out.println(e.getX() + ", " + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}	
}
