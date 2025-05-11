package auto.mobile.formcliatd.screenFragment;


import auto.mobile.formcliatd.constants.AppConstant;
import auto.mobile.formcliatd.constants.PointWay;
import auto.mobile.formcliatd.constants.ScrollDirection;
import auto.mobile.formcliatd.pojo.DobPojo;
import auto.mobile.formcliatd.screenObject.MobileBaseScreen;
import auto.mobile.formcliatd.utils.DateUtils;
import auto.mobile.formcliatd.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static auto.mobile.formcli.constants.AppConstant.DISPLAYED_DATE_FORMAT_IOS;

public class IOSDatePicker extends MobileBaseScreen {

    private final By BY_IOS_PICKER = AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel");
    private final By BY_CONFIRM_BTN = AppiumBy.accessibilityId("confirmDateSelectionBtn");

    public IOSDatePicker(AppiumDriver driver) {
        super(driver);
    }

    public DobPojo setDob(DobPojo dob) {
        String date;
        if (dob.toString().contains(AppConstant.DEFAULT_DOB_PLACEHOLDER)) {
            date = DateUtils.getCurrentDate(DateTimeFormat.forPattern(DISPLAYED_DATE_FORMAT_IOS));
            return DobPojo.builder()
                    .day(DateUtils.getDay(date, DateTimeFormat.forPattern(DISPLAYED_DATE_FORMAT_IOS)))
                    .month(
                            DateUtils.getMonthAsText(date, DateTimeFormat.forPattern(DISPLAYED_DATE_FORMAT_IOS)))
                    .year(DateUtils.getFullYear(date, DateTimeFormat.forPattern(DISPLAYED_DATE_FORMAT_IOS)))
                    .build();
        }
        return dob;
    }

    private List<WebElement> getPickerElement() {
        return this.driver.findElements(BY_IOS_PICKER);
    }

    /**
     * This method helps to select the dob (date-of-birth) by scrolling date-picker-wheel element
     * <p>
     * from a beginning point `fromDob`
     *
     * @param fromDob   the beginning point to start to scroll
     * @param targetDob the target dob to scroll to
     */
    @Step(value = "select dob by scrolling from a based value")
    public void selectDobFrom(DobPojo fromDob, DobPojo targetDob) {
        scrollFromVal1ToVal2(getDayElement(), fromDob.getDay(), targetDob.getDay());

        // Scroll to select month
        ScrollDirection monthScrollDirection =
                MobileActionUtils.setDirectionToScroll(fromDob.getMonth(), targetDob.getMonth());
        scrollPickerElement(
                getMonthElement(),
                monthScrollDirection,
                DateUtils.getMonthAsText(
                        targetDob.toString(), DateTimeFormat.forPattern(DISPLAYED_DATE_FORMAT_IOS)));

        // Scroll to select year
        scrollFromVal1ToVal2(getYearElement(), fromDob.getYear(), targetDob.getYear());
    }

    @Step(value = "submit setup dob")
    public void submitSettingDob() {
        this.driver.findElement(BY_CONFIRM_BTN).click();
    }


    /**
     * The method helps to ensure the displaying date-wheel-picker shows correct current date values
     * as the setting
     */
    @Step(value = "Checking the dob picker loads current date")
    public boolean isPickerLoadedCurrentDate() {

        LocalDate date = LocalDate.now();
        this.wait.until(
                d ->
                        Objects.requireNonNull(getValueOfPickerElement(getDayElement()))
                                .equalsIgnoreCase(String.valueOf(date.getDayOfMonth())));
        this.wait.until(
                d ->
                        Objects.requireNonNull(getValueOfPickerElement(getMonthElement()))
                                .equalsIgnoreCase(DateUtils.getMonthAsText(date)));
        this.wait.until(
                d ->
                        Objects.requireNonNull(getValueOfPickerElement(getYearElement()))
                                .equalsIgnoreCase(String.valueOf(date.getYear())));

        return true;
    }

    private WebElement getDayElement() {
        return getPickerElement().get(0);
    }

    private WebElement getMonthElement() {
        return getPickerElement().get(1);
    }

    private WebElement getYearElement() {
        return getPickerElement().get(2);
    }

    private void scrollFromVal1ToVal2(WebElement picker, String val1, String val2) {
        ScrollDirection scrollDirection = MobileActionUtils.setDirectionToScroll(val1, val2);
        scrollPickerElement(picker, scrollDirection, val2);
    }

    private void scrollPickerElement(
            WebElement pickerElement, ScrollDirection scrollDirection, String value) {

        Map<PointWay, Point> pointMap =
                MobileActionUtils.setPointsToScroll(scrollDirection, pickerElement);

        while (true) {

            if (Objects.requireNonNull(getValueOfPickerElement(pickerElement))
                    .trim()
                    .equalsIgnoreCase(value)) {
                pickerElement.sendKeys(value);
                break;
            }

            logger.info(
                    "Scrolling the picker dropdown by coordinate (from - to):  {} - {}",
                    pointMap.get(PointWay.FROM),
                    pointMap.get(PointWay.TO));

            MobileActionUtils.scrollToSelect(pointMap.get(PointWay.FROM), pointMap.get(PointWay.TO));
        }
    }

    private String getValueOfPickerElement(WebElement pickerElement) {
        return pickerElement.getDomAttribute("value");
    }

}
