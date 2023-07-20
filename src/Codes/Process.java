package Codes;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Process extends DriverSetup {

	public void city() throws InterruptedException {

		WebElement fromField = driver.findElement(By.xpath("//*[@id=\"origin\"]/span/input"));
		fromField.sendKeys(from);
		Thread.sleep(2000);
		fromField.sendKeys(Keys.ARROW_DOWN);
		fromField.sendKeys(Keys.RETURN);

		WebElement toField = driver.findElement(By.xpath("//*[@id=\"destination\"]/span/input"));
		toField.sendKeys(to);
		Thread.sleep(2000);
		toField.sendKeys(Keys.ARROW_DOWN);
		toField.sendKeys(Keys.RETURN);
	}

	public void date() throws InterruptedException {
		LocalDate futureDate = LocalDate.now().plusDays(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedFutureDate = futureDate.format(formatter);
		WebElement departureDateField = driver.findElement(By.xpath("//*[@id=\"jDate\"]/span/input"));
		departureDateField.click();
		driver.findElement(By.xpath("//a[@class='ui-state-default ng-tns-c58-10 ng-star-inserted' and text()='"
				+ formattedFutureDate.split("/")[0] + "']")).click();
		TimeUnit.SECONDS.sleep(2);
	}

	public void businessClass() throws InterruptedException {
		WebElement traveller = driver.findElement(By.xpath("//*[@id=\"journeyClass\"]/div/div[3]"));
		traveller.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Sleeper (SL)']")).click();
		TimeUnit.SECONDS.sleep(3);
	}

	public void search() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		driver.findElement(By.xpath("//div[@class='col-xs-12 remove-padding']/div/span[1]/label")).click();
		driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable pi pi-check']")).click();
		TimeUnit.SECONDS.sleep(3);

		WebElement searchButton = driver.findElement(By.xpath(
				"//*[@id=\"divMain\"]/div/app-main-page/div/div/div[1]/div[1]/div[1]/app-jp-input/div/form/div[5]/div[1]/button"));
		searchButton.click();
		TimeUnit.SECONDS.sleep(10);
	}

	public void validation() throws ParseException, InterruptedException {
		WebElement resultLine = driver.findElement(By.xpath("//div[@class='hidden-xs']/span"));
		String sDate = resultLine.getText().split(" ")[9].concat(" ").concat(resultLine.getText().split(" ")[10])
				.concat(" ").concat(resultLine.getText().split(" ")[11]);
		String fromLocation = resultLine.getText().split(" ")[3].concat(" ").concat(resultLine.getText().split(" ")[4]);
		String toLocation = resultLine.getText().split(" ")[5].concat(" ").concat(resultLine.getText().split(" ")[6]);

		DateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		Date date = format.parse(sDate);
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date today = c.getTime();

		boolean validateDate = (date.getYear() == today.getYear() && date.getDate() == today.getDate() + 4);
		boolean validateFrom = fromLocation.equals("HYDERABAD DECAN");
		boolean validateTo = toLocation.equals("PUNE JN");

		if (validateDate && validateFrom && validateTo) {
			System.out.println("Cities and Date are verified");
		} else {
			System.out.println("Cities and Date are not verified");
		}

	}

	public void print() throws InterruptedException {
		List<WebElement> trainName = driver.findElements(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong"));
		System.out.println("Total number of trains available are: " + trainName.size());
		int j = 1;
		for (int i = 0; i < trainName.size(); i++) {
			System.out.println(trainName.get(i).getText() + " - " + j++);
		}
		TimeUnit.SECONDS.sleep(5);

	}

	public void screenShots() throws InterruptedException, IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshot);
		FileHandler.copy(src, dest);
		TimeUnit.SECONDS.sleep(5);

	}
}
