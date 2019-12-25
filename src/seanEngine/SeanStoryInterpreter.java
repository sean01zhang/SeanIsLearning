package seanEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;
import seanMain.Display;
import seanMain.Display2;

public class SeanStoryInterpreter
{
	File currentStory;
	public Scanner input;
	Boolean firstTime;
	Boolean autoRead;

	public SeanStoryInterpreter(File f)
	{
		currentStory = f;

		// initial string reader
		try {
			input = new Scanner(new FileReader(currentStory));
			input.useDelimiter(";;");
		} catch (Exception e) {}

		firstTime= true;
		autoRead= false;
	}

	// read line
	public String readLine()
	{
		String str = "";

		// consume raw input
		if(input.hasNext()) {
			str = input.next();
		}
		
		return str;
	}

	public void changeStoryFile(File f) {
		currentStory = f;
		
		input.close();
		
		try {
			input = new Scanner(new FileReader(currentStory));
			input.useDelimiter(";;");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		firstTime = true;
		autoRead=false;
	}
}
