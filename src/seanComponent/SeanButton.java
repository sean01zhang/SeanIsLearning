package seanComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import javax.swing.JComponent;

import seanEngine.SeanCausalityObj;
import seanGeometry.SeanRoundedRect;
import seanGeometry.SeanShape;
import seanMisc.Animations;
import seanMisc.SeanDrawables;
import seanMisc.SeanUtil;


public class SeanButton extends SeanComponent implements MouseListener {
	SeanDrawables sbg,soly;
	String displayText;
	Color textColor,hoverShade,clickShade;
	Font f;
	protected final int PRESSED = 0;
	final int DEFAULT = 1;
	final int HOVER = 2;
	protected int pressStatus =1;
	SeanCausalityObj sco;
	Animations anime;
	
	public SeanButton(SeanShape ss, String s) {
		super();
		this.setOpaque(false);
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		// set location based on location of shape
		this.setBounds(ss.getBounds());
		
		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);
		textColor = Color.black;
		hoverShade = new Color(125,125,125,128);
		clickShade = new Color(75,75,75,128);
		displayText = s;
		
		//configure background
		ss.setLocation(0, 0);
		sbg = new SeanDrawables(ss);
		//overlay
		soly = new SeanDrawables(ss,0f,hoverShade);
		
		anime = new Animations(this);
	}
	
	public SeanButton(SeanShape ss, SeanCausalityObj sco) {
		super();
		this.setOpaque(false);
		this.enableInputMethods(true);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		// set location based on location of shape
		this.setBounds(ss.getBounds());

		// Default Font
		f = new Font("Arial", Font.PLAIN, 20);
		textColor = Color.black;
		hoverShade = new Color(125,125,125,128);
		clickShade = new Color(75,75,75,128);
		displayText = sco.getCause();
		this.sco = sco;
		
		//configure background
		ss.setLocation(0, 0);
		sbg = new SeanDrawables(ss);
		//overlay
		soly = new SeanDrawables(ss,0f,hoverShade);
		
		anime = new Animations(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// antialias
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		sbg.draw(g);

		// draw foreground
		g.setColor(textColor);
		SeanUtil.drawString(displayText,g,0,getHeight()/2-g.getFontMetrics(f).getHeight()/2,
							f,getWidth()-20,getWidth()/2);

		// overlay with shades to get "hover" or "pressed" effect
		switch (pressStatus) {
			case 0:
				soly.setColor(clickShade);
				soly.setOpacity(1f);
				break;
			case 2:
				soly.setColor(hoverShade);
				soly.setOpacity(1f);
				break;
			default:
				soly.setOpacity(0f);
				break;
		}
		
		soly.draw(g);
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
	
	public Color getTextColor() {
		return textColor;
	}

	public void setText(String s) {
		this.displayText = s;
		repaint();
	}

	public void setBackgroundColor(Color c) {
		sbg.setColor(c);
		repaint();
	}

	public void setBackgroundImage(Image i) {
		sbg.setImage(i);
	}
	
	public Animations getAnime() {
		return anime;
	}
	
	public void setBoundsModified(int x, int y, int width, int height) {
		this.setBounds(x,y,width,height);
		sbg.setBounds(0, 0, width, height);
		soly.setBounds(0, 0, width, height);
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
	public void mouseClicked(MouseEvent e) {
		if(sco != null){
			sco.enactEffect();
		} else {
			System.out.println("Sean Causality Object Isn't Initialized!");
		}
	}
}
