package quangdoan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import quangdoan.wd.Manager;

import java.util.List;

public class BasePage {
    int timeout = 60;

    protected void WaitForPageReady() {
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((JavascriptExecutor) Manager.get())
                .executeScript("return document.readyState").toString().equals("complete");

        new WebDriverWait(Manager.get(), timeout).until(jQueryLoad);
    }

    protected WebElement WaitForElementClickable(By by) {
        WaitForPageReady();
        return new WebDriverWait(Manager.get(), timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement WaitForElementClickable(WebElement ele) {
        WaitForPageReady();
        return new WebDriverWait(Manager.get(), timeout).until(ExpectedConditions.elementToBeClickable(ele));
    }

    protected List<WebElement> FindElements(By by) {
        return Manager.get().findElements(by);
    }

    protected void Click(By by) {
        WaitForElementClickable(by).click();
    }

    protected void Click(WebElement ele) {
        WaitForElementClickable(ele).click();
    }

    protected void Type(By by, String txt, boolean clear) {
        WebElement ele = WaitForElementClickable(by);
        if (clear) {
            ele.clear();
        }
        ele.sendKeys(txt);
    }

    protected void Type(By by, String txt) {
        Type(by, txt, true);
    }
}
