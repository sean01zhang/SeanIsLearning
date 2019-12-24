package seanComponent;

import java.awt.Color;

import javax.swing.JComponent;

public abstract class SeanComponent extends JComponent {
	public abstract void setTextColor(Color c);
	
	public abstract Color getTextColor();
	
	public abstract void setBoundsModified(int x, int y, int width, int height);
}
