import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class TestNGTests {

    public WebDriver driver;
    public String demoURL = "https://demo.guru99.com/test/newtours/";

    @BeforeTest
    public void setup(){
  //  ChromeOptions co = new ChromeOptions();
   // co.addArguments("--remote-allow-origins=*");
    //Launch browser
    driver = new ChromeDriver();
    driver.get(demoURL);
    }

    @Test(priority = 1)
    public void validateMercuryPageTitle(){
        //Navigate url
    //    driver.get(demoURL);
        String actualMercuryPageTile = driver.getTitle();
        String expectedMercuryPageTitle = "Welcome: Mercury Tours";
        //Validate Page Title
        Assert.assertEquals(actualMercuryPageTile,expectedMercuryPageTitle);
    }

    @Test(priority = 2)
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

    @Test(priority = 3, groups = "Functional", description = "To verify registration of new user")
    public void validateRegister(){

        // Test Data
        String firstName =  RandomStringUtils.randomAlphabetic(8);
        String lastName = RandomStringUtils.randomAlphabetic(8);
        String phone = "021-225-55566";
        String email = firstName+"@abc.com";
        String address = RandomStringUtils.randomAlphabetic(8);
        String city = "city";
        String state = "state";
        String postalCode = "123456";
        String password = RandomStringUtils.randomAlphabetic(8);
        String expectedUserName = "Dear "+firstName+" "+lastName+",";

        //Navigate url
        WebElement registerLink = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]"));
        registerLink.click();
        String registerPageTitle = driver.getTitle();
        String expectedPageTile = "Register: Mercury Tours";
        Assert.assertEquals(registerPageTitle,expectedPageTile);

        WebElement firstNameTextBox = driver.findElement(By.name("firstName"));
        firstNameTextBox.sendKeys(firstName);

        WebElement lastNameTextBox = driver.findElement(By.name("lastName"));
        lastNameTextBox.sendKeys(lastName);

        WebElement phoneTextBox = driver.findElement(By.name("phone"));
        phoneTextBox.sendKeys(phone);

        WebElement emailTextBox = driver.findElement(By.name("userName"));
        emailTextBox.sendKeys(email);

        WebElement addressTextBox = driver.findElement(By.name("address1"));
        addressTextBox.sendKeys(address);

        WebElement cityTextBox = driver.findElement(By.name("city"));
        cityTextBox.sendKeys(city);

        WebElement stateTextBox = driver.findElement(By.name("state"));
        stateTextBox.sendKeys(state);

        WebElement postalCodeTextBox = driver.findElement(By.name("postalCode"));
        postalCodeTextBox.sendKeys(postalCode);

        Select countryDropDown = new Select(driver.findElement(By.name("country")));
        countryDropDown.selectByValue("ANGOLA");

        WebElement userNameTextBox = driver.findElement(By.name("email"));
        userNameTextBox.sendKeys(email);


        WebElement passwordTextBox = driver.findElement(By.name("password"));
        passwordTextBox.sendKeys(password);

        WebElement confirmPasswordTextBox = driver.findElement(By.name("confirmPassword"));
        confirmPasswordTextBox.sendKeys(password);

        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();

        WebElement registerSuccess = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b"));
        String registerSuccessText = registerSuccess.getText();

        Assert.assertEquals(expectedUserName,registerSuccessText);

    }

    @AfterTest
    public void Exit(){
        driver.quit();
    }

}