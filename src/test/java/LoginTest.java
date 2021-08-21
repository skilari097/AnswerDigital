import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

        System.setProperty("webdriver.chrome.driver","resource1//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);

    }

   @Test
    public void testValidLogin() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        //assert Text
        String actualText=driver.findElement(By.xpath("//div[@class='example']/p")).getText();
        assertEquals(actualText, "Congratulations! You must have the proper credentials.");
        System.out.println("Test passed");
    }


    @Test
    public void testInValidUser() {
        driver.get("http://ddmin:admin@the-internet.herokuapp.com/basic_auth");
        //assert Text
        assertEquals(driver.getCurrentUrl(), "http://ddmin:admin@the-internet.herokuapp.com/basic_auth");
    }



    @Test
    public void testInValidPassword() {
        driver.get("http://admin:ddmin@the-internet.herokuapp.com/basic_auth");
        //assert Text
        assertEquals(driver.getCurrentUrl(), "http://admin:ddmin@the-internet.herokuapp.com/basic_auth");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

