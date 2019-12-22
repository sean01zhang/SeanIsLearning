package seanGeometry;

import java.awt.Graphics;

public class SeanDimentedRect extends SeanRoundedRect {
	Boolean topL,topR,botL,botR;
	
	public SeanDimentedRect(int x, int y, int w, int h, int al, 
			int aw, Boolean[] isRounded) {
		super(x,y,w,h,al,aw);
		
		topL = isRounded[0];
		topR = isRounded[1];
		botL = isRounded[2];
		botR = isRounded[3];
	}
	
	public void draw(Graphics g) {
		g.fillRoundRect(x,y,width,height,arcw,arch);
		
		if(botR) {
			g.fillRect(width-arcw, height-arch, arcw, arch);
		} if(botL) {
			g.fillRect(x, height-arch, arcw, arch);
		} if(topL) {
			g.fillRect(x, y, arcw, arch);
		} if (topR) {
			g.fillRect(width-arcw, y, arcw, arch);
		}
	}
}
