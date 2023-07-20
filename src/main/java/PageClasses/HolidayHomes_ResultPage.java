package PageClasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Utilities.BaseClass;

public class HolidayHomes_ResultPage extends BaseClass {

	private DateTimeFormatter format;

	private By tripAdvisorSort_Xpath = By.xpath("//span[text() = 'Tripadvisor Sort']");
	private By travellerRating_Xpath = By.xpath("//span[text() = 'Traveller Rating']");
	private By amenitiesShowMoreButton_Xpath = By.xpath("//div[text()='Show all']");
	private By selectLift_Xpath = By.xpath("//label[@for='amenities.27']/span");
	private By apply_Xpath = By.xpath("//span[text()='Apply']");

	private By enterDatesButton_Xpath = By
			.xpath("//div[@class='ryeme f aFRNs']//div//button[@aria-label='Enter the date range.']");
	private By guestButton_Xpath = By.xpath("//button[@class='duUGc z B2 Z BC Gz _S q o W f u G wSSLS']");
	private By guestAddButton_Xpath = By
			.xpath("//div[text()='guests']/parent::div/following-sibling::div//button[@aria-label='increase']");
	private By guestValue_Xpath = By.xpath("//div[@class='ZSTqW f u Wg vfszC']//span[@class='biGQs _P ttuOS']");
	private By guestApply_Xpath = By
			.xpath("//*[text()='Apply']/parent::button[@class='rmyCe _G B- z _S c Wc wSSLS jWkoZ sOtnj']/span");
	private By titles_Xpath = By.xpath("//h2[contains(@class,'ToVyw b S7 W o q')]");
	private By prices_Xpath = By.xpath("//div[contains(@class,'iCUJC b')]");
	private By totalprices_Xpath = By.xpath("//div[contains(@class,'MvXmI')]");

	// Sorting by Traveler rating
	public void sortByTravellerRating() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(tripAdvisorSort_Xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(travellerRating_Xpath)).click();
	}

	// Selecting the LiftCheckBox
	public void selectingLift() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(amenitiesShowMoreButton_Xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectLift_Xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(apply_Xpath)).click();
	}

	// Selecting Check-In date
	public void selectingCheckInDate() throws Exception {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,1000)", "");

		wait.until(ExpectedConditions.elementToBeClickable(enterDatesButton_Xpath)).click();

		// Getting Tomorrow Date as Check-In date.
		//LocalDate.now() to get today's date
		LocalDate tomorrowDate = LocalDate.now().plusDays(1);
		format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		String checkInDate_formatted = tomorrowDate.format(format);

		// removing 0 from checkInDate "01 July 2023 -> 1 July 2023"
		if (Integer.valueOf(checkInDate_formatted.split(" ")[0]) < 10)
			checkInDate_formatted = checkInDate_formatted.replaceFirst("0", "");

		String checkIn_Xpath = "//div[@aria-label='" + checkInDate_formatted + "' and @aria-disabled='false']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkIn_Xpath))).click();
	}

	// Selecting Check_out date
	public void selectingCheckOutDate() throws Exception {

		// Getting 5 Days from Tomorrow's date for CheckOut Date
		LocalDate fiveDaysFromTmrwDate = LocalDate.now().plusDays(6);
		String checkOutDate_formatted = fiveDaysFromTmrwDate.format(format);

		// removing 0 from checkInDate "01 July 2023 -> 1 July 2023"
		if (Integer.valueOf(checkOutDate_formatted.split(" ")[0]) < 10)
			checkOutDate_formatted = checkOutDate_formatted.replaceFirst("0", "");

		String checkOut_Xpath = "//div[@aria-label='" + checkOutDate_formatted + "' and @aria-disabled='false']";

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkOut_Xpath))).click();
	}

	// Adding guests
	public void addingGuests(int guests) throws Exception {
		hardWait(4);
		wait.until(ExpectedConditions.elementToBeClickable(guestButton_Xpath)).click();
		hardWait(2);
		// Adding 4 guest
		for (int i = 0; i < guests; i++) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(guestAddButton_Xpath))).click();
			if (driver.findElement(guestValue_Xpath).getText().equals("4"))
				break;
		}
		wait.until(ExpectedConditions.elementToBeClickable(guestApply_Xpath)).click();
	}

	// Printing Output
	public void printHotelDetails() throws Exception {
		hardWait(2);

		List<WebElement> titles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(titles_Xpath));
		List<WebElement> prices1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(prices_Xpath));
		List<WebElement> totalprices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(totalprices_Xpath));

		String[] Titles = new String[3];
		String[] Prices = new String[3];
		String[] Totalprices = new String[3];

		System.out.println("HOTEL DETAILS :\n");
		for (int i = 0; i < 3; i++) {
			Titles[i] = titles.get(i).getText();
			System.out.println("Hotel Name  -  " + Titles[i]);
			Prices[i] = prices1.get(i).getText();
			System.out.println("Cost for Per Night - Rs." + Prices[i]);
			Totalprices[i] = totalprices.get(i).getText();
			System.out.println("Total Cost - Rs." + Totalprices[i]);
		}
		System.out.println("\n===============================================");
		Utilities.ExcelReadWrite.writeExcel(Titles, Prices, Totalprices);
		System.out.println("---------------------------------------------------------");
		System.out.println("* Printed the Excel Successfully");
		System.out.println("---------------------------------------------------------");
	}
}
