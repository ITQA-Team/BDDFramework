package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.List;

public class CartStepsDefinition {
	static WebDriver driver;

	@Given("User is on the home page")
	public void user_is_on_the_home_page() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Assert.assertTrue(driver.findElements(By.xpath("//div[@class='app_logo']")).size() > 0, "User should be on the home page.");
	}

	@When("Clicks on the Add to cart button for {string}")  // Changed from "([^\"]*)" to {string}
	public void clicks_on_the_add_to_cart_button_for(String item) {
		WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(),'" + item + "')]/ancestor::div[@class='inventory_item']//button[@class='btn_primary btn_inventory' and text()='ADD TO CART']"));
		addToCartButton.click();
	}

	@Then("User should navigate to the cart Page")
	public void user_should_navigate_to_the_cart_page() {
		driver.get("https://www.saucedemo.com/v1/cart.html");
	}

	@Then("{string} should be present in the cart")  // Changed from @And to @Then and fixed pattern
	public void should_be_present_in_the_cart(String item) {
		WebElement cartItemName = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + item + "']"));
		Assert.assertNotNull(cartItemName, item + " should be present in the cart.");
	}

	@When("Navigates to the cart Page")
	public void navigates_to_the_cart_page() {
		driver.get("https://www.saucedemo.com/v1/cart.html");
	}

	@When("Clicks on the Remove from cart button for {string}")
	public void clicks_on_the_remove_from_cart_button_for(String item) {
		WebElement removeButton = driver.findElement(By.xpath("//div[@class='cart_item']//div[text()='" + item + "']/ancestor::div[@class='cart_item']//button[text()='REMOVE']"));
		removeButton.click();
	}

	@Then("{string} should not be present in the cart")
	public void should_not_be_present_in_the_cart(String item) {
		Assert.assertEquals(0, driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + item + "']")).size(),
				item + " should not be present in the cart.");
	}

	@When("Clicks on the Checkout button")
	public void clicks_on_the_checkout_button() {
		WebElement checkoutButton = driver.findElement(By.xpath("//div[@class='cart_footer']//a[text()='CHECKOUT']"));
		checkoutButton.click();
	}

	@Then("User should navigate to the Checkout Page")
	public void user_should_navigate_to_the_checkout_page() {
		driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
	}

	@When("User clicks on the image of {string}")
	public void userClickOnTheImageOf(String item) {
		WebElement itemImage = driver.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(),'" + item + "')]/ancestor::div[@class='inventory_item']//img"));
		itemImage.click();
	}

	@Then("User should be redirected to the detailed page of {string}")
	public void userShouldBeRedirectedToTheDetailedPageOf(String item) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("inventory-item.html"));
		String currentUrl = driver.getCurrentUrl();
		String actualItemId = getItemIdFromUrl(currentUrl);
		WebElement itemNameElement = driver.findElement(By.xpath("//div[contains(@class, 'inventory_details_name') and text()='" + item + "']"));
		Assert.assertNotNull(itemNameElement, "The detailed page does not display the correct item name: " + item);
		Assert.assertFalse(actualItemId.isEmpty(), "Item ID is missing in the URL.");
	}

	private String getItemIdFromUrl(String url) {
		String[] urlParts = url.split("\\?id=");
		if (urlParts.length > 1) {
			return urlParts[1];
		}
		return "";
	}

	@When("User clicks on the item name of {string}")
	public void user_clicks_on_the_item_name_of(String itemName) {
		WebElement itemLink = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']"));
		itemLink.click();
	}

	@Then("User should be redirected to the detailed description page of {string}")
	public void user_should_be_redirected_to_the_detailed_description_page_of(String itemName) {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("inventory-item.html"), "User is not on the detailed description page.");
		WebElement itemDescription = driver.findElement(By.xpath("//div[contains(@class, 'inventory_details_name') and text()='" + itemName + "']"));
		Assert.assertNotNull(itemDescription, "The detailed page does not show the correct item: " + itemName);
	}



//	@And("Close the browser")
//	public void close_the_browser() {
//		driver.quit();
//	}
}
