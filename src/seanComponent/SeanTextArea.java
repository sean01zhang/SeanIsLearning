package seanComponent;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.Timer;

public class SeanTextArea extends JComponent implements MouseWheelListener {

	String outputText;
	Font f;

	int boundx;
	int boundy;
	int boundw;
	int boundh;
	
	int x, y;
	int endofstring;
	
	String scrollType;
	public final static String SCROLL_NONE = "None";
	public final static String SCROLL_WORD = "Word";
	public final static String SCROLL_CHAR = "Char";
	
	SeanBackground sb;
	
	Queue<String> queueString;
	
	Timer t;

	public SeanTextArea() {
		super();

		this.enableInputMethods(true);
		this.addMouseWheelListener(this);
		
		
		outputText = "";
		
		

		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);

		// Default Text Bounding
		boundx = 10;
		boundy = 10;
		boundw = this.getWidth() - 20;
		boundh = this.getHeight() - 20;

		this.x= boundx;
		this.y=boundy;
		
		// Default Scrolling Type
		scrollType = SCROLL_NONE;
		
		queueString = new LinkedList();

		
		
		this.setVisible(true);
		
		this.setText("");
		
		sb = new SeanBackground();
		
		
		t = new Timer(70, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	scrollText();
	        	repaint();
	        }
	    });
		
		t.start();
		
		
		
	}
	
	
	public void setSpeed(int delay) {
		t.setDelay(delay);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// background
		//g.setColor(Color.WHITE);
		//g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		
		sb.draw(g,getWidth(),getHeight(), 20);
		
		

		// turn on anti-alias mode
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.black);
		//g.setColor(new Color(43,237,142,255));
		g.setFont(f);

		endofstring = drawString(outputText,g,boundx,y,f);
		
		
	}

	String outputText1 = "";
	

	public String getScrollType() {
		if (this.scrollType.equals("Word")) {
			return " ";
		} else if (this.scrollType.equals("Char")) {
			return "";
		} else {
			return "None";
		}
	}
	
	public void setScrollType(String s) {
		this.scrollType = s;
	}
	
	public void setText(String s) {
		outputText1 = s;
		outputText = "";
		
		if (this.scrollType.equals("None")) {
			outputText = s;
		} else {
			queueString = new LinkedList<>(Arrays.asList(s.split(this.getScrollType())));
		}
		
		repaint();
	}
	
	
	public void scrollText() {
		if(queueString.isEmpty()) {
			
		} else {
			if (this.getScrollType().equals(" ")) {
				outputText += (queueString.poll() + " ");
			} else {
				outputText += queueString.poll();
			}
			
			this.y = getHeight()-endofstring+y-boundy;
		}
	}
	
	
	// current useless self
	public String breakString(String s,Graphics g) {
		
		String[] words = s.split(" ");
		String finalWordsSoFar = "";
		String wordsSoFar = "";
		
		for(String sean : words) {
			String temp = "";
			
			if(wordsSoFar.length()==0) {
				temp = sean;
			} else {
				temp = wordsSoFar + " " + sean;
			}
			
			int width = g.getFontMetrics().stringWidth(temp);
			
			if(width>= (getWidth()-20)) {
				finalWordsSoFar += (wordsSoFar + "\n");
				wordsSoFar = sean;
			} else {
				wordsSoFar = temp;
			}
		}
		
		finalWordsSoFar += wordsSoFar;
		
		return finalWordsSoFar;
	}
	
	// why is my past self so smart
	public int drawString(String s,Graphics g, int x , int y, Font f) {
        for (String line : s.split("\n")) {
        	for (String subline : breakString(line, g).split("\n")) {
        		g.drawString(subline, x, y += g.getFontMetrics(f).getAscent());
        	}
        }
        return y;
    }

	public String getText() {
		return this.outputText1;
	}

	public void setFont(Font f) {
		this.f = f;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Double omega = e.getPreciseWheelRotation();
		
		if(omega >= 0) {
			y= (int) Math.min(y+omega,boundx );
		} 
		
		else {
			//y+=omega;
			y= (int) Math.max(y+omega,getHeight()-endofstring+y-boundy);
		}
		
		repaint();
		
		
	}

	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}


}
