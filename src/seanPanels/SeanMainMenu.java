package seanPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JPanel;

import seanComponent.SeanButton;
import seanGeometry.SeanRoundedRect;
import seanMain.Display;

@SuppressWarnings("serial")
public class SeanMainMenu extends JPanel{
	
	SeanButton[] sb = new SeanButton[3];
	int sbWidth;
	int sbHeight;
	final int PANEL_NUM = 0;
	
	public SeanMainMenu(int displayWidth, int displayHeight){

		sbWidth = (int)(displayWidth*0.8);
		sbHeight = (int)(displayHeight*0.1);

		sb[0] = new SeanButton(new SeanRoundedRect(0, 0, sbWidth, sbHeight, 30, 30), 0 + ""){
			public void mousePressed(MouseEvent e){
				pressStatus = this.PRESSED;
				repaint();
				System.out.println("New Game");
			}
		};
		
		sb[1] = new SeanButton(new SeanRoundedRect(0, 0, sbWidth, sbHeight, 30, 30), 0 + ""){
			public void mousePressed(MouseEvent e){
				pressStatus = this.PRESSED;
				repaint();
				System.out.println("Load Game");
			}
		};
		
		sb[2] = new SeanButton(new SeanRoundedRect(0, 0, sbWidth, sbHeight, 30, 30), 0 + ""){
			public void mousePressed(MouseEvent e){
				pressStatus = this.PRESSED;
				repaint();
				System.out.println("Exit");
				System.exit(0);
			}
		};
		
		for(int i = 0; i < 3; i++){
			sb[i].setBoundsModified((int)((double)(displayWidth - sbWidth)/2), (int)((double)(displayHeight - sbHeight)/2) + (sbHeight + 10)*i + 60, sbWidth, sbHeight);
			sb[i].setBackgroundColor(Color.DARK_GRAY);
			//font size change not working properly???
			sb[i].setFont(new Font("Consolas", Font.PLAIN, (int)(displayWidth*0.04)));
			sb[i].setTextColor(Color.WHITE);
		}
		//(int)((double)dis.getContentPane().getHeight()*0.2))
		sb[0].setText("New Game");
		sb[1].setText("Load");
		sb[2].setText("Exit");
		
		for(int i = 0; i < sb.length; i++){
			add(sb[i]);
			sb[i].setVisible(true);
		}
		
		setSize(displayWidth, displayHeight);
		//getContentPane gets significantly smaller values for width for some reason lol
		setLocation(0, 0);
		setLayout(null);
		setVisible(true);
	}
	
	public int getPanelNum(){
		return PANEL_NUM;
	}
	
}
