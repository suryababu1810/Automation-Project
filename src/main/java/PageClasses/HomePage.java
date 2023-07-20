package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BaseClass;
import Utilities.ExcelReadWrite;


public class HomePage extends BaseClass {

	private By holidayHomesBtn_Xpath = By.xpath("//span[text() = 'Holiday Homes']");
	private By whereToTextbox_Xpath = By.xpath("//div[@class='slvrn Z0 Wh rsqqi EcFTp GADiy']//input[@name='q']");
	private By cityName_Xpath = By.xpath("//div[@class='biGQs _P fiohW fOtGX'][normalize-space()='Nairobi']");

	// Clicking Holiday homes button
	public void clickHolidayHomeLink() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(holidayHomesBtn_Xpath)).click();
	}

	// Entering the city name
	public void fillingCityName() throws Exception {
	    String city= (String) ExcelReadWrite.ReadExcel();
		wait.until(ExpectedConditions.elementToBeClickable(whereToTextbox_Xpath)).sendKeys(city);
	}

	// Clicking the city name
	public void clickingCityName() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(cityName_Xpath)).click();
	}

}
