package seanComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Queue;

import javax.swing.JComponent;

import seanEngine.SeanCausalityObj;
import seanGeometry.SeanDimentedRect;
import seanGeometry.SeanRoundedRect;

public class SeanOptionChooser extends SeanComponent {
	Queue<SeanCausalityObj> sco;
	SeanButton[] sba;
	Color textColor;
	int buttonSpacing;
	int cornerRadius;
	
	public SeanOptionChooser(Queue<SeanCausalityObj> sc) {
		this.setSize(400, 400);
		
		sco=sc;
		buttonSpacing = (int)((double)getHeight()/sc.toArray().length);
		cornerRadius = 20;
		textColor = Color.black;
		
		sba = new SeanButton[sc.toArray().length];
		
		
		initButtons();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=1;i<sba.length;i++) {
			g.drawLine(0, i*buttonSpacing, getWidth(), i*buttonSpacing);
		}
	}
	
	
	@SuppressWarnings("serial")
	private void initButtons() {
		for(int i=0;i<sba.length;i++) {
			if(i==0) {
				Boolean[] temp = {true,true,false,false};
				sba[i] = new SeanButton(new SeanDimentedRect(0,0, getWidth(), buttonSpacing,
															cornerRadius,cornerRadius,temp),sco.poll());
			} else if (i==sba.length-1) {
				Boolean[] temp = {false,false,true,true};
				sba[i] = new SeanButton(new SeanDimentedRect(0,0, getWidth(), buttonSpacing,
															cornerRadius,cornerRadius,temp),sco.poll());
			} else {
				sba[i] = new SeanButton(new SeanRoundedRect(0,0, getWidth(), buttonSpacing,
															0,0),sco.poll());
			}
			sba[i].setBoundsModified(0, i*buttonSpacing, getWidth(), buttonSpacing);
			System.out.println(sba[i].getBounds());
			this.add(sba[i]);
		}
	}


	@Override
	public void setTextColor(Color c) {
		textColor = c;
	}

	@Override
	public Color getTextColor() {
		return textColor;
	}
	
	
}
