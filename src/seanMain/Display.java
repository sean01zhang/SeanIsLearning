package seanMain;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import seanComponent.SeanButton;
import seanComponent.SeanButtonArray;
import seanComponent.SeanTextArea;
import seanComponent.SeanBGPanel;
import seanEngine.KeyboardInput;
import seanEngine.SeanCausalityObj;
import seanEngine.SeanStoryInterpreter;
import seanGeometry.SeanRoundedRect;

public class Display extends JFrame{
	SeanTextArea sta;
	SeanBGPanel sbgpanel;
	SeanButtonArray sba;
	SeanButton sb;
	Cursor c;
	
	public void invokeResize() {
		//sta.setBoundsModified(10,getHeight()-135,780,125);
		sta.setBoundsModified((this.getContentPane().getWidth()-700)/2,this.getContentPane().getHeight()-sta.getHeight()-10,sta.getWidth(),sta.getHeight());
		sbgpanel.resizePanel(this.getContentPane().getWidth(),this.getContentPane().getHeight());
		//tb.setBoundsModified(10,10,getWidth()-20,30);
	}
	
	public Display() {
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));
		this.setLayout(null);
		this.addComponentListener(new ComponentAdapter() {
		    @Override
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

		this.setFocusable(true);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		// DO ANIMATIONS HERE
		//sta.getAnime().horShake(5, 5, 500, 5);
		
		/*
		sbgpanel.getAnime().rotShake(sbgpanel.getBG(),0.07, 0.07, sbgpanel.getWidth()/2, sbgpanel.getHeight()/2, 200, 5);
	    sbgpanel.getAnime().horShake(10, 7, 50, 7,2);
		sbgpanel.getAnime().vertShake( 7, 5, 50, 7);
		sbgpanel.getAnime().fadeOut(sbgpanel.getBG(), 0.1f, sbgpanel.getBG().getOpacity(), 400); 
		*/
	}
	
	public void initComponents() {
		//SeanButton
				sb = new SeanButton(new SeanRoundedRect(10,10,110,50,20,20),"Click Me");
				add(sb);
		
		//SeanTextArea
		sta = new SeanTextArea(new SeanRoundedRect(10,getHeight()-135,700,125,40,40));
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
		
		
		
		SeanButton sb2 = new SeanButton(new SeanRoundedRect(120,10,100,50,20,20),"Delet") {
			@Override
			public void mouseClicked(MouseEvent e) {
				sta.setSComp(null);
				invokeResize();
			}
		};
		add(sb2);
		
		// SeanBGPanel
		try {
			sbgpanel = new SeanBGPanel(0,0,getWidth(),getHeight(),ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		add(sbgpanel);
		
	}

	public SeanTextArea getSta() {
		return sta;
	}

	public void setSta(SeanTextArea sta) {
		this.sta = sta;
	}

	public SeanBGPanel getSbgpanel() {
		return sbgpanel;
	}

	public void setSbgpanel(SeanBGPanel sbgpanel) {
		this.sbgpanel = sbgpanel;
	}

	
}
