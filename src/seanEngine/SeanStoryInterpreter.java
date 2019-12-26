package seanEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
	BufferedReader in;
	
	public SeanStoryInterpreter(File f)
	{
		currentStory = f;

		// initial string reader
		
		
		try {
			input = new Scanner(f,"UTF-8");
			input.useDelimiter(";;");
		} catch (Exception e) {}
		

		try {
			in = new BufferedReader(
					   new InputStreamReader(
			                      new FileInputStream(f), "UTF8"));
		} catch (Exception e) {e.printStackTrace();
		}
		
		firstTime= true;
		autoRead= false;
	}
	
	// read line
	public String readLine()
	{
		String str="";

		// consume raw input
		
		if(input.hasNext()) {
			str = input.next();
		}
		
		
		/*
		if(in == null) {
			System.out.println("What du heck man");
		}
		try {
			if((str = in.readLine()) != null) {
				
			} else {
				str = "";
			}
		} catch (IOException e) {e.printStackTrace();}
		*/
		
		System.out.println(str);
		return str;
	}

	public void changeStoryFile(File f) {
		currentStory = f;
		
		input.close();
		
		try {
			input = new Scanner(f,"UTF-8");
			input.useDelimiter(";;");
		} catch (Exception e) {}
		
		firstTime = true;
		autoRead=false;
	}
}
