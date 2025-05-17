package auto.mobile.formcli.screenObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileBaseScreen {
    protected static int WAIT_TIMEOUT = 20;
    protected AppiumDriver driver;
    protected WebDriverWait wait;
    protected Logger logger = LogManager.getLogger();

    public MobileBaseScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT_TIMEOUT));
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public String getContentOfInputField(WebElement inputElement) {
        return inputElement.getText();
    }

    public String getPlatform() {
        return this.driver.getCapabilities().getPlatformName().toString();
    }

    public void setValueToInputField(WebElement inputField, String val) {
        this.wait.until(d -> inputField.isDisplayed());
        inputField.clear();
        inputField.sendKeys(val);
    }
}
