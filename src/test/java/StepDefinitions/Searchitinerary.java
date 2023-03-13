package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

public class Searchitinerary {
    ExtentReports extent;
    ExtentTest test;
    @And("a user clicks on search hotel link")
    public void aUserClicksOnSearchHotelLink() throws IOException {

        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\SearchItinerary.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Search_Itinerary");

        if(Constants.getDriver().findElement(By.id("order_no")).isDisplayed()){
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
            test.pass("Order numbers was successfully found");
        }else {
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\OrderNumNotFound.png"));
            test.pass("Order number was not found!");
            Constants.getDriver().quit();
        }

    }

    @And("a user enter order number and click on the go button")
    public void aUserEnterOrderNumberAndClickOnTheGoButton() {
        Constants.getDriver().findElement(By.id("search_hotel_id")).click();
    }

    @Then("the search is successful")
    public void theSearchIsSuccessful() throws IOException {
        if(!Constants.getDriver().findElement(By.id("search_result_error")).isDisplayed()) {
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\successBooking.png"));
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
