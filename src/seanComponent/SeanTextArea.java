package seanComponent;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

import seanEngine.SeanCausalityObj;
import seanGeometry.SeanRoundedRect;
import seanGeometry.SeanShape;
import seanMisc.Animations;
import seanMisc.SeanDrawables;
import seanMisc.SeanUtil;

public class SeanTextArea extends SeanComponent implements MouseWheelListener {
	// string related
	String outputText;
	String text;
	Font f;
	// bounding the position of the string
	int strx,stry,boundx,boundy,boundw,boundh;
	int endofstring;
	Color textColor;
	// for scrolling
	String scrollType;
	public final static String SCROLL_NONE = "None";
	public final static String SCROLL_WORD = "Word";
	public final static String SCROLL_CHAR = "Char";

	Queue<String> queueString;
	Timer t;

	int radius;

	// Background
	private SeanDrawables sbg;
	
	// Animations
	Animations anime;
	
	SeanComponent sc;
	

	// CONSTRUCTORS *****************************************
	
	/**
	 * Takes in a SeanShape. This automatically makes the bounds of the textarea the 
	 * bounds of the SeanShape.
	 * @param ss
	 */
	public SeanTextArea(SeanShape ss) {
		super();
		this.enableInputMethods(true);
		this.addMouseWheelListener(this);
		this.setOpaque(false);
		
		// bounds getting
		this.setBounds(ss.getBounds());
		
		// configure background
		ss.setLocation(0, 0);
		setBG(new SeanDrawables(ss));
		
		// Default Scrolling Type
		scrollType = SCROLL_NONE;
		queueString = new LinkedList<>();
		
		// Timer Stuffs
		t = new Timer(70, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	scrollText();
	        	repaint();
	        }
	    });
		t.start();

		// Default Font Metrics
		f = new Font("Arial", Font.PLAIN, 20);
		boundx = 15;
		boundy = 15;
		strx= boundx;
		stry= boundy;
		textColor = Color.black;
		text = "";
		this.setText("");
		anime = new Animations(this);
	}

	public SeanTextArea(int x, int y, int width,int height) {
		super();
		this.enableInputMethods(true);
		this.addMouseWheelListener(this);
		this.setOpaque(false);
		this.setBounds(x,y,width,height);
		// Default Scrolling Type
		scrollType = SCROLL_NONE;
		queueString = new LinkedList<>();
		// configure background
		setBG(new SeanDrawables(new SeanRoundedRect(0,0,getWidth(),getHeight(),0,0)));
		
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
		textColor = Color.black;
		text = "";
		this.setText("");
		
		anime = new Animations(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// turn on anti-alias mode
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		// paint background
		getBG().draw(g);
		// paint string
		g.setColor(textColor);
		g.setFont(f);
		
		endofstring = SeanUtil.drawString(outputText,g,boundx,stry,f,getWidth()-20);
		
		if(sc != null) {
			if(outputText.equals("")) {
				sc.setLocation(sc.getLocation().x, stry);
			} else {
				sc.setLocation(sc.getLocation().x, endofstring+boundy);
			}
		}
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

	public void setBackgroundImage(Image i) {
		getBG().setImage(i);
	}

	public void setBackgroundColor(Color c) {
		getBG().setColor(c);
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
		}

		t.start();
		repaint();
	}

	public String getText() {
		return text;
	}
	
	public Animations getAnime() {
		return anime;
	}
	
	public void setBoundsModified(int x, int y, int width, int height) {
		this.setBounds(x,y,width,height);
		getBG().setBounds(0, 0, width, height);
		repaint();
	}
	

// SCROLLING FOR THE TEXT BOX ******************************************
	public void scrollText() {
		if(queueString.isEmpty()) {
			t.stop();
		} else {
			// adds to output text depending on scrolltype
			outputText += queueString.poll();

			if (getScrollType().equals(" ")) {
				outputText += getScrollType();
			}

			if(endofstring+boundy<getHeight()) {
				stry=10;
			} else {
				stry = getHeight()-endofstring+stry-boundy;
			}
		}
	}

// MOUSEWHEEL STUFF ***********************************************
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(endofstring+boundy<getHeight() && sc == null) {
			stry=10;
		} else if(sc == null) {
			Double omega = e.getPreciseWheelRotation()*6;
			
			// move the text position
			if(omega >= 0) {
				stry= (int) Math.min(stry+omega,boundy);
			} else {
				stry= (int) Math.max(stry+omega,getHeight()-endofstring+stry-boundy);
			}
			repaint();
		} else if(endofstring+2*boundy+sc.getHeight()<getHeight()) {
			stry=10;
		} else {
			Double omega = e.getPreciseWheelRotation()*6;
			// move the text position
			if(omega >= 0) {
				stry= (int) Math.min(stry+omega,boundy );
				repaint();
			} else {
				if(outputText.equals("")) {
					stry= (int) Math.max(stry+omega,getHeight()-boundy-sc.getHeight());
				} else {
					stry= (int) Math.max(stry+omega,getHeight()-endofstring-boundy-sc.getHeight()+stry-boundy);
				}
				
				repaint();
			}
		}
	}

	public SeanDrawables getBG() {
		return sbg;
	}

	public void setBG(SeanDrawables sbg) {
		this.sbg = sbg;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public SeanComponent getSComp() {
		return sc;
	}
	
	int sd = 0;
	public void setSComp(SeanComponent sc) {
		if(sd==0) {
			if(sc !=null) {
				this.sc = sc;
				add(this.sc);
				sd++;
			}
		} else {
			if(sc == null) {
				remove(this.sc);
				this.sc = null;
			} else {
				if(this.sc== null) {
					this.sc = sc;
					add(this.sc);
				} else {
					remove(this.sc);
					this.sc = sc;
					add(this.sc);
				}
			}
		}
		
		repaint();
	}

	public int getBoundx() {
		return boundx;
	}

	public void setBoundx(int boundx) {
		this.boundx = boundx;
	}

	public int getBoundy() {
		return boundy;
	}

	public void setBoundy(int boundy) {
		this.boundy = boundy;
	}
	
	
}
