package Codes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DriverSetup {
	static WebDriver driver;
	static int driverSelection;
	static String url, from, to, chromeDriver, edgeDriver, fireFoxDriver, screenshot;

	public void setDriver() throws IOException {
		System.out.println("Select your Browser: 1.Chrome 2.Edge 3.Firefox");
		Scanner scanner = new Scanner(System.in);
		driverSelection = scanner.nextInt();
		scanner.close();
		loadProperties();
		switch (driverSelection) {
		case 1:
			chromeDriverSetup();
			break;
		case 2:
			edgeDriverSetup();
			break;
		case 3:
			fireFoxDriverSetup();
			break;
		default:
			System.out.println("Invalid Input");
		}
		openUrl();
	}

	private static void loadProperties() throws IOException {
		Properties prop = new Properties();
		FileInputStream readFile = new FileInputStream("C:\\Users\\2269497\\eclipse-workspace\\Miniproject\\src\\Codes\\config.properties");
		prop.load(readFile);
		url = prop.getProperty("url");
		from = prop.getProperty("from");
		to = prop.getProperty("to");
		chromeDriver = prop.getProperty("chromeDriverPath");
		edgeDriver = prop.getProperty("edgeDriverPath");
		fireFoxDriver = prop.getProperty("FirefoxDriverPath");
		screenshot = prop.getProperty("screenshotpath");
	}

	private static void openUrl() {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	private void chromeDriverSetup() {

		System.setProperty("webdriver.chrome.driver", chromeDriver);

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		// Create a new instance of the Chrome driver

		driver = new ChromeDriver(options);

		}

	public void edgeDriverSetup() {
		System.setProperty("webdriver.edge.driver", edgeDriver);
		driver = new EdgeDriver();
	}

	public void fireFoxDriverSetup() {
		System.setProperty("webdriver.gecko.driver", fireFoxDriver);
		driver = new FirefoxDriver();
	}

	public void driverClose() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		driver.quit();
	}

}
