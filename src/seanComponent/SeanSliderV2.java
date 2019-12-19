package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class SeanSliderV2 extends JComponent implements MouseListener {
	int cursorX;
	SeanDrawables circle;
	SeanDrawables left,right;
	
	public SeanSliderV2(int x , int y, int w, int h) {
		super();
		this.setBounds(x, y, w, h);
		left = new SeanDrawables(0,0,getWidth()/2,getHeight());
		right = new SeanDrawables(getWidth()/2,0,getWidth()/2,getHeight());
		left.setColor(Color.white);
		right.setColor(Color.black);
		left.setOpacity(0.5f);
		right.setOpacity(0.5f);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		left.draw(g);
		right.draw(g);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
