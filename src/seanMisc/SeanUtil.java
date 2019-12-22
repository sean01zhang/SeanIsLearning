package seanMisc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.Font;

public class SeanUtil {
  public static String breakString(String s,Graphics g,int width) {
		String[] words = s.split(" ");
		String finalWordsSoFar = "";
		String wordsSoFar = "";

		for(String temp : words) {
			int widthpx = g.getFontMetrics().stringWidth(wordsSoFar + " " + temp);
			
			if(widthpx > width) {
				finalWordsSoFar += (wordsSoFar + "\n");
				wordsSoFar = temp;
			} else {
				if (wordsSoFar.isEmpty()) {
					wordsSoFar = temp;
				} else {
					wordsSoFar += (" "+ temp);
				}
			}
		}
		return finalWordsSoFar+wordsSoFar;
	}

	public static int drawString(String s,Graphics g, int x , int y, Font f,int width) {
    for (String line : s.split("\n")) {
    	for (String subline : SeanUtil.breakString(line, g,width).split("\n")) {
				g.drawString(subline, x, y += g.getFontMetrics(f).getAscent());
    	}
    }
    return y;
  }

  public static int drawString(String s, Graphics g, int x, int y, Font f, int width,int center) {
		for (String line : s.split("\n")) {
			for (String subline : SeanUtil.breakString(line, g,width).split("\n")) {
				int temp = center-g.getFontMetrics().stringWidth(subline)/2;
				g.drawString(subline, temp, y += g.getFontMetrics(f).getAscent());
			}
		}
		return y;
	}
  
  public static BufferedImage getBlurredImage(BufferedImage bi, int radius) {

      //this is for calculating the blur radius
      int size = radius * 2 + 1;
      float weight = 1.0f / (size * size);
      float[] data = new float[size * size];
      for (int i = 0; i < data.length; i++) {
          data[i] = weight;
      }

      //creates an image kernel
      Kernel kernel = new Kernel(size, size, data);
      
      //blurs the image, leaving the edges of the blur non-blurred. This will be cropped out using
      //getSubImage.
      ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
      return op.filter(bi, null);
      //return op.filter(bi, null).getSubimage(radius, radius, bi.getWidth()-(2*radius), bi.getHeight()-(2*radius));
  }
  
  
  
  
  
}
