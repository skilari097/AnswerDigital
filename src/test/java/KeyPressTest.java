import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyPressTest {

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
    public void testKeyPress() throws InterruptedException {

        driver.get("http://the-internet.herokuapp.com/key_presses");



        WebElement element = driver.findElement(By.id("target"));
        element.sendKeys("A");
        String actualText=driver.findElement(By.xpath("//div[@class='example']/p[2]")).getText();
        Thread.sleep(1000);
        assertEquals("You entered: A", actualText);

        element.sendKeys("B");
        actualText=driver.findElement(By.xpath("//div[@class='example']/p[2]")).getText();
        Thread.sleep(1000);
        assertEquals("You entered: B", actualText);

        element.sendKeys("C");
        actualText=driver.findElement(By.xpath("//div[@class='example']/p[2]")).getText();
        Thread.sleep(1000);
        assertEquals("You entered: C", actualText);


        element.sendKeys("D");
        actualText=driver.findElement(By.xpath("//div[@class='example']/p[2]")).getText();
        Thread.sleep(1000);
        assertEquals("You entered: D", actualText);

    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
