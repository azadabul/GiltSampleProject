import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    public static WebDriver driver;

    public void openBrowser(String URL){
        driver = new ChromeDriver();
        driver.get(URL);
    }
}
