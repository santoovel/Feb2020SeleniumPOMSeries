package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop= basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
	//	System.out.println("Driver Home Page Title is: ===>"+ driver.getTitle());
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page Title is:===> "+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home Page title is not found....");
	}
	
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("Home page Header is ==> "+ header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserTest() {
		String accountName = homePage.getAccountName();
		System.out.println("Logged In Account name is ====> "+ accountName );
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
		
	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
