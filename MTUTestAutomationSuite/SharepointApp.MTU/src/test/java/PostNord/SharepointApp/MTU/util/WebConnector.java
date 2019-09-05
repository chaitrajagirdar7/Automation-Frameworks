package PostNord.SharepointApp.MTU.util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import cucumber.api.Scenario;

import static org.junit.Assert.*;

public class WebConnector {
	public Properties or=null;
public Properties config=null;
public FileInputStream fs=null;
public String doll;
public WebDriver w=null;
public WebDriver mozilla=null;
public static WebConnector Lib=null;
public Logger APPLICATION_LOGS;

private WebConnector()
{
	try{
		
		APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
		
if(or==null){
			or=new Properties();
			fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\PostNord\\SharepointApp\\MTU\\config\\or.properties");
						or.load(fs);
		    
		   config=new Properties();
			fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\PostNord\\SharepointApp\\MTU\\config\\"+or.getProperty("Testenv")+"_config.properties");
			config.load(fs);
			
			log("Initializing all webConnector class variables");
				
     }}
		
		
		catch(Exception e){
			//System.out.println(e.getStackTrace());
		}
	
}
	
	public static WebConnector  getinstance()
	{
		
		if(Lib==null)
		{
			Lib=new WebConnector();
			}
		return Lib;
	}
	/*
	 * public void LaunchBrowser(String Browser) {
	 * 
	 * DesiredCapabilities dc = new DesiredCapabilities();
	 * dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
	 * UnexpectedAlertBehaviour.IGNORE); if(Browser.equals("Mozilla") &&
	 * mozilla==null){ w= new FirefoxDriver(dc);//dc-purposefully removed mozilla=w;
	 * }else if(Browser.equals("Mozilla") && mozilla!=null){ w=mozilla; }
	 * w.manage().window().maximize();
	 * log("Launching Mozilla browser/execute in opened instance"); }
	 */
	
	public void LaunchBrowser(String Browser)
	{
		
		if (Browser.equals("Chrome")) {
			System.out.println("Constants.CHROMEDRIVER_EXE:"+Constants.CHROMEDRIVER_EXE);
						System.setProperty("webdriver.chrome.driver",
								Constants.CHROMEDRIVER_EXE);
						// System.setProperty("webdriver.chrome.driver",
						// prop.getProperty("chromedriver_exe"));
						w = new ChromeDriver();
				
					w.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					w.manage().window().maximize();
					log("Launching Mozilla browser/execute in opened instance");
	}}
	
	/**************Logging***************/
	public void log(String msg){
		 
				 APPLICATION_LOGS.debug(msg);
	}
	
	/*Extent reports*/
	public void tearDown(Scenario scenario) {
	    	            final byte[] screenshot = ((TakesScreenshot) w)
	                        .getScreenshotAs(OutputType.BYTES);
	            scenario.embed(screenshot, "image/png"); //stick it in the report
	    
	}
	public void gotourl(String url)
	{
		w.get(config.getProperty(url));
		implicitwait(5);
		log("Naviating to "+config.getProperty(url));
	}
	
	public String waitForElement(String item) {
	    WebDriverWait wait = new WebDriverWait(w,30);
	    WebElement element = wait.until(
	                        ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty(item))));
	    return item;
	}
	public void implicitwait(int Time){
		//
		w.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
		System.out.println("waiting for :"+Time+"secs");
		log("waiting for:"+Time);
	}
	
	public void ThreadSleep() throws InterruptedException{
		Thread.sleep(3000);
		System.out.println("Thread sleep :3000ms");
		log("Thread sleep :3000ms");
	}
	
	public void pageLoadWait(int Time){
		//
		
		w.manage().timeouts().pageLoadTimeout(Time, TimeUnit.SECONDS);
		System.out.println("waiting for :"+Time+"secs");
		log("waiting for:"+Time);
	}
	public void click(String object)
	{

		implicitwait(30);
		 Alert alert;
		try
		{   waitForElement(object);
			w.findElement(By.xpath(or.getProperty(object))).click();
		}catch(UnhandledAlertException f) {
		    try {
		        alert = w.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println("Alert data: " + alertText);
		        alert.accept();
		    } catch (NoAlertPresentException e) {
		        e.printStackTrace();
		        
		        
		    }
		    catch (NoSuchElementException e) {
		        e.printStackTrace();
		        tearDown((Scenario) this);
		        
		        
		    }
		}
		log("Clicking:"+object);
	}
	
	public void settext(String object,String data){
		implicitwait(30);
		
		w.findElement(By.xpath(or.getProperty(object))).sendKeys(data);
		//implicitwait(30);
		log("Setting obj:"+object+"data"+data);
	}
	
	public boolean isElementPresent(String object){
		implicitwait(30);
		//
		ArrayList<WebElement> Li=new ArrayList<WebElement>();
		
		Li=(ArrayList<WebElement>) w.findElements(By.xpath(or.getProperty(object)));
		log("Evaluating element's presence:");
		if(Li.size()>0)
		{
		return true;}
		else
		return false;
	}
	
	public void AssertTestResult(String ActualResult,String ExpectedResult){
		//
		implicitwait(30);
		log("------------------------Asserting Test Result----------------------------------");
		assertEquals("TestScenario Execution: FAIL", ExpectedResult, ActualResult);
	}
		public void scrolldowntoaElement(String xpathExpression) throws InterruptedException
		{
			//
			
			System.out.println("xpathExpression:"+xpathExpression);
			WebElement element=w.findElement(By.xpath(or.getProperty(xpathExpression)));
			System.out.println("size"+element.getSize());
			System.out.println("isEnabled()"+element.isEnabled());
			System.out.println("isEnabled()"+element.isDisplayed());
			JavascriptExecutor jse = (JavascriptExecutor)w;
			jse.executeScript("arguments[0].scrollIntoView();", element);
			log("Scrolling down to element:"+xpathExpression);
		}
		public void getlocation(String xpathExpression) throws InterruptedException
		{
			//
			WebElement element = w.findElement(By.xpath(xpathExpression));
			System.out.println(element.getLocation());
			log("Getting location of :"+xpathExpression);
		}
	public void quitbrowser()
	{
		//
		w.quit();
		log("Quitting browser instance");
		
	}
	//Application specific method-MTUScenario1
	public void ClickRespectiveSearchResult(String data,String object){
		//
		implicitwait(30);
		ArrayList<WebElement> Li=new ArrayList<WebElement>();
		
		Li=(ArrayList<WebElement>) w.findElements(By.xpath(or.getProperty(object)));
		
		if(Li.size()>0)
		{
			for(int i=1;i<Li.size();i++){
				WebElement SearchResults=w.findElement(By.xpath(or.getProperty(object)+"["+i+"]/span[1]"));
				System.out.println("SearchResults:"+SearchResults.getText());
				System.out.println("data:"+data);
				if(SearchResults.getText().trim().contains(data))
						{System.out.println("match found");
						SearchResults.click();
						 break;
						}
			}
						}
		else System.out.println("size is 0");
		log("Clicking data"+data+"outof:"+object+"search results");
		}
		
	}

