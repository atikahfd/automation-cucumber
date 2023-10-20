package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cart {
    WebDriver driver; // initiate webdriver
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User already logged in to the application" )
    public void userAlreadyLoggedInToTheApplication() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("User adds one product to cart" )
    public void userAddsProductToCart() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @And("User clicks on cart icon" )
    public void userClicksOnCartIcon() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @Then("Your cart page shown" )
    public void yourCartPageShown() {
        String cart_url = "https://www.saucedemo.com/cart.html";
        WebElement CartItem = driver.findElement(By.xpath("//div[@class='cart_item']"));
        Assert.assertEquals(cart_url, driver.getCurrentUrl());
        CartItem.isDisplayed();
    }


    @And("User clicks on Checkout button" )
    public void userClicksOnCheckoutButton() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @Then("Checkout page shown" )
    public void checkoutPageShown() {
        String checkout_url = "https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertEquals(checkout_url, driver.getCurrentUrl());
        driver.quit();
    }
}
