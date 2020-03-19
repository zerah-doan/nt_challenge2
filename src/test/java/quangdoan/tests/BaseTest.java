package quangdoan.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import quangdoan.wd.Factory;
import quangdoan.wd.Manager;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod() {
        Manager.set(Factory.initBrowser());
        Manager.get().get("https://unsplash.com");
        Manager.get().manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        Manager.dismiss();
    }
}
