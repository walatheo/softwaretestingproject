import org.openqa.selenium.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InstagramExplore {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    public void beforeTest() throws InterruptedException {
        System.out.println("=== BeforeTest: Launching Browser ===");
        System.setProperty("webdriver.firefox.driver", "C:\\Users\\newma\\Documents\\selenium_drivers\\geckodriver-v0.35.0-win32\\geckodriver.exe");
        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[1]/div/label/input"));
        username.sendKeys("newmanc3gen@gmail.com");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[2]/div/label/input"));
        password.sendKeys("password");
        Thread.sleep(2000);

        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[3]/button"));
        login.click();
        Thread.sleep(15000);

        WebElement notNow = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div/div"));
        notNow.click();
        Thread.sleep(5000);
    }

    @Test

    @AfterTest
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}