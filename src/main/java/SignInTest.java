import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        setDriverPath();
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
        driver.switchTo().frame("modal_window");
        waitFor(2000);
        WebElement signInButton = driver.findElement(By.id("signInButton"));

        Actions action = new Actions(driver);
        action.moveToElement(signInButton).click().build().perform();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        System.out.println(errors1);
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
      //  driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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
}
