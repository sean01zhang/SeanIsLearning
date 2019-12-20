package seanMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import seanComponent.SeanButton;
import seanComponent.SeanTextArea;
import seanGeometry.SeanEllipse;

public class TitleBar extends SeanTextArea implements MouseListener,MouseMotionListener {
	Point mouseInitial;
	Display d;
	SeanButton exit;
	SeanButton mini,max;
	int toggleMax =0;
	
	public TitleBar(Display d) {
		super();
		this.setFocusable(true);
		this.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
		this.d = d;
		
		exit = new SeanButton(new SeanEllipse(0,0,getHeight()-4,getHeight()-4),"") {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			
			public void mousePressed(MouseEvent e){
				System.exit(0);
			}
		};
		
		mini = new SeanButton(new SeanEllipse(0,0,getHeight()-4,getHeight()-4),"") {
			@Override
			public void mouseClicked(MouseEvent e) {
				d.setState(JFrame.ICONIFIED);
			}
			
			public void mousePressed(MouseEvent e){
				d.setState(JFrame.ICONIFIED);
			}
			
		};
		
		
		
		max = new SeanButton(new SeanEllipse(0,0,getHeight()-4,getHeight()-4),"") {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(toggleMax ==0) {
					d.setExtendedState(JFrame.MAXIMIZED_BOTH);
					toggleMax =1;
				} else {
					d.setExtendedState(JFrame.NORMAL);
					toggleMax =0;
				}
				
			}
			
			public void mousePressed(MouseEvent e){
				if(toggleMax ==0) {
					d.setExtendedState(JFrame.MAXIMIZED_BOTH);
					toggleMax =1;
				} else {
					d.setExtendedState(JFrame.NORMAL);
					toggleMax =0;
				}
			}
			
		};
		
		exit.setBoundsModified(this.getWidth()-50, 2, getHeight()-4, getHeight()-4);
		mini.setBoundsModified(this.getWidth()-110, 2, getHeight()-4, getHeight()-4);
		max.setBoundsModified(this.getWidth()-80, 2, getHeight()-4, getHeight()-4);
		
		try {
			exit.setBackgroundImage(ImageIO.read(new File("src/images/exit.png")));
			mini.setBackgroundImage(ImageIO.read(new File("src/images/mini.png")));
			max.setBackgroundImage(ImageIO.read(new File("src/images/max.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.add(exit);
		this.add(mini);
		this.add(max);
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point newLocation = e.getLocationOnScreen();
		d.setLocation(newLocation.x-mouseInitial.x-this.getX(),newLocation.y-mouseInitial.y-this.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseInitial = e.getPoint();
		
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
	
	public void setBoundsModified(int x, int y, int width, int height) {
		this.setBounds(x,y,width,height);
		this.getBG().setBounds(0, 0, width, height);
		exit.setBoundsModified(getWidth()-28, 2, getHeight()-4, getHeight()-4);
		mini.setBoundsModified(this.getWidth()-58, 2, getHeight()-4, getHeight()-4);
		max.setBoundsModified(this.getWidth()-88, 2, getHeight()-4, getHeight()-4);
	}
	
}
