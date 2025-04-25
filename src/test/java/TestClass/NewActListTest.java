package TestClass;


import org.testng.annotations.Test;
import Pomclass.Login;
import Pomclass.NewActList;
import generic.ConfingData_provider;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("ActList")
@Feature("Verify NewActlist")
public class NewActListTest extends NewBaseTest{
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifynewnote() throws Exception
	
	{
		Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

		NewActList list= new NewActList (driver);
		 list.clicactlist();

	      list.validateAllTabs();
	     
	
	}
	
}