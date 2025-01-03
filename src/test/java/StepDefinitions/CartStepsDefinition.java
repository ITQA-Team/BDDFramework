package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

	/*@And("Close the browser")
	public void close_the_browser() {
		driver.quit();
	}*/
}
