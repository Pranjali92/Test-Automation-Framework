package com.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {  

	public static String readProperty(Env env, String PropertyName) {

		System.out.println(System.getProperty("user.dir"));
		File propfile = new File(System.getProperty("user.dir") + "//config//" + env + ".properties");
		FileReader fileReader = null;
		Properties properties = new Properties();
		try {
			fileReader = new FileReader(propfile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = properties.getProperty(PropertyName.toUpperCase());
		return value;

	}

}
