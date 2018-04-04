package TestVergant;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static TestVergant.commons.setDriverPath;
import static TestVergant.commons.waitFor;

public class SignINTest {
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
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


}
