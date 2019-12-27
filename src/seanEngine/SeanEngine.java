package seanEngine;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import seanComponent.SeanButtonArray;
import seanComponent.SeanTextArea;
import seanMain.Display;

public class SeanEngine {
	Display d;
	SeanStoryInterpreter ssi;
	KeyboardInput ki;
	
	public SeanEngine() {
		d = new Display();
		d.getSta().setFont(new Font("Arial Unicode MS", Font.PLAIN, 17));
		d.getSta().setSpeed(10);
		ssi = new SeanStoryInterpreter(new File("src/textfiles/sean3.txt"));
		ki = new KeyboardInput(this);
		d.addKeyListener(ki);
	}
	
	// Keyboard input interpretation
	public void keyInterpreter(int[] keys) {
		if(keys[KeyEvent.VK_ENTER] == 1) {
			// do next line
			cmdInterpreter(ssi.readLine());
			//d.getSta().setText(ssi.readLine());
			
		} else if(keys[KeyEvent.VK_ESCAPE]==1) {
			
		}
	}
	
	// something to get the next line from Display
	public void cmdInterpreter(String s)
	{
		// gets rid of whitespace
		s= s.trim();

		if (s.equals("") && ssi.firstTime){
			ssi.firstTime = false;
			cmdInterpreter(ssi.readLine());
			
		// "-" means that it does not require user input to invoke.
		} else if(ssi.firstTime && s.startsWith("~")){
			// removes the prefix "-" and invokes suffixInterpreter
			suffixInterpreter(s.replaceFirst("~", "").trim());

			cmdInterpreter(ssi.readLine());
		// then if it is on autoRead then it will continue.
		} else if(ssi.autoRead){
			suffixInterpreter(s);

			// options cannot be autoRead
			if(!s.startsWith("OPTION:"))
				cmdInterpreter(ssi.readLine());
		} else {
			suffixInterpreter(s);
		}
	}

	public void suffixInterpreter(String s)
	{
		if(s.startsWith("STORY:")) {
			// isolates the command followed by story
			String temp = s.replaceFirst("STORY:", "").trim();
			String charName = temp.substring(0, temp.indexOf(':'));
			String body = temp.substring(1+temp.indexOf(':')).trim();

			// VisualEngine Part -> Give it body and character name.
			d.getSta().setText(charName+ ": "+ body);
			
			// Testing
			System.out.println("CHARNAME:" + charName + "\nBODY:" + body);
		} else if (s.startsWith("SETTING:")) {
			// isloates the command from the body
			String temp = s.replaceFirst("SETTING:","").trim();

			//visual engine part -> Give it image source and set
			//it in the background
			try {
				d.getSbgpanel().setBG(ImageIO.read(new File("src/images/" + temp)));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Testing
			System.out.println("SETTING:" +temp);
		} else if (s.startsWith("EFFECT:")) {
			// isolates the cmd from the body
			String temp = s.replaceFirst("EFFECT:", "").trim();

			// tells the game engine what effect needs to be enacted
			VFXInterpreter(temp);
			
			// Testing
			System.out.println("EFFECT:" +temp);
		} else if (s.startsWith("OPTION:")) {
			// isolates the cmd from the body
			String temp = s.replaceFirst("OPTION:", "").trim();

			// splits up the options and command and puts it into causality
			// objects
			String delim = Pattern.quote("[") +Pattern.compile("|")+
										 Pattern.quote("]");
			String[] sarr = temp.split(delim);
			Stack<SeanCausalityObj> cause = new Stack<>();
			// converts array into causality objects
			for(int i=0;i<sarr.length;i++)
			{
				sarr[i] = sarr[i].replaceAll(Pattern.quote("]"), "");

				if (sarr[i].contains("->")) {
					cause.push(new SeanCausalityObj(sarr[i].
							replaceFirst("->", "").trim(),this));
				} else if (sarr[i].trim().isEmpty()) {
				} else {
					SeanCausalityObj top = cause.pop();
					top.addEffect(sarr[i].trim());
					cause.push(top);
				}
			}
			
			ki.ignoreInput = true;
			
			// Causality Objects obtained... now create the array and add it to the thing.
			System.out.println(Arrays.toString(cause.toArray()));
			
			SeanTextArea sta = d.getSta();
			Stack<SeanCausalityObj> sean = new Stack<>();
			Queue<SeanCausalityObj> sean2 = new LinkedList<>();
			while(!cause.isEmpty()) {
				sean.push(cause.pop());
			}
			while(!sean.isEmpty()) {
				sean2.add(sean.pop());
			}
			int length = sean2.toArray().length;
			SeanButtonArray sba = new SeanButtonArray(sean2);
			sba.setBoundsModified(sta.getBoundx(), sta.getBoundy(), sta.getWidth()-2*sta.getBoundx(), 30*length);
			
			sta.setSComp(sba);

			// testing
			//System.out.println(Arrays.toString(sarr));
			//System.out.println(Arrays.toString(cause.toArray()));
		} else if(s.startsWith("CHANGESTAT:")) {
			// isolates the cmd from the body
			String temp = s.replaceFirst("CHANGESTAT:", "").trim();

			String[] sarr = temp.split(Pattern.quote("|"));
			String charName = sarr[0];;
			String stat = sarr[1];
			//int amt = Integer.parseInt(sarr[2]);

			// pass this into the cmd control method.
			

			// Testing
			//System.out.println(amt + "change in " + charName + "'s stat," + stat);
			
			System.out.println("POOPIE");
		} else if(s.startsWith("CHANGEFILE:")) {
			// isolate the cmd from the body
			String temp = s.replaceFirst("CHANGEFILE:", "").trim();

			// change file of the storyInterpreter
			ssi.changeStoryFile(new File("src/textfiles/" + temp));
			
			// maybe some transition
			cmdInterpreter(ssi.readLine());
			
			// testing
			System.out.println("FILE: " + temp);
		}
	}
	
	
	// something to set Animations
	public void VFXInterpreter(String s) {
		// is math related to science
		if(s.equalsIgnoreCase("Shaking")) {
			// WIP -> can tweak this later
			d.getSbgpanel().getAnime().horShake(7, 13, 7, 1);
			d.getSbgpanel().getAnime().vertShake(11, 9, 7, 1);
		} 
	}
	
}
