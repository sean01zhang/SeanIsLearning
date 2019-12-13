package seanComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;


public abstract class SeanButton extends JComponent implements MouseListener {
	SeanDrawable sbg;
	String displayText;
	Color textColor,hoverShade,clickShade;
	boolean borders;
	Font f;
	final int PRESSED = 0;
	final int DEFAULT = 1;
	final int HOVER = 2;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int pressStatus =1;
	int radius = 0;

	public SeanButton() {
		super();
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		// default location
		this.setSize(400, 300);
		this.setLocation(((int)screenSize.getWidth() - 200)/2, ((int)screenSize.getHeight())/2);

		borders = false;
		displayText = "";
		//background
		sbg = new SeanDrawable(getX(),getY(),getWidth(),getHeight());

		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);
		textColor = Color.black;
		hoverShade = new Color(125,125,125,128);
		clickShade = new Color(125,125,125,128);
	}

	public SeanButton(String s) {
		super();
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		// default location
		this.setSize(400, 300);
		this.setLocation(((int)screenSize.getWidth() - 200)/2, ((int)screenSize.getHeight())/2);

		borders = false;
		displayText = s;
		//background
		sbg = new SeanDrawable(getX(),getY(),getWidth(),getHeight());

		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);
		textColor = Color.black;
		hoverShade = new Color(125,125,125,128);
		clickShade = new Color(125,125,125,128);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// antialias
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(borders){
			sbg.draw(g,radius);
		} else {
			sbg.draw(g);
		}

		// draw foreground
		g.setColor(textColor);
		drawString(displayText,g,0,getHeight()/2-g.getFontMetrics(f).getHeight()/2,f,getWidth()/2);


		// overlay with shades to get "hover" or "pressed" effect
		switch (pressStatus) {
			case 0:
				g.setColor(clickShade);
				g.fillRect(0,0 ,getWidth(), getHeight());
				break;
			case 2:
				g.setColor(hoverShade);
				g.fillRect(0,0,getWidth(), getHeight());
				break;
			default:
				break;
		}

	}

	// STRING MANIPULATION *****************************************
	// current useless self
	public String breakString(String s, Graphics g) {

		String[] words = s.split(" ");
		String finalWordsSoFar = "";
		String wordsSoFar = "";

		for (String sean : words) {
			String temp = "";

			if (wordsSoFar.length() == 0) {
				temp = sean;
			} else {
				temp = wordsSoFar + " " + sean;
			}

			int width = g.getFontMetrics().stringWidth(temp);

			if (width >= (getWidth() - 20)) {
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
	public int drawString(String s, Graphics g, int x, int y, Font f, int center) {
		for (String line : s.split("\n")) {
			for (String subline : breakString(line, g).split("\n")) {
				int temp = center-g.getFontMetrics().stringWidth(subline)/2;
				g.drawString(subline, temp, y += g.getFontMetrics(f).getAscent());
			}
		}
		return y;
	}

	public int drawString(String s, Graphics g, int x, int y, Font f) {
		for (String line : s.split("\n")) {
			for (String subline : breakString(line, g).split("\n")) {
				g.drawString(subline, x, y += g.getFontMetrics(f).getAscent());
			}
		}
		return y;
	}





	// SETTERS AND GETTERS:
	// *********************************************
	public void setFont(Font f) {
		this.f = f;
		repaint();
	}

	public void setTextColor(Color c) {
		this.textColor = c;
		repaint();
	}

	public void setText(String s) {
		this.displayText = s;
		repaint();
	}

	public void setBorders(Boolean b, int r) {
		this.borders = b;
		this.radius = r;
		repaint();
	}

	public void setBackgroundColor(Color c) {
		sbg.setColor(c);
		repaint();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x,y,width,height);
		sbg.setBounds(x,y,width,height);
	}

	public void setLocation(int x,int y) {
		super.setLocation(x,y);
		sbg.setLocation(x,y);
	}

	// *********************************************

	// MOUSELISTENER STUFFS
	// *****************************************************
	@Override
	public void mousePressed(MouseEvent e) {
		pressStatus = this.PRESSED;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressStatus = this.DEFAULT;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		pressStatus = this.HOVER;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		pressStatus = this.DEFAULT;
		repaint();
	}

	@Override
	public abstract void mouseClicked(MouseEvent e);
}
