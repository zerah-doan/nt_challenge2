package quangdoan.wd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Sample Factory
 */
public class Factory {

    public static WebDriver initBrowser() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
