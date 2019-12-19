package seanMisc;

import java.awt.Color;
import java.awt.Rectangle;

public abstract class SeanDrawableForeground extends SeanDrawables {

	public SeanDrawableForeground(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public SeanDrawableForeground(int x,int y,int width,int height,float opacity) {
		super(x,y,width,height,opacity);
	}
	
	public abstract Rectangle getPreferredBounds(int x,int y, int width,int height);
}
