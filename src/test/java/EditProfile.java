import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class EditProfile {
    WebDriver driver;

    @BeforeClass
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver1", "C:\\Users\\Tristan\\Browser Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1) // Logging in
    public void login() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);
        // Logging in
        driver.findElement(By.name("username")).sendKeys("tankderank");
        Thread.sleep(800);
        driver.findElement(By.name("password")).sendKeys("Seleniumproject");
        Thread.sleep(800);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);

        //Clicking on Profile
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div/div/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[8]/div/span/div/a/div")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 2) // Upload Photos
    public void photoUpload() throws InterruptedException {
        // Attempt to upload photo
        WebElement create = driver.findElement(By.xpath("//span[text()='Create']"));
        create.click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input._ac69[type='file']")).sendKeys("C:\\Users\\Tristan\\Pictures\\white terrior with glasses.jpeg");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/section/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[8]/div/span/div/a/div")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5) // Profile Photos
    public void photoManage() throws InterruptedException {
        // Delete a post
        WebElement dog = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/div[2]/div/div/div/div[1]/a"));
        dog.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[3]/div/div/div/div/div[2]/div/article/div/div[2]/div/div/div[1]/div/div/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        Thread.sleep(5000);
        driver.close();
    }

    @Test(priority = 3) // Editing Profile
    public void editProfile() throws InterruptedException {
        //  Edit Profile
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/header/section[2]/div/div/div[2]/div/div[1]/a")).click();
        Thread.sleep(2000);

        //   Adding to bio
        driver.findElement(By.id("pepBio")).sendKeys("I ");
        Thread.sleep(800);
        driver.findElement(By.id("pepBio")).sendKeys("am ");
        Thread.sleep(800);
        driver.findElement(By.id("pepBio")).sendKeys("happy ");
        Thread.sleep(800);
        driver.findElement(By.id("pepBio")).sendKeys("today");
        Thread.sleep(800);

        // Change gender
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[2]/div[2]/div/div[1]/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[3]/div[2]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[3]/div[2]/input")).sendKeys("A ");
        Thread.sleep(800);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[3]/div[2]/input")).sendKeys("project");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[2]/div[2]/div/div[1]/div")).click();
        Thread.sleep(2000);

        // Saving changes
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[5]/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[8]/div/span/div/a/div/div[2]/div/div/span/span")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 4) // Profile Photos
    public void storyHighlight() throws InterruptedException {
        // Adding a story highlight
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/section/main/div/header/section[6]/div/div/div/div/div/ul/li[3]/div/div/div/div[2]/span/span")).click();
        Thread.sleep(2000);
//        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div/div/div/div/div[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div/div[2]/input")).sendKeys("Selenium");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div/div/div[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div/div/div/button")).click();


    }
}
