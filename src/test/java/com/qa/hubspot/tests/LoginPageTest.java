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


//test will not extend basepage have to create reference

	//dl.bintray.com/testng-team/testng-eclipse-release/6.14.3
	//testng stable version
	//all tests  should be written with testNg always
	// add dependency as well as javabuild path testng library both we have to do
	
	// Manual test case
	// pre-condition -> test -> actual result vs expected result --> post step
	// @BeforeTest, @Test, Assert -> @AfterTest
	// launch browser, url, test google title -> Google vs Google -->close browser
// Never close browser in @Test

// if we have mul @Test annotations it will pick alphabetical order

// test page class should never call driver api or calls
	
public class LoginPageTest {

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop= basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1, enabled=true)
	/*
	 * @Description("verify LoginPage Title Test....")
	 * 
	 * @Severity(SeverityLevel.NORMAL)
	 */
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is : "+ title);
	//	Assert.assertEquals(title, "HubSpot Login");
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "title is not found....");
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink(),"singUp link is not present ....");
	}
	
	@Test(priority=3)
	public void verifShowPasswordLinkTest() {
		Assert.assertTrue(loginPage.checkShowPasswordLink(),"Show password failed to displayed....");
	}
	
	@Test(priority=4)
	public void verifyForgotPasswordTest() {
		Assert.assertTrue(loginPage.checkForgotPasswordLink(),"Forgot password link failed....");
	}
	
	@Test(priority=5)
	public void verifycheckloginSSOButtonTest() {
		Assert.assertTrue(loginPage.checkloginSSOButton(),"Check Login sso button failed....");
	}
	
	
	
	@Test(priority=6)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println("Home Page Logged IN Account Name :==> "+ homePage.getAccountName());
		Assert.assertEquals(homePage.getAccountName(), prop.getProperty("accountName"),"Login is failed");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
