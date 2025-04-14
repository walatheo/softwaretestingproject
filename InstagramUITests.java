package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;

public class InstagramUITests {
    static WebDriver driver;
    static WebDriverWait wait;
    static String mainWindowHandle;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testInstagramMainPage() {
        try {
            driver.get("https://www.instagram.com/");
            Thread.sleep(2000);
            mainWindowHandle = driver.getWindowHandle();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < 5; i++) {
                js.executeScript("window.scrollBy(0, 150)");
                Thread.sleep(800);
            }
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testBlogPage() {
        try {
            driver.get("https://www.instagram.com/");
            Thread.sleep(2000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement blogLink = findLinkInFooter("Blog", "blog");
            openLinkInNewTab(blogLink);

            Thread.sleep(2000);
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                Thread.sleep(700);
            }

            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);
        } catch (Exception e) {
            driver.switchTo().window(mainWindowHandle);
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testHelpCenter() {
        try {
            if (!driver.getCurrentUrl().contains("instagram.com")) {
                driver.get("https://www.instagram.com/");
                Thread.sleep(2000);
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement helpLink = findLinkInFooter("Help", "help");
            openLinkInNewTab(helpLink);

            WebElement searchBox = findElement("//input[@type='search']",
                    "//input[contains(@placeholder, 'Search')]");

            if (searchBox != null) {
                highlightElement(searchBox);
                searchBox.clear();
                searchBox.sendKeys("account privacy");
                searchBox.sendKeys(Keys.ENTER);
                Thread.sleep(2000);
            }

            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                Thread.sleep(700);
            }

            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);
        } catch (Exception e) {
            driver.switchTo().window(mainWindowHandle);
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testAboutPage() {
        try {
            if (!driver.getCurrentUrl().contains("instagram.com")) {
                driver.get("https://www.instagram.com/");
                Thread.sleep(2000);
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement aboutLink = findLinkInFooter("About", "about");
            openLinkInNewTab(aboutLink);

            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                Thread.sleep(700);
            }

            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);
        } catch (Exception e) {
            driver.switchTo().window(mainWindowHandle);
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testTermsPage() {
        try {
            if (!driver.getCurrentUrl().contains("instagram.com")) {
                driver.get("https://www.instagram.com/");
                Thread.sleep(2000);
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement termsLink = findLinkInFooter("Terms", "terms");
            openLinkInNewTab(termsLink);

            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                Thread.sleep(600);
            }

            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);
        } catch (Exception e) {
            driver.switchTo().window(mainWindowHandle);
            Assert.fail(e.getMessage());
        }
    }

    private WebElement findLinkInFooter(String linkText, String href) throws Exception {
        WebElement link = findElement(
                "//a[text()='" + linkText + "']",
                "//a[contains(text(), '" + linkText + "')]",
                "//footer//a[contains(@href, '" + href + "')]");

        if (link == null) {
            throw new Exception(linkText + " link not found");
        }

        highlightElement(link);
        return link;
    }

    private void openLinkInNewTab(WebElement link) throws InterruptedException {
        String clickInNewTab = "window.open(arguments[0].getAttribute('href'), '_blank');";
        ((JavascriptExecutor) driver).executeScript(clickInNewTab, link);
        Thread.sleep(3000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private WebElement findElement(String... xpaths) {
        for (String xpath : xpaths) {
            try {
                WebElement element = driver.findElement(By.xpath(xpath));
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {}
        }
        return null;
    }

    private void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', 'border: 3px solid red; background-color: yellow; padding: 3px;');",
                    element);
            Thread.sleep(800);
            js.executeScript("arguments[0].setAttribute('style', '');", element);
        } catch (Exception e) {}
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}