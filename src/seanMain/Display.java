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
import seanComponent.SeanDrawables;
import seanComponent.SeanSlider;
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
	SeanSlider ss;

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


		sb.repaint();
		add(sb);

		sb.getAnime().horShake(100, 100, 1, 20);


		try {
			sbgpanel = new SeanBGPanel(0,0,getWidth(),getHeight(),ImageIO.read(new File("src/images/anime-background.jpeg")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}



		add(sbgpanel);

		/*
		SeanDrawables in = new SeanDrawables(10, 10, 20, 20);
		SeanDrawables bg = new SeanDrawables(0, 0, 200, 200);
		in.setColor(Color.RED);
		bg.setColor(Color.GREEN);
		ss = new SeanSlider(bg, in, 100, 0, "");
		ss.setVisible(true);
		ss.repaint();
		add(ss);

		*/

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		//sbgpanel.getAnime().rotShake(sbgpanel.getBG(),0.07, 0.07, sbgpanel.getWidth()/2, sbgpanel.getHeight()/2, 200, 5);
		//sbgpanel.getAnime().horShake(10, 7, 50, 7,2);
		//sbgpanel.getAnime().vertShake( 7, 5, 50, 7);

		//sbgpanel.getAnime().fade(sbgpanel.getBG(), 0.1f, sbgpanel.getBG().getOpacity(), 400);

		sbgpanel.addDrawables(new SeanDrawables(600,0,500,sbgpanel.getHeight(),0.5f));
		try {
			sbgpanel.getDrawables(0).setImage(ImageIO.read(new File("src/images/nagisa_with_umbrella.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//sta.setText("AYY LMAO SD DSF SD F A SD DSF ASD DSF S A Q D DDD");

	}


}
