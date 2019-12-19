package seanMain;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JPanel;

import seanComponent.SeanTextArea;
import seanEngine.SeanStoryInterpreter;

public class ContentPanel extends JPanel {
	SeanTextArea sta;
	SeanStoryInterpreter sip;
	File f;
	
	public ContentPanel() {
		this.setLayout(null);
		
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        invokeResize();
		    }
		});

		// STA Stuffs

				this.sta = new SeanTextArea();

				sta.setBoundsModified(0,0,780,125);

				sta.repaint();
				sta.setRadius(20);
				sta.setScrollType(SeanTextArea.SCROLL_CHAR);
				sta.setSpeed(50);
				sta.setBackgroundColor(new Color(255,25,255,190));

				/* Image setting in the text box
				try {
					sta.setBackgroundImage(ImageIO.read(new File("src/images/raining.jpeg")));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				*/
				

				add(sta);

				f = new File("src/seanEngine/sean.txt");

				sip = new SeanStoryInterpreter(f,this);
		/*
				
				// button testing time
				sb = new SeanButton(new SeanRoundedRect(0,0,getWidth(),getHeight(),20,20),"hello") {
					@Override
					public void mouseClicked(MouseEvent e) {
						sta.setText("Hello");
					}
				};

				
				sb.setBounds(300, 30, 100, 50);
				
				add(sb);

			
				*/
				//sb.getAnime().horShake(100, 100, 1, 20);
				/*
				try {
					sbgpanel = new SeanBGPanel(0,0,getWidth(),getHeight(),ImageIO.read(new File("src/images/raining.jpeg")));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			
				add(sbgpanel);
				
				// ANIMATIONS
				//sbgpanel.getAnime().rotShake(sbgpanel.getBG(),0.07, 0.07, sbgpanel.getWidth()/2, sbgpanel.getHeight()/2, 200, 5);
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
				
				//sbgpanel.getAnime().rotShake(sbgpanel.getDrawables(0),0.07, 0.07, sbgpanel.getWidth()/2, sbgpanel.getHeight()/2, 200, 5);
				
				
				//sb.getAnime().slideh(sb, 1000, -100);
				
				*/
				
				this.setVisible(true);
	}
	
	public void invokeResize() {
		//sta.setBoundsModified(0,getHeight()-155,800,125);
		//sta.setBoundsModified((getWidth()-700)/2,getHeight()-155,700,125);
		//sbgpanel.resizePanel(getWidth(),getHeight());
	}
	
	
}
