package TestClass;
import java.io.IOException;
import org.testng.annotations.Test;
import Pomclass.Login;
import generic.ConfingData_provider;
import generic.NewBaseTest;
import generic.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
@Epic("Login Page")
@Feature("Verify Login by Individual")
public class LoginTest extends NewBaseTest {
	

	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description(
		    "Verify that a user can successfully log in using a valid Individual ID:\n" +
		    "- Enter a valid, username, and password.\n" +
		    "- Click the login button.\n" +
		    "- Confirm that the user lands on the dashboard or home page.\n" 
		    
		)
	public void verifyLoginforIndividual() throws InterruptedException, IOException
	
	{
		
		 Login log= new Login(driver);
		
		 log.login(ConfingData_provider.Email,ConfingData_provider.Password);

	
	}
	

}
