package seanMisc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import seanComponent.SeanComponent;

import javax.swing.JComponent;

public class Animations {
	JComponent comp;
	
	public Animations(JComponent jc) {
		comp = jc;
	}
	
	// EXPAND **************************************************************
	Timer exp;
	int initHeight;
	int initWidth;
	int thresh;
	int timeExpandCounter = 0;
	public void expand(SeanDrawables sd, int max, int milliseconds){
		
		//height = ;
		//width = ;
		initHeight = sd.getBounds().height;
		initWidth = sd.getBounds().width;
		thresh = 0;
		
		double changePerMilli = (double)max/milliseconds; 
		
		//because of this, the timer is not EXACTLY accurate (maybe off by a couple of milliseconds)
		while(changePerMilli*thresh < 1){
			thresh++;
		}
		//System.out.println(thresh);
		
		exp = new Timer(1, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				timeFadeCounter++;
				
				if(timeFadeCounter == thresh){
					sd.setBounds((int)sd.getX(), (int)sd.getY(), sd.getBounds().width + (int)((double)changePerMilli*thresh), sd.getBounds().height + (int)((double)changePerMilli*thresh));
					timeFadeCounter = 0;
					System.out.println(sd.getBounds().width);
				}
				
				if(sd.getBounds().height - initHeight >= max){
					System.out.println("stops");
					exp.stop();
				}
				comp.repaint();
			}
		});
		
		exp.start();
	}
	
	
	// SLIDE IN/OUT ***************************************************************
	
	Timer slideT;
	double counter;
	
	public void slideh(SeanDrawables sd,int initialPos, int finalPos) {
		int difference = finalPos - initialPos;
		double increment= Math.PI/5;
		counter =0;
		
		
		slideT = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sd.setLocation((int)(difference*Math.max(Math.sin(counter)/counter,0)+initialPos)
							   ,(int)sd.getY());
				
				counter += increment;
				comp.repaint();
			}
		});
	}
	
	public void slideh(JComponent sd,int initialPos, int finalPos) {
		int difference = finalPos - initialPos;
		double increment= Math.PI/5;
		counter =0;
		
		
		slideT = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sd.setLocation((int)(difference*Math.max(Math.sin(counter)/counter,0)+initialPos)
							   ,(int)sd.getY());
				
				counter += increment;
				comp.repaint();
			}
		});
	}
	
	
	
	
	
	// DRAWABLES FADING OUT ***************************************************************
	Timer fadT;
	
	public void fadeOut(SeanDrawables sd, float finalOpacity, float initialOpacity, int milliseconds) {
		float difference = finalOpacity -initialOpacity;
		float rate = difference*5/milliseconds;
		
		fadT = new Timer(5,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sd.setOpacity(sd.getOpacity()+rate);
				comp.repaint();
				if ((-difference/Math.abs(difference))*(sd.getOpacity()-finalOpacity)<=0) {
					fadT.stop();
				}
			}
		});
		
		fadT.start();
	}
	
	
	// TEXT FADING OUT **********************************************************
	int timeFadeCounter = 0;
	int makeInt = 0;
	Timer fadeText;
	//initialOpacity, finalOpacity from 0 to 255
	public void fadeOutText(SeanComponent sComp, int initOpacity, int finalOpacity, int milliseconds){
		int difference = initOpacity - finalOpacity;
		float rate;
		
		if((float)((float)difference*1/milliseconds) == 0){
			rate = (float) 0.01;
		} else {
			rate = (float)difference*1/milliseconds;
		}
		//System.out.println(rate);
		
		//because of this, the timer is not EXACTLY accurate (maybe off by a couple of milliseconds)
		while(initOpacity > finalOpacity && (double)(makeInt*rate) < 1){
			makeInt++;
		}//System.out.println(makeInt);
		
		fadeText = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeFadeCounter++;
				//System.out.println("hi");
				if(timeFadeCounter == makeInt){
					sComp.setTextColor(new Color(sComp.getTextColor().getRed(), sComp.getTextColor().getGreen(), sComp.getTextColor().getBlue(), sComp.getTextColor().getAlpha() - (int)(makeInt*rate)));
					timeFadeCounter = 0;
					//System.out.println(sComp.getTextColor().getAlpha());
				} //System.out.println(timeCounter);
				if(sComp.getTextColor().getAlpha() - (int)(makeInt*rate) <= 0){
					sComp.setTextColor(new Color(sComp.getTextColor().getRed(), sComp.getTextColor().getGreen(), sComp.getTextColor().getBlue(), 0));
					fadeText.stop();
				}
				sComp.repaint();
			}
		});
		fadeText.start();
	}
	
	
	
	
	
	// ROTATIONAL SHAKING ***************************************************
	int rotCounter;
	Boolean CW;
	Timer rotT;
	
	public void rotShake(SeanDrawables sd, double radL,double radR, int x, int y,int maxCount,int interval){
		rotCounter=0;
		CW=true;
		double origPos = sd.getRadians();
		sd.setPivotX(x);
		sd.setPivotY(y);
		
		rotT = new Timer(interval, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CW && sd.getRadians() < origPos+radL) {
					sd.setRadians(sd.getRadians()+0.05);
					comp.repaint();
					if(sd.getRadians()>=origPos+radL) {
						CW=false;
						rotCounter++;
					}
				} else {
					sd.setRadians(sd.getRadians()-0.05);
					comp.repaint();
					if(sd.getRadians()<=origPos-radR) {
						CW=true;
						rotCounter++;
					}
					
				}
				
				if(rotCounter >= maxCount) {
					rotT.stop();
					sd.setRadians(0);
					comp.repaint();
				}
			}	
		});

		rotT.start();
	}
	
	public void rotShake(SeanDrawables sd, double radL,double radR, int x, int y,int maxCount,int interval,int vel){
		rotCounter=0;
		CW=true;
		double origPos = sd.getRadians();
		sd.setPivotX(x);
		sd.setPivotY(y);
		
		rotT = new Timer(interval, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CW && sd.getRadians() < origPos+radL) {
					sd.setRadians(sd.getRadians()+vel);
					comp.repaint();
					if(sd.getRadians()>=origPos+radL) {
						CW=false;
						rotCounter++;
					}
				} else {
					sd.setRadians(sd.getRadians()-vel);
					comp.repaint();
					if(sd.getRadians()<=origPos-radR) {
						CW=true;
						rotCounter++;
					}
					
				}
				
				if(rotCounter >= maxCount) {
					rotT.stop();
					sd.setRadians(0);
					comp.repaint();
				}
			}	
		});

		rotT.start();
	}


	// VERTICAL SHAKING EFFECT ***************************************
	Boolean goingUp;
	int verCounter;
	Timer verT;

	public void vertShake(int max, int min, int maxCount, int interval){
		int absMax = comp.getY() + max;
		int absMin = comp.getY() - min;
		goingUp = true;
		verCounter = 0;

		verT = new Timer(interval, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					if(goingUp == true && comp.getY() < absMax){
						comp.setLocation(comp.getX(), comp.getY() + 1);
						comp.revalidate();
						if(comp.getY() == absMax){
							goingUp = false;
							verCounter++;
						}
					} else if (goingUp == false && comp.getY() > absMin){
						comp.setLocation(comp.getX(), comp.getY() - 1);
						comp.revalidate();
						if(comp.getY() == absMin){
							goingUp = true;
							verCounter++;
						}
					}
					if(verCounter >= maxCount) {
						verT.stop();
					}
				}
		});

		verT.start();
	}

	
	// HORIZTONTAL SHAKING ***********************************
	Boolean goingRight;
	int horCounter;
	Timer horT;

	public void horShake(int max, int min, int maxCount, int interval){
		int absMax = comp.getX() + max;
		int absMin = comp.getX() - min;
		goingRight = true;
		horCounter = 0;

		horT = new Timer(interval, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					if(goingRight == true && comp.getX() < absMax){
						comp.setLocation(comp.getX() + 1, comp.getY());
						comp.revalidate();
						if(comp.getX() == absMax){
							goingRight = false;
							horCounter++;
						}
					} else if (goingRight == false && comp.getX() > absMin){
						comp.setLocation(comp.getX() - 1, comp.getY());
						comp.revalidate();
						if(comp.getX() == absMin){
							goingRight = true;
							horCounter++;
						}
					}

					if(horCounter >= maxCount) {
						horT.stop();
					}
			}
		});

		horT.start();
	}

	public void horShake(int max, int min, int maxCount, int interval, int vel){
		int absMax = comp.getX() + max;
		int absMin = comp.getX() - min;
		goingRight = true;
		horCounter = 0;

		horT = new Timer(interval, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					if(goingRight == true && comp.getX() < absMax){
						comp.setLocation(comp.getX() + vel, comp.getY());
						comp.revalidate();
						if(comp.getX() == absMax){
							goingRight = false;
							horCounter++;
						}
					} else if (goingRight == false && comp.getX() > absMin){
						comp.setLocation(comp.getX() - vel, comp.getY());
						comp.revalidate();
						if(comp.getX() == absMin){
							goingRight = true;
							horCounter++;
						}
					}

					if(horCounter >= maxCount) {
						horT.stop();
					}
			}
		});

		horT.start();
	}


}
