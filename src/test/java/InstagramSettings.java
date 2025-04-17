import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.time.Duration;

public class InstagramSettings {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Open instagram and make it full screen
        driver.get("https://www.instagram.com/");

        driver.manage().window().maximize();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));

        // Login

        driver.findElement(By.name("username")).sendKeys("replaceme");
        driver.findElement(By.name("password")).sendKeys("replaceme");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait for feed or popup

        Thread.sleep(7000);
        WebElement popup = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div/div/div"));
        popup.click();

        Thread.sleep(3000);

        //Open the settings

        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[3]/span/div/a/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/a[1]/div[1]/div/div/div[2]/div/div")).click();


        Thread.sleep(5000);

        //Change Bio
        WebElement bio = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[1]/div/textarea"));
        bio.clear();
        bio.sendKeys("Im him and this is my bio");

        Thread.sleep(2000);

        // Change Gender to Male
        WebElement genderDropdown = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[3]/div[2]"));
        genderDropdown.click();

        Thread.sleep(4000);

        WebElement maleOption = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[2]"));
        maleOption.click();

        Thread.sleep(2000);
        //Turning Push Notifications for Like off
        WebElement notifications = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[1]/div/div/div[2]/div[2]/div[3]/a/div[1]"));
        notifications.click();

        Thread.sleep(1000);

        WebElement pushNotifications = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/div[2]/div/a[1]/div/div/div/div/div[1]/div/div/div/span"));
        pushNotifications.click();

        Thread.sleep(3000);
        WebElement likesNotification = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div[2]/div/div/div/div[1]/div/div/div/div[3]/div/div[1]/div[2]/div/div[1]/div/div"));
        likesNotification.click();

        Thread.sleep(2000);

        //Toggling account privacy

        WebElement privacy = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[1]/div/div/div[2]/div[3]/div[2]/a/div[1]/div/div/div[2]/div/div"));
        privacy.click();

        Thread.sleep(2000);
        WebElement privacyToggle = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/div[2]/div/div[1]/div/div/div/div/div[2]/div/input"));
        privacyToggle.click();

        Thread.sleep(3000);

        WebElement privacyConfirm = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/button[1]"));
        privacyConfirm.click();

        //Make Users close Friends
        WebElement closeFriendsButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[1]/div/div/div[2]/div[3]/div[3]/a/div[1]/div/div/div[2]/div/div/span"));
        closeFriendsButton.click();

        Thread.sleep(2000);

        WebElement justinButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div[2]/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div/div[2]/div/div/div"));
        WebElement calebButton  = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div[2]/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/div[4]/div/div[2]/div/div/div"));
        justinButton.click();
        Thread.sleep(1000);
        calebButton.click();


    }
}