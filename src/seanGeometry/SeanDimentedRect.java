package seanGeometry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class SeanDimentedRect extends Path2D.Float implements SeanShape {
	Boolean[] isRounded;
	int x,y,w,h,arc;
	
	public SeanDimentedRect(int x, int y, int w, int h, int arc, Boolean[] isRounded) {
		this.x = x;
		this.y =y;
		this.w = w;
		this.h = h;
		this.arc = arc;
		this.isRounded = isRounded;
		
		alignPath();
	}
	
	public void alignPath() {
		for(int i=0;i<isRounded.length;i++) {
			switch(i) {
				case 0: if(isRounded[0]) {
							moveTo(arc+x,y);
						} else {
							moveTo(x,y);
						}
						break;
				case 1: if(isRounded[1]) {
							lineTo(w-arc,y);
							curveTo( w, 0, w, 0, w, arc);
						} else {
							lineTo(w,y);
						}
						break;
				case 2: if(isRounded[2]) {
							lineTo(w,h-arc);
							curveTo(w,h,w,h,w-arc,h);
						} else {
							lineTo(w,h);
						}
						break;
				case 3: if(isRounded[3]) {
							lineTo(x+arc,h);
							curveTo(x,h,x,h,x,h-arc);
						} else {
							lineTo(-w,h);
						}
						break;
			}
		}
		
		if(isRounded[0]) {
			lineTo(x,y+arc);
			curveTo(x,y,x,y,x+arc,y);
		} else {
			lineTo(x,y);
		}
		closePath();
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fill(this);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y =y;
		this.w = width;
		this.h = height;
		this.reset();
		alignPath();
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y =y;
		this.reset();
		alignPath();
	}

	@Override
	public void setSize(int w, int h) {
		this.w = w;
		this.h = w;
		this.reset();
		alignPath();
	}
}
