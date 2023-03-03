package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LoginStepsDefs extends Constants {
    ExtentReports extent;
    ExtentTest test;
    @Given("a user is on the home page")
    public void aUserIsOnTheHomePage() {
        // Invoke the browser and hit the url
        initializeWebDriver();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\Login.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Login");
    }

    @When("a user navigates to the Login page using {string}")
    public void aUserNavigatesToTheLoginPageUsing(String sUrl) {

        getDriver().get(sUrl);
    }

    @And("a user enter {string} and {string}")
    public void aUserEnterUsernameAndPassword(String sUsername, String sPassword) throws InterruptedException {
        Thread.sleep(2000);
        getDriver().findElement(By.id("username")).sendKeys(sUsername);
        getDriver().findElement(By.id("password")).sendKeys(sPassword);
    }

    @And("a user clicks the login button")
    public void aUserClicksTheLoginButton() {
        getDriver().findElement(By.id("login")).click();
    }

    @Then("a user has login successfully")
    public void aUserHasLoginSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        String welcomeMsg = getDriver().findElement(By.id("username_show")).getAttribute("value");
        System.out.println(welcomeMsg);
        if (!getDriver().findElement(By.id("username_show")).isDisplayed()){

            test.fail("Login unsuccessful!");
            Assert.fail();
        }else{
            test.pass("Login successful!");
        }
        extent.flush();
    }

}
