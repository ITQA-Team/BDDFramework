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

public class SideMenuStepsDefinition {

    static WebDriver driver;

    @Given("User is on the homepage")
    public void user_is_on_the_homepage() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//div[@class='app_logo']")).size() > 0, "User should be on the homepage.");
    }

    @When("User clicks on the Open Menu button")
    public void user_clicks_on_the_open_menu_button() {
    	 WebElement openMenuButton = driver.findElement(By.cssSelector(".bm-burger-button button"));
        openMenuButton.click();
    }

    @Then("Side menu should be visible")
    public void side_menu_should_be_visible() {
    	WebElement menu = driver.findElement(By.cssSelector(".bm-menu-wrap"));
        Assert.assertTrue(menu.isDisplayed(), "Side menu should be visible.");
    }

    @When("User clicks on {string} menu item")
    public void user_clicks_on_menu_item(String menuItem) {
    	 String menuItemId;
    	    switch (menuItem) {
    	        case "All Items":
    	            menuItemId = "inventory_sidebar_link";
    	            break;
    	        case "About":
    	            menuItemId = "about_sidebar_link";
    	            break;
    	        case "Logout":
    	            menuItemId = "logout_sidebar_link";
    	            break;
    	        case "Reset App State":
    	            menuItemId = "reset_sidebar_link";
    	            break;
    	        default:
    	            throw new IllegalArgumentException("Invalid menu item: " + menuItem);
    	    }
    	    WebElement menuOption = driver.findElement(By.id(menuItemId));
    	    menuOption.click();
    }

    @Then("User should be redirected to the {string} page")
    public void user_should_be_redirected_to_the_page(String page) {
        String currentUrl = driver.getCurrentUrl();
        if (page.equals("All Items")) {
            Assert.assertTrue(currentUrl.contains("inventory.html"), "User should be on the All Items page.");
        } else if (page.equals("About")) {
            Assert.assertTrue(currentUrl.contains("saucelabs.com"), "User should be redirected to the About page.");
        } else if (page.equals("Logout")) {
            Assert.assertTrue(currentUrl.contains("index.html"), "User should be on the Login page after logging out.");
        }
    }

    @When("User clicks on the Close Menu button")
    public void user_clicks_on_the_close_menu_button() {
    	WebElement closeMenuButton = driver.findElement(By.cssSelector(".bm-cross-button button"));
        closeMenuButton.click();
    }

    @Then("Side menu should be closed")
    public void side_menu_should_be_closed() {
    	WebElement menu = driver.findElement(By.cssSelector(".bm-menu-wrap"));
        Assert.assertFalse(menu.isDisplayed(), "Side menu should be closed.");
    }

    /*@And("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }*/
}
