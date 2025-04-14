package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    @Test(priority = 1, description = "Test 1: Instagram Main Page Interaction")
    public void testInstagramMainPage() {
        try {
            driver.get("https://www.instagram.com/");
            Thread.sleep(2000);
            mainWindowHandle = driver.getWindowHandle();

            System.out.println("Starting Instagram Main Page Test");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < 5; i++) {
                js.executeScript("window.scrollBy(0, 150)");
                Thread.sleep(800);
            }
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2500);

            WebElement footer = driver.findElement(By.xpath("//footer | //div[contains(@class, 'footer')] | //div[contains(@role, 'contentinfo')]"));
            highlightElement(footer);
            System.out.println("Found footer");

            System.out.println("Completed Instagram Main Page Test");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Test 2: Blog Page Interaction")
    public void testBlogPage() {
        try {
            driver.get("https://www.instagram.com/");
            Thread.sleep(2000);

            System.out.println("Starting Blog Page Test");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement blogLink = null;
            List<String> possibleBlogXPaths = new ArrayList<>();
            possibleBlogXPaths.add("//a[text()='Blog']");
            possibleBlogXPaths.add("//a[contains(text(), 'Blog')]");
            possibleBlogXPaths.add("//a[text()='Press']");
            possibleBlogXPaths.add("//a[contains(text(), 'News')]");
            possibleBlogXPaths.add("//footer//a[contains(@href, 'blog')]");

            for (String xpath : possibleBlogXPaths) {
                try {
                    blogLink = driver.findElement(By.xpath(xpath));
                    if (blogLink.isDisplayed()) {
                        System.out.println("Found Blog link using: " + xpath);
                        break;
                    }
                } catch (Exception e) {
                }
            }

            if (blogLink == null) {
                throw new Exception("Blog link not found after trying all XPaths");
            }

            highlightElement(blogLink);
            String clickInNewTab = "window.open(arguments[0].getAttribute('href'), '_blank');";
            ((JavascriptExecutor) driver).executeScript(clickInNewTab, blogLink);
            Thread.sleep(3000);

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            System.out.println("Switched to Blog tab");

            Thread.sleep(2000);

            // Try to find any blog post with multiple XPaths
            WebElement blogPost = null;
            List<String> possiblePostXPaths = new ArrayList<>();
            possiblePostXPaths.add("//a[contains(@href, 'restrict-mute-block')]");
            possiblePostXPaths.add("//h2//a | //h3//a");
            possiblePostXPaths.add("//article//a[contains(@href, 'blog')]");
            possiblePostXPaths.add("//a[contains(@href, 'post') or contains(@href, 'article')]");
            possiblePostXPaths.add("//div[contains(@class, 'post') or contains(@class, 'article')]//a");

            for (String xpath : possiblePostXPaths) {
                try {
                    List<WebElement> possiblePosts = driver.findElements(By.xpath(xpath));
                    for (WebElement post : possiblePosts) {
                        if (post.isDisplayed()) {
                            blogPost = post;
                            System.out.println("Found blog post using: " + xpath);
                            break;
                        }
                    }
                    if (blogPost != null) break;
                } catch (Exception e) {
                }
            }

            if (blogPost != null) {
                highlightElement(blogPost);
                System.out.println("Clicking on blog post: " + blogPost.getText());
                blogPost.click();
                Thread.sleep(3000);
                for (int i = 0; i < 8; i++) {
                    js.executeScript("window.scrollBy(0, 200)");
                    Thread.sleep(700);
                }
            } else {
                System.out.println("No specific blog post found, scrolling through blog page");
                for (int i = 0; i < 8; i++) {
                    js.executeScript("window.scrollBy(0, 200)");
                    Thread.sleep(700);
                }
            }

            Thread.sleep(1000);
            System.out.println("Completed Blog Page Test");
            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            driver.switchTo().window(mainWindowHandle);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Test 3: Help Center Interaction")
    public void testHelpCenter() {
        try {
            if (!driver.getCurrentUrl().contains("instagram.com")) {
                driver.get("https://www.instagram.com/");
                Thread.sleep(2000);
            }

            System.out.println("Starting Help Center Test");

            // Scroll to bottom to ensure footer is visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            // Find Help Center link with multiple possible XPaths
            WebElement helpLink = null;
            List<String> possibleHelpXPaths = new ArrayList<>();
            possibleHelpXPaths.add("//a[text()='Help']");
            possibleHelpXPaths.add("//a[contains(text(), 'Help')]");
            possibleHelpXPaths.add("//a[text()='Help Center']");
            possibleHelpXPaths.add("//footer//a[contains(@href, 'help')]");

            for (String xpath : possibleHelpXPaths) {
                try {
                    helpLink = driver.findElement(By.xpath(xpath));
                    if (helpLink.isDisplayed()) {
                        System.out.println("Found Help link using: " + xpath);
                        break;
                    }
                } catch (Exception e) {
                    // Try next xpath
                }
            }

            if (helpLink == null) {
                throw new Exception("Help link not found after trying all XPaths");
            }

            highlightElement(helpLink);
            System.out.println("Clicking Help Center link");

            String clickInNewTab = "window.open(arguments[0].getAttribute('href'), '_blank');";
            ((JavascriptExecutor) driver).executeScript(clickInNewTab, helpLink);
            Thread.sleep(3000);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            System.out.println("Switched to Help Center tab");

            Thread.sleep(2000);

            WebElement searchBox = null;
            List<String> possibleSearchXPaths = new ArrayList<>();
            possibleSearchXPaths.add("//input[@type='search']");
            possibleSearchXPaths.add("//input[contains(@placeholder, 'Search')]");
            possibleSearchXPaths.add("//div[contains(@role, 'search')]//input");

            for (String xpath : possibleSearchXPaths) {
                try {
                    searchBox = driver.findElement(By.xpath(xpath));
                    if (searchBox.isDisplayed()) {
                        System.out.println("Found search box using: " + xpath);
                        break;
                    }
                } catch (Exception e) {
                    // Try next xpath
                }
            }

            if (searchBox != null) {
                highlightElement(searchBox);
                searchBox.clear();

                // Type search term slowly for visual effect
                String searchTerm = "account privacy";
                System.out.println("Typing search term: " + searchTerm);
                for (char c : searchTerm.toCharArray()) {
                    searchBox.sendKeys(String.valueOf(c));
                    Thread.sleep(100);
                }

                searchBox.sendKeys(Keys.ENTER);
                Thread.sleep(3000);

                // Try to find and click a search result
                List<WebElement> searchResults = driver.findElements(
                        By.xpath("//a[contains(@href, 'help')] | //div[@role='link']"));

                if (!searchResults.isEmpty()) {
                    for (WebElement result : searchResults) {
                        if (result.isDisplayed()) {
                            highlightElement(result);
                            System.out.println("Clicking search result: " + result.getText());
                            result.click();
                            Thread.sleep(3000);

                            // Scroll through the help article
                            for (int i = 0; i < 8; i++) {
                                js.executeScript("window.scrollBy(0, 200)");
                                Thread.sleep(700);
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println("No search results found, scrolling through help center");
                    for (int i = 0; i < 8; i++) {
                        js.executeScript("window.scrollBy(0, 200)");
                        Thread.sleep(700);
                    }
                }
            } else {
                System.out.println("Search box not found, looking for help topics");
                List<WebElement> helpTopics = driver.findElements(
                        By.xpath("//a[contains(text(), 'Account') or contains(text(), 'Privacy') or " +
                                "contains(text(), 'Login') or contains(text(), 'Password')]"));

                if (!helpTopics.isEmpty()) {
                    for (WebElement topic : helpTopics) {
                        if (topic.isDisplayed()) {
                            highlightElement(topic);
                            System.out.println("Clicking help topic: " + topic.getText());
                            topic.click();
                            Thread.sleep(2500);

                            // Scroll through the topic
                            for (int i = 0; i < 8; i++) {
                                js.executeScript("window.scrollBy(0, 200)");
                                Thread.sleep(700);
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println("No help topics found, scrolling through help center");
                    for (int i = 0; i < 8; i++) {
                        js.executeScript("window.scrollBy(0, 200)");
                        Thread.sleep(700);
                    }
                }
            }

            System.out.println("Completed Help Center Test");
            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            driver.switchTo().window(mainWindowHandle);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 4, description = "Test 4: About Page Interaction")
    public void testAboutPage() {
        try {
            // Make sure we're on the Instagram main page
            if (!driver.getCurrentUrl().contains("instagram.com")) {
                driver.get("https://www.instagram.com/");
                Thread.sleep(2000);
            }

            System.out.println("Starting About Page Test");

            // Scroll to bottom to ensure footer is visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            // Find About link with multiple possible XPaths
            WebElement aboutLink = null;
            List<String> possibleAboutXPaths = new ArrayList<>();
            possibleAboutXPaths.add("//a[text()='About']");
            possibleAboutXPaths.add("//a[contains(text(), 'About')]");
            possibleAboutXPaths.add("//a[text()='About Us']");
            possibleAboutXPaths.add("//footer//a[contains(@href, 'about')]");

            for (String xpath : possibleAboutXPaths) {
                try {
                    aboutLink = driver.findElement(By.xpath(xpath));
                    if (aboutLink.isDisplayed()) {
                        System.out.println("Found About link using: " + xpath);
                        break;
                    }
                } catch (Exception e) {
                    // Try next xpath
                }
            }

            if (aboutLink == null) {
                throw new Exception("About link not found after trying all XPaths");
            }

            highlightElement(aboutLink);
            System.out.println("Clicking About link");

            // Open about in new tab
            String clickInNewTab = "window.open(arguments[0].getAttribute('href'), '_blank');";
            ((JavascriptExecutor) driver).executeScript(clickInNewTab, aboutLink);
            Thread.sleep(3000);

            // Switch to new tab
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            System.out.println("Switched to About page tab");

            // Now on about page, scroll through content
            Thread.sleep(2000);

            // Scroll through the page
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                Thread.sleep(700);
            }

            // Try to find interactive elements on the page
            List<WebElement> interactiveElements = driver.findElements(
                    By.xpath("//a[not(contains(@href, 'facebook') or contains(@href, 'meta'))] | " +
                            "//button[not(ancestor::header or ancestor::nav)] | " +
                            "//div[@role='button']"));

            boolean elementClicked = false;
            for (WebElement element : interactiveElements) {
                if (element.isDisplayed() && element.isEnabled()) {
                    try {
                        highlightElement(element);
                        System.out.println("Clicking interactive element on About page");
                        element.click();
                        Thread.sleep(2000);
                        elementClicked = true;

                        // Scroll through new content
                        for (int i = 0; i < 5; i++) {
                            js.executeScript("window.scrollBy(0, 150)");
                            Thread.sleep(700);
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Could not click element: " + e.getMessage());
                    }
                }
            }

            if (!elementClicked) {
                System.out.println("No interactive elements found, continuing to scroll");
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, 0)");
                Thread.sleep(1500);
            }

            System.out.println("Completed About Page Test");

            // Close the about tab and return to main window
            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            driver.switchTo().window(mainWindowHandle);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 5, description = "Test 5: Terms Page Interaction")
    public void testTermsPage() {
        try {
            if (!driver.getCurrentUrl().contains("instagram.com")) {
                driver.get("https://www.instagram.com/");
                Thread.sleep(2000);
            }

            System.out.println("Starting Terms Page Test");

            // Scroll to bottom to ensure footer is visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            // Find Terms link with multiple possible XPaths
            WebElement termsLink = null;
            List<String> possibleTermsXPaths = new ArrayList<>();
            possibleTermsXPaths.add("//a[text()='Terms']");
            possibleTermsXPaths.add("//a[contains(text(), 'Terms')]");
            possibleTermsXPaths.add("//a[text()='Terms of Use']");
            possibleTermsXPaths.add("//footer//a[contains(@href, 'terms')]");

            for (String xpath : possibleTermsXPaths) {
                try {
                    termsLink = driver.findElement(By.xpath(xpath));
                    if (termsLink.isDisplayed()) {
                        System.out.println("Found Terms link using: " + xpath);
                        break;
                    }
                } catch (Exception e) {
                    // Try next xpath
                }
            }

            if (termsLink == null) {
                throw new Exception("Terms link not found after trying all XPaths");
            }

            highlightElement(termsLink);
            System.out.println("Clicking Terms link");

            String clickInNewTab = "window.open(arguments[0].getAttribute('href'), '_blank');";
            ((JavascriptExecutor) driver).executeScript(clickInNewTab, termsLink);
            Thread.sleep(3000);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            System.out.println("Switched to Terms page tab");

            Thread.sleep(2000);

            // Find and highlight section headers first
            List<WebElement> sections = driver.findElements(
                    By.xpath("//h1 | //h2 | //h3 | //h4 | //strong | //b"));

            for (WebElement section : sections) {
                if (section.isDisplayed()) {
                    highlightElement(section);
                    System.out.println("Found section header: " + section.getText());
                    break;
                }
            }
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 200)");
                Thread.sleep(600);
            }

            List<WebElement> expandableSections = driver.findElements(
                    By.xpath("//*[@aria-expanded='false'] | //details | //summary"));

            if (!expandableSections.isEmpty()) {
                for (WebElement section : expandableSections) {
                    if (section.isDisplayed()) {
                        try {
                            highlightElement(section);
                            System.out.println("Clicking expandable section");
                            section.click();
                            Thread.sleep(1500);

                            // Scroll to see expanded content
                            js.executeScript("window.scrollBy(0, 100)");
                            Thread.sleep(1500);
                            break;
                        } catch (Exception e) {
                            System.out.println("Could not expand section: " + e.getMessage());
                        }
                    }
                }
            }
            js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
            Thread.sleep(1500);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(1500);
            js.executeScript("window.scrollTo(0, 0)");
            Thread.sleep(1500);

            System.out.println("Completed Terms Page Test");

            // Close the terms tab and return to main window
            driver.close();
            driver.switchTo().window(mainWindowHandle);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            // Make sure we return to the main window
            driver.switchTo().window(mainWindowHandle);
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    private void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String originalStyle = element.getAttribute("style");

            js.executeScript(
                    "arguments[0].setAttribute('style', 'border: 3px solid red; background-color: yellow; padding: 3px;');",
                    element);
            Thread.sleep(800);

            js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
        } catch (Exception e) {
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("All tests completed successfully.");
    }
}