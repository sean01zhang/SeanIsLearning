package seanMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

import seanComponent.SeanButton;
import seanComponent.SeanTextArea;
import seanComponent.SeanTextArea;
import seanComponent.Animations;
import seanEngine.SeanStoryInterpreter;

public class Display extends JFrame {
	int width = 600;
	int height = 400;
	Panel p;
	SeanStoryInterpreter sip;
	File f;
	JTextArea jta;
	SeanTextArea sta;
	Timer t;

	SeanButton sb;

	int vel_x;

	int vel_y;
	int pos_y;
	int acc;

	public int getVel_y() {
		vel_y = Math.max(0,vel_y+ acc);
		return vel_y;
	}

	public int getPos_y() {
		pos_y = pos_y+ getVel_y();
		return pos_y;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, 100, 200);
	}

	// = new SeanStoryInterpreter(, null);

	public Display() {

		this.setLayout(null);

		// panel stuffs

		//this.p = new Panel(); p.setSize(width, height);
		//add(p);
		//p.setLocation(0, 0);
		//p.repaint();


		// STA Stuffs

		this.sta = new SeanTextArea();
		sta.setSize(200, 100);
		sta.setLocation(10, 10);
		sta.repaint();
		add(sta);
		
		
		

		sta.setScrollType(SeanTextArea.SCROLL_CHAR);
		sta.setSpeed(50);

		f = new File("src/seanEngine/sean.txt");

		sip = new SeanStoryInterpreter(f,this);


		// button testing time

		sb = new SeanButton("hello") {
			@Override
			public void mouseClicked(MouseEvent e) {
				sta.setText("Hello");
				System.out.println("eyyy... lmao!");
			}
		};


		sb.setBounds(300, 30, 100, 50);
		sb.setBorders(true, 30);
		sb.repaint();
		add(sb);

		Animations.horShake(sb, 100, 100, 1, 20);

		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);


		sta.setText("AYY LMAO SD DSF SD F A SD DSF ASD DSF S A Q D DDD");

	}




}
