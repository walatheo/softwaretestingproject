import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class InstagramMessagingAndFriends {
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
    public void sendTestDM() throws InterruptedException {
        WebElement messages = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[5]/div/div/span/div/a"));
        messages.click();
        Thread.sleep(2000);

        WebElement firstChat = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/section/div/div/div/div[1]/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div[1]/div/div/div"));
        firstChat.click();
        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/section/div/div/div/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[1]"));
        textBox.sendKeys("Hello, ");
        Thread.sleep(2000);
        textBox.sendKeys("how are you?");
        Thread.sleep(2000);
        textBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void sendImageUpload() throws InterruptedException {
        WebElement textBox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/section/div/div/div/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[1]"));
        textBox.sendKeys("C:\\Users\\Caleb Newman\\Pictures\\cat.jpg");
        Thread.sleep(3000);
        textBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void printDMURL() throws InterruptedException {
        String dmURL = driver.getCurrentUrl();
        System.out.println("URL of DM conversation: " + dmURL);

    }

    @Test(priority = 4)
    public void dmScreenshot() throws InterruptedException, IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File des = new File ("C:\\Users\\Caleb Newman\\Documents\\TestingProject\\softwaretestingproject\\dmScreenshot.png");
        FileHandler.copy(src, des);
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void newMessage() throws InterruptedException{
        WebElement newMessage = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/section/div/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div/div"));
        newMessage.click();
        Thread.sleep(2000);

        WebElement searchBox = driver.findElement(By.cssSelector("#«r74»"));
        searchBox.sendKeys("Max Newman");
        Thread.sleep(2000);

        WebElement firstResult = driver.findElement(By.xpath("//span[text()='Max Newman']"));
        firstResult.click();
        Thread.sleep(2000);

        WebElement chatButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div/div/div/div/div/div[1]/div/div[4]/div"));
        chatButton.click();
        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/section/div/div/div/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]/div"));
        textBox.sendKeys("Hi Max!");
        Thread.sleep(2000);
        textBox.sendKeys(Keys.ENTER);
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
