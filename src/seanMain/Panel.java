package seanMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel {
	// This is a constructor
	public Panel() {

		
	}

	Boolean firstTime = true;
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());

	}

}
