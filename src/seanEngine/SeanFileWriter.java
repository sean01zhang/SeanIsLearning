package seanEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SeanFileWriter {
	public static void changeCharacterStat(File f, String charTrait, int newValue) {
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String verify,putData;
			
			while((verify=br.readLine()) != null) {
				if(verify != null) {
					putData = verify.replaceAll("", "");
				}
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
		
	}
}
