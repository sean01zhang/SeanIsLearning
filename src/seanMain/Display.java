package seanMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

import seanComponent.SeanButton;
import seanComponent.SeanTextArea;
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

	public void invokeResize() {
		sta.setBounds(10,getHeight()-130,getWidth()-20,100);
		sbgpanel.resizePanel(getWidth(),getHeight());
	}
	
	public Display() {
		this.setLayout(null);
		this.setSize(600, 400);
		this.setPreferredSize(new Dimension(600,400));
		
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        invokeResize();
		    }
		});
		
		// STA Stuffs

		this.sta = new SeanTextArea();
		
		sta.setBounds(10,getHeight()-130,getWidth()-20,100);

		sta.repaint();
		sta.setRadius(20);
		sta.setScrollType(SeanTextArea.SCROLL_CHAR);
		sta.setSpeed(50);
		/*
		try {
			sta.setBackgroundImage(ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
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


		Animations.horShake(sb, 100, 100, 1, 20);

		
		try {
			sbgpanel = new SeanBGPanel(0,0,getWidth(),getHeight(),ImageIO.read(new File("src/images/raining.jpeg")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		add(sbgpanel);
		

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		sta.setText("AYY LMAO SD DSF SD F A SD DSF ASD DSF S A Q D DDD");

	}


}
