package seanGeometry;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SeanDimentedRect extends Path2D.Float {
	Boolean topL,topR,botL,botR;
	
	public SeanDimentedRect(int x, int y, int w, int h, int arc, Boolean[] isRounded) {
		topL = isRounded[0];
		topR = isRounded[1];
		botL = isRounded[2];
		botR = isRounded[3];
		
		moveTo(x,y);
		for(int i=0;i<isRounded.length;i++) {
			switch(i) {
				case 0: if(isRounded[0]) {
							moveTo(arc+x,y);
						} else {
							moveTo(x,y);
						}
						break;
				case 1: if(isRounded[1]) {
							if(isRounded[0]) {
								lineTo(w-2*arc,y);
							} else {
								lineTo(w-arc,y);
							}
							
							curveTo( w, 0, w, 0, w, arc);
						} else {
							if(isRounded[0]) {
								lineTo(w-arc,y);
							} else {
								lineTo(w,y);
							}
						}
				case 2: if(isRounded[2]) {
							if(isRounded[1]) {
								lineTo(w,h-2*arc);
							} else {
								lineTo(w,h-arc);
							}
							curveTo(w,h,w,h,w-arc,h);
						} else {
							
						}
			}
		}
	}
	
	public void draw(Graphics g) {
		
	}
}
