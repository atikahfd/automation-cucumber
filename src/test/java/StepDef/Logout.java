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

import java.time.Duration;

public class Logout {
    WebDriver driver; // initiate webdriver
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User logged in to the application" )
    public void userLoggedInToTheApplication() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("User clicks on logout button" )
    public void userClicksOnLogoutButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();

    }

    @Then("Login page shown" )
    public void loginPageShown() {
        String login_url = "https://www.saucedemo.com/";
        Assert.assertEquals(login_url, driver.getCurrentUrl());
        WebElement LoginForm = driver.findElement(By.xpath("//div[@class='login-box']"));
        LoginForm.isDisplayed();
        driver.quit();
    }


}
