import POM.FormPage;
import POM.HomePage;
import POM.ThanksPage;
import actions.BrowserActions;
import actions.UiActions;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class BaseTests {

    WebDriver driver;
    protected BrowserActions browserActions;
    protected UiActions uiActions;
    protected HomePage homePage;
    protected FormPage formPage;
    protected ThanksPage thanksPage;

    @BeforeClass
    public void setup(){
        browserActions=new BrowserActions();
        driver=browserActions.init(BrowserActions.Browsers.chrome,true);
        browserActions.maximizeWindow();
        homePage=new HomePage(driver);
        uiActions=new UiActions(driver);

    }


    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (result.getStatus()==ITestResult.FAILURE){
        File screenShot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.move(screenShot,new File("screenShots/"+result.getName()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }}
    }

    @AfterClass
    public void tearDown(){
          browserActions.driverQuit();
    }
}
