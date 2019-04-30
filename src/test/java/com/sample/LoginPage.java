package com.sample;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginPage 
{
	WebDriver driver;

	Homepage homepage;
	@BeforeSuite
	public void start() throws MalformedURLException
	{

		String username = System.getenv("BROWSERSTACK_USER");
		String accessKey = System.getenv("BROWSERSTACK_ACCESSKEY");
		String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
		String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("browser", "chrome");
		capabilities.setCapability("browserstack.local", browserstackLocal);
		capabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
		driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capabilities);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("http://pcsv2dev.azurewebsites.net/#/");

	}
	@Test(priority=2)
	public void Login() throws Exception 
	{
		homepage=new Homepage(driver);
		homepage.Verify_Login();

		Assert.assertEquals(driver.getCurrentUrl(), "http://pcsv2dev.azurewebsi");

	}

	@Test(priority=1)
	public void Url() 
	{
		homepage=new Homepage(driver);
		System.out.println(homepage.Verify_Url());

		Assert.assertEquals(homepage.Verify_Url(), "http://pcsv2dev.azurewebsites.net/#/");
	}

	@Test(priority=3)
	public void Title() 
	{
		homepage=new Homepage(driver);
		System.out.println(homepage.Verify_Title());

		Assert.assertEquals(homepage.Verify_Title(), "Paradm");
	}

	@Test(priority=4)
	public void Logout() throws Exception 
	{
		homepage=new Homepage(driver);
		homepage.Verify_Login();

		System.out.println("Successfully Login");
		homepage.Verify_Logout();
		System.out.println("Successfully Logout");

		Assert.assertEquals(driver.getCurrentUrl(), "http://pcsv2dev.azurewebsites.net/#/");
	}

	@AfterMethod
	public void Close() throws Exception 
	{
		Thread.sleep(3000);

		driver.close();
	}

}
