package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class SeanBackground {
	Color c;
	Image img;

	public SeanBackground() {
		c= Color.white;
	}

	public SeanBackground( Color c) {
		this.c = c;
	}

	public void setImage(Image i,int width,int height) {
		img = i.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public void draw(Graphics g,int width, int height, int rad) {
		if (null == img) {
			g.setColor(c);
			g.fillRoundRect(0, 0, width, height,rad,rad);
		} else {
			g.drawImage(img, 0, 0, null);
		}
	}

	public void draw(Graphics g,int width, int height) {
		if (null == img) {
			g.setColor(c);
			g.fillRect(0, 0, width, height);
		} else {
			g.drawImage(img, 0, 0, null);
		}
	}
}
