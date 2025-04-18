import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class InstagramExplore {

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
    void testExploreScroll() throws InterruptedException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.instagram.com/exploree/";

        System.out.println("Current URL: " + currentURL);
        System.out.println("Asserting URL matches wrong expected URL on purpose ... ");
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
        File des = new File ("C:\\Users\\Caleb Newman\\Documents\\TestingProject\\softwaretestingproject\\postScreenshot.png");
        FileHandler.copy(src, des);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    void testExploreComment() throws InterruptedException {
        driver.get("https://www.instagram.com/explore/");
        Thread.sleep(2000);

        WebElement post = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[2]/div/div[1]/div[4]"));
        post.click();
        Thread.sleep(2000);

        WebElement commentBox = driver.findElement(By.xpath("//textarea[@aria-label='Add a comment…']"));
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

    @Test(priority = 6)
    void getPostURL() throws InterruptedException {
        String postURL = driver.getCurrentUrl();
        System.out.println("Current post url is: " + postURL);
    }

    @AfterClass
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}