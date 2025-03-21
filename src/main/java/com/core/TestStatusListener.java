package com.core;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestStatusListener extends Util implements ITestListener {
	public void onTestFailure(ITestResult scenario) {
	//	takeScreenshotOnFailure(scenario.getName());
	}
}
