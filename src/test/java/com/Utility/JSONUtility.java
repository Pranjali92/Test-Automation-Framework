package com.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JSONUtility {

	public static Environment readJSON(Env env) {
		Gson gson = new Gson();
		File jsonfile = new File(
				System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config = gson.fromJson(fileReader, Config.class);
		Environment environment = config.getEnvironments().get("QA");
		return environment;

	}

}
