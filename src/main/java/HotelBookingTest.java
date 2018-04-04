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
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    HotelBookingTest(){
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
        waitFor(2000);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();

        /*List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        System.out.println(destinationOptions.size());
        destinationOptions.get(1).click();*/

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listViewLink")));

       // driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "/chromedriver");
        }
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
