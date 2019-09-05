package StepDefinations;


import cucumber.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.sonatype.plexus.components.cipher.Base64;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.lu.wann;
import PostNord.SharepointApp.MTU.RunTest;
import PostNord.SharepointApp.MTU.util.WebConnector;


public class MTUStepdefs {
	public static WebConnector selenium=WebConnector.getinstance();
	public Scenario scenario;
	public long Time;
	private String testId = "Screenshot"; 
	
	
	public MTUStepdefs()
	{
		
	}

	@Before
	 public void before(Scenario scenario) {
	 this.scenario = scenario;
	 System.out.println("BEFORE EVERY FEATURE IS RUN...");
	 
	 }
	
	@After
	 public void embedScreenshot(Scenario scenario) {
	 if (scenario.isFailed()) {
	 try {
		 
		 //For email
		
		 File src= ((TakesScreenshot)selenium.w). getScreenshotAs(OutputType. FILE); 
			// now copy the screenshot to desired location using copyFile method.
			 Time=System. currentTimeMillis();
			 String Scrpath=System.getProperty("user.dir")+"\\TestAutomationReports\\"+RunTest.CurrentFolderName+"\\Screenshot_"+Time+".png";
			FileUtils. copyFile(src, new File(Scrpath));
			
	 
			//for embedding in report
	byte[] screenshot = ((TakesScreenshot) selenium.w)
	 .getScreenshotAs(OutputType.BYTES);
	

	 scenario.write("Refer screenshot for failed scenario :Current extracted folder\\TestAutomationReports\\"+RunTest.CurrentFolderName+"\\Screenshot_"+Time+".png");
	 System.out.println("RunTest.CurrentFolderName:"+RunTest.CurrentFolderName);
		System.out.println("Refer screenshot for failed step:-"+Scrpath);
			
	 } 
		 catch (Exception e) {
	 e.printStackTrace();
		 }}
	 
	 }
	
	private String generateFileName(String ofType) { 
		// create a date
		Date date = new Date(20);
		 //  long diff = date.getTime();
        return testId.concat("_").concat(String.valueOf(date.getTime())) 
                .concat(ofType).concat(".png"); 
    }
	
	public byte[] FiletoByteConvert(String Filepath)
	{
		File file = new File("/temp/abc.txt");
		//init array with file length
		byte[] bytesArray = new byte[(int) file.length()];
try{
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray); //read file into bytes[]
		fis.close();
}
catch(Exception e)
{
	System.out.println("error in convertion");
}
		return bytesArray;
	}

@Given("^I go to \"([^\"]*)\" on \"([^\"]*)\"$")//I go to "http://salesforce.com" on "Mozilla"
public void I_go_to(String url,String browser)
{try{
	System.out.println("Loading Following url:"+url+"---on Browser:"+browser);
	selenium.LaunchBrowser(browser);
	selenium.gotourl(url);
	
	
}
catch(Exception e)
{
	System.out.println("call to gotourl from selenium class"+e.getStackTrace());
}
	
}


@And ("^Type to console n log the feature executed$")
public void Type_to_console_n_log_the_feature_executed(DataTable usercredentials) throws Throwable {
	List<List<String>> data = usercredentials.raw();
	System.out.println("=========================================================================================");
	System.out.println("Feature name:"+data.get(0).get(0));
	System.out.println("Application name :"+data.get(0).get(1));
	System.out.println("Status of feature executed:"+data.get(0).get(2));
	selenium.log("=============================================================================================");
	
	selenium.log("Feature name:"+data.get(0).get(0));
	selenium.log("Application name:"+data.get(0).get(1));
	selenium.log("Status of feature executed:"+data.get(0).get(2));
	selenium.log("=============================================================================================");
	System.out.println("=========================================================================================");
	
}
@And("^i enter \"(.*?)\" as \"(.*?)\"$")//i enter username as "chaitra@gmail.com"
public void I_Enter(String obj,String data)
{
	System.out.println("entered username/pwd"+obj+"data:"+data);
	selenium.settext(obj,data);
}

@And("^i get location of \"([^\"]*)\" Element$")//i click on "Login" button
public void I_getlocation(String object) throws InterruptedException
{   selenium.getlocation(object);
	
}
@And("^i scroll till \"([^\"]*)\" Element$") 
public void I_scroll_till_Element(String object) throws InterruptedException
{
	
	selenium.scrolldowntoaElement(object);
}

@And("^i click on \"(.*?)\" Element out of \"(.*?)\"$")
public void I_click_SearchResults(String data,String object)
{
	
	selenium.ClickRespectiveSearchResult(data,object);
	System.out.println("clicked Searchresult:"+data);
}




@And("^i wait for \"([^\"]*)\" seconds$")
public void I_wait(int Time)
{
	
	selenium.implicitwait(Time);
}
@And("^i click on \"(.*?)\" Element$")
public void i_click_on_Element(String arg1) throws Throwable {
    selenium.click(arg1);
}

@And("^i wait till page is loaded$")
public void pageLoadWait() throws InterruptedException
{
	
	selenium.pageLoadWait(30000);
}


@And("^i wait till Element is loaded$")
public void BrowserSleep() throws InterruptedException
{
	
	selenium.ThreadSleep();
}


@And("^i quit browser instance$")
public void quit_browser()
{
	selenium.quitbrowser();
}

@Then("^\"(.*?)\" should be present for scenario to be \"(.*?)\"$")
public void should_be_present_for_scenario_to_be(String obj,String ExpectedResult){
	
	Boolean result=selenium.isElementPresent(obj);
	String actualResult=null;
	if(result)
		actualResult="PASS";
	else
		actualResult="FAIL";
	
	System.out.println("result is :"+actualResult);
	
	selenium.AssertTestResult(actualResult, ExpectedResult);
	
}
}
