import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends BaseClass{

    HomePage home;

    @BeforeMethod
    void setUp(){
        openBrowser("https://www.gilt.com/");
        home = PageFactory.initElements(BaseClass.driver, HomePage.class);
    }

    @AfterMethod
    void clean(){

    }

    @Test
    void testLogin(){
        home.logIn();
    }

    @Test
    void test1(){
        Assert.assertTrue(true);
    }

    @Test
    void test2(){
        Assert.assertTrue(false);
    }
}
