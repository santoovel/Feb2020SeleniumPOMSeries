package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest  {

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop= basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	
	@Test(priority=1)
	public void verifyContactsPageTitle() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts Page Title is:===> "+ title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE,"Contacts Page title is not found....");
	}
	
	
	/*
	 * @DataProvider public Object[][] getContactsTestData() { Object[][] data =
	 * ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME); return data; }
	 * 
	 * 
	 * @Test(priority=2, dataProvider = "getContactsTestData" ) public void
	 * createNewContactTest(String email, String firstName, String lastName, String
	 * jobTitle)
	 *  { contactsPage.createNewContact(email, firstName, lastName,
	 * jobTitle); 
	 * }
	 */
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}

	//@Test(priority = 2, dataProvider = "getContactsTestData")
	
	@Test(priority=2, dataProvider="getContactsTestData")
	public void createNewContactTest(String email, String firstname, String lastname, String jobtitle) {
		//contactsPage.createNewContact(email, firstname, lastname, jobtitle);
		String name = contactsPage.createNewContact(email, firstname, lastname, jobtitle);
		Assert.assertEquals(name, firstname+" "+lastname); // Expected vs Actual
	}

	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
