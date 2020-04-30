package com.qa.hubspot.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.TimeUtil;
public class LoginPage extends BasePage {
	
	//local driver
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. By locators:
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	By showPasswordLink = By.xpath("//span[text()='Show Password']");
	By forgotPwdLink = By.linkText("Forgot my password");
	By loginSSOButton = By.xpath("//button[@id='ssoBtn']");
	
	
	
	//2. constructor of the page class we have to create
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page actions and methods we have to create
	// in page classes we don't verify anything
	// Never write assertion in Page class
	
	public String getLoginPageTitle() {
	//	return driver.getTitle();
		 elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE,10);
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink() {
		// return driver.findElement(signUpLink).isDisplayed();
		
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public boolean checkShowPasswordLink() {
		return driver.findElement(showPasswordLink).isDisplayed();
	}
	
	public boolean checkForgotPasswordLink() {
		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	public boolean checkloginSSOButton() {
		return driver.findElement(loginSSOButton).isEnabled();
	}
	
	
	public HomePage doLogin(String un, String pwd) {
	//	driver.findElement(username).sendKeys(un);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginButton).click();
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		TimeUtil.mediumWait();
		//driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		/*
		 * try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// as we are landing on HomePage that page  method should be returned 
		return new HomePage(driver);
	}
	

}
