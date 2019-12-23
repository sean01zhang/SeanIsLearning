package seanMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import seanComponent.SeanButton;
import seanComponent.SeanOptionChooser;
import seanComponent.MapSlider;
import seanComponent.SeanTextArea;
import seanComponent.SeanBGPanel;
import seanEngine.SeanCausalityObj;
import seanEngine.SeanStoryInterpreter;
import seanGeometry.SeanDimentedRect;
import seanGeometry.SeanRoundedRect;
import seanMisc.SeanDrawableForeground;

public class Display extends JFrame{
	SeanTextArea sta;
	SeanStoryInterpreter sip;
	SeanBGPanel sbgpanel;
	SeanButton sb;
	File f;
	Cursor c;
	
	public void invokeResize() {
		//sta.setBoundsModified(10,getHeight()-135,780,125);
		sta.setBoundsModified((this.getContentPane().getWidth()-700)/2,this.getContentPane().getHeight()-135,700,125);
		sbgpanel.resizePanel(this.getContentPane().getWidth(),this.getContentPane().getHeight());
		//tb.setBoundsModified(10,10,getWidth()-20,30);
	}
	
	public Display() {
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));
		this.setLayout(null);
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        invokeResize();
		    }
		});
		
		/* CURSOR
		try {
			c = this.getToolkit().createCustomCursor(ImageIO.read(new File("src/images/cursor.png")) , new Point(this.getX(), 
			           this.getY()), "img");
		} catch (Exception e) {}
		this.setCursor(c);
		
		*/
		initComponents();
		
				
		/* ANIMATIONS
		sbgpanel.getAnime().rotShake(sbgpanel.getBG(),0.07, 0.07, sbgpanel.getWidth()/2, sbgpanel.getHeight()/2, 200, 5);
	    sbgpanel.getAnime().horShake(10, 7, 50, 7,2);
		sbgpanel.getAnime().vertShake( 7, 5, 50, 7);
		sbgpanel.getAnime().fade(sbgpanel.getBG(), 0.1f, sbgpanel.getBG().getOpacity(), 400); 
		*/				
		
		this.pack();
		
		System.out.println(this.getContentPane().getWidth());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initComponents() {
		//SeanTextArea
		Boolean[] thing = {false, false, true, true};
		sta = new SeanTextArea(new SeanDimentedRect(10,getHeight()-135,780,125,40,thing));
		sta.setScrollType(SeanTextArea.SCROLL_CHAR);
		sta.setSpeed(50);
		sta.setBackgroundColor(new Color(255,255,255,190));
		/* Image setting in the text area
		try {
			sta.setBackgroundImage(ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
		add(sta);
		
		//SeanButton
		sb = new SeanButton(new SeanDimentedRect(10,10,100,50,20,thing),"Click Me") {
			@Override
			public void mouseClicked(MouseEvent e) {
				sta.setText("Hello");
			}
		};
		add(sb);
		
		// SeanBGPanel
		try {
			sbgpanel = new SeanBGPanel(0,0,getWidth(),getHeight(),ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		add(sbgpanel);
		
	}


}
