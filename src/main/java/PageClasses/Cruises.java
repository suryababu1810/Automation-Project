package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Utilities.BaseClass;

public class Cruises extends BaseClass {

	private By selectCruises_Xpath = By.xpath("//a[@class='EJavG c _S']/span[text()='Cruises']");
	private By cruiseLine_Xpath = By.xpath("//span[normalize-space()='Cruise line']");
	private By chooseCruiseLine_Xpath = By.xpath("//*[@id=\"menu-item-17391421\"]");
	private By cruiseShip_Xpath = By.xpath("//*[@id=\"component_1\"]/div/div[2]/div/div[2]/div/button");
	private By chooseCruiseShip_Xpath = By.xpath("//span[@id='menu-item-15691721']/div/span[1]");
	private By search_Xpath = By.xpath("//button[contains(text(),'Search')]");
	private By passengers_Xpath = By.xpath("//div[contains(@class,'szsLm')]//div[1]");
	private By launched_Xpath = By.xpath("//*[@id=\"ship_overview\"]/div/div/div/div[1]/div[1]/div[2]/div[4]");
	private By lang_Xpath = By.xpath("//div[contains(@class,'is-4-tablet is-shown-at-tablet')]");

	public void selectingCruiseLine() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectCruises_Xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(cruiseLine_Xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(chooseCruiseLine_Xpath)).click();
	}

	public void selectingCruiseShip() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(cruiseShip_Xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(chooseCruiseShip_Xpath)).click();
	}

	public void searchingCruises() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(search_Xpath)).click();
		hardWait(2);
	}

	public void extractDetails() throws Throwable {
		windowHandling(1);
		String passengersdetails = wait.until(ExpectedConditions.presenceOfElementLocated(passengers_Xpath)).getText();
		String[] list = passengersdetails.split("\\|   ", 2);
		String launchedyear = wait.until(ExpectedConditions.presenceOfElementLocated(launched_Xpath)).getText();
		String languages = wait.until(ExpectedConditions.presenceOfElementLocated(lang_Xpath)).getText();
		System.out.println("CRUISES DETAILS :\n");
		System.out.println(list[0]);
		System.out.println(list[1]);
		System.out.println(launchedyear);
		System.out.println("Languages offered are: \n" + languages);
		Utilities.ExcelReadWrite.writeExcelCruise(list, launchedyear, languages);
		System.out.println("---------------------------------------------------------");
		System.out.println("* Printed the Excel Successfully");
		System.out.println("---------------------------------------------------------");
	}

}