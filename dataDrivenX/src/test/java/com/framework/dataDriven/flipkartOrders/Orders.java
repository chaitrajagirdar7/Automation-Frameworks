package com.framework.dataDriven.flipkartOrders;

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

public class Orders extends TestBase {

	@BeforeTest
	public void initLogs() {
		initLogs(this.getClass());
	}

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "flipkartOrdersDataProvider")
	public void orders(Hashtable<String, String> table) {
		validateRunmodes("Orders", Constants.ORDERS_SUITE, table.get("Runmode"));
		log("Executing test: Orders");
		flipkartLogin();
		wait(5000);
		moveToElementClick("Account_xpath", "orders_xpath");
		wait(5000);
		AssertTestResult(isElementPresent("orderNum_xpath",table.get("OrderNumber")), ExpectedResult(table.get("Result_Present")));
		
	}

	@AfterTest
	public void quitbrowser() {
		quitBrowser();
	}

}
