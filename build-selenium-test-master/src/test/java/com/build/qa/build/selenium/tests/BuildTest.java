package com.build.qa.build.selenium.tests;

import org.junit.Assert;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.CartPage;
import com.build.qa.build.selenium.pageobjects.homepage.CategoryPage;
import com.build.qa.build.selenium.pageobjects.homepage.EmailCart;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import com.build.qa.build.selenium.pageobjects.homepage.ProductPage;

public class BuildTest extends BaseFramework { 
		
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() { 
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		
		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
		
		
	}
	
	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() { 
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		ProductPage productPage = new ProductPage(driver,wait);
		
		//Close email subscription pop up
		homePage.closePromotion();
				
		//Enter product name into search bar		
		homePage.sendSearchBarText(getConfiguration("PRODUCTSEARCH"));
		
		//Verify particular product detail displayed
		Assert.assertTrue("Particular Product page is not opened", productPage.verifyProductTitle(getConfiguration("PRODUCTSEARCH")));
	}
	
	/** 
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504) 
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @throws InterruptedException 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() throws InterruptedException { 
		driver.get(getConfiguration("BATHROOM-SINKS"));
		HomePage homePage = new HomePage(driver, wait);
		ProductPage productPage = new ProductPage(driver,wait);
		CartPage cartPage = new CartPage(driver,wait);		
		
		//Close email subscription pop up
		homePage.closePromotion();
		
		//Select a product by index
		productPage.selectProductByIndex(2);
		
		//Store the product name
		String selectedProduct = productPage.getProductTitle();
		
		//Click on Add to cart
		productPage.clickAddToCart();
		
		//Verify Product in cart
		Assert.assertTrue("The product is not successfully added to the cart", cartPage.verifyProductCart(selectedProduct));
		
	}
	
	/** 
	 * Add a product to the cart and email the cart to yourself, also to my email address: jgilmore+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() { 
		driver.get(getConfiguration("BATHROOM-SINKS"));
		HomePage homePage = new HomePage(driver, wait);
		ProductPage productPage = new ProductPage(driver,wait);
		CartPage cartPage = new CartPage(driver,wait);	
		EmailCart emailCart = new EmailCart(driver,wait);
	
		//Close email subscription pop up
		homePage.closePromotion();
	
		//Select a product by index
		productPage.selectProductByIndex(4);
	
		//Store the product name
		String selectedProduct = productPage.getProductTitle();
	
		//Click on Add to cart
		productPage.clickAddToCart();
		
		//Click on Cart button
		cartPage.clickCartButton();
		
		//Click on Email Cart button
		cartPage.clickEmailCartButton();
		
		//Fill email cart form
		emailCart.fillEmailCartForm();
		
		//Verify whether Email Sent successfully 
		Assert.assertTrue("Email message is not sent successfully", emailCart.verifyEmailSent());
	
	}
	
	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() { 
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		CategoryPage categoryPage = new CategoryPage(driver,wait);
		
		//Close email subscription pop up
		homePage.closePromotion();
		
		//Click on Bathroom in menu bar
		homePage.clickBathroomCategory();
		
		//Click on Bathroom Faucets
		categoryPage.clickBathroomFaucets();
		
		//Click on Chrome filters
		categoryPage.clickFilter("Chromes");
		
		//Click on Modern filters
		categoryPage.clickFilter("Modern");
		
		//Verify the selected Chromes filter on top
		Assert.assertTrue("Chromes filter is not narrowed in product list", categoryPage.verifyCategory("Chromes"));
		
		//Verify the selected Modern filter on top
		Assert.assertTrue("Modern filter is not narrowed in product list",categoryPage.verifyCategory("Modern"));
		
		//Verify the Product count
		Assert.assertTrue("The filtered product count is not matching",categoryPage.verifyProductCount("989"));
	}
}
