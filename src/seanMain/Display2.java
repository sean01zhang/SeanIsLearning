package seanMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

import seanComponent.SeanButton;
import seanComponent.MapSlider;
import seanComponent.SeanTextArea;
import seanComponent.SeanBGPanel;
import seanEngine.SeanStoryInterpreter;
import seanGeometry.SeanRoundedRect;
import seanMisc.Animations;
import seanMisc.SeanDrawables;
import seanPanels.SeanMainMenu;

@SuppressWarnings("serial")
public class Display2 extends JFrame{
	SeanStoryInterpreter sip;
	File f;
	JTextArea jta;
	SeanTextArea sta;
	Timer t;
	SeanBGPanel sbgpanel;
	SeanButton sb;
	MapSlider ss;
	SeanMainMenu smm;
	
	public void invokeResize() {
		
	}

	public Display2() {
		this.setLayout(null);
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));
		
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        invokeResize();
		    }
		});
		
		smm = new SeanMainMenu(800, 600);
		add(smm);
		smm.setVisible(true);
		
		try {
			sbgpanel = new SeanBGPanel(0, 0, getWidth(), getHeight(), ImageIO.read(new File("src/images/anime-background.jpeg")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		smm.add(sbgpanel);
		/*
		//Color picker settings
		SeanDrawables in = new SeanDrawables(new SeanRoundedRect(10, 10, 20, 20, 30, 30));
		SeanDrawables bg = new SeanDrawables(new SeanRoundedRect(0, 0, 300, 300, 30, 30));
		in.setColor(Color.RED);
		
		//Modern look settings
		//why is in a rounded rectangle!!!
		SeanDrawables in = new SeanDrawables(-400, 0, 400, 50);
		SeanDrawables bg = new SeanDrawables(new SeanRoundedRect(0, 0, 400, 50, 30, 30));
		in.setColor(Color.WHITE);
		bg.setColor(Color.LIGHT_GRAY);
		
		ss = new MapSlider(100, 100, bg, in, 20, 0, 30, 30, "color picker");
		ss.setVisible(true);
		ss.repaint();
		add(ss);
		
		//Animations anim = new Animations(ss);
		//anim.horShake(5, 5, 2000, 1);
		
		SeanTextArea sta = new SeanTextArea(new SeanRoundedRect(0,0,20,2,0,0));
		sta.setBoundsModified(200, 200, 200, 100);
		sta.setText("Hello World! I'm a Sean Text Area.");
		sta.setTextColor(Color.BLACK);
		sta.setVisible(true);
		add(sta);
		
		Animations anim2 = new Animations(sta);
		anim2.fadeOutText(sta, 255, 0, 100);
		
		//Animations anim3 = new Animations(ss);
		//anim3.expand(in, 50, 500);
		
		//anim.hor
		//min, max, maxCount, interval
		*/
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}


}
