import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HelperClass extends BaseClass{

    void waitForElement(int time, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    String passDecoder(String password){
        byte[] decoded = Base64.decodeBase64(password);
        return new String(decoded);
    }

    void doubleClick(WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
    }

}
