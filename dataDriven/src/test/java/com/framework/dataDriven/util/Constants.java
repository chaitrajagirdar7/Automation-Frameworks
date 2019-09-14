package com.framework.dataDriven.util;

public class Constants {
	//Browsers
	public static String BROWSER_FF="Mozilla";
	public static String BROWSER_CH="Chrome";
	// suiteNames
	public static String FIRST_SUITE="SuiteA";
	public static String SECOND_SUITE="SuiteB";
	public static String ROOT_SUITE="My Project";
	public static String LOGIN="Login";
	public static String SHOPPING_SUITE="FlipkartShopping";
	public static String ORDERS_SUITE="FlipkartOrders";
	
	//sheets
	public static String SUITE_SHEET="Suite";
	public static String TESTCASES_SHEET="TestCases";
	public static String DATA_SHEET="Data";

	//Col names
	public static String SUITENAME_COL="SuiteName";
	public static String RUNMODE_COL="Runmode";
	public static String TESTCASES_COL="TestCases";
	public static String USERNAME_COL="Username";
	public static String PASSWORD_COL="Password";
	
	// runmode
	public static String RUNMODE_YES="Y";
	public static String RUNMODE_NO="N";
	
	
	//pass--fail
	public static Boolean SUCCESS=true;
	
	public static String XLSFILELOCATION=System.getProperty("user.dir")+"\\RunConfiguration\\";
	public static String CHROMEDRIVER_EXE=System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe";
	public static String GECKODRIVER_EXE=System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe";
}

	

