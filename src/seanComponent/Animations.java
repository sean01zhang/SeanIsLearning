package seanComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JComponent;

public class Animations {

	public void rotShake(JComponent comp){
		//int
	}


	// VERTICAL SHAKING EFFECT ***************************************
	static Boolean goingUp;
	static int verCounter;
	static Timer verT;

	public static void vertShake(JComponent comp, int max, int min, int maxCount, int interval){
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
							System.out.println("Going down");
						}
					} else if (goingUp == false && comp.getY() > absMin){
						comp.setLocation(comp.getX(), comp.getY() - 1);
						comp.revalidate();
						if(comp.getY() == absMin){
							goingUp = true;
							verCounter++;
							System.out.println("Going up");
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
	static Boolean goingRight;
	static int horCounter;
	static Timer horT;

	public static void horShake(JComponent comp, int max, int min, int maxCount, int interval){
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
							System.out.println("Going Left");
						}
					} else if (goingRight == false && comp.getX() > absMin){
						comp.setLocation(comp.getX() - 1, comp.getY());
						comp.revalidate();
						if(comp.getX() == absMin){
							goingRight = true;
							horCounter++;
							System.out.println("Going Right");
						}
					}

					if(horCounter >= maxCount) {
						horT.stop();
					}
			}
		});

		horT.start();
	}

	public static void horShake(JComponent comp, int max, int min, int maxCount, int interval, int vel){
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
							System.out.println("Going Left");
						}
					} else if (goingRight == false && comp.getX() > absMin){
						comp.setLocation(comp.getX() - vel, comp.getY());
						comp.revalidate();
						if(comp.getX() == absMin){
							goingRight = true;
							horCounter++;
							System.out.println("Going Right");
						}
					}

					System.out.println("i like tacos.");

					if(horCounter >= maxCount) {
						horT.stop();
					}
			}
		});

		horT.start();
	}


}
