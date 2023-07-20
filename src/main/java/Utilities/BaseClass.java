package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop=null;
	public static ExtentReports reports;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentTest Logger;
	public static WebDriverWait wait;
	
	// Invoking the Browser
	public static WebDriver invokeBrowser(String browserName) {
		String browser = browserName;
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
	
		if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		return driver;
	}

	// Opening the WebSite
	public static void openURL() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\Utilities\\Project.properties"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    String url= prop.getProperty("URL");
		driver.get(url);
		System.out.println("===============================================");
		System.out.println("              Opening the Website              ");
		System.out.println("===============================================");
		System.out.println("---------------------------------------------------------");
		System.out.println("* Read Excel Successfully");
		System.out.println("---------------------------------------------------------");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	// Static wait
	public static void hardWait(int waitTime) throws InterruptedException {
		TimeUnit.SECONDS.sleep(waitTime);
	}

	// Switch window
	public static void windowHandling(int windowIndex) {
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> switchWindow = new ArrayList<>(windows);
		driver.switchTo().window(switchWindow.get(windowIndex));
	}
	public static void takeScreenshot(WebDriver driver, String file) throws IOException {
		try {
		

			TakesScreenshot scrShot = ((TakesScreenshot) driver);//Convert web driver object to TakeScreenshot
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			//Call getScreenshot method to create image file			
			File DestFile=new File("./ScreenShots/" +file + ".png");//move image file to new designation
			FileUtils.copyFile(SrcFile, DestFile);//Copy file to desired location
			System.out.println("------Screenshot is taken successfully-------\n");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Close the browser
	public static void closeBrowser() {
		System.out.println("               Closing the Browser             ");
		System.out.println("===============================================");
		driver.quit();
	}
}
