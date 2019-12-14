package seanComponent;

import java.awt.Graphics;
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
}
