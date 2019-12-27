package seanMain;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

import seanEngine.SeanCondObj;
import seanEngine.SeanEngine;
import seanEngine.SeanFileWriter;
import seanMisc.Animations;
import seanMisc.SeanDrawables;

public class seanIsLearning {
	public static void main(String[]args) {
		SeanEngine se = new SeanEngine();
		//Display2 lynn = new Display2();
		
		System.out.println(SeanCondObj.evaluateStatement("6*5<=4"));
	}
}
