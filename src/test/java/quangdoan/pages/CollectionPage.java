package quangdoan.pages;

import org.openqa.selenium.By;

public class CollectionPage extends BasePage {
    By byImages = By.xpath("//figure");

    public void openImageByIndex(int index){
        Click(FindElements(byImages).get(index));
    }
}
