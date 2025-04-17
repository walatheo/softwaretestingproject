import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class Login {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.firefox.driver", "C:\\Users\\newma\\Documents\\selenium_drivers\\geckodriver-v0.35.0-win32\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test //Attempting to sign up
    public void test1() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);
        // Open playstore link
        driver.findElement(By.className("x1vqgdyp")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        // Shows the page scrolling down
        exe.executeScript("window.scroll(0,50)", "");
        Thread.sleep(800);
        exe.executeScript("window.scroll(0,250)", "");
        Thread.sleep(800);
        exe.executeScript("window.scroll(0,500)", "");
        Thread.sleep(2000);
        // Scrolls back up to origin
        exe.executeScript("window.scroll(0,-800)", "");
        Thread.sleep(2000);
        //Clicks install button
        driver.findElement(By.xpath("//button[contains(., 'Install')]")).click();
        Thread.sleep(2000);
        // Clicks cancel
        driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[5]/div/div[2]/div[3]/div/button[1]")).click();
        Thread.sleep(2000);
        // Closes the tab
        driver.close();
        // Goes back to original tab
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(2000);

        // Clicks on facebook login and enters fake credentials
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[5]/button/div/div[2]")).click();
        Thread.sleep(2000);
        // Entering credentials
        driver.findElement(By.id("email")).sendKeys("mrwrongallthetime@hotmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("pass")).sendKeys("supercoolguy");
        Thread.sleep(2000);
        driver.findElement(By.id("loginbutton")).click();
        Thread.sleep(7500); // Waits a little longer so you can see that it is not able to login
        driver.navigate().back();
        driver.navigate().back();
        Thread.sleep(2000);


        // Clicks on forgot password
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[2]/a/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("cppEmailOrUsername")).sendKeys("no");
        Thread.sleep(800);
        driver.findElement(By.name("cppEmailOrUsername")).sendKeys("t");
        Thread.sleep(1000);
        driver.findElement(By.name("cppEmailOrUsername")).sendKeys("wrong");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(), 'Send login link')]/..")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div/div/div/div[8]/div")).click();
        Thread.sleep(2000);

        // Putting in fake credentials to display an unsuccessful login
        driver.findElement(By.name("username")).sendKeys("m");
        Thread.sleep(800);
        driver.findElement(By.name("username")).sendKeys("r");
        Thread.sleep(1000);
        driver.findElement(By.name("username")).sendKeys("wrong");
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("mister");
        Thread.sleep(800);
        driver.findElement(By.name("password")).sendKeys("mister");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
        // Clearing the credentials for correct ones
        driver.findElement(By.name("username")).sendKeys(Keys.CONTROL + "a");
        Thread.sleep(1000);
        driver.findElement(By.name("username")).sendKeys("replaceme");
        Thread.sleep(800);
        driver.findElement(By.name("password")).sendKeys(Keys.CONTROL + "a");
        Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("replaceme");
        Thread.sleep(800);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);

        driver.close();




    }
}
