package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class InstagramDMTests {
    WebDriver driver;
    WebDriverWait wait;
    final String INSTAGRAM_URL = "https://www.instagram.com/";
    final String USERNAME = "salatheo";
    final String PASSWORD = "**********";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(description = "Test 5: Reply to Existing Message")
    public void testReplyToExistingConversation() throws InterruptedException {
        loginToInstagram();
        navigateToDMs();

        List<WebElement> conversationItems = driver.findElements(By.xpath(
                "//div[contains(@role, 'listitem')] | " +
                        "//a[contains(@href, '/direct/t/')]"));

        if (!conversationItems.isEmpty()) {
            conversationItems.get(0).click();
            System.out.println("Opened first conversation in inbox");
            Thread.sleep(3000);

            WebElement messageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[contains(@aria-label, 'Message')] | " +
                            "//textarea[contains(@placeholder, 'Message')] | " +
                            "//div[@contenteditable='true'] | " +
                            "//div[contains(@role, 'textbox')]")));

            String replyMessage = "Hi, this is a reply test";
            messageInput.click();
            messageInput.sendKeys(replyMessage);

            WebElement sendButton = driver.findElement(By.xpath(
                    "//button[contains(@type, 'submit')] | " +
                            "//div[contains(@role, 'button') and contains(@aria-label, 'Send')] | " +
                            "//button[contains(@aria-label, 'Send message')]"));
            sendButton.click();

            Thread.sleep(3000);
            List<WebElement> sentMessages = driver.findElements(By.xpath("//div[contains(text(), '" + replyMessage + "')]"));
            Assert.assertFalse(sentMessages.isEmpty(), "Reply message was not sent successfully");
            System.out.println("Successfully replied to message");
        } else {
            Assert.fail("No existing conversations to open");
        }
    }

    private void loginToInstagram() throws InterruptedException {
        driver.get(INSTAGRAM_URL);
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys(USERNAME);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        System.out.println("Logged in");

        try {
            WebElement notNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Not Now')]")));
            notNow.click();
        } catch (TimeoutException e) {
            System.out.println("No 'Save Login Info' popup appeared");
        }

        try {
            WebElement notNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Not Now')]")));
            notNow.click();
        } catch (TimeoutException e) {
            System.out.println("No 'Turn on Notifications' popup appeared");
        }
    }

    private void navigateToDMs() throws InterruptedException {
        WebElement messagesIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/direct/inbox')]")));
        messagesIcon.click();
        System.out.println("Navigated to DM Inbox");

        try {
            WebElement notNow = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Not Now')]")));
            notNow.click();
            System.out.println("Dismissed 'Turn on Notifications' popup");
        } catch (TimeoutException e) {
            System.out.println("No 'Turn on Notifications' popup appeared after opening DMs");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Closed browser");
        }
    }
}