package com.framework.dataDriven.util;


import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.framework.dataDriven.TestBase;

public class TestDataProvider {
	
	@DataProvider(name="suiteADataProvider")
	public static Object[][] getDataSuiteA(Method m){
		TestBase.init();
		System.out.println(Constants.XLSFILELOCATION);
		Xls_Reader xls1 = new Xls_Reader(Constants.XLSFILELOCATION+Constants.FIRST_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}
	
	@DataProvider(name="suiteBDataProvider")
	public static Object[][] getDataSuiteB(Method m){
		TestBase.init();
		Xls_Reader xls1 = new Xls_Reader(Constants.XLSFILELOCATION+Constants.SECOND_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}
	
	@DataProvider(name="flipkartShoppingDataProvider")
	public static Object[][] getDataFlipkartShopping(Method m){
		TestBase.init();
		Xls_Reader xls1 = new Xls_Reader(Constants.XLSFILELOCATION+Constants.SHOPPING_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}
	@DataProvider(name="flipkartOrdersDataProvider")
	public static Object[][] getDataFlipkartOrders(Method m){
		TestBase.init();
		Xls_Reader xls1 = new Xls_Reader(Constants.XLSFILELOCATION+Constants.ORDERS_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}

}
