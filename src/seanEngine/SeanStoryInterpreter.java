package seanEngine;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

import seanMain.Display;

public class SeanStoryInterpreter
{
	File currentStory;
	public Scanner input;
	Display d;
	Boolean firstTime;
	Boolean autoRead;

	public SeanStoryInterpreter(File f, Display d)
	{
		currentStory = f;

		// initial string reader
		try {
			input = new Scanner(new FileReader(currentStory));
			input.useDelimiter(";;");
		} catch (Exception e) {}

		this.d = d;
		firstTime= true;
		autoRead= false;
	}

	// read line and return story
	public String readLine()
	{
		String str = "";

		// consume raw input
		if(input.hasNext());
			str = input.next();

		// interpret
		cmdInterpreter(str);

		return str;
	}

	public void cmdInterpreter(String s)
	{
		// gets rid of whitespace
		s= s.trim();

		if (s.equals("") && firstTime){
			readLine();
			firstTime = false;
		// "-" means that it does not require user input to invoke.
		} else if(firstTime && s.startsWith("-")){
			// removes the prefix "-" and invokes suffixInterpreter
			suffixInterpreter(s.replaceFirst("-", "").trim());

			readLine();
		// then if it is on autoRead then it will continue.
		} else if(autoRead){
			suffixInterpreter(s);

			// options cannot be autoRead
			if(!s.startsWith("OPTION:"));
				readLine();
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

			// Testing
			//System.out.println("CHARNAME:" + charName + "\nBODY:" + body);
		} else if (s.startsWith("SETTING:")) {
			// isloates the command from the body
			String temp = s.replaceFirst("SETTING:","").trim();

			//visual engine part -> Give it image source and set
			//it in the background
			temp = temp.replace(' ', '_') + ".png";

			// Testing
			//System.out.println("SETTING:" +temp);
		} else if (s.startsWith("EFFECT:")) {
			// isolates the cmd from the body
			String temp = s.replaceFirst("EFFECT:", "").trim();

			// tells the game engine what effect needs to be enacted

			// Testing
			//System.out.println("EFFECT:" +temp);
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
							replaceFirst("->", "").trim()));
				} else if (sarr[i].trim().isEmpty()) {
				} else {
					SeanCausalityObj top = cause.pop();
					top.addEffect(sarr[i].trim());
					cause.push(top);
				}
			}
			// Pass causality objects to the main engine

			// testing
			//System.out.println(Arrays.toString(sarr));
			//System.out.println(Arrays.toString(cause.toArray()));
		} else if(s.startsWith("CHANGESTAT:")) {
			// isolates the cmd from the body
			String temp = s.replaceFirst("CHANGESTAT:", "").trim();

			String[] sarr = temp.split(Pattern.quote("|"));


		}
	}

}
