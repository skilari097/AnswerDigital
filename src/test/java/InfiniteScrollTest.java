import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfiniteScrollTest {


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
    public void testScroll() throws InterruptedException {

        driver.get("http://admin:ddmin@the-internet.herokuapp.com/infinite_scroll");

        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(1000,2000)");

        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,-1000)");

        Thread.sleep(2000);

        String bodyText = driver.findElement(By.tagName("body")).getText();
       assertTrue(bodyText.contains("Infinite Scroll"));

    }



    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
