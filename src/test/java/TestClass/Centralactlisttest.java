package TestClass;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.annotations.Test;
import Pomclass.CentralActList;
import Pomclass.Login;
import generic.ConfingData_provider;
import generic.EmailUtility;
import generic.NewBaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify CentralActlist")
public class Centralactlisttest extends NewBaseTest {


	
	@Test
	@Description("Verify all Type of central Links and paginations")
	public void Actlistverify() throws InterruptedException, IOException, MessagingException
	
	{
		 Allure.step("Login with by Valid credentails");
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		 Allure.step("Click on Central actlsit and central actlist tab");
		 CentralActList central= new CentralActList(driver);
		 central.checkAllActLinks();
		 Allure.step("Click on all links and paginations");
		 
		 List<String> brokenUrls = central.checkAllActLinks();
		 if (!brokenUrls.isEmpty()) {
		     String subject = "CentralActlist";
             String message = "Please check 404 Broken Act Links Found on CentralActlist";
             EmailUtility.sendSummaryEmailWithScreenshots(driver, subject, message, generic.Library.errorUrls, generic.Library.screenshotBytesList);
         } 
                else{
                	System.out.println();
                }
		 }
		           
	}
	
	
	


