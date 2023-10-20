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

public class Login {

    WebDriver driver; // initiate webdriver
    String baseUrl = "https://www.saucedemo.com/"; // setup base url

    @Given("User opens saucedemo website")
    public void userOpensSaucedemoWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @When("User inputs username {string}")
    public void userInputsUsername(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("User inputs password {string}")
    public void userInputsPassword(String validPassword) {
        driver.findElement(By.id("password")).sendKeys(validPassword);
    }

    @And("User clicks on login button")
    public void userClicksOnLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("Products page shown")
    public void productsPageShown() {
        String URL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(URL, driver.getCurrentUrl());
        driver.quit();
    }

    @Then("Alert error shown" )
    public void alertErrorShown() {
        WebElement AlertErrorLogin = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]"));
        String AlertError = "Epic sadface: Sorry, this user has been locked out." ;
        AlertErrorLogin.isDisplayed();
        Assert.assertEquals(AlertError, driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]")).getText());
        driver.quit();
    }

    @And("User inputs locked username {string}")
    public void userInputsLockedUsername(String lockedUsername) {
        driver.findElement(By.id("user-name")).sendKeys(lockedUsername);
    }
}
