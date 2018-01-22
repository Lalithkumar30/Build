package com.build.qa.build.selenium.pageobjects.homepage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class EmailCart extends BasePage {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailCart.class);
	private By txtYourName;
	private By txtYourEmail;
	private By txtRecipientName;
	private By txtRecipientEmail;
	private By txtQuoteMessage;
	private By btnEmailCart;
	private By lblEmailSent;
	
	public EmailCart(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		
		
		txtYourName = By.id("yourName");
		txtYourEmail = By.id("yourEmail");
		txtRecipientName = By.id("recipientName");
		txtRecipientEmail = By.id("recipientEmail");
		txtQuoteMessage = By.id("quoteMessage");
		btnEmailCart = By.xpath("//*[@id='cart-email']//button[contains(text(),'Email Cart')]");
		lblEmailSent = By.xpath("//*[@id='header']//ul/li");
		//lblEmailSent = By.cssSelector("#header > div.container-fluid > div > ul > li");
				
	}
	
	
	/**
	 * This method Fill email cart form
	 * @param null
	 * @return void
	 */
	public void fillEmailCartForm() {
		try {
		WebElement name = (new WebDriverWait(getDriver(), 60)).until(ExpectedConditions.elementToBeClickable(txtYourName));
		// Enter your name
		driver.findElement(txtYourName).sendKeys("LalithKumar");
		// Enter your Email
		driver.findElement(txtYourEmail).sendKeys("lalithkumar.ganesan@gmail.com");
		// Enter recipient Name
		driver.findElement(txtRecipientName).sendKeys("Lalith");
		// Enter recipient Email
		driver.findElement(txtRecipientEmail).sendKeys("jgilmore+SeleniumTest@build.com");		
		// Enter Quote Message
		driver.findElement(txtQuoteMessage).sendKeys("This is Lalithkumar, sending you a cart from my automation!");
		Thread.sleep(5000);
		// Click on Email cart button
		driver.findElement(btnEmailCart).click();		
		}catch(Exception e) {
			LOG.info("Issue in filling the email cart form");
		}
	}
	
	/**
	 * This method verify the email sent message
	 * @param null
	 * @return void
	 */
	public boolean verifyEmailSent() {
		try {
			boolean returnValue=false;
			Thread.sleep(3000);
			(new WebDriverWait(getDriver(), 20)).until(ExpectedConditions.presenceOfElementLocated(lblEmailSent));					
			if(driver.findElements(lblEmailSent).size()>0) {
				returnValue = true;
			}else {
				returnValue = false;
			}
			return returnValue;
		}catch (Exception e) {
			Assert.fail("Issue in Email sent verification message"+e);
			return false;
		}
	}
}
