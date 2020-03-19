package quangdoan.pages;

import org.openqa.selenium.By;
import quangdoan.wd.Manager;

public class HomePage extends BasePage {
    private By lnkLogin = By.xpath("//a[text()='Login' and @rel]");
    private By txtEmail = By.id("user_email");
    private By txtPassword = By.id("user_password");
    private By btnLogin = By.xpath("//input[@value='Login']");
    private By tabFollowing = By.xpath("//ul/li[.//text()='Following']");
    private By btnInfo = By.xpath("//button[.//text()='Info']");
    private By mdlInfo = By.xpath("//h2[text()='Info']/../../..");


    public HomePage loginSuccessfully() {
        Click(lnkLogin);
        try {
            Thread.sleep(1000);//Temp
        } catch (InterruptedException e) {
        }
        login("doanxuanquang9@gmail.com", "hihi@123456");
        WaitForElementClickable(tabFollowing);
        return this;
    }

    public void login(String email, String password) {
        Type(txtEmail, email);
        Type(txtPassword, password);
        Click(btnLogin);
    }

    public HomePage openImageUsingID(String id) {
        Manager.get().get("https://unsplash.com/photos/" + id);
        return this;
    }

    public CollectionPage openCollectionUsingID(String id) {
        Manager.get().get("https://unsplash.com/collections/" + id);
        return new CollectionPage();
    }

    public InfoPage viewInfo() {
        Click(btnInfo);
        WaitForElementClickable(mdlInfo);
        return new InfoPage();
    }
}
