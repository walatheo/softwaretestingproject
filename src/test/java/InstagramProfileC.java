import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class InstagramProfileC {
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

    @Test(priority=1)
    public void InstagramProfileChangeBio() throws InterruptedException {
        WebElement profile = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[8]/div/span/div/a/div/div[2]/div"));
        profile.click();
        Thread.sleep(2000);

        WebElement editProfile = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/header/section[2]/div/div/div[2]/div/div[1]"));
        editProfile.click();
        Thread.sleep(2000);

        WebElement bioTextArea = driver.findElement(By.xpath("//*[@id=\"pepBio\"]"));
        bioTextArea.sendKeys("Hello, my");
        bioTextArea.sendKeys(" name is ");
        bioTextArea.sendKeys("Caleb.");
        Thread.sleep(2000);

        WebElement submit = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[6]/div"));
        submit.click();
        Thread.sleep(2000);
    }

    @Test(priority=2)
    public void CreateHighlight() throws InterruptedException {
        driver.get("https://www.instagram.com/calebnewman__/");
        Thread.sleep(2000);

        WebElement createHighlight = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/header/section[6]/div/div/div/div/div/ul/li[3]/div/div/div/div[1]"));
        createHighlight.click();
        Thread.sleep(2000);

        WebElement highlightName = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div/div/div/div[2]/input"));
        highlightName.sendKeys("Selenium Highlights");
        Thread.sleep(2000);

        WebElement nextOnHighlight = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div/div/div/button"));
        nextOnHighlight.click();
        Thread.sleep(2000);

        WebElement story = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div/div/div[2]"));
        story.click();
        Thread.sleep(2000);

        WebElement nextOnStory = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div/div/div/button"));
        nextOnStory.click();
        Thread.sleep(2000);

        WebElement done = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div/div/div/button"));
        done.click();
        Thread.sleep(2000);

    }

    @Test(priority=3)
    public void InstagramProfileChangePrivacy() throws InterruptedException {
        driver.get("https://www.instagram.com/calebnewman__/");
        Thread.sleep(2000);

        WebElement editProfile = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/header/section[2]/div/div/div[2]/div/div[1]"));
        editProfile.click();
        Thread.sleep(2000);

        WebElement accountPrivacy = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[1]/div/div/div[2]/div[3]/div[2]/a/div[1]/div/div/div[2]/div/div"));
        accountPrivacy.click();
        Thread.sleep(2000);

        WebElement privacyToggle = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/div[2]/div/div[1]/div/div/div/div/div[2]/div/input"));
        privacyToggle.click();
        Thread.sleep(2000);

        WebElement switchToPublic = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/button[1]"));
        switchToPublic.click();
        Thread.sleep(2000);

    }

    @Test(priority=4)
    public void InstagramAssertProfileName() throws InterruptedException {
        driver.get("https://www.instagram.com/calebnewman__/");

        WebElement profileTextSpan = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/header/section[2]/div/div/div[1]/div/a/h2/span"));
        Thread.sleep(2000);

        String profileName = profileTextSpan.getText();

        System.out.println("Asserting Profile Name is 'Caleb Newman' incorrectly,on purpose.");
        Assert.assertEquals(profileName, "Caleb Newman", "Profile name does not match expected.");
        Thread.sleep(2000);
    }

    @Test(priority=5)
    public void InstagramProfileScreenshot() throws InterruptedException, IOException {
        driver.get("https://www.instagram.com/calebnewman__/");

        System.out.println("Taking screenshot of the profile.");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File des = new File ("C:\\Users\\Caleb Newman\\Documents\\TestingProject\\softwaretestingproject\\profileScreenshot.png");
        FileHandler.copy(src, des);
        Thread.sleep(4000);
    }

    @AfterClass
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}
