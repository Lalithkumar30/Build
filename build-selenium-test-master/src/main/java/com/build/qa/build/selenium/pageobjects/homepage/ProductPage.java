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

import junit.framework.Assert;

public class ProductPage extends BasePage {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductPage.class);
	private By lblProductTitle;
	private By productByIndex;
	private int index;
	private By allProduct;
	private By btnAddToCart;
	
	public ProductPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		lblProductTitle = By.id("heading");		
		productByIndex = By.xpath("//ul[@id='category-product-drop']/li["+index+"]");
		allProduct = By.id("category-product-drop");
		//btnAddToCart = By.cssSelector("#configure-product-wrap");
		//btnAddToCart = By.cssSelector(".add-to-cart");
		//btnAddToCart = By.cssSelector("#add-to-cart-wrap > button");
		//btnAddToCart = By.id("configure-product-wrap");
		btnAddToCart = By.xpath("//*[@id='configure-product-wrap']/button");
				
	}
	
	
	/**
	 * This method verify the opened product page contains the search product in the page heading
	 * @param productTitle
	 * @return boolean
	 */
	public boolean verifyProductTitle(String productTitle) {
		WebElement product = (new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.visibilityOfElementLocated(lblProductTitle));
			
		if(driver.findElement(lblProductTitle).getText().contains(productTitle))
				return true;
		else
			return false;		
	}
	
	/**
	 * This method verify the opened product page contains the search product in the page heading
	 * @param productTitle
	 * @return boolean
	 */
	public String getProductTitle() {
		WebElement product = (new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.visibilityOfElementLocated(lblProductTitle));
		LOG.info("Product Page of"+ driver.findElement(lblProductTitle).getText());
		return driver.findElement(lblProductTitle).getText();
						
	}
	
	
	/**
	 * This method is to select product by Index
	 * @param index
	 * @return void
	 */
	public void selectProductByIndex(int index) {
		WebElement product = (new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(allProduct));
		driver.findElement(By.xpath("//ul[@id='category-product-drop']/li["+index+"]")).click();
	}
	
	
	
	/**
	 * This method is to click Add to cart button
	 * @param null
	 * @return void
	 * @throws InterruptedException 
	 */
	public void clickAddToCart() {
	try {
		((JavascriptExecutor)driver).executeScript("scroll(100,400)");
		(new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.visibilityOfElementLocated(btnAddToCart));		
		driver.findElement(btnAddToCart).click();
//		WebElement element = driver.findElement(btnAddToCart);
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
	}catch(Exception e) {
		System.out.println("Issue in Add to cart"+e);
	}
	}
	
	
	
}
