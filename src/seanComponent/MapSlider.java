package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

import seanGeometry.SeanRoundedRect;
import seanMisc.SeanDrawables;

@SuppressWarnings("serial")
public class MapSlider extends JComponent implements MouseListener, MouseMotionListener{
	
	Point compCoords = new Point();
	
	SeanDrawables bg;
	SeanDrawables in;
	SeanDrawables helloWorld;
	SeanRoundedRect defRect;
	SeanTextArea ssOutput;
	String type; //circular, straight, wavy, 2D (ex. a map), etc.
	int max;
	int min;
	int currentValue;
	int currentValue2;
	int radiiIn;
	int radiiBg;
	
	public MapSlider(SeanDrawables bg, SeanDrawables in, int max, int min, int radiiBg, int radiiIn, String type){
		compCoords = null;
		this.bg = bg;
		this.in = in;
		this.type = type;
		this.max = max;
		this.min = min;
		currentValue = (int)((max - min)*(in.getX()/(bg.width - in.width))) + min;
		currentValue2 = (int)((max - min)*(in.getY()/(bg.height - in.height))) + min;
		this.radiiIn = radiiIn;
		this.radiiBg = radiiBg;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setOpaque(false);
		this.setSize(bg.width, bg.height);
		this.setLocation((int)bg.getX(), (int)bg.getY());
		
		defRect = new SeanRoundedRect(0, 0, 100, 50, 30, 30);
		helloWorld = new SeanDrawables(defRect, 1f, Color.WHITE);
		/*
		System.out.println(in.getX());
		System.out.println(in.getY());
		System.out.println(bg.getX());
		System.out.println(bg.getY());
		*/
		
		if(type.equals("horizontal")){
			bg.height = in.height;
			bg.setBounds((int)bg.getX(), (int)bg.getY(), bg.width, bg.height);
			in.setBounds(0, 0, in.width, in.height);
		} else if (type.equals("vertical")){
			bg.width = in.width;
			bg.setBounds((int)bg.getX(), (int)bg.getY(), bg.width, bg.height);
			in.setBounds(0, 0, in.width, in.height);
		} else if (type.equals("modern hor")){
		} else if (type.equals("modern vert")){
			
		} else if (type.equals("color picker")){
			setTestColor();
		}
		
		//in.setLocation(0, 0);
		bg.setLocation(0, 0);
		
		//text area stuff
		/*ssOutput = new SeanTextArea();
		ssOutput.setText(getValue() + "," + getValue2());
		ssOutput.setBounds(250, 100, 200, 100);
		ssOutput.setRadius(30);
		ssOutput.setScrollType(SeanTextArea.SCROLL_CHAR);
		ssOutput.setSpeed(50);
		add(ssOutput);*/
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// turn on anti-alias mode
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		//g.setClip(new SeanRoundedRect(0, 0, bg.width, bg.height, radiiBg, radiiBg));
		//bg.draw(g);
		//in.draw(g);
		//defRect.draw(g);
		helloWorld.draw(g);
		//System.out.println(in.getX() + ", " + in.getY());
		//in. getX, getY --> 0,0 with respect to where position of bg is on frame
	}
	
	public void setValue(int value){
		currentValue = value;
	}
	
	public void setValue2(int value2){
		if(type.equals("2d")){
			currentValue2 = value2;
		}
	}
	
	public void setMin(int min){
		this.min = min;
	}
	
	public void setInLocation(int x, int y){
		int modX;
		int modY;
		if(x + in.width >= bg.width){
			modX = bg.width - in.width;
		} else if (x <= 0 && !type.equals("modern hor")){
			modX = 0;
		} else {
			modX = x;
		}
		if(y + in.width >= bg.height){
			modY = bg.height - in.height;
		} else if (y <= 0 && !type.equals("modern vert")){
			modY = 0;
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
	
	public int getValue2(){
		return currentValue2;
	}

	public void setTestColor(){
		int[] par = new int[3];
		
		par[0] = (int)(255*(double)currentValue2/max);
		par[1] = (int)(255*(double)currentValue/max);
		par[2] = (int)(255*(double)(1 - (currentValue/max)));
		
		for(int i = 0; i < par.length; i++){
			if(i == 0 && Math.abs(currentValue2) > Math.abs(max)){
				par[i] = (int)(255*(double)max/currentValue2);
			} else if (i == 1 && Math.abs(currentValue) > Math.abs(max)){
				par[i] = (int)(255*(double)max/currentValue);
			} else if (i == 2 && Math.abs(currentValue) > Math.abs(max)){
				par[i] = (int)(255*(double)(1 - (max/currentValue)));
			}
			if(par[i] < 0){
				par[i] = -par[i];
			}
			//System.out.println(par[i]);
		}
		
		bg.setColor(new Color(par[0], par[1], par[2]));
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
		//if the inside drawing is selected and dragged, it will follow
		/*if(e.getX() < in.getX() + in.width && e.getX() > in.getX() && e.getY() < in.getY() + in.height && e.getY() > in.getY()){
			setInLocation(e.getX() - (int)(in.width/2), e.getY() - (int)(in.height/2));
		}*/
		if(type.equals("modern hor") || type.equals("modern vert")){
			setInLocation(e.getX() - (int)(in.width), e.getY() - (int)(in.height/2));
		} else {
			setInLocation(e.getX() - (int)(in.width/2), e.getY() - (int)(in.height/2));
		}
		if(type.equals("modern hor") || type.equals("modern vert")){
			if(max - (int)((max - min)*((double)(bg.getX() - in.getX())/bg.width)) + min > 0){
				currentValue = max - (int)((max - min)*((double)(bg.getX() - in.getX())/bg.width)) + min;
				currentValue2 = 0;
			} else {
				currentValue = 0;
				currentValue2 = 0;
			}
		} else {
			currentValue = (int)((max - min)*(in.getX()/(bg.width - in.width))) + min;
			currentValue2 = (int)((max - min)*(in.getY()/(bg.height - in.height))) + min;
		}
		//ssOutput.setText(getValue() + ", " + getValue2());
		if(type.equals("color picker")){
			setTestColor();
		}
		repaint();
		System.out.println(getValue() + "," + getValue2());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}	
}
