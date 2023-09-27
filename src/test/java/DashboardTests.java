import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DashboardTests {

    public WebDriver driver;
    public String demoURL = "https://demo.guru99.com/test/newtours/";

    @BeforeTest
    public void setup(){
    System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\Version 117.0.5938.92\\chromedriver-win64\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get(demoURL);
    }

    @Test(priority = 1, groups = "Smoke Test")
    public void validateMercuryPageTitle(){

        String actualMercuryPageTile = driver.getTitle();
        String expectedMercuryPageTitle = "Welcome: Mercury Tours";
        //Validate Page Title
         Assert.assertEquals(actualMercuryPageTile,expectedMercuryPageTitle);

    }

    @Test(priority = 2, groups="Regression Tests")
    public void validateLoginTest(){
      //  driver.get(demoURL);
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