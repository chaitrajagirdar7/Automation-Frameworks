package com.framework.dataDriven;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;

import com.framework.dataDriven.util.Constants;
import com.framework.dataDriven.util.Utility;
import com.framework.dataDriven.util.Xls_Reader;

public class TestBase {
	public static WebDriver driver = null;
	public static Properties prop;
	public Logger APPLICATION_LOGS;

	/*
	 * public void initLogs(Class<?> class1) { APPLICATION_LOGS =
	 * Logger.getLogger("devpinoyLogger"); APPLICATION_LOGS.setLevel(Level.DEBUG);
	 * 
	 * }
	 */

	public void initLogs(Class<?> class1) {

		FileAppender appender = new FileAppender(); // configure the appender here, with file location, etc
		appender.setFile(System.getProperty("user.dir") + "//target//reports//" + CustomListener.resultFolderName + "//"
				+ class1.getName() + ".log");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setAppend(false);
		appender.activateOptions();

		APPLICATION_LOGS = Logger.getLogger(class1);
		APPLICATION_LOGS.setLevel(Level.DEBUG);
		APPLICATION_LOGS.addAppender(appender);
	}

	public static void init() {
		if (prop == null) {
			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\project.properties";
			System.out.println("path:" + path);

			prop = new Properties();
			try {
				FileInputStream fs = new FileInputStream(path);
				prop.load(fs);
				System.out.println("==========");
			} catch (Exception e) {
				System.out.println("error occured in loading prop");
				e.printStackTrace();
			}
		}

	}

	public void validateRunmodes(String testName, String suiteName, String dataRunmode) {
		log("Validating runmode for " + testName + " in suite " + suiteName);
		// Sinit();
		// suite runmode
		boolean suiteRunmode = Utility.isSuiteRunnable(suiteName,
				new Xls_Reader(Constants.XLSFILELOCATION + "Suite.xlsx"));
		boolean testRunmode = Utility.isTestCaseRunnable(testName,
				new Xls_Reader(Constants.XLSFILELOCATION + suiteName + ".xlsx"));
		boolean dataSetRunmode = false;
		if (dataRunmode.equals(Constants.RUNMODE_YES))
			dataSetRunmode = true;

		if (!(suiteRunmode && testRunmode && dataSetRunmode)) {
			log("Skipping the test " + testName + " inside the suite " + suiteName);
			throw new SkipException("Skipping the test " + testName + " inside the suite " + suiteName);
		}

	}

	/********************* libraries *******************************/

	public void openBrowser(String bType) {

		if (bType.equals("Mozilla")) {

			driver = loadFF();
		} else if (bType.equals("Chrome")) {
			driver = loadChrome();

		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log("navigate :" + bType);
	}

	public FirefoxOptions configureFF() {

		System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVER_EXE);
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("dom.webnotifications.enabled", false);
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		return options;
	}

	public WebDriver loadFF() {
		driver = new FirefoxDriver(configureFF());
		return driver;
	}

