package com.qa.opencart.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameWorkException;

public class Elementutil {
	static WebDriver driver;

	public Elementutil(WebDriver driver) {
		this.driver = driver;
	}

	public void dosendkey(WebElement e, String text) {
		e.sendKeys(text);
	}

	public void dosendkey(By locator, String text) {
		WebElement we = getElement(locator);
		we.sendKeys(text);
	}

	public void dosendkey(By locator, CharSequence... value) {
		getElement(locator).sendKeys(value);
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> element = driver.findElements(locator);
		return element;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public int getElementsCount(By locator) {
		return driver.findElements(locator).size();
	}

	public String doElementGetAttribute(By locator, String attName) {
		return getElement(locator).getAttribute(attName);
	}

	public void doclick(WebElement e) {
		e.click();
	}
	public void doclick(By locator) {
		driver.findElement(locator).click();
	}

	public String GetTextofElement(WebElement e) {
		return e.getText();
	}

	// Action utils

	public void parentandchildmenu(By parent, By child) throws InterruptedException {
		Actions act = new Actions(driver);

		act.moveToElement(getElement(parent)).perform();
		Thread.sleep(3000);
		act.click(getElement(child)).perform();
		System.out.println(
				"===================================================child clicked============================================");

	}

	public void parentandchildmenu(String parent_menu, String child_menu) throws InterruptedException {
		Actions act = new Actions(driver);
		By parent = By.xpath("//*[text()='" + parent_menu + "']");
		By child = By.xpath("//*[text()='" + child_menu + "']");

		act.moveToElement(getElement(parent)).perform();
		Thread.sleep(3000);
		act.click(getElement(child)).perform();
		System.out.println(
				"===================================================child clicked from string============================================");

	}

	public boolean dosearch(By searchfield, By suggestions, String searchkey, String matchval)
			throws InterruptedException, FrameWorkException {
		boolean flag = false;
		dosendkey(searchfield, searchkey);
		Thread.sleep(3000);
		List<WebElement> suggList = getElements(suggestions);
		int Total_suggestions = suggList.size();
		if (Total_suggestions == 0) {
			System.out.println("Total no of suggestions ========" + Total_suggestions);
			throw new FrameWorkException("No Suggestions FOUND");
		}
		for (WebElement e : suggList) {

			String text = e.getText();
			if (text.contains(matchval)) {
				e.click();
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(matchval + " is found.");
		}

		else {
			System.out.println(matchval + " is not found.");
		}
		return flag;

	}
	public WebElement waitforElementVisible(By locator,int timeout) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public WebElement waitforElementVisible(By locator,int timeout,int intervalTime) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout, intervalTime));
		//WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout),Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitforElementVisiblewithFluentFeatures(By locator,int timeout,int pollingTime) {
		// THIS METHOD NOT IMPLEMENTED COMPLETELY
		Wait<WebDriver> wait= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.withTimeout(Duration.ofSeconds(pollingTime));
		// THIS METHOD NOT IMPLEMENTED COMPLETELY
	}
	public WebElement waitforElementpresence(By locator,int timeout) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}


	
	public boolean IsElementDisplayed(By element) {
		try {
			return driver.findElement(element).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found" + element);
			return false;
		}
	}

	public String waitForTitleContainsAndReturn(String text, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(text));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("Title is not matched .....");
			return "-1";
		}
	}

	public String waitForURLContainsAndReturn(String fractionurl, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.urlContains(fractionurl));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			System.out.println("URL is not  matched.....");
			return "-1";

		}
	}

	public String doGetElementText(By locator) {
		String Eletext = getElement(locator).getText();
		if (Eletext != null) {
			return Eletext;
		} else {
			System.out.println("Element text is null");
			return null;
		}
	}
}