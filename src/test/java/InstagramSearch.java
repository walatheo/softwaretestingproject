import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InstagramSearch {

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
    void testSearch() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div[2]/span/div/a/div/div[2]/div/div/span/span"));
        search.click();
        Thread.sleep(2000);

        WebElement searchInput = driver.findElement(By.cssSelector(".x1lugfcp"));
        searchInput.sendKeys("gordongram");
        Thread.sleep(4000);

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
        System.out.println("Searched post URL is : " + currentURL);
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

    @AfterClass
    public void afterTest() {
        System.out.println("=== AfterTest: Closing Browser ===");
        if (driver != null) {
            driver.quit();
        }
    }
}