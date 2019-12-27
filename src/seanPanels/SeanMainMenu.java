package seanPanels;

import java.awt.Color;

import javax.swing.JPanel;

import seanComponent.SeanButton;
import seanGeometry.SeanRoundedRect;
import seanGeometry.SeanShape;
import seanMain.Display;

public class SeanMainMenu extends JPanel{

	SeanButton[] sb = new SeanButton[3];
	Display dis = new Display(); 
	int sbWidth;
	int sbHeight; 
	
	public SeanMainMenu(){
		
		sbWidth = (int)(dis.getContentPane().getWidth()*0.8);
		sbHeight = (int)(dis.getContentPane().getHeight()*0.1);
		
		for(int i = 0; i < 3; i++){
			sb[i] = new SeanButton(new SeanRoundedRect(0, 0, sbWidth, sbHeight, 0, 0), "Hi " + i);
			sb[i].setBounds((int)(((double)dis.getContentPane().getWidth() - sbWidth)/2), (int)((double)(dis.getContentPane().getHeight() - sbHeight)/2) + (sbHeight + 10)*i, sbWidth, sbHeight);
			sb[i].setBackgroundColor(Color.BLACK);
		}
		
		for(int i = 0; i < sb.length; i++){
			add(sb[i]);
			sb[i].setVisible(true);
		}
		
		setSize(dis.getContentPane().getWidth(), dis.getContentPane().getHeight());
		System.out.println(dis.getContentPane().getWidth() + ", " + dis.getContentPane().getHeight());
		setLocation(0, 0);
		setLayout(null);
		setVisible(true);
	}
	
}