	public WebDriver loadChrome() {
		System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_EXE);
		driver = new ChromeDriver();
		return driver;
	}

	public void quitBrowser() {
		log("quitBrowser :");
		driver.quit();

	}

	public void navigate(String urlKey) {
		System.out.println("url:" + prop.getProperty(urlKey));
		driver.get(prop.getProperty(urlKey));
		log("navigate :" + urlKey);

	}

	public void wait(int time) {
		try {
			log("wait :" + time);
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void click(String locatorKey) {
		getElement(locatorKey).click();
		log("click :" + locatorKey);
	}

	public String getText(String locatorKey) {
		log("getText :" + locatorKey);
		return getElement(locatorKey).getText();

	}

	public void moveToElementClick(String locatorKey, String toClick) {
		log("moveToElementClick :" + locatorKey);
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locatorKey)).build().perform();
		act.click(getElement(toClick)).build().perform();
	}

	public boolean ExpectedResult(String result1) {
		if (result1.contentEquals("SUCCESS") || result1.contentEquals("Y"))
			return true;
		else
			return false;
	}

	public void type(String locatorKey, String data) {
		getElement(locatorKey).sendKeys(data);
		log(" data Entery :" + locatorKey);
	}

	public void keyin(String locatorKey, String data) {
		log("Enter Keys :" + data);
		if (data.contentEquals("ENTER"))
			getElement(locatorKey).sendKeys(Keys.ENTER);

	}

	public void selectfromDropdown(String locatorKey, String value) {
		log("selecting frm drpdwn :" + locatorKey);
		Select options = new Select(getElement(locatorKey));
		options.selectByValue(value);
	}

	public int stringToInteger(String locatorKey) {
		int value = 0;
		try {
			log("convert string to integer:" + locatorKey);
			System.out.println("gettext" + getElement("ResultPrice_xpath").getText());
			String tosplit = getElement("ResultPrice_xpath").getText();
			value = Integer.parseInt(tosplit.substring(1));
			System.out.println("value" + value);

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}

	public void checkbox(String locatorKey) {
		log("Checkbox :" + locatorKey);
		click(locatorKey);
	}

	public void switchFrame() {
		log("Switching frame :");
		List<WebElement> frames = new ArrayList<WebElement>();
		frames = driver.findElements(By.tagName("iframe"));
		System.out.println("frames.size():" + frames.size());
		for (int f = 0; f < frames.size(); f++) {
			// driver.switchTo().frame(0)
		}

		// logger.log(LogStatus.PASS, "Enter data into element :"+locatorKey, "Data
		// entry Successful");
	}

	// finding element and returning it
	public WebElement getElement(String locatorKey) {
		WebElement e = null;
		try {
			log("getting Element :" + locatorKey);
			if (locatorKey.endsWith("_id"))
				e = driver.findElement(By.id(prop.getProperty(locatorKey)));
			else if (locatorKey.endsWith("_name"))
				e = driver.findElement(By.name(prop.getProperty(locatorKey)));
			else if (locatorKey.endsWith("_xpath")) {
				System.out.println("username:" + prop.getProperty(locatorKey));
				wait(2000);
				e = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			} else {
				reportFailure("Locator not correct - " + locatorKey);
				Assert.fail("Locator not correct - " + locatorKey);
			}

		} catch (Exception ex) {
			// fail the test and report the error
			reportFailure(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test - " + ex.getMessage());
		}
		return e;
	}

	/*********************** Validations ***************************/
	public boolean verifyTitle() {
		return false;
	}

	public boolean isElementPresent(String locatorKey) {
		List<WebElement> elementList = null;
		if (locatorKey.endsWith("_id"))
			elementList = driver.findElements(By.id(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_name"))
			elementList = driver.findElements(By.name(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_xpath"))
			elementList = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		else {
			reportFailure("Locator not correct - " + locatorKey);
			Assert.fail("Locator not correct - " + locatorKey);
		}
		log("IsElement Present? :" + locatorKey);
		if (elementList.size() == 0)
			return false;
		else
			return true;
	}

	public boolean isElementPresent(String locatorKey, String Value) {
		List<WebElement> elementList = null;
		if (locatorKey.endsWith("_xpath"))
			elementList = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_id"))
			elementList = driver.findElements(By.id(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_name"))
			elementList = driver.findElements(By.name(prop.getProperty(locatorKey)));

		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i).getText().contentEquals(Value)) {
				System.out.println("______"+elementList.get(i).getText());
				System.out.println("______"+Value);
				return true;
			}
		}
		return false;
	}

	public void implicitwait(int Time) {
		//
		driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
		System.out.println("waiting for :" + Time + "secs");
		log("-----------ImplicitWait- for " + Time);
	}

	public boolean verifyText(String locatorKey, String expectedTextKey) {
		String actualText = getElement(locatorKey).getText().trim();
		String expectedText = prop.getProperty(expectedTextKey);
		log("------------------------Verifying test-------------------------------");
		if (actualText.equals(expectedText))
			return true;
		else
			return false;

	}

	public void AssertTestResult(String ActualResult, String ExpectedResult) {

		implicitwait(30);
		log("------------------------Asserting Test Result----------------------------------");
		try {
			assertEquals("TestScenario Execution: FAIL", ExpectedResult, ActualResult);
			// logger.log(LogStatus.PASS, "Asserting testresult :", "Assert Successful");
			// passTest();
		} catch (Exception e) {
			// logger.log(LogStatus.FAIL, "Asserting testresult :", "Assert Failed");
			// failTest();
		}
	}

	public void AssertTestResult(Boolean actual, Boolean expected) {

		implicitwait(30);
		log("------------------------Asserting Test Result----------------------------------");
		try {
			Assert.assertEquals(actual, expected);

			// passTest();
		} catch (Exception e) {
			// failTest();
		}
	}

	/***************************** Reporting ********************************/

	public void reportPass(String msg) {
		// logger.log(LogStatus.PASS, msg);
	}

	public void reportFailure(String msg) {
		// log(LogStatus.FAIL, msg);
		takeScreenShot();
		Assert.fail(msg);
	}

	public void takeScreenShot() {
		// fileName of the screenshot
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//screenshots//" + screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// put screenshot file in reports
		/*
		 * logger.log(LogStatus.INFO, "Screenshot-> " +
		 * logger.addScreenCapture(System.getProperty("user.dir") + "//screenshots//" +
		 * screenshotFile));
		 */
	}

	/************** Logging ***************/
	public void log(String msg) {

		APPLICATION_LOGS.debug(msg);
	}

	/******** custom methods *******/
	public void flipkartLogin() {
		openBrowser(prop.getProperty("Browser"));
		navigate("Flipkart_url");
		wait(5000);
		wait(2000);
		type("username_xpath", "chaitra.jagirdar7@gmail.com");
		type("pwd_xpath", "Chaitra@7");
		click("login_xpath");
		wait(1000);
		AssertTestResult(isElementPresent("userName_xpath"), true);
		log("Loggedinto flipkart account succesfully");
	}
}