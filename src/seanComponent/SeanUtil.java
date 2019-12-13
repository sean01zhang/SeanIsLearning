package seanComponent;

import java.awt.Graphics;
import java.awt.Font;

public class SeanUtil {
  public static String breakString(String s,Graphics g,int boundw) {
		String[] words = s.split(" ");
		String finalWordsSoFar = "";
		String wordsSoFar = "";

		for(String temp : words) {
			if(wordsSoFar.isEmpty()) {
				wordsSoFar = temp;
			} else {
				wordsSoFar = wordsSoFar + " " + temp;
			}
			int width = g.getFontMetrics().stringWidth(wordsSoFar);

			if(width>= boundw) {
				finalWordsSoFar += (wordsSoFar + "\n");
				wordsSoFar = temp;
			}
		}
		return finalWordsSoFar+wordsSoFar;
	}

	public static int drawString(String s,Graphics g, int x , int y, Font f,int boundw) {
    for (String line : s.split("\n")) {
    	for (String subline : SeanUtil.breakString(line, g,boundw).split("\n")) {
				g.drawString(subline, x, y += g.getFontMetrics(f).getAscent());
    	}
    }
    return y;
  }

  public static int drawString(String s, Graphics g, int x, int y, Font f, int boundw,int center) {
		for (String line : s.split("\n")) {
			for (String subline : breakString(line, g,boundw).split("\n")) {
				int temp = center-g.getFontMetrics().stringWidth(subline)/2;
				g.drawString(subline, temp, y += g.getFontMetrics(f).getAscent());
			}
		}
		return y;
	}
}
