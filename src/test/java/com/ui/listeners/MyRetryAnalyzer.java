package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.Utility.JSONUtility;
import com.Utility.PropertiesUtil;
import com.constants.Env;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	
	  private static final int MAX_NUMBER_OF_ATTEMPTS = Integer
	  .parseInt(PropertiesUtil.readProperty(Env.DEV, "MAX_NUMBER_OF_ATTEMPTS"));
	 
	 
	//private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
	private static int currentAttempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}

		return false;
	}

}
