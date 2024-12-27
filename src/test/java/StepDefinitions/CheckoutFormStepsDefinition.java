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

public class CheckoutFormStepsDefinition {
    static WebDriver driver;

    @Given("User is on the checkout page")
    public void user_is_on_the_checkout_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/checkout-step-one.html");
        // Assuming the user has already logged in and navigated to the checkout page
        Assert.assertTrue(driver.findElements(By.className("checkout_info")).size() > 0, "User should be on the checkout page.");
    }

    @When("User enters valid first name, last name, and postal code")
    public void user_enters_valid_first_name_last_name_and_postal_code() {
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
    }

    @And("User clicks the continue button")
    public void user_clicks_the_continue_button() {
        WebElement continueButton = driver.findElement(By.className("btn_primary"));
        continueButton.click();
    }

    @Then("The form should be submitted successfully")
    public void the_form_should_be_submitted_successfully() {
    	// Wait for the next page to load and verify the URL
        try {
            Thread.sleep(2000); // Temporary sleep for demonstration; consider WebDriverWait for better practice
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectedUrl = "https://www.saucedemo.com/v1/checkout-step-two.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Form submission should navigate to the checkout overview page.");
    }

    @And("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }
}