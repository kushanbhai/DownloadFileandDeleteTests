package Tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DownloadFileConcept {

	WebDriver driver;
	File folder;

	@BeforeMethod
	public void setup() {
		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();

		// chrome:
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Kushan\\Desktop\\Desktop\\Selenium Setup\\SeleniumDrivers\\ChromeDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();

		prefs.put("profile.default_content.settings.popups", 0);
		prefs.put("download.default_directory", folder.getAbsolutePath());
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(cap);
		driver.manage().window().maximize();
	}

	@Test
	public void downloadFileTest() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/download");
		driver.findElement(By.linkText("some-file.txt")).click();
		
		Thread.sleep(2000);
		File[] listFiles = folder.listFiles();
		System.out.println(listFiles.length);
		Assert.assertTrue(listFiles.length>0);
		
		for(File file: listFiles) {
			//Assert.assertEquals(file.length(), is(not((long)0)));
			Assert.assertTrue(file.length() >0);
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		for (File file : folder.listFiles()) {
			file.delete();
		}

		folder.delete();
	}
}
