package AutomationFrameworks.testcases.supportsuite;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import AutomationFrameworks.base.BaseTest;
import AutomationFrameworks.util.Constants;
import AutomationFrameworks.util.DataUtil;


public class SupportTest extends BaseTest{

	
	
	  @Test(dataProvider="getData",priority=1) public void
	  loginTest(Hashtable<String,String> data) throws Exception{
	  test.log(Status.INFO, "Starting "+ testName);
	  
	  if(DataUtil.isSkip(testName, xls)
	  ||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
	  test.log(Status.SKIP, "Runmode is set to NO"); throw new
	  SkipException("Runmode is set to NO"); } ds.executeKeywords(testName, xls,
	  data);
	  
	  }
	 
	 
	
	@Test(dataProvider="getData",dependsOnMethods={"loginTest"},priority=2)
			public void navigateTest(Hashtable<String,String> data) throws Exception{
		test.log(Status.INFO, "Starting "+ testName);

		if(DataUtil.isSkip(testName, xls) ||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP, "Runmode is set to NO");
			throw new SkipException("Runmode is set to NO");
		}		
	    ds.executeKeywords(testName, xls, data);

	}
}
