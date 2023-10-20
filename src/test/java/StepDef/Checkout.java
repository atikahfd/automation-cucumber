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

public class Checkout {
    WebDriver driver; // initiate webdriver
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User already add product to cart" )
    public void userAlreadyAddProductToCart() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

    }

    @When("User clicks on checkout button" )
    public void userClicksOnCheckoutButton() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @And("User inputs First name {string}" )
    public void userInputsFirstName(String firstName) {
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(firstName);

    }

    @And("User inputs Last name {string}" )
    public void userInputsLastName(String lastName) {
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lastName);
    }

    @And("User inputs postal code {string}" )
    public void userInputsPostalCode(String postalCode) {
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(postalCode);
    }

    @And("User clicks on continue button" )
    public void userClicksOnContinueButton() {
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    @Then("Checkout overview page shown" )
    public void checkoutOverviewPageShown() {
        String checkout2_url = "https://www.saucedemo.com/checkout-step-two.html";
        int qty = Integer.parseInt(driver.findElement(By.xpath("//div[@class='cart_quantity']")).getText());
        Float item_price = Float.parseFloat(
                driver.findElement(By.xpath("//div[@class='inventory_item_price']" )).getText().replaceAll("[^\\d.]",""));
        Float subtotal = Float.parseFloat(
                driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().replaceAll("[^\\d.]",""));
        Float tax = Float.parseFloat(
                driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText().replaceAll("[^\\d.]",""));
        Float total = Float.parseFloat(
                driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']")).getText().replaceAll("[^\\d.]",""));
        Float total_expected = subtotal + tax;
        Assert.assertEquals(checkout2_url,driver.getCurrentUrl());
        Assert.assertEquals(1,qty);
        Assert.assertEquals(item_price,subtotal);
        Assert.assertEquals(total_expected, total);
        driver.quit();
    }


    @Then("Alert error appears" )
    public void alertErrorAppears() {
        WebElement ErrorContainer = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        String AlertError1 = "Error: First Name is required";
        ErrorContainer.isDisplayed();
        Assert.assertEquals(AlertError1, ErrorContainer.getText());
        driver.quit();
    }
}
