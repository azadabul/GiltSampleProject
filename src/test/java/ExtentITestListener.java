import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class ExtentITestListener implements ITestListener {
    private static final ExtentReports EXTENT = ExtentManager.createInstance("extentReport.html");
    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();

    public synchronized void onStart(ITestContext context){

    }

    public synchronized void onFinish(ITestContext context){
        EXTENT.flush();
    }

    public synchronized void onTestStart(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        if(result.getParameters().length > 0) {
            if (methodTest.get() != null && methodTest.get().getModel().getName().equals(methodName)) {

            } else {
                createTest(result);
            }
            String paramName = Arrays.asList(result.getParameters()).toString();
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            createTest(result);
        }
    }

    private void createTest(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = EXTENT.createTest(methodName);
        methodTest.set(test);

        String[] groups = result.getMethod().getGroups();

        if(groups.length >0){
            Arrays.asList(groups).forEach(x -> methodTest.get().assignCategory(x));
        }
    }

    public synchronized void onTestSuccess(ITestResult result) {
        getTest(result).pass("Test passed");
    }
    private ExtentTest getTest(ITestResult result){
       return result.getParameters() != null && result.getParameters().length>0
                ? dataProviderTest.get()
                : methodTest.get();

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH-mm-ss");
        Date date = new Date();
        String name = dateFormat.format(date);
        File file = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("src/screenshot/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getTest(result).fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {

        getTest(result).skip(result.getThrowable());
    }
}
