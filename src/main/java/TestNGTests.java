import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGTests {

    public WebDriver driver;
    public String demoURL = "https://demo.guru99.com/test/newtours/";

    @BeforeTest
    public void setup(){
    ChromeOptions co = new ChromeOptions();
    co.addArguments("--remote-allow-origins=*");
    //Launch browser
    driver = new ChromeDriver(co);
    }

    @Test(priority = 1)
    public void validateMercuryPageTitle(){
        //Navigate url
        driver.get(demoURL);
        String actualMercuryPageTile = driver.getTitle();
        String expectedMercuryPageTitle = "Welcome: Mercury Tours";
        //Validate Page Title
        Assert.assertEquals(actualMercuryPageTile,expectedMercuryPageTitle);
    }

    @Test(priority = 2)
    public void validateLoginTest(){
        driver.get(demoURL);
        WebElement userNameTextField = driver.findElement(By.name("userName"));
        userNameTextField.sendKeys("test");
        WebElement passwordTextField = driver.findElement(By.name("password"));
        passwordTextField.sendKeys("test");
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();

        //Dashboard
        WebElement successMessage = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3"));
        String successMessageText = successMessage.getText();
        String expectedMessageText = "Login Successfully";
        Assert.assertEquals(successMessageText,expectedMessageText);
    }

    @AfterTest
    public void Exit(){
        driver.quit();
    }

}