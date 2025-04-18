package Pomclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage1 {
	
	public WebDriver driver;
	
	
	 public BasePage1(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); // Initialize the WebElements
	        
	    }

}
