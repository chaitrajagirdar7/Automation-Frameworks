package PostNord.SharepointApp.MTU;
import java.io.File;
import StepDefinations.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.ExtentCucumberFormatter;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.NetworkMode;

import PostNord.SharepointApp.MTU.MailReport.SendMail;
import PostNord.SharepointApp.MTU.util.WebConnector;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.it.Date;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"com.cucumber.listener.ExtentCucumberFormatter"},tags = {"@BestallPostbox,@Bestallhamtning,@Bestalltillfallig_hamtning,@BestallUtkorning"},features = "src/test/resources/PostNord/SharepointApp/MTU/MTUScenarios",glue={"StepDefinations"})
public class RunTest {
	
	public Scenario scenario;
	static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	static Calendar calobj = Calendar.getInstance();
	static String Timestamp=df.format(calobj.getTime()).toString().replaceAll("[/:]", "_");
	public static String FolderName="Reports_"+Timestamp;
	public static String CurrentFolderName;
	public static String OUTPUT_ZIP_FILE = null;
	public static String SOURCE_FOLDER=null;

	
	
	static String ReportFileName="AutomationReport"+Timestamp+".html";
	
	
	/*
	 * @AfterClass public static void SendMailToStakeholders() throws Exception{
	 * 
	 * //Close browser instance MTUStepdefs.selenium.quitbrowser();
	 * System.out.println("=======================================");
	 * System.out.println("ReportFileName:"+ReportFileName);
	 * 
	 * //To send Reports & screenshots as compressed attachment to stakeholders
	 * SOURCE_FOLDER=System.getProperty("user.dir")+"\\TestAutomationReports\\"+
	 * CurrentFolderName; OUTPUT_ZIP_FILE=System.getProperty("user.dir")+
	 * "\\TestAutomationReports\\"+CurrentFolderName+"\\TestAutomationReport.zip";
	 * System.out.println("SOURCE_FOLDER:"+SOURCE_FOLDER);
	 * 
	 * SendMail.execute("TestAutomationReport.zip");
	 * 
	 * 
	 * 
	 * }
	 */

    @BeforeClass
    public static void setup() throws Throwable {
        // Initiates the extent report and generates the output in the output/Run_<unique timestamp>/report.html file by default.
    	String CurrentReportFolder=FolderName;
    	CurrentFolderName=CurrentReportFolder;
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(new File(System.getProperty("user.dir")+"//TestAutomationReports//"+CurrentReportFolder+"//"+ReportFileName));
        ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));

        // User can add the system information as follows
        ExtentCucumberFormatter.addSystemInfo("Browser Name", "Firefox");
        ExtentCucumberFormatter.addSystemInfo("Browser version", "v31.0");
        ExtentCucumberFormatter.addSystemInfo("Selenium version", "v2.53.0");

        // Also you can add system information using a hash map
        Map<String, String> systemInfo = new HashMap<String, String>();
        
        systemInfo.put("User Name", "Chaitra Jagirdar");
        systemInfo.put("Cucumber version", "v1.2.3");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.1");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);
        
        
     
    }
   
}