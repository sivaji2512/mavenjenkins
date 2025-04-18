package mavenforjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UITest {

    WebDriver driver;

    @Parameters("Browser")
    @Test
    public void startBrowser(String browserName) {
        System.out.println("Parameter value is " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            opt.addArguments("--no-sandbox");
            opt.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(opt);
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
        driver.quit();
    }
}
