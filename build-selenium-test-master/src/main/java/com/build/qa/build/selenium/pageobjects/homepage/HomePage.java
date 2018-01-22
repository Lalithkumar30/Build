package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {
	
	private By buildThemeBody;
	private By txtSearch; 
	private By lblProductTitle;
	private By btnClosePromotionBanner;
	private By navBathroom;
	
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
		txtSearch = By.id("search_txt");
		lblProductTitle = By.id("heading");
		//btnClosePromotionBanner = By.xpath("//*[@id='email-subscribe-splash']/div/div/div[1]/button/span[1]");
		btnClosePromotionBanner = By.cssSelector("#email-subscribe-splash > div > div > div.modal-header.table.modal-no-title > button > span:nth-child(1)");
		//navBathroom = By.xpath("//*[@id='header']/nav/div/ul/li[2]");
		navBathroom = By.cssSelector("#header > nav > div > ul > li:nth-child(2) > a");
	}
	
	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	
		
	/**
	 * This method is to close the email promotion
	 * @param null
	 * @return void
	 */
	public void closePromotion() {		
		(new WebDriverWait(getDriver(), 150)).until(ExpectedConditions.visibilityOfElementLocated(btnClosePromotionBanner));
		if(driver.findElements(btnClosePromotionBanner).size()>0)
			driver.findElement(btnClosePromotionBanner).click();
	}
	
	/**
	 * This method is to enter text into the search bar
	 * @param text
	 * @return void
	 */
	
	public void sendSearchBarText(String text) {
		//((JavascriptExecutor)driver).executeScript("scroll(0,400)");
		WebElement search = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.visibilityOfElementLocated(txtSearch));
		driver.findElement(txtSearch).sendKeys(text+Keys.ENTER);		
	}
		
	/**
	 * This method is to click bathroom category
	 * @param null
	 * @return void
	 */
	public void clickBathroomCategory() {
		WebElement bathCategory = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.visibilityOfElementLocated(navBathroom));
		driver.findElement(navBathroom).click();
	}
	
}
