package seanEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SeanFileWriter {
	public static void changeCharacterStat(File f, String charTrait, int newValue) {
		FileReader fr;
		
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String verify,putData="";
			while((verify=br.readLine()) != null) {
				if(verify.split(",")[0].equalsIgnoreCase(charTrait)) {
					putData += verify.split(",")[0] +","+newValue+"\n";
				} else {
					putData += verify+"\n";
				}
			}
			br.close();
			PrintWriter pw = new PrintWriter(f);
			pw.write(putData.trim());
			pw.close();
			
		} catch (Exception e) {e.printStackTrace();}	
	}
	
	public static int retrieveCharacterStat(File f, String charTrait) {
		try {
			Scanner s = new Scanner(f,"UTF-8");
			String verify,putData="";
			while((verify=s.nextLine()) != null) {
				if(verify.split(",")[0].equalsIgnoreCase(charTrait)) {
					return Integer.parseInt(verify.split(",")[1].trim());
				} else {
					
				}
			}
			
		} catch (Exception e) {e.printStackTrace();}
	}
}
