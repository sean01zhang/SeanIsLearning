package seanEngine;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

import seanMain.Display;

public class SeanStoryInterpreter {
	File currentStory;
	public Scanner input;
	Display d;
	
	
	public SeanStoryInterpreter(File f, Display d) {
		currentStory = f;
		
		// initial string reader
		try {
			input = new Scanner(new FileReader(currentStory));
		} catch (Exception e) {}
		
		input.useDelimiter(";;");
		
		this.d = d;
	}
	
	// read line and return story
	public String readLine() {
		String str = "";
		
		// consume raw input
		if(input.hasNext()) {
			str = input.next();
		}
		
		// interpret
		cmdInterpreter(str);
		
		return str;
	}
	
	Boolean firstTime = true;
	
	public void cmdInterpreter(String s) {
		if (s.equals("") && firstTime) {
			readLine();
			firstTime = false;
		} else if(firstTime) {
			s= s.trim();
			
			String charName;
			String body;
			
			if(s.startsWith("STORY:")) {
				String temp = s.replaceFirst("STORY:", "").trim();
				charName = temp.substring(0, temp.indexOf(':'));
				body = temp.substring(1+temp.indexOf(':')).trim();
				
				d.getSta().setText(body);
				System.out.println("CHARNAME:" + charName + "\nBODY:" + body);
			} else if (s.startsWith("SETTING:")) {
				String temp = s.replaceFirst("SETTING:","").trim();
				
				//visual engine part
				temp = temp.replace(' ', '_') + ".png";
				
				
				
				
				System.out.println("SETTING:" +temp);
			} else if (s.startsWith("EFFECT:")) {
				String temp = s.replaceFirst("EFFECT:", "").trim();
				
				System.out.println("EFFECT:" +temp);
				
			} else if (s.startsWith("OPTION:")) {
				String temp = s.replaceFirst("OPTION:", "").trim();
				
				String delim = Pattern.quote("[") +Pattern.compile("|")+
											 Pattern.quote("]");
				
				String[] sarr = temp.split(delim);
				
				Stack<SeanCausalityObj> cause = new Stack<>();
				
				for(int i=0;i<sarr.length;i++) {
					sarr[i] = sarr[i].replaceAll(Pattern.quote("]"), "");
					
					if (sarr[i].contains("->")) {
						cause.push(new SeanCausalityObj(sarr[i].
								replaceFirst("->", "").trim()));
					} else if (sarr[i].trim().isEmpty()) {
					} else {
						SeanCausalityObj top = cause.pop();
						top.addEffect(sarr[i].trim());
						cause.push(top);
					}
				}
				
				//System.out.println(Arrays.toString(sarr));
				System.out.println(Arrays.toString(cause.toArray()));
				
				// Pass causality objects to the main engine
				
			} else if() {
				
			}
		} else {
			
		}
	}
	
}
