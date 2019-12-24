package seanComponent;

import java.awt.Color;

import javax.swing.JComponent;

public abstract class SeanComponent extends JComponent {
	double scale = 1;
	
	public abstract void setTextColor(Color c);
	
	public abstract Color getTextColor();
	
	public abstract void setBoundsModified(int x, int y, int width, int height);
	
	/* Sketchy... Needs work
	public void scaleSComponent(double newScale, int x, int y) {
		this.setBoundsModified(x,y,(int)(getWidth()*newScale/scale),
				(int)(getHeight()*newScale/scale));
		scale = newScale;
	}
	*/
}
