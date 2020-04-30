package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {

	//local driver
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. By locators:
	
	By header = By.cssSelector("h1.private-page__title");
	By accountName = By.cssSelector("span.account-name ");
	
	//By contactsLinkPrimary = By.id("nav-primary-contacts-branch");
	
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");

	
	//2. constructor of the page class we have to create
	
		public HomePage(WebDriver driver) {
			this.driver = driver;
			elementUtil = new ElementUtil(driver);
		}
		//3. Page actions and methods we have to create
		// in page classes we don't verify anything

			

	/*
	 * public String getHomePageTitle() {
	 * elementUtil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE); return
	 * elementUtil.doGetPageTitle(); }
	 */
		
		public String getHomePageTitle() {
			//return driver.getTitle();
		 elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
		 return elementUtil.doGetPageTitle();
		}
		
		
		public String getHomePageHeader() {
			elementUtil.waitForElementToBePresent(header, 10);
			if(elementUtil.doIsDisplayed(header)) {
				return elementUtil.doGetText(header);
			}
		/*
		 * if(driver.findElement(header).isDisplayed()) { return
		 * driver.findElement(header).getText(); }
		 */
			return null;
		}
		

		public String getLoggedInUserAccountName() {
			return elementUtil.doGetText(accountName);
		}

		public String getAccountName() {
			elementUtil.waitForElementToBePresent(accountName, 10);
			if(elementUtil.doIsDisplayed(accountName)) {
				return elementUtil.doGetText(accountName);
			}
			return null;
		}
		
		public ContactsPage goToContactsPage() {
			clickOnContacts();
			return new ContactsPage(driver);
		}
		
		private void clickOnContacts() {
			elementUtil.waitForElementToBePresent(mainContactsLink, 10);
			elementUtil.doClick(mainContactsLink);
			
			elementUtil.waitForElementToBePresent(childContactsLink, 5);
			elementUtil.doClick(childContactsLink);
		}
}
