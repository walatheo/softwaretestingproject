import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class combined {
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
    void testSearch() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[2]/span/div/a/div/div[2]/div/div/span/span"));
        search.click();
        Thread.sleep(2000);

        WebElement searchInput = driver.findElement(By.cssSelector(".x1lugfcp"));
        searchInput.sendKeys("gordongram");
        Thread.sleep(2000);

        WebElement firstResult = driver.findElement(By.cssSelector("a[href='/gordongram/']"));
        firstResult.click();
        Thread.sleep(2000);

    }

    @Test(priority = 2)
    void clearSearch() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[2]/span/div/a/div/div[2]/div/div/span/span"));
        search.click();
        Thread.sleep(2000);

        WebElement clearSearch = driver.findElement(By.cssSelector(".x16n37ib > div:nth-child(1) > div:nth-child(1) > svg:nth-child(1)"));
        clearSearch.click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    void searchAndScroll() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[2]/span/div/a/div/div[2]/div/div/span/span"));
        search.click();
        Thread.sleep(2000);

        WebElement searchInput = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div[1]/div/div/input"));
        searchInput.sendKeys("lamborghini");
        Thread.sleep(2000);

        WebElement searchResult = driver.findElement(By.cssSelector("a[href='/explore/search/keyword/?q=lamborghini']"));
        searchResult.click();
        Thread.sleep(5000);

        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(2000);

    }

    @Test(priority = 4)
    void testSearchClickLike() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[2]/span/div/a/div/div[2]/div/div/span/span"));
        search.click();
        Thread.sleep(2000);

        WebElement searchInput = driver.findElement(By.cssSelector(".x1lugfcp"));
        searchInput.sendKeys("redbone coonhound");
        Thread.sleep(4000);

        WebElement searchResult = driver.findElement(By.cssSelector("a[href='/explore/search/keyword/?q=redbone%20coonhound']"));
        searchResult.click();
        Thread.sleep(5000);

        WebElement post = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[2]/div/div[1]/div[1]"));
        post.click();
        Thread.sleep(2000);

        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        Thread.sleep(2000);

        WebElement likeButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[3]/div/div/div/div/div[2]/div/article/div/div[2]/div/div/div[2]/section[1]/span[1]/div/div/div"));
        likeButton.click();
        Thread.sleep(3000);
    }

    @Test(priority = 5)
    void searchClearAll() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[2]/span/div/a/div/div[2]/div/div/span/span"));
        search.click();
        Thread.sleep(2000);

        WebElement clearAll = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/div/div/div"));
        clearAll.click();
        Thread.sleep(2000);

        WebElement clearConfirm = driver.findElement(By.cssSelector("button._a9--:nth-child(1)"));
        clearConfirm.click();
        Thread.sleep(2000);
    }

    @Test(priority = 6)
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

    @Test(priority = 7)
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

    @Test(priority = 8)
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

    @Test(priority = 9)
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

    @Test(priority = 10)
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

    @Test(priority = 11) //Attempting to sign up
    void test1() throws InterruptedException {
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

    }

    @Test(priority = 12)
    void test2(String[] args) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Open instagram and makje it full screen
        driver.get("https://www.instagram.com/");

        /*
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

        */

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

    @Test(priority = 13)
    void test3(String[] args) throws InterruptedException {
        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get("https://www.instagram.com/");

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

    }

    @Test(priority = 14) // Logging in
    public void login() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        //Clicking on Profile
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div/div/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[8]/div/span/div/a/div")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 15) // Upload Photos
    public void photoUpload() throws InterruptedException {
        // Attempt to upload photo
        WebElement create = driver.findElement(By.xpath("//span[text()='Create']"));
        create.click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input._ac69[type='file']")).sendKeys("C:\\Users\\newma\\Downloads\\istockphoto-158894647-612x612.jpg");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/section/div/div/div[3]/div")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[8]/div/span/div/a/div")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 18) // Profile Photos
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

    @Test(priority = 16) // Editing Profile
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
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[3]/div[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[3]/div[1]/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/div/div/div/div[4]")).click();
        Thread.sleep(2000);

        // Saving changes
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/section/main/div/div[3]/div/div/form/div[6]/div")).click();

    }

    @Test(priority = 17) // Profile Photos
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

    @AfterSuite
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}
