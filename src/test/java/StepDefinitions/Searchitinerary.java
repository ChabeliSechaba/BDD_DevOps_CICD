package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Searchitinerary {
    ExtentReports extent;
    ExtentTest test;
    @And("a user clicks on search hotel link")
    public void aUserClicksOnSearchHotelLink() {

        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\SearchItinerary.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Book_hotel");

        Actions a = new Actions(Constants.getDriver());
        WebElement ordNum = Constants.getDriver().findElement(By.id("order_no"));
        a.moveToElement(ordNum).click();
        a.keyDown(Keys.CONTROL).sendKeys("a","c");

        a.keyUp(Keys.CONTROL);
        a.build().perform();

        Constants.getDriver().findElement(By.id("my_itinerary")).click();

        WebElement ordId = Constants.getDriver().findElement(By.id("order_id_text"));
        a.moveToElement(ordId).click();
        a.keyDown(Keys.CONTROL).sendKeys("v");
        a.keyUp(Keys.CONTROL);
        a.build().perform();
    }

    @And("a user enter order number and click on the go button")
    public void aUserEnterOrderNumberAndClickOnTheGoButton() {
        Constants.getDriver().findElement(By.id("search_hotel_id")).click();
    }

    @Then("the search is successful")
    public void theSearchIsSuccessful() {
        if(!Constants.getDriver().findElement(By.id("search_result_error")).isDisplayed()) {
            test.fail("Itinerary order was unsuccessful");
            Assert.fail();
        }else{
            test.pass("Itinerary order was successful");
        }
        extent.flush();
        Constants.getDriver().findElement(By.id("logout")).click();
        Constants.getDriver().quit();
    }
}
