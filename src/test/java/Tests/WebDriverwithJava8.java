package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverwithJava8 {

	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Kushan\\Desktop\\Desktop\\Selenium Setup\\SeleniumDrivers\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		List<WebElement> linkslist = driver.findElements(By.tagName("a"));
		System.out.println(linkslist.size());
		
		for(WebElement ele: linkslist) {
			System.out.println(ele.getText());
		}
		
		driver.quit();
	}
	
	
}
