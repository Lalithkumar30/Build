package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class CartPage extends BasePage {
	
	private By lblCartProduct;
	private By btnCart;
	private By btnEmailCart;
	
	public CartPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		lblCartProduct = By.xpath("//strong[contains(text(),'Kohler')]");
		btnCart = By.xpath("//*[@id='header']//button");
		btnEmailCart = By.cssSelector("button[title='Email your cart']");
		
	}
	
	
	/**
	 * This method verify the cart list product
	 * @param productList
	 * @return boolean
	 */
	public boolean verifyProductCart(String productList) {
		boolean returnValue = false;
		WebElement product = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(lblCartProduct));
		if(productList.contains("Kohler")) {		
			if(driver.findElement(By.xpath("//strong[contains(text(),'Kohler')]")).isDisplayed()) {
				returnValue = true;
		}}else {
			returnValue = false;
		}
		return returnValue;		
	}
	
	
	/**
	 * This method is to click on cart button
	 * @param null
	 * @return void
	 */
	public void clickCartButton() {
		WebElement cart = (new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(btnCart));
		driver.findElement(btnCart).click();
	}
	
	
	/**
	 * This method is to click on Email cart button
	 * @param null
	 * @return void
	 */
	public void clickEmailCartButton() {
		WebElement cart = (new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(btnEmailCart));
		driver.findElement(btnEmailCart).click();
//		WebElement element = driver.findElement(btnEmailCart);
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", element);
	}
	
}
