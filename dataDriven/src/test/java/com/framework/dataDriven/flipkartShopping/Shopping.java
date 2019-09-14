package com.framework.dataDriven.flipkartShopping;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.poi.xssf.model.Table;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.dataDriven.TestBase;
import com.framework.dataDriven.util.Constants;
import com.framework.dataDriven.util.TestDataProvider;

import java.util.Hashtable;

public class Shopping extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
		


	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "flipkartShoppingDataProvider")
	public void shopping(Hashtable<String, String> table) {
		validateRunmodes("Shopping", Constants.SHOPPING_SUITE, table.get("Runmode"));
		log("Executing test: Shopping");
		flipkartLogin();
		wait(5000);
		type("searchProducts_xpath", table.get("Product"));
		wait(1000);
		keyin("searchProducts_xpath", "ENTER");
		wait(5000);
		selectfromDropdown("setMinPrice_xpath",table.get("MinPrice"));
		wait(8000);
		AssertTestResult(isElementPresent("Result_xpath"),ExpectedResult(table.get("Result_Present")));
		int price=stringToInteger("ResultPrice_xpath");
		AssertTestResult(price<=Integer.parseInt(table.get("MinPrice")), ExpectedResult(table.get("Expected_Result")));
		

	}
	
	

	@AfterTest
	public void quitbrowser() {
		quitBrowser();
	}

}
