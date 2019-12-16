package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class SeanSlider extends JComponent implements MouseListener, MouseMotionListener{
	
	Point compCoords = new Point();
	
	SeanDrawables bg;
	SeanDrawables in;
	SeanTextArea ssOutput;
	String type; //circular, straight, wavy, 2D (ex. a map), etc.
	int max;
	int min;
	int currentValue;
	int currentValue2;
	
	public SeanSlider(SeanDrawables bg, SeanDrawables in, int max, int min, String type){
		compCoords = null;
		this.bg = bg;
		this.in = in;
		this.type = type;
		this.max = max;
		this.min = min;
		currentValue = (int)((max - min)*(in.getX()/(bg.width - in.width))) + min;
		currentValue2 = (int)((max - min)*(in.getY()/(bg.height - in.height))) + min;
		
		setTestColor();
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setOpaque(false);
		this.setSize(1000, 1000);
		this.setLocation(20, 20);
		
		if(type.equals("horizontal")){
			bg.height = in.height;
			bg.setBounds((int)bg.getX(), (int)bg.getY(), bg.width, bg.height);
			in.setBounds(0, 0, in.width, in.height);
		} else if (type.equals("vertical")){
			bg.width = in.width;
			bg.setBounds((int)bg.getX(), (int)bg.getY(), bg.width, bg.height);
			in.setBounds(0, 0, in.width, in.height);
		}
		
		ssOutput = new SeanTextArea();
		ssOutput.setText(getValue() + "," + getValue2());
		ssOutput.setBounds(250, 100, 200, 100);
		ssOutput.setRadius(30);
		ssOutput.setScrollType(SeanTextArea.SCROLL_CHAR);
		ssOutput.setSpeed(50);
		add(ssOutput);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// turn on anti-alias mode
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		bg.draw(g);
		in.draw(g);
		//System.out.println(in.getX() + ", " + in.getY());
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
		} else if (x <= 0){
			modX = 0;
		} else {
			modX = x;
		}
		if(y + in.width >= bg.height){
			modY = bg.height - in.height;
		} else if (y <= 0){
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
			System.out.println(par[i]);
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
		setInLocation(e.getX() - (int)(in.width/2), e.getY() - (int)(in.height/2));
		currentValue = (int)((max - min)*(in.getX()/(bg.width - in.width))) + min;
		currentValue2 = (int)((max - min)*(in.getY()/(bg.height - in.height))) + min;
		ssOutput.setText(getValue() + ", " + getValue2());
		setTestColor();
		//repaint((int)bg.getX(), (int)bg.getY(), (int)bg.getWidth(), (int)bg.getHeight());
		repaint(50, 50, 1, 1);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}	
}
