package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.BasePage;

public class CategoryPage extends BasePage {
	
	private static final Logger LOG = LoggerFactory.getLogger(CategoryPage.class);
	private By lblBathroomFaucets;
	private By lblProductCount;
	private By lblNarrowFilter;
	private By lblClearAll;
	
	public CategoryPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		lblBathroomFaucets = By.xpath("//*[@id='main']/div[2]/section[2]/ul/li[1]");
		lblNarrowFilter = By.xpath("//*[@id='category-content']//ul/li[contains(text(),'Narrow Your Results')]");
		lblClearAll = By.xpath("//*[@id='category-content']//a[contains(text(),'Clear All')]");
		lblProductCount = By.cssSelector("#category-content > div.clearfix.row.productgrid-header > div > div:nth-child(1) > span > span");
	}
	
	
	/**
	 * This method to click on the Bathroom Faucets
	 * @param null
	 * @return void
	 */
	public void clickBathroomFaucets() {
		(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(lblBathroomFaucets));
		driver.findElement(lblBathroomFaucets).click();	
	}
	
	/**
	 * This method to click on Check box 
	 * @param filter
	 * @return void
	 */
	public void clickFilter(String filter) {
		try {
		(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(lblNarrowFilter));
		//driver.findElement(By.xpath("//*[@data-facet-value='"+filter+"']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("label[data-facet-value='"+filter+"']")).click();
		Thread.sleep(3000);
	}catch(Exception e) {
		LOG.info("Issue in Filter selection");
	}
	}
	
	/**
	 * This method to verify category
	 * @param category
	 * @return boolean
	 */
	public boolean verifyCategory(String category) {
		(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(lblClearAll));
		
		WebElement categoryElement  = driver.findElement(By.xpath("//*[@id='category-content']//span[contains(text(),'"+category+"')]"));
		if(categoryElement.isDisplayed())
			return true;
		else
			return false;
	}
	
		
	/**
	 * This method to verify filtered product count
	 * @param count
	 * @return boolean
	 */
	public boolean verifyProductCount(String count) {
		(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(lblProductCount));
	if(driver.findElement(lblProductCount).getText().equalsIgnoreCase(count))
		return true;
	else
		return false;
	}
	
}
