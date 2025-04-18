package Pomclass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import generic.SwitchWindow;

public class Login extends BasePage1 {

	public Login(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "/html/body/div[2]/div/nav/div/a[2]")
	private WebElement lgbtn1;

	@FindBy(xpath = "//*[@id=\"login-modal\"]/div/div/div[2]/div/div[1]/div/a/div")
	private WebElement lgbtn2;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[1]/div/input")

	private WebElement username;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[2]/div/div/input")
	private WebElement pwd;

	@FindBy(xpath = "//*[@id=\"indiviual_form\"]/div/div[3]/button")
	private WebElement lgbtn;
		
	@FindBy(xpath = "/html/body/div[3]/div/div/div[3]/button[2]")
	private WebElement loginpop;

	
	public WebElement getlgbtn1() {
		return lgbtn1;
	}

	public WebElement getlgbtn2() {
		return lgbtn2;
	}

	public WebElement getusername() {
		return username;
	}

	public WebElement getpwd() {
		return pwd;
	}

	public WebElement getlgbtn() {
		return lgbtn;
	}

	public WebElement getloginpop() {
		return loginpop;
	}

	public void login(String user, String pass) throws InterruptedException {
		

		lgbtn1.click(); // login button for home page first
		
		Thread.sleep(3000);
		lgbtn2.click(); // login button for home page second
		
		Thread.sleep(3000);

		// Window switch
		SwitchWindow.switchWindowByIndex(driver, 1);
		Thread.sleep(3000);
		
		username.sendKeys(user);
		
		pwd.clear();
		pwd.sendKeys(pass);
		lgbtn.click();
		
		Thread.sleep(5000);
		JavascriptExecutor js7= (JavascriptExecutor)driver;
		js7.executeScript("arguments[0].scrollIntoView(true);",loginpop );
		loginpop.click();
	
		

	}
}
