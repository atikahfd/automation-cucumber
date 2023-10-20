package StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Finish {
    WebDriver driver; // initiate webdriver
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User already fill users information" )
    public void userAlreadyFillUsersInformation() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Doe");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("10000");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    @When("User clicks on Finish button" )
    public void userClicksOnFinishButton() {
        driver.findElement(By.xpath("//button[@id='finish']")).click();
    }

    @Then("Checkout complete page shown" )
    public void checkoutCompletePageShown() {
        String finish_url = "https://www.saucedemo.com/checkout-complete.html";
        WebElement finish_container = driver.findElement(By.xpath("//div[@id='checkout_complete_container']"));
        Assert.assertEquals(finish_url,driver.getCurrentUrl());
        finish_container.isDisplayed();
    }

    @When("User clicks on Back Home page" )
    public void userClicksOnBackHomePage() {
        driver.findElement(By.xpath("//button[@id='back-to-products']")).click();
    }

    @Then("Redirected to Products page" )
    public void redirectedToProductsPage() {
        String product_url = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(product_url,driver.getCurrentUrl());
        driver.quit();
    }
}
