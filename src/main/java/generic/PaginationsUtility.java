package generic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
public class PaginationsUtility {


    public static boolean clickNextPagination(WebDriver driver) {
        try {
            WebElement nextBtn = driver.findElement(By.xpath("//li[contains(@class,'page-item next') and not(contains(@class,'disabled'))]/a"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Pagination complete or Next button not found.");
            return false;
        }
    }
    
    public static void clickAllNumberedPages(WebDriver driver) {
        try {
            List<WebElement> pages = driver.findElements(By.xpath("//li[contains(@class,'page-item')]//a"));
            
            for (int i = 0; i < pages.size(); i++) {
                List<WebElement> refreshedPages = driver.findElements(By.xpath("//li[contains(@class,'page-item') ]//a"));
                WebElement page = refreshedPages.get(i);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", page);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", page);
                System.out.println("Clicked on page: " + page.getText());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Could not click all numbered pages: " + e.getMessage());
        }
    }
}

