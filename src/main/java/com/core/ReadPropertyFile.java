package com.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	static Properties p;
	static FileReader reader;
	
	public ReadPropertyFile() {
		//String filepath = System.getProperty("PROP_FILE");
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\PropertyFiles\\Android-Google-Pixel.properties";
		p= new Properties();
		try {
			reader = new FileReader(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String  getProperty (String name){  
	    try {
			p.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    return p.getProperty(name);
	}
}
