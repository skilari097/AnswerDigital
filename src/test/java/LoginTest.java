import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.Headers;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginTest {


    ChromeDriver driver;

    @BeforeEach
    public void setup() {
        // Setup Chrome driver
        System.setProperty("webdriver.chrome.driver","resource//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);

    }


    @Test
    public void testInValidUser() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/login");

        WebElement userElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));


        userElement.sendKeys("tomsmith1");
        passwordElement.sendKeys("SuperSecretPassword!");

        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.className("radius"));

        loginButton.click();

        Thread.sleep(2000);

        assertEquals("http://the-internet.herokuapp.com/login", driver.getCurrentUrl());

        WebElement flashWebElement = driver.findElement(By.xpath("//div[@class='flash error']"));

        assertEquals("Your username is invalid!\n" +
                "×", flashWebElement.getText());


    }



    @Test
    public void testInValidPassword() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/login");

        WebElement userElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));


        userElement.sendKeys("tomsmith");
        passwordElement.sendKeys("SuperSecretPassword");

        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.className("radius"));

        loginButton.click();

        Thread.sleep(2000);

        assertEquals("http://the-internet.herokuapp.com/login", driver.getCurrentUrl());

        WebElement flashWebElement = driver.findElement(By.xpath("//div[@class='flash error']"));

        assertEquals("Your password is invalid!\n" +
                "×", flashWebElement.getText());
    }


    @Test
    public void testValidLogin() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/login");

        WebElement userElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));


        userElement.sendKeys("tomsmith");
        passwordElement.sendKeys("SuperSecretPassword!");

        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.className("radius"));

        loginButton.click();

        Thread.sleep(2000);

        assertEquals("http://the-internet.herokuapp.com/secure",driver.getCurrentUrl());

        WebElement logoutButton = driver.findElement(By.xpath("//div[@class='example']/a"));

        logoutButton.click();
        Thread.sleep(2000);
        assertEquals("http://the-internet.herokuapp.com/login", driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

