package auto.mobile.formcliatd.screenObject.checkout;

import auto.mobile.formcliatd.constants.AppConstant;
import auto.mobile.formcliatd.constants.TestConstant;
import auto.mobile.formcliatd.pojo.DobPojo;
import auto.mobile.formcliatd.screenFragment.AndroidCountryDropdown;
import auto.mobile.formcliatd.screenFragment.AndroidDatePicker;
import auto.mobile.formcliatd.screenFragment.IOSCountryDropdown;
import auto.mobile.formcliatd.screenFragment.IOSDatePicker;
import auto.mobile.formcliatd.screenObject.MobileBaseScreen;
import auto.mobile.formcliatd.utils.DateUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.stream.Stream;

public class PersonalTab extends MobileBaseScreen {

    private final IOSDatePicker iosDatePicker;
    private final AndroidDatePicker androidDatePicker;
    private final IOSCountryDropdown iosCountryDropdown;
    private final AndroidCountryDropdown androidCountryDropdown;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"fullName-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "fullName-errorMsg")
    public WebElement fullNameErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"address-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "address-errorMsg")
    public WebElement addressErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"city-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "city-errorMsg")
    public WebElement cityErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"postCode-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "postCode-errorMsg")
    public WebElement postCodeErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"phoneNumber-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "phoneNumber-errorMsg")
    public WebElement phoneErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"country-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "country-errorMsg")
    public WebElement countryErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"dateOfBirth-errorMsg\")")
    @iOSXCUITFindBy(accessibility = "dateOfBirth-errorMsg")
    public WebElement dobErrorField;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"fullName-content\")")
    @iOSXCUITFindBy(id = "fullName-content")
    private WebElement fullName;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Full Name\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Full Name\"`][2]")
    private WebElement fullNameLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"address-content\")")
    @iOSXCUITFindBy(id = "address-content")
    private WebElement address;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Address\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Address\"`][2]")
    private WebElement addressLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"city-content\")")
    @iOSXCUITFindBy(id = "city-content")
    private WebElement city;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"City\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"City\"`][2]")
    private WebElement cityLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"postCode-content\")")
    @iOSXCUITFindBy(id = "postCode-content")
    private WebElement postCode;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Post code\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Post code\"`][2]")
    private WebElement postCodeLabel;
    @AndroidFindBy(
            uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").instance(15)")
    @iOSXCUITFindBy(id = "ios_touchable_wrapper")
    private WebElement country;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Country\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Country\"`][2]")
    private WebElement countryLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"phoneNumber-content\")")
    @iOSXCUITFindBy(id = "phoneNumber-content")
    private WebElement phone;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Telephone\")")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Telephone\"`][2]")
    private WebElement phoneLabel;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"dateOfBirth-content\")")
    @iOSXCUITFindBy(id = "dateOfBirth-content")
    private WebElement dob;
    @AndroidFindBy(accessibility = "Next")
    @iOSXCUITFindBy(id = "Next-button")
    private WebElement nextBtn;

    public PersonalTab(AppiumDriver driver) {
        super(driver);
        logger = LogManager.getLogger(PersonalTab.class);
        iosDatePicker = new IOSDatePicker(this.driver);
        androidDatePicker = new AndroidDatePicker(this.driver);
        iosCountryDropdown = new IOSCountryDropdown(this.driver);
        androidCountryDropdown = new AndroidCountryDropdown(this.driver);
    }

    public String getContentOfErrorField(WebElement errorField) {
        return this.getPlatform().equalsIgnoreCase("ios")
                ? errorField.getDomAttribute("label")
                : errorField.getText();
    }

    @Step
    public boolean isAt() {
        logger.info("Waiting for Personal Tab to be loaded");
        Stream.of(this.fullNameLabel, this.addressLabel, this.cityLabel, this.postCodeLabel)
                .parallel()
                .forEach(WebElement::isDisplayed);
        return true;
    }

    @Step(value = "Get value in full name field")
    public String getFullName() {
        this.fullNameLabel.click();
        String value = this.getContentOfInputField(this.fullName);
        logger.info("Value in Full Name field: {} ", value);
        return value;
    }

    @Step(value = "Input value to full name field")
    public void setFullName(String value) {
        logger.info("Input value {} to Full Name field: ", value);
        setValueToInputField(this.fullName, value);
    }

    @Step(value = "Get value in address field")
    public String getAddress() {
        String value = this.getContentOfInputField(this.address);
        logger.info("Value in Address field: {} ", value);
        return value;
    }

    @Step(value = "Input value to address field")
    public void setAddress(String value) {
        logger.info("Input value {} to Address field: ", value);
        setValueToInputField(this.address, value);
    }

    @Step(value = "Get value in city field")
    public String getCity() {
        this.cityLabel.click();
        String value = this.getContentOfInputField(this.city);
        logger.info("Value in City field: {} ", value);
        return value;
    }

    @Step(value = "Input value to city field")
    public void setCity(String value) {
        logger.info("Input value {} to City field: ", value);
        setValueToInputField(this.city, value);
    }

    @Step(value = "Get value in postcode field")
    public String getPostCode() {
        this.postCodeLabel.click();
        String value = this.getContentOfInputField(this.postCode);
        logger.info("Value in Postcode field: {} ", value);
        return value;
    }

    @Step(value = "Input value to post-code field")
    public void setPostCode(String value) {
        logger.info("Input value {} to PostCode field: ", value);
        setValueToInputField(this.postCode, value);
    }

    @Step(value = "Get value in phone field")
    public String getPhone() {
        this.phoneLabel.click();
        String value = this.getContentOfInputField(this.phone);
        logger.info("Value in Phone field: {} ", value);
        return value;
    }

    @Step(value = "Input value to phone field")
    public void setPhone(String value) {
        logger.info("Input value {} to Phone field: ", value);
        setValueToInputField(this.phone, value);
    }

    @Step(value = "Set value in country field")
    public void setCountry(String countryName) {

        if (this.getPlatform().equalsIgnoreCase("ios")) {
            logger.info("Setting country in IOS platform");

            iosCountryDropdown.openCountryInIOS();
            iosCountryDropdown.scrollToCountryInIOS(countryName);
            iosCountryDropdown.touchAcceptDropdownInIOS();
        } else {
            logger.info("Setting country in Android platform");

            androidCountryDropdown.openCountryInAndroid();
            androidCountryDropdown.scrollToCountryInAndroid(countryName);
        }
        this.isAt();
    }

    @Step(value = "Get value in country field")
    public String getCountryCode() {
        return this.getPlatform().equalsIgnoreCase("ios")
                ? getCountryCodeInIOS()
                : getCountryCodeInAndroid();
    }

    @Step(value = "Get value in dob field")
    public DobPojo getDob() {
        return this.getPlatform().equalsIgnoreCase("ios") ? getDobInIOS() : getDobInAndroid();
    }

    @Step(value = "Set value in dob field")
    public void setDob(DobPojo dob) {

        if (this.getPlatform().equalsIgnoreCase("ios")) {
            logger.info("Setting dob in IOS platform");
            this.dob.click();
            setDobInIOSV1(dob);
        } else {
            logger.info("Setting dob in Android platform");
            DobPojo currentDisplayingDob = this.getDob();
            this.dob.click();
            setDobInAndroidV1(currentDisplayingDob, dob);
        }
    }

    @Step(value = "Click Next")
    public void clickNext() {
        this.nextBtn.click();
    }

    @Step(value = "Select dob in IOS")
    private void setDobInIOSV1(DobPojo dob) {

        DobPojo targetDob = iosDatePicker.setDob(dob);
        DobPojo currentDob = iosDatePicker.setDob(this.getDob());

        // Wait the date-picker until load value as current displaying dob
        this.wait.until(d -> iosDatePicker.isPickerLoadedCurrentDate());

        // Scroll to targetDob based on currentDob
        iosDatePicker.selectDobFrom(currentDob, targetDob);

        // Confirm
        iosDatePicker.submitSettingDob();
        this.isAt();
    }

    @Step(value = "Select dob in Android")
    private void setDobInAndroidV1(DobPojo currentDob, DobPojo targetDob) {

        // Select year
        androidDatePicker.setYear(currentDob.getYear(), targetDob.getYear());

        // Select month
        androidDatePicker.setMonth(currentDob.getMonth(), targetDob.getMonth());

        // Select day
        androidDatePicker.setDay(targetDob.getDay());

        // Click on [OK]
        androidDatePicker.submitSettingDob();
        this.isAt();
    }

    @Step(value = "Get country code in IOS")
    private String getCountryCodeInIOS() {
        String countryCode = Objects.requireNonNull(this.country.getDomAttribute("label")).trim();
        logger.info("Get content of country field as country_code in IOS: {}", countryCode);
        return Objects.requireNonNull(this.country.getDomAttribute("label")).trim();
    }

    @Step(value = "Get country code in Android")
    private String getCountryCodeInAndroid() {
        String countryCodeLocator = "new UiSelector().resourceId(\"testID-testInputProps\")";
        String countryCode =
                Objects.requireNonNull(
                                this.driver
                                        .findElement(AppiumBy.androidUIAutomator(countryCodeLocator))
                                        .getDomAttribute("content-desc"))
                        .trim();
        logger.info("Get content of country field as country_code in Android: {}", countryCode);
        return countryCode;
    }

    @Step(value = "Get dob in IOS")
    private DobPojo getDobInIOS() {
        String dobContent = Objects.requireNonNull(this.dob.getDomAttribute("label")).trim();
        logger.info("Get content of dob field in IOS: {}", dobContent);

        if (dobContent.equalsIgnoreCase(AppConstant.DEFAULT_DOB_PLACEHOLDER)) {
            return DobPojo.builder()
                    .day(String.valueOf(LocalDate.now().getDayOfMonth()))
                    .month(String.valueOf(LocalDate.now().getMonthOfYear()))
                    .year(String.valueOf(LocalDate.now().getYear()))
                    .build();
        }
        LocalDate date =
                DateUtils.parseStringAsDatePattern(
                        dobContent, DateTimeFormat.forPattern(TestConstant.DATE_PATTERN));
        return DobPojo.builder()
                .day(String.valueOf(date.getDayOfMonth()))
                .month(String.valueOf(date.getMonthOfYear()))
                .year(String.valueOf(date.getYear()))
                .build();
    }

    @Step(value = "Get dob in Android")
    private DobPojo getDobInAndroid() {
        String dobContent = Objects.requireNonNull(this.dob.getText()).trim();

        if (dobContent.equalsIgnoreCase(AppConstant.DEFAULT_DOB_PLACEHOLDER)) {
            return DobPojo.builder()
                    .day(String.valueOf(LocalDate.now().getDayOfMonth()))
                    .month(String.valueOf(LocalDate.now().getMonthOfYear()))
                    .year(String.valueOf(LocalDate.now().getYear()))
                    .build();
        }
        logger.info("Get content of dob field in Android: {}", dobContent);
        LocalDate date =
                DateUtils.parseStringAsDatePattern(
                        dobContent, DateTimeFormat.forPattern(AppConstant.DISPLAYED_DATE_FORMAT_ANDROID));
        return DobPojo.builder()
                .day(String.valueOf(date.getDayOfMonth()))
                .month(String.valueOf(date.getMonthOfYear()))
                .year(String.valueOf(date.getYear()))
                .build();
    }

}
