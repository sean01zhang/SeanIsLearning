package seanMain;

import java.util.Arrays;
import java.util.regex.Pattern;

import seanComponent.Animations;
import seanComponent.SeanDrawables;

public class seanIsLearning {
	public static void main(String[]args) {
		Display sean = new Display();
		//Display2 lynn = new Display2();
		//System.out.println(sean.sip.readLine());

		String lineatm =" ";

		while(sean.sip.input.hasNext()) {
			lineatm = sean.sip.readLine();
		}

		//System.out.println(sean.sip.readLine());

		
		
		SeanDrawables s = new SeanDrawables(0,0,20,20);
		
		System.out.println(s.getBounds().toString());
	}
}
