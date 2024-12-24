package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepsDefinition {
	static WebDriver driver;

	@Given("User is on the login page")
	public void user_is_on_the_login_page() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1/");
	}

	@When("User enters a valid {string} and {string}")
	public void user_enters_a_valid_and(String username, String password) throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(2000);
	}

	@When("User enters a valid {string} and an invalid {string}")
	public void user_enters_a_valid_and_an_invalid(String username, String password) throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(2000);
	}

	@And("Clicks on the Login button")
	public void clicks_on_the_login_button() {
		driver.findElement(By.id("login-button")).click();
	}

	@Then("User should be navigated to the Home Page")
	public void user_should_be_navigated_to_the_home_page() {
		Assert.assertTrue(driver.findElements(By.xpath("//div[@class='app_logo']")).size() > 0, "User should be navigated to the Home Page");
	}

	@Then("User should see an error message")
	public void user_should_see_an_error_message() {
		String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();
		Assert.assertTrue(errorMessage.contains("Epic sadface"), "Error message not displayed as expected.");
	}

//	@And("Close the browser")
//	public void close_the_browser() {
//		driver.close();
//	}
}
