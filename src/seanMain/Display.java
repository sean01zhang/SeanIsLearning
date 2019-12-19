package seanMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;
import seanComponent.SeanButton;
import seanComponent.MapSlider;
import seanComponent.SeanTextArea;
import seanComponent.SeanBGPanel;
import seanEngine.SeanStoryInterpreter;
import seanGeometry.SeanRoundedRect;
import seanMisc.SeanDrawableForeground;

public class Display extends JFrame{
	ContentPanel p;
	

	public Display() {
		this.setLayout(null);
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));
		this.setLayout(new GridLayout(1,1));
		
		p = new ContentPanel();
		this.add(p);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


}
