package TestScenarios;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import PageClasses.Cruises;
import PageClasses.HolidayHomes_ResultPage;
import PageClasses.HomePage;
import Utilities.BaseClass;

public class TestCases extends BaseClass {

	//classes as objects
	private HomePage homePage = new HomePage();
	private HolidayHomes_ResultPage holidayHomes = new HolidayHomes_ResultPage();
	private Cruises cruises = new Cruises();

	//beforetest specifies that this method should be run before each test case
	@BeforeTest(groups = { "Smoke Test","Regression Test" })
	@Parameters("browserName")
	public void Configdriver1(String browserName) throws Exception {
		BaseClass.invokeBrowser(browserName);
		BaseClass.openURL();
		reports = new ExtentReports();
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//Reports// ExtentReport.html");
		reports.attachReporter(htmlReporter);
	}

	@Test(priority = 1, groups = { "Regression Test" })
	public void clickingHolidayHomeslink() {
		Logger = reports.createTest("Module 1: RETRIEVE HOTEL DETAILS ");
		try {
			homePage.clickHolidayHomeLink();
			Logger.log(Status.INFO, "Navigated to www.tripadvisor.in website");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 2,groups = { "Regression Test" })
	public void enteringCityName() {
		try {
			homePage.fillingCityName();
			BaseClass.takeScreenshot(driver,"Nairobi");
			Logger.log(Status.INFO, "Clicked on the 'Holiday Homes' button successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 3, groups = { "Regression Test" })
	public void clickingCityName() {
		try {
			homePage.clickingCityName();
			Logger.log(Status.INFO, "Performed a search for 'Nairobi Hotels'");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 4,groups = { "Regression Test" })
	public void sortBy() {
		try {
			holidayHomes.sortByTravellerRating();
			Logger.log(Status.INFO, "Clicked on 'Sort By Traveler Rating' successfully");
			Logger.log(Status.INFO, "Selected traveler rating successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 5,groups = { "Regression Test" })
	public void selectingElevator() {
		try {
			holidayHomes.selectingLift();
			Logger.log(Status.INFO, "Clicked on 'Show All' button for amenities successfully");
			Logger.log(Status.INFO, "Selected 'Elevator/Lift Access' option successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 6, groups = { "Regression Test" })
	public void selectCheckInDate() {
		try {
			holidayHomes.selectingCheckInDate();
			Logger.log(Status.INFO, "Selected check-in date successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 7, groups = { "Regression Test" })
	public void selectCheckOutDate() {
		try {
			holidayHomes.selectingCheckOutDate();
			Logger.log(Status.INFO, "Selected check-in date successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 8,groups = { "Regression Test" })
	public void addingguests() {
		try {
			holidayHomes.addingGuests(4);
			BaseClass.takeScreenshot(driver,"RentalsInNairobi");
			Logger.log(Status.INFO, "Added 4 people to guest list successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 9, groups = { "Regression Test" })
	public void printHotelDetails() {
		try {
			holidayHomes.printHotelDetails();
			Logger.log(Status.INFO, "Successfully printed hotel details output");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 10,groups = { "Regression Test" })
	public void selectCruiseLine() {
		Logger = reports.createTest("Module 2: RETRIEVE CRUISES DETAILS ");
		try {
			cruises.selectingCruiseLine();
			Logger.log(Status.INFO, "Selected cruise line successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 11, groups = { "Regression Test" })
	public void selectCruiseShip() {
		try {
			cruises.selectingCruiseShip();
			BaseClass.takeScreenshot(driver,"CruiseSelection");
			Logger.log(Status.INFO, "Selected cruise ship successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 12, groups = { "Regression Test" })
	public void searchCruises() {
		try {
			cruises.searchingCruises();
			Logger.log(Status.INFO, "Clicked on 'Search' button for cruise ship successfully");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(priority = 13, groups = { "Regression Test" })
	public void extractDetails_Cruise() {

		try {
			cruises.extractDetails();
			BaseClass.takeScreenshot(driver,"CruiseOutput");
			Logger.log(Status.INFO, "Successfully printed cruise details output");
		} catch (Throwable e) {
			Logger.log(Status.FAIL, e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterTest(groups = { "Smoke Test","Regression Test" })
	public void closeBrowser2() {
		reports.flush();
		closeBrowser();
	}

}
