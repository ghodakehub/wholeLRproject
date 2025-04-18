package TestClass;

import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Pomclass.ActListPdfDownload;
import Pomclass.Login;
import Pomclass.PDFdownloadStateActList;
import UtilityClass.UtilityClass;
import generic.ConfingData_provider;
import generic.ForMultiplemailReceipent;
import generic.NewBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("ActList")
@Feature("Verify CentralActlist")
public class ActListPdfDwn extends NewBaseTest{
	
	@Test
	@Description("verify downlaod pdf button for central actlist")
	public void verifyActlistforCentralPdf() throws InterruptedException, IOException
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);
		
		 PDFdownloadStateActList act = new PDFdownloadStateActList (driver);
		 act.downloadRulesPDF(driver);
				
	
	}
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  UtilityClass.Capaturescreenshot(driver,result.getName() );
		
		
		
		String testUrl =driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "ActList For Central ",
           	    "Please check Actlist issue coming to downlaod pdf , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	Reporter.log("take screenshot succesfully");
	
	}


}
