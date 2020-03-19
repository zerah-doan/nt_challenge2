package quangdoan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class InfoPage extends BasePage {
    public String getCamModel() {
        return findDdByDt("Camera Model")[0];
    }

    public String getFocalLeng() {
        return findDdByDt("Focal Length")[0];
    }

    private String[] findDdByDt(String dd) {
        List<WebElement> list = FindElements(By.xpath("//dd[../dt//text()='" + dd + "']"));
        return list.stream()
                .map(ele -> ele.getText())
                .collect(Collectors.toList())
                .toArray(new String[list.size()]);
    }
}
