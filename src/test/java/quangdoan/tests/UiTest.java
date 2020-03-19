package quangdoan.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import quangdoan.biz.Api;
import quangdoan.biz.Collection;
import quangdoan.biz.Image;
import quangdoan.pages.HomePage;
import quangdoan.pages.InfoPage;
import quangdoan.wd.Manager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class UiTest extends BaseTest {
    String testPhotoId = "2TLREZi7BUg";
    Collection col;
    HomePage homePage;
    InfoPage infoPage;

    @BeforeMethod
    public void beforeUiTest() {
        homePage = new HomePage().loginSuccessfully();
    }

    @Test
    public void testInfo() {
        Image expected = Api.getImage(testPhotoId); //get image data from api
        infoPage = homePage.openImageUsingID(testPhotoId).viewInfo();
        Assert.assertEquals(infoPage.getCamModel(), expected.getCamModel());
        Assert.assertEquals(infoPage.getFocalLeng(), expected.getFocalLeng("mm"));
    }

    @Test(groups = "create")
    public void testAddPhotoToCollection() {
        col = Api.createCollection("TestCollection");
        Api.addImageToCollection(col.getId(), testPhotoId);
        homePage.openCollectionUsingID(col.getId()).openImageByIndex(0);
        Assert.assertEquals(Manager.get().getCurrentUrl(), "https://unsplash.com/photos/" + testPhotoId);
    }

    @AfterMethod
    public void afterMethod() {
        if (col != null) {
            Api.deleteCollection(col.getId());
        }
        super.afterMethod();
    }
}
