package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserActions {
    WebDriver driver;

    public WebDriver init(Browsers browser,boolean headlessMode){
       if (headlessMode) {
           switch (browser) {
               case chrome -> driver = new ChromeDriver(setChromeHeadlessMode());
               case firefox -> driver = new FirefoxDriver(setFirefoxHeadlessMode());
           }
       }
       else {
           switch (browser) {
               case chrome -> driver = new ChromeDriver();
               case firefox -> driver = new FirefoxDriver();
           }
       }
        return driver;
    }

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public void driverQuit(){
        driver.quit();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    private ChromeOptions setChromeHeadlessMode(){
        ChromeOptions options=new ChromeOptions();
       // options.setHeadless(true);
        options.addArguments("--headless");
        return options;
    }
    private FirefoxOptions setFirefoxHeadlessMode(){
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--headless");
        return options;
    }

    public enum Browsers{
        chrome,
        firefox
    }
}
