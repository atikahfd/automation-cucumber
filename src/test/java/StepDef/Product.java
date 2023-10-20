package StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Product {
    WebDriver driver; // initiate webdriver
    String baseUrl = "https://www.saucedemo.com/"; // setup base url

    @Given("User is logged in to the application" )
    public void userIsLoggedInToTheApplication() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("User clicks on Add to cart button" )
    public void userClicksOnAddToCartButton() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @Then("Number on cart icon is added to 1" )
    public void numberOnCartIconIsAdded() {
        int totalProd = Integer.parseInt(driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText());
        Assert.assertEquals(1,totalProd);
        driver.quit();
    }
}
