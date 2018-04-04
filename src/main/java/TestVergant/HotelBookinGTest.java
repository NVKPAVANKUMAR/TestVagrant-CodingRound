package TestVergant;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

import static TestVergant.commons.setDriverPath;
import static TestVergant.commons.waitFor;

public class HotelBookinGTest {
    WebDriver driver = new ChromeDriver();

    HotelBookinGTest(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"Home\"]/div/div/ul/li/div/div[2]/aside[1]/nav/ul[1]/li[2]/a[1]/span")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(how = How.ID,using = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar,Bangalore");
        waitFor(1000);
        //Actions action = new Actions(driver);
       // action.sendKeys(Keys.ENTER).build().perform();

        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        destinationOptions.get(1).click();

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"area\"]/section/div/div[2]/div[3]/div/p/small")));


    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
