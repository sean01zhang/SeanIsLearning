package seanComponent;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.Timer;

public class SeanTextArea extends JComponent implements MouseWheelListener {
	// string related
	String outputText;
	String text;
	Font f;
	// bounding the position of the string
	int strx,stry,boundx,boundy,boundw,boundh;
	int endofstring;
	// for scrolling
	String scrollType;
	public final static String SCROLL_NONE = "None";
	public final static String SCROLL_WORD = "Word";
	public final static String SCROLL_CHAR = "Char";

	Queue<String> queueString;
	Timer t;

	// Background
	SeanDrawables sbg;

	// CONSTRUCTORS *****************************************
	public SeanTextArea() {
		super();
		this.enableInputMethods(true);
		this.addMouseWheelListener(this);
		// Default Scrolling Type
		scrollType = SCROLL_NONE;
		queueString = new LinkedList<>();
		// configure background
		sbg = new SeanDrawables(getX(),getY(),getWidth(),getHeight());

		t = new Timer(70, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	scrollText();
	        	repaint();
	        }
	    });
		t.start();

		// Default Font Metrics
		f = new Font("Arial", Font.PLAIN, 20);
		boundx = 10;
		boundy = 10;
		strx= boundx;
		stry= boundy;
		text = "";
		this.setText("");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// turn on anti-alias mode
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		// paint background
		sbg.setBounds(0, 0, getWidth(), getHeight());
		sbg.draw(g,20);
		// paint string
		g.setColor(Color.black);
		g.setFont(f);

		System.out.println("What the hec " +outputText);
		endofstring = SeanUtil.drawString(outputText,g,boundx,stry,f,getWidth()-20);
	}

	// SETTERS AND GETTERS ********************************************
	public void setSpeed(int delay) {
		t.setDelay(delay);
	}

	public void setFont(Font f) {
		this.f = f;
	}

	public void setScrollType(String s) {
		this.scrollType = s;
	}

	public String getScrollType() {
		if (this.scrollType.equals("Word")) {
			return " ";
		} else if (this.scrollType.equals("Char")) {
			return "";
		} else {
			return "None";
		}
	}

	public void setText(String s) {
	 	text = s;
		outputText = "";
		
		// sets output text depending on what scrolltype is set
		if (getScrollType().equals("None")) {
			outputText = s;
		} else {
			queueString = new LinkedList<>(Arrays.asList(s.split(getScrollType())));
			System.out.println(Arrays.toString(queueString.toArray()));
		}

		t.start();
		repaint();
	}

	public String getText() {
		return text;
	}

// SCROLLING FOR THE TEXT BOX ******************************************
	public void scrollText() {
		if(queueString.isEmpty()) {
			t.stop();
		} else {
			// adds to output text depending on scrolltype
			outputText += queueString.poll();
			System.out.println(outputText);
			
			if (getScrollType().equals(" ")) {
				outputText += getScrollType();
			}
			
			if(endofstring<getHeight()) {
				stry=10;
			} else {
				stry = getHeight()-endofstring+stry-boundy;
			}
		}
	}

// MOUSEWHEEL STUFF ***********************************************
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Double omega = e.getPreciseWheelRotation();
		// move the text position
		if(omega >= 0) {
			stry= (int) Math.min(stry+omega,boundx );
		} else {
			stry= (int) Math.max(stry+omega,getHeight()-endofstring+stry-boundy);
		}
		repaint();
	}
}
