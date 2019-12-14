package seanComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;


public abstract class SeanButton extends JComponent implements MouseListener {
	SeanDrawables sbg;
	String displayText;
	Color textColor,hoverShade,clickShade;
	Boolean borders;
	Boolean roundCorners;
	Font f;
	final int PRESSED = 0;
	final int DEFAULT = 1;
	final int HOVER = 2;
	int pressStatus =1;
	int radius = 0;


	public SeanButton() {
		super();
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		this.setSize(200, 100);

		borders = false;
		roundCorners = true;
		displayText = "";
		//background
		sbg = new SeanDrawables(getX(),getY(),getWidth(),getHeight());

		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);
		textColor = Color.black;
		hoverShade = new Color(125,125,125,128);
		clickShade = new Color(75,75,75,128);
	}

	public SeanButton(String s) {
		super();
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		this.setSize(200, 100);

		borders = false;
		roundCorners = true;
		displayText = s;
		//background
		sbg = new SeanDrawables(getX(),getY(),getWidth(),getHeight());

		

		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);
		textColor = Color.black;
		hoverShade = new Color(125,125,125,128);
		clickShade = new Color(75,75,75,128);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		sbg.setBounds(0,0,getWidth(),getHeight());

		// antialias
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(roundCorners){
			sbg.draw(g,radius);
		} else {
			sbg.draw(g);
		}

		// draw foreground
		g.setColor(textColor);
		SeanUtil.drawString(displayText,g,0,getHeight()/2-g.getFontMetrics(f).getHeight()/2,
							f,getWidth()-20,getWidth()/2);

		// overlay with shades to get "hover" or "pressed" effect
		switch (pressStatus) {
			case 0:
				System.out.println("CLICKED");
				g.setColor(clickShade);
				if(roundCorners) {
					g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
				} else {
					g.fillRect(0,0 ,getWidth(), getHeight());
				}
				break;
			case 2:
				g.setColor(hoverShade);
				if(roundCorners) {
					g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
				} else {
					g.fillRect(0,0 ,getWidth(), getHeight());
				}
				break;
			default:
				break;
		}
	}

	// SETTERS AND GETTERS:  *********************************************
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

	public void setRoundCorners(Boolean b, int r) {
		this.borders = b;
		this.radius = r;
		repaint();
	}

	public void setBackgroundColor(Color c) {
		sbg.setColor(c);
		repaint();
	}

	public void setBackgroundImage(Image i) {
		sbg.setImage(i);
	}


	// MOUSELISTENER STUFFS  *****************************************************
	@Override
	public void mousePressed(MouseEvent e) {
		pressStatus = this.PRESSED;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressStatus = this.HOVER;
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
