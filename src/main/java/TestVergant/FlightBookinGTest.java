package TestVergant;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static TestVergant.commons.isElementPresent;
import static TestVergant.commons.setDriverPath;
import static TestVergant.commons.waitFor;

public class FlightBookinGTest {
    WebDriver driver = new ChromeDriver();


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        setDriverPath();
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
        //wait for the auto complete options to appear for the origin
        waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();
        driver.findElement(By.xpath("//*[@id=\"ToTag\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"ToTag\"]")).sendKeys("Delhi");
        //wait for the auto complete options to appear for the destination
        waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();
        waitFor(2000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(driver,By.className("searchSummary")));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }






}
