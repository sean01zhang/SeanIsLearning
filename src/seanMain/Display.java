package seanMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
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
import seanComponent.SeanDrawableForeground;
import seanComponent.SeanDrawables;
import seanComponent.SeanSliderV2;
import seanComponent.MapSlider;
import seanComponent.SeanTextArea;
import seanComponent.Animations;
import seanComponent.SeanBGPanel;
import seanEngine.SeanStoryInterpreter;

public class Display extends JFrame{
	Panel p;
	SeanStoryInterpreter sip;
	File f;
	JTextArea jta;
	SeanTextArea sta;
	Timer t;
	SeanBGPanel sbgpanel;
	SeanButton sb;
	MapSlider ss;

	public void invokeResize() {
		sta.setBounds((getWidth()-700)/2,getHeight()-155,700,125);
		sbgpanel.resizePanel(getWidth(),getHeight());
	}

	public Display() {
		this.setLayout(null);
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));


		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        invokeResize();
		    }
		});

		// STA Stuffs

		this.sta = new SeanTextArea();

		sta.setBounds(50,getHeight()-155,700,125);

		sta.repaint();
		sta.setRadius(20);
		sta.setScrollType(SeanTextArea.SCROLL_CHAR);
		sta.setSpeed(50);
		sta.setBackgroundColor(new Color(255,255,255,190));

		/*
		try {
			sta.setBackgroundImage(ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
		

		add(sta);

		f = new File("src/seanEngine/sean.txt");

		sip = new SeanStoryInterpreter(f,this);

		// button testing time
		sb = new SeanButton("hello") {
			@Override
			public void mouseClicked(MouseEvent e) {
				sta.setText("Hello");
			}
		};

		sb.setBounds(300, 30, 100, 50);
		sb.setRoundCorners(30);
		add(sb);

		//sb.getAnime().horShake(100, 100, 1, 20);

		try {
			sbgpanel = new SeanBGPanel(0,0,getWidth(),getHeight(),ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	
		add(sbgpanel);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		// ANIMATIONS
		sbgpanel.getAnime().rotShake(sbgpanel.getBG(),0.07, 0.07, sbgpanel.getWidth()/2, sbgpanel.getHeight()/2, 200, 5);
		//sbgpanel.getAnime().horShake(10, 7, 50, 7,2);
		//sbgpanel.getAnime().vertShake( 7, 5, 50, 7);
		//sbgpanel.getAnime().fade(sbgpanel.getBG(), 0.1f, sbgpanel.getBG().getOpacity(), 400);

		sbgpanel.addDrawables(new SeanDrawableForeground (150,0,200,getHeight(),0.5f) {
			@Override
			public Rectangle getPreferredBounds(int x, int y, int width, int height) {
				return new Rectangle((getScaledImageWidth()-width)/-2,(getScaledImageHeight()-height)/-2,200,height);
			}
			
		});

		try {
			sbgpanel.getDrawables(0).setImage(ImageIO.read(new File("src/images/nagisa_with_umbrella.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//sb.getAnime().slideh(sb, 1000, -100);
		
		SeanSliderV2 ssv2 = new SeanSliderV2(100,100,400,20);
	}


}
