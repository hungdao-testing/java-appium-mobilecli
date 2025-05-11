package auto.mobile.formcliatd.screenObject.checkout;

import auto.mobile.formcli.constants.AppConstant;
import auto.mobile.formcli.constants.TestConstant;
import auto.mobile.formcli.pojo.PaymentModel;
import auto.mobile.formcli.pojo.PersonalModel;
import auto.mobile.formcli.screenObject.MobileBaseScreen;
import auto.mobile.formcli.screenObject.checkout.PaymentField;
import auto.mobile.formcli.screenObject.checkout.PersonalField;
import auto.mobile.formcli.utils.DateUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class SummaryTab extends MobileBaseScreen {

    private final PersonalModel personalInfo;
    private final PaymentModel paymentInfo;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"personalInfo\")")
    @iOSXCUITFindBy(accessibility = "personalInfo")
    private WebElement personalCard;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Edit\"`][1]")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"editPersonal\")")
    private WebElement editBtnOnPersonalCard;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"editPayment\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Edit\"`][2]")
    private WebElement editBtnOnPaymentCard;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"paymentInfo\")")
    @iOSXCUITFindBy(accessibility = "paymentInfo")
    private WebElement paymentCard;

    @AndroidFindBy(accessibility = "Submit")
    @iOSXCUITFindBy(accessibility = "Submit-button")
    private WebElement submitBtn;

    public SummaryTab(AppiumDriver driver, PersonalModel personalInfo, PaymentModel paymentInfo) {
        super(driver);
        this.personalInfo = personalInfo;
        this.paymentInfo = paymentInfo;
    }

    public boolean isAt() {
        Stream.of(this.personalCard, this.paymentCard)
                .forEach(card -> this.wait.until(d -> card.isDisplayed()));
        return true;
    }

    public String getValueOfPersonalCard(PersonalField field) {
        String value;
        if (this.getPlatform().equalsIgnoreCase("ios")) {
            String locator =
                    String.format("**/XCUIElementTypeStaticText[`name CONTAINS \"%s\"`][1]", field.getName());
            value = this.driver
                    .findElement(AppiumBy.iOSClassChain(locator))
                    .getText()
                    .split(":")[1].trim();
        } else {
            String locator = String.format("new UiSelector().textContains(\"%s\")", field.getName());
            value = this.driver
                    .findElement(AppiumBy.androidUIAutomator(locator))
                    .getText()
                    .split(":")[1].trim();
        }

        if (field == PersonalField.DATE_OF_BIRTH) {
            if (this.getPlatform().equalsIgnoreCase("ios")) {
                value = DateUtils.parseStringAsDatePattern(value, DateTimeFormat.forPattern(AppConstant.DISPLAYED_DATE_FORMAT_IOS)).toString(TestConstant.DATE_PATTERN);
            } else {
                value = DateUtils.parseStringAsDatePattern(value, DateTimeFormat.forPattern(AppConstant.DISPLAYED_DATE_FORMAT_ANDROID)).toString(TestConstant.DATE_PATTERN);
            }

        }

        return value;
    }

    public String getValueOfPaymentCard(PaymentField field) {
        if (this.getPlatform().equalsIgnoreCase("ios")) {
            String locator =
                    String.format("**/XCUIElementTypeStaticText[`name CONTAINS \"%s\"`][1]", field.getName());
            return this.driver
                    .findElement(AppiumBy.iOSClassChain(locator))
                    .getText()
                    .split(":")[1].trim();
        } else {
            String locator = String.format("new UiSelector().textContains(\"%s\")", field.getName());
            return this.driver
                    .findElement(AppiumBy.androidUIAutomator(locator))
                    .getText()
                    .split(":")[1].trim();
        }
    }

    public void editPersonalCard() {
        this.editBtnOnPersonalCard.click();
    }

    public void editPaymentCard() {
        this.editBtnOnPaymentCard.click();
    }


}
