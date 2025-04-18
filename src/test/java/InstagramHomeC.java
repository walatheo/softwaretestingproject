import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class InstagramHomeC {
    WebDriver driver;

    @BeforeClass
    public void beforeTest() throws InterruptedException {
        System.out.println("=== BeforeTest: Launching Browser ===");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumDrivers\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\Caleb Newman\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Default"); // or Profile 1, 2, etc.

        driver = new ChromeDriver(options);

        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void instagramChangeAppearance() throws InterruptedException {
        WebElement more = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[3]/span/div"));
        more.click();
        Thread.sleep(2000);

        WebElement changeAppearance = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/div[1]/div/div[1]"));
        changeAppearance.click();
        Thread.sleep(2000);

        WebElement appearanceToggle = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/div/div[2]/div/div/input"));
        appearanceToggle.click();
        Thread.sleep(2000);
        appearanceToggle.click();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void instagramScrollAndScreenshot() throws InterruptedException, IOException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("window.scrollBy(0, 1000)");
        Thread.sleep(2000);

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File des = new File ("C:\\Users\\Caleb Newman\\Documents\\TestingProject\\softwaretestingproject\\homeScreenshot.png");
        FileHandler.copy(src, des);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void instagramLikeAndSaveFirstPost() throws InterruptedException {
        driver.get("https://www.instagram.com/");

        WebElement firstPostLike = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div[1]/div[1]/div/div[2]/div/div[1]/div/article[1]/div/div[3]/div/div/section[1]/div[1]/span[1]/div/div/div"));
        firstPostLike.click();
        Thread.sleep(2000);

        WebElement save = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div[1]/div[1]/div/div[2]/div/div[1]/div/article[1]/div/div[3]/div/div/section[1]/div[2]/div/div/div/div"));
        save.click();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void viewStories() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement firstStory = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div[1]/div[1]/div/div[1]/div/div/div/div/div/div/ul/li[2]/div/div[1]"));
        firstStory.click();
        Thread.sleep(6000);

        WebElement closeStories = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div/div[3]/div/section/div[3]/div"));
        closeStories.click();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void goToFirstPostAndGetURL() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement firstPostOptions = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div[1]/div[1]/div/div[2]/div/div[1]/div/article[1]/div/div[1]/div/div[3]/div/div"));
        firstPostOptions.click();
        Thread.sleep(2000);

        WebElement goToPostButton = driver.findElement(By.cssSelector("button._a9--:nth-child(4)"));
        goToPostButton.click();
        Thread.sleep(2000);

        String firstPostURL = driver.getCurrentUrl();
        System.out.println("Post URL is: " + firstPostURL);
        Thread.sleep(2000);
    }

    @AfterClass
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}
