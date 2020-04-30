package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;
	
	// Thread local is new concept in java 1.8 will take care of driver n extent reports
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	
	
	
	 
	/**
	 * This method is used to initialize driver based on
	 * @param browser
	 * C:\Santhoshbackup\selenium\Feb2020POMSeries :mvn clean install
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browser = prop.getProperty("browser");
		System.out.println("browser name you're using is : "+ browser);
		
		// calling options manager 
		optionsManager = new OptionsManager(prop);
	
		
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browser.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		}
		else {
			System.out.println(browser + "is not found, please pass the correct browser name");
		}
		
		
		//driver.get(prop.getProperty("url"));
		
		getDriver().get(prop.getProperty("url"));
		
		TimeUtil.mediumWait();
		
		/*
		 * try { Thread.sleep(6000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();

		return getDriver();
		
		//driver.manage().deleteAllCookies();
		//driver.manage().window().fullscreen();
		//return driver;
	}
	
	/**
	 * This method is used to load the properties from config file
	 * @return
	 */
	
	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;
		
		
		try { 
			env = System.getProperty("env");
		if(env==null) {
			path = "./src/main/java/com/qa/hubspot/config/config.properties"; 
		}
		else {
			switch (env) {
			case "qa":
				path = "./src/main/java/com/qa/hubspot/config/qa.config.properties"; 
				break;
			case "dev":
				path = "./src/main/java/com/qa/hubspot/config/dev.config.properties"; 
				break;
			default:
				System.out.println("Please pass the correct environment value....");
				break;
			}
		}
		
		FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
		/*
		 * try { env = System.getProperty("env");
		 * 
		 * if (env.equals("qa")) { path =
		 * "./src/main/java/com/qa/hubspot/config/qa.config.properties"; } else if
		 * (env.equals("stg")) { path =
		 * "./src/main/java/com/qa/hubspot/config/config.stg.properties"; } } catch
		 * (Exception e) { path =
		 * "./src/main/java/com/qa/hubspot/config/config.properties"; }
		 */
		
		/*try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
		//	prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		


	
	
	/**
	 * take screenshot
	 */

	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
	//	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("screenshot captured failed...");
		}
		return path;

	}

	
}
