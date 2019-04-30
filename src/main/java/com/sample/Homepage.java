package com.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage 
{
	WebDriver driver;
	
	@FindBy(id="email")
	private WebElement Email;

	@FindBy(id="password")
	private WebElement Password;
	
	@FindBy(xpath="//*[@id='main-wrapper']/app-body/div/ng-component/div/div/div/div[2]/div/form/div[5]/button")
	private WebElement Login_Btn;
	
	@FindBy(xpath="//*[@id='main-wrapper']/app-header/header/nav/div[2]/ul[2]/li")
	private WebElement MoveToLogout_Btn;
	
	@FindBy(xpath="//*[@id='main-wrapper']/app-header/header/nav/div[2]/ul[2]/li/div/ul/li[3]/a")
	private WebElement Logout_Btn;
	
	public Homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Verify_Login() throws Exception
	{
		Email.sendKeys("superadmin@paradigmclaims.com");
		Password.sendKeys("paradigm@123");
		Login_Btn.click();
		Thread.sleep(5000);
	}
	
	public String Verify_Title()
	{
		return driver.getTitle();
	}
	
	public String Verify_Url()
	{
		return driver.getCurrentUrl();
		
	}
	
	public void Verify_Logout() throws Exception
	{
		Actions act=new Actions(driver);
		act.moveToElement(MoveToLogout_Btn).click().build().perform();
		
		Thread.sleep(3000);
		Logout_Btn.click();
		
		
		Thread.sleep(3000);
		driver.navigate().refresh();
	}
	
}
