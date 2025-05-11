package auto.mobile.formcliatd.screenObject.checkout;

import auto.mobile.formcli.screenObject.MobileBaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class PaymentTab extends MobileBaseScreen {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"cardNumber-errorMsg\")")
    @iOSXCUITFindBy(id = "cardNumber-errorMsg")
    public WebElement cardNumberErrorElement;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"expireDate-errorMsg\")")
    @iOSXCUITFindBy(id = "expireDate-errorMsg")
    public WebElement expiredDateErrorElement;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"cvv-errorMsg\")")
    @iOSXCUITFindBy(id = "cvv-errorMsg")
    public WebElement cvvErrorElement;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"cardNumber-content\")")
    @iOSXCUITFindBy(id = "cardNumber-content")
    private WebElement cardNumber;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Card Number\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Card Number\"`][2]")
    private WebElement cardNumberLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"expireDate-content\")")
    @iOSXCUITFindBy(id = "expireDate-content")
    private WebElement expiredDate;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Expire Date\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Expire Date\"`][2]")
    private WebElement expiredDateLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"cvv-content\")")
    @iOSXCUITFindBy(id = "cvv-content")
    private WebElement cvv;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"CVV\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"CVV\"`][2]")
    private WebElement cvvLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"saveCard-checkbox\")")
    @iOSXCUITFindBy(id = "saveCard-checkbox")
    private WebElement saveCardCheckbox;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Save Credit Card\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Save Credit Card\"`][2]")
    private WebElement saveCardCheckboxLabel;
    @AndroidFindBy(accessibility = "Next")
    @iOSXCUITFindBy(accessibility = "Next-button")
    private WebElement nextBtn;

    public PaymentTab(AppiumDriver driver) {
        super(driver);
        logger = LogManager.getLogger(PaymentTab.class);
    }

    @Step(value = "Waiting Payment tab loaded")
    public boolean isAt() {
        logger.info("Waiting for Payment Tab to be loaded");
        Stream.of(this.cardNumberLabel, this.cvvLabel, this.expiredDateLabel)
                .parallel()
                .forEach(WebElement::isDisplayed);
        return true;
    }

    @Step(value = "get card number")
    public String getCardNumber() {
        return this.getContentOfInputField(this.cardNumber);
    }

    @Step(value = "input value to card number")
    public void setCardNumber(String cardNumber) {
        setValueToInputField(this.cardNumber, cardNumber);
    }

    @Step(value = "get expired date")
    public String getExpiredDate() {
        return this.getContentOfInputField(this.expiredDate);
    }

    @Step(value = "input value to expired date")
    public void setExpiredDate(String expiredDate) {
        setValueToInputField(this.expiredDate, expiredDate);
    }

    @Step(value = "get cvv value")
    public String getCvv() {
        return this.getContentOfInputField(this.cvv);
    }

    @Step(value = "input cvv value")
    public void setCvv(String cvv) {
        setValueToInputField(this.cvv, cvv);
    }

    public boolean isSaveCardCheckboxEnable() {
        if (this.getPlatform().equalsIgnoreCase("ios")) {
            return isSaveCardEnableInIOS();
        } else {
            return isSaveCardEnableInAndroid();
        }
    }

    @Step(value = "enable save-card checkbox")
    public void enableSaveCard(boolean enable) {
        if (enable && !isSaveCardCheckboxEnable()) {
            logger.info("Tick on save-card checkbox");
            this.saveCardCheckbox.click();
        }

        if (!enable && isSaveCardCheckboxEnable()) {
            logger.info("Un-tick on save-card checkbox");
            this.saveCardCheckbox.click();
        }
    }

    @Step(value = "submit payment form")
    public void submit() {
        this.nextBtn.click();
    }

    private boolean isSaveCardEnableInIOS() {
        String value = this.getContentOfInputField(this.saveCardCheckbox);
        return !value.equalsIgnoreCase("checkbox, unchecked, off");
    }

    private boolean isSaveCardEnableInAndroid() {
        String contentDesc = this.saveCardCheckbox.getDomAttribute("content-desc");
        return !contentDesc.equalsIgnoreCase("off");
    }
}
