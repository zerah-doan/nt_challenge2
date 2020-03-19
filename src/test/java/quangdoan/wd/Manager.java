package quangdoan.wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Sample Manager
 */
public class Manager {
    private static ThreadLocal<WebDriver> wd = new ThreadLocal<>();

    public static WebDriver get() {
        return wd.get();
    }

    public static void set(final WebDriver driver) {
        if (wd.get() != null) {
            dismiss();
        }
        wd.set(driver);
    }

    public static void dismiss() {
        if (get() != null) {
            get().quit();
        }
        wd.remove();
    }
}
