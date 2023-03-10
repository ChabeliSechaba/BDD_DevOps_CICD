package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Book_HotelStepDefs {

    ExtentReports extent;
    ExtentTest test;

    @And("a user fill in the booking form")
    public void aUserFillInTheBookingForm() throws InterruptedException, IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\Booking.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Book_hotel");
//      Find location and hotel
        WebElement statDropdown = Constants.getDriver().findElement(By.id("location"));
        Select dropdown = new Select(statDropdown);
        dropdown.selectByVisibleText("Sydney");

        WebElement statDropdown1 = Constants.getDriver().findElement(By.id("hotels"));
        Select dropdown1 = new Select(statDropdown1);
        dropdown1.selectByVisibleText("Hotel Creek");

//      Select the type of room and the number of rooms
        WebElement statDropdown2 = Constants.getDriver().findElement(By.id("room_type"));
        Select dropdown2 = new Select(statDropdown2);
        dropdown2.selectByValue("Standard");


        WebElement statDropdown3 = Constants.getDriver().findElement(By.id("room_nos"));
        Select dropdown3 = new Select(statDropdown3);
        dropdown3.selectByValue("1");

//      Select the arrival date and departure date
        Constants.getDriver().findElement(By.id("datepick_in")).clear();
        Constants.getDriver().findElement(By.id("datepick_in")).sendKeys("06/03/2023");
        Constants.getDriver().findElement(By.id("datepick_out")).clear();
        Constants.getDriver().findElement(By.id("datepick_out")).sendKeys("11/03/2023");

        List<WebElement> adultRoom = Constants.getDriver().findElements(By.id("adult_room"));
        for (int i = 0; i < adultRoom.size(); i++) {
            Constants.getDriver().findElement(By.xpath("//*[@id='adult_room']/option[3]")).click();
        }
        WebElement statDropdown5 = Constants.getDriver().findElement(By.id("child_room"));
        Select dropdown5 = new Select(statDropdown5);
        dropdown5.selectByValue("0");

//      Submit the form, select the hotel and continue to confirmation
        Thread.sleep(1000);
        Constants.getDriver().findElement(By.id("Submit")).click();
        Thread.sleep(2000);
        if (!Constants.getDriver().findElement(By.xpath("//*[@id='select_form']/table/tbody/tr[1]/td")).isDisplayed()) {
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\unsuccessSearch.png"));
            test.fail("Search unsuccessful!");
            Assert.fail();
        } else {
            test.pass("Hotel search successful");
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\successSearch.png"));
        }

        Thread.sleep(2000);
        Constants.getDriver().findElement(By.id("radiobutton_0")).click();
        Constants.getDriver().findElement(By.id("continue")).click();
//
        if (!Constants.getDriver().findElement(By.xpath("//*[@id='book_hotel_form']/table/tbody/tr[2]/td")).isDisplayed()) {
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\unsuccessSelection.png"));
            test.fail("Hotel selected unsuccessfully!");
            Assert.fail();
        } else {
            test.pass("Hotel selected successfully!");
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\successSelection.png"));
        }
    }

    @And("a user enters {string}, {string}, {string}, {string}, {string}")
    public void aUserEnters(String pFirstname, String pLastname, String pBill, String pCard_no, String pCvv) throws IOException {
        //      Insert the names, card number and billing address
        Constants.getDriver().findElement(By.id("first_name")).sendKeys(pFirstname);
        Constants.getDriver().findElement(By.id("last_name")).sendKeys(pLastname);
        Constants.getDriver().findElement(By.id("address")).sendKeys(pBill);
        Constants.getDriver().findElement(By.id("cc_num")).sendKeys(pCard_no);

//      Select the card type, expiry month and year
        WebElement statDropdown = Constants.getDriver().findElement(By.id("cc_type"));
        Select dropdown = new Select(statDropdown);
        dropdown.selectByIndex(3);

        WebElement statDropdown1 = Constants.getDriver().findElement(By.id("cc_exp_month"));
        Select dropdown1 = new Select(statDropdown1);
        dropdown1.selectByValue("12");

        WebElement statDropdown2 = Constants.getDriver().findElement(By.id("cc_exp_year"));
        Select dropdown2 = new Select(statDropdown2);
        dropdown2.selectByValue("2022");

//      Insert the cvv number and book the hotel
        Constants.getDriver().findElement(By.id("cc_cvv")).sendKeys(pCvv);
        Constants.getDriver().findElement(By.id("book_now")).click();
    }

    @Then("the hotel was booked successfully")
    public void theHotelWasBookedSuccessfully() throws IOException {

        WebDriverWait objWait = new WebDriverWait(Constants.getDriver(), 120);
        objWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("order_no")));

        if (Constants.getDriver().findElement(By.id("cc_num_span")).isDisplayed()) {
            Constants.getDriver().manage().window().fullscreen();
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\unsuccessBooking.png"));
            Constants.getDriver().close();
            test.pass("Hotel booking unsuccesfully!");
            extent.flush();
            Constants.getDriver().close();
        } else {
            Constants.getDriver().manage().window().fullscreen();
            File src = ((TakesScreenshot) Constants.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("C:\\Users\\Sechaba.Chabedi\\Desktop\\BDD_Training\\BDD_Exercise\\Reports\\successBooking.png"));
            test.pass("Hotel was booked successfully");
        }
        extent.flush();
        System.out.println(Constants.getDriver().findElement(By.id("order_no")).getText());
        Constants.getDriver().close();
    }
}













