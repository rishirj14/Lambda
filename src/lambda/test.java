package lambda;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class test {

	public String username = "rishirj1295";
	public String accesskey = "bqWa9isCBEXAlMkHFRm7Ys034aAinsUx0ywidJJsBX9zNAXZqm";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@BeforeTest
	@Parameters(value={"browser"})
	public void setUp(String browser) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "Cross1");
		capabilities.setCapability("name", "Demo");
		
		if (browser.equalsIgnoreCase("Internet Explorer"))
		{
			capabilities.setCapability("platform", "Windows 10");
			capabilities.setCapability("browserName", "Internet Explorer");
			capabilities.setCapability("version","11.0");
			capabilities.setCapability("ie.compatibility",11001);

		}

		else if(browser.equalsIgnoreCase("Chrome"))
		{
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "One Plus 6T");
			capabilities.setCapability("platformVersion","9");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("version","83.0");
		}
		else if(browser.equalsIgnoreCase("Safari"))
		{
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "iPad (6th generation)");
			capabilities.setCapability("platformVersion","13.1");
			capabilities.setCapability("browserName", "Safari");
			capabilities.setCapability("version","13.0");
		}

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSimple() throws Exception {

		driver.get("https://accounts.lambdatest.com/login");
		driver.findElement(By.xpath("//*[@type=\"email\"]")).sendKeys("rishirj1295@gmail.com");
		driver.findElement(By.xpath("//*[@type=\"password\"]")).sendKeys("Test#123");
		driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
	}

}
