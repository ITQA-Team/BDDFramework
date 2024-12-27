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
import java.util.List;
import java.util.stream.Collectors;

public class FilterStepsDefinition {
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

    @When("User selects {string} from the filter dropdown")
    public void user_selects_from_the_filter_dropdown(String filterOption) {
        WebElement filterDropdown = driver.findElement(By.className("product_sort_container"));
        filterDropdown.click();
        WebElement option = driver.findElement(By.xpath("//option[text()='" + filterOption + "']"));
        option.click();
    }

    @Then("Products should be sorted according to {string}")
    public void products_should_be_sorted_according_to(String filterOption) {
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> productNames = productElements.stream().map(WebElement::getText).collect(Collectors.toList());

        if (filterOption.equals("Name (A to Z)")) {
            List<String> sortedNames = productNames.stream().sorted().collect(Collectors.toList());
            Assert.assertEquals(productNames, sortedNames, "Products should be sorted by Name (A to Z).");
        } else if (filterOption.equals("Name (Z to A)")) {
            List<String> sortedNames = productNames.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
            Assert.assertEquals(productNames, sortedNames, "Products should be sorted by Name (Z to A).");
        } else if (filterOption.equals("Price (low to high)") || filterOption.equals("Price (high to low)")) {
            List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
            List<Double> prices = priceElements.stream()
                    .map(el -> Double.parseDouble(el.getText().replace("$", "")))
                    .collect(Collectors.toList());

            if (filterOption.equals("Price (low to high)")) {
                List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
                Assert.assertEquals(prices, sortedPrices, "Products should be sorted by Price (low to high).");
            } else if (filterOption.equals("Price (high to low)")) {
                List<Double> sortedPrices = prices.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
                Assert.assertEquals(prices, sortedPrices, "Products should be sorted by Price (high to low).");
            }
        }
    }

    /*@And("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }*/
}
