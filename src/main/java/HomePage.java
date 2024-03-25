import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends HelperClass{

    @FindBy(css = ".already-a-member")
    WebElement logInHere;

    @FindBy(css = "#login_email")
    WebElement email;

    @FindBy(css = "#login_password")
    WebElement password;

    @FindBy(css = "#login_submit")
    WebElement submit;

    @FindBy(css = ".greeting")
    WebElement account;

    final int waitTime = 180;

    void logIn(){
        logInHere.click();
        waitForElement(waitTime, email);
        email.sendKeys("jsaikat22@gmail.com");
        waitForElement(waitTime,password);
        password.sendKeys(passDecoder("cGFzczJhdXRvbWF0ZQ=="));
        waitForElement(waitTime,submit);
        submit.click();
        waitForElement(waitTime,account);
        doubleClick(account);
    }

//    public static void main(String[] args) {
//        byte[] encoded = Base64.encodeBase64("pass2automate".getBytes());
//        System.out.println(new String(encoded));
//
//        byte[] decoded = Base64.decodeBase64(encoded);
//        System.out.println(new String(decoded));
//    }

}
