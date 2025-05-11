package auto.mobile.formcliatd.screenObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

public class HomeScreen extends MobileBaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"HomeScreen\")")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'HomeScreen' AND label == 'HomeScreen'")
    private WebElement title;
    @AndroidFindBy(accessibility = "Checkout")
    @iOSXCUITFindBy(accessibility = "Checkout")
    private WebElement checkoutBtn;

    public HomeScreen(AppiumDriver driver) {
        super(driver);
        logger = LogManager.getLogger(HomeScreen.class);
    }

    @Step(value = "Check user is at the home screen")
    public boolean isAt() {
        logger.info("Waiting for Home Screen to be loaded");
        this.wait.until(driver -> this.title.isDisplayed());
        return true;
    }

    @Step(value = "Touch on [Checkout] button")
    public void openCheckout() {
        logger.info("Click on [Checkout] button");
        this.checkoutBtn.click();
    }
}
