import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.time.Duration;

public class InstagramHome {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();



        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));

        // Login

        driver.findElement(By.name("username")).sendKeys("rickyswagbrien");
        driver.findElement(By.name("password")).sendKeys("Cabes1203");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait for feed or popup

        Thread.sleep(3000);

        //Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");
        Thread.sleep(2000);


        // Locate first post
        WebElement firstPost = driver.findElement(By.xpath("(//article)[1]"));

        // Like the first post
        actions.doubleClick(firstPost).perform();
        Thread.sleep(2000);

        //Unlike a post using the "My activity screen"
        WebElement tripleLines = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[3]/span/div/a/div"));
        tripleLines.click();
        Thread.sleep(1000);

        // Go to recent actiity and unlike the liked post
        WebElement yourActivity = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/a[2]/div[1]/div/div/div[2]/div/div"));
        yourActivity.click();

        Thread.sleep(5000);

        WebElement selectButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/article/div/div[2]/div/div/div[1]/div/div/div/div/div[2]/div[2]/span"));
        selectButton.click();

        Thread.sleep(2000);

        WebElement mostRecentLiked = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/article/div/div[2]/div/div/div[1]/div/div/div/div/div[3]/div/div/div[1]/div[1]/div/div[1]/div[2]/div/div/div/div[1]/div/div[1]/img"));
        mostRecentLiked.click();

        Thread.sleep(2000);

        WebElement unliked = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/article/div/div[2]/div/div/div[1]/div/div/div/div/div[4]/div/div/div[2]/div/div/div[2]/div/div/div/div"));
        unliked.click();

        Thread.sleep(1000);
        WebElement confirmUnlike = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/button[1]/div"));
        confirmUnlike.click();

        //Toggle Light/Dark Mode

        Thread.sleep(2000);

        WebElement tripleLinesTwo = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[3]/span/div/a/div"));
        tripleLinesTwo.click();

        Thread.sleep(1000);

        WebElement switchAppearence = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/div[1]/div/div[1]"));
        switchAppearence.click();
        Thread.sleep(1000);


        WebElement confirmAppearenceToggle = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/div/div[2]/div/div/input"));
        confirmAppearenceToggle.click();

        //Submitting a report
        Thread.sleep(2000);
        tripleLinesTwo.click();
        tripleLinesTwo.click();

        WebElement logout = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/div[8]/div[1]/div/div/div[1]/div/div"));
        logout.click();



    }
}