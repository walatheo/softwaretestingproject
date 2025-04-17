import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.IOException;

public class InstagramExplore {

    WebDriver driver = new FirefoxDriver();

    @BeforeSuite
    public void beforeTest() throws InterruptedException {
        System.out.println("=== BeforeTest: Launching Browser ===");
        System.setProperty("webdriver.firefox.driver", "C:\\Users\\newma\\Documents\\selenium_drivers\\geckodriver-v0.35.0-win32\\geckodriver.exe");
        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[1]/div/label/input"));
        username.sendKeys("replaceme");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[2]/div/label/input"));
        password.sendKeys("replaceme");
        Thread.sleep(2000);

        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[3]/button"));
        login.click();
        Thread.sleep(15000);

        WebElement notNow = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div/div"));
        notNow.click();
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    void testExploreScroll() throws InterruptedException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.instagram.com/explore/";

        System.out.println("Current URL: " + currentURL);
        Assert.assertEquals(currentURL, expectedURL, "URLs do not match.");
        Thread.sleep(2000);

        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    void testExplorePost() throws InterruptedException, IOException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        WebElement post = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[2]/div/div[1]/div[4]"));
        post.click();
        Thread.sleep(2000);

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File des = new File ("C:\\Users\\newma\\Documents\\screenshot.png");
        FileHandler.copy(src, des);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    void testExploreComment() throws InterruptedException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        WebElement post = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[2]/div/div[2]/div[1]"));
        post.click();
        Thread.sleep(2000);

        WebElement commentBox = driver.findElement(By.cssSelector("textarea.x1i0vuye"));
        commentBox.sendKeys("cool!");
        Thread.sleep(2000);

        WebElement submitPost = driver.findElement(By.cssSelector("div.xjyslct"));
        submitPost.click();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    void testExploreReact() throws InterruptedException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        WebElement post = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[2]/div/div[2]/div[1]"));
        post.click();
        Thread.sleep(2000);

        WebElement react = driver.findElement(By.cssSelector("div.x1pi30zi:nth-child(1) > div:nth-child(1) > div:nth-child(1) > svg:nth-child(1)"));
        react.click();
        Thread.sleep(2000);

        WebElement laughReact = driver.findElement(By.cssSelector("div.x1rtvea0:nth-child(2)"));
        laughReact.click();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    void testExploreGoToPost() throws InterruptedException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        WebElement post = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[2]/div/div[2]/div[1]"));
        post.click();
        Thread.sleep(2000);

        WebElement options = driver.findElement(By.cssSelector("._aasm"));
        options.click();
        Thread.sleep(2000);

        WebElement goToPost = driver.findElement(By.cssSelector("button._a9--:nth-child(2)"));
        goToPost.click();
        Thread.sleep(5000);
    }

    @AfterSuite
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}