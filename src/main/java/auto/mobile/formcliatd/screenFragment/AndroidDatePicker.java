package auto.mobile.formcliatd.screenFragment;


import auto.mobile.formcli.constants.PointWay;
import auto.mobile.formcli.constants.ScrollDirection;
import auto.mobile.formcli.screenObject.MobileBaseScreen;
import auto.mobile.formcli.utils.MobileActionUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AndroidDatePicker extends MobileBaseScreen {
    private final By BY_YEAR_PICKER_HEADER = AppiumBy.id("android:id/date_picker_header_year");
    private final By BY_YEAR_PICKER = AppiumBy.id("android:id/date_picker_year_picker");
    private final By BY_TEXT_CLASS = AppiumBy.className("android.widget.TextView");
    private final By BY_VIEW_CLASS = AppiumBy.className("android.view.View");
    private final By BY_PREV_MONTH_BTN = AppiumBy.accessibilityId("Previous month");
    private final By BY_NEXT_MONTH_BTN = AppiumBy.accessibilityId("Next month");
    private final By BY_CONFIRM_BTN = AppiumBy.id("android:id/button1");

    public AndroidDatePicker(AppiumDriver driver) {
        super(driver);
    }


    @Step(value = "select year value")
    public void setYear(String fromYear, String targetYear) {
        this.driver.findElement(BY_YEAR_PICKER_HEADER).click();
        ScrollDirection direction = MobileActionUtils.setDirectionToScroll(fromYear, targetYear);
        scrollYearPickerWith(this.driver.findElement(BY_YEAR_PICKER), direction, targetYear);
    }

    @Step(value = "select month value")
    public void setMonth(String fromMonth, String toMonth) {

        WebElement navigateButton = setMonthNavigationBtn(fromMonth, toMonth);

        for (int i = 0; i < Math.abs((Integer.parseInt(fromMonth) - Integer.parseInt(toMonth))); i++) {
            navigateButton.click();
        }
    }

    @Step(value = "select day value")
    public void setDay(String targetDay) {
        List<WebElement> dayOptionsInMonth = driver.findElements(BY_VIEW_CLASS);
        dayOptionsInMonth.stream()
                .filter(opt -> opt.isEnabled() && opt.getText().equalsIgnoreCase(targetDay))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    @Step(value = "submit setting dob")
    public void submitSettingDob() {
        this.driver.findElement(BY_CONFIRM_BTN).click();
    }

    private void scrollYearPickerWith(
            WebElement pickerElement, ScrollDirection scrollDirection, String value) {

        Map<PointWay, Point> pointMap =
                MobileActionUtils.setPointsToScroll(scrollDirection, pickerElement);

        while (true) {

            List<WebElement> yearOptions = pickerElement.findElements(BY_TEXT_CLASS);
            if (Objects.requireNonNull(yearOptions.stream().map(WebElement::getText))
                    .toList()
                    .contains(value)) {
                yearOptions.stream()
                        .filter(opt -> opt.getText().equalsIgnoreCase(value))
                        .findFirst()
                        .ifPresent(WebElement::click);
                break;
            }

            logger.info(
                    "Scrolling the year picker dropdown by coordinate (from - to):  {} - {}",
                    pointMap.get(PointWay.FROM),
                    pointMap.get(PointWay.TO));

            MobileActionUtils.scrollToSelect(pointMap.get(PointWay.FROM), pointMap.get(PointWay.TO));
        }
    }

    private WebElement getPrevMonthBtn() {
        return this.driver.findElement(BY_PREV_MONTH_BTN);
    }

    private WebElement getNextMonthBtn() {
        return this.driver.findElement(BY_NEXT_MONTH_BTN);
    }

    private WebElement setMonthNavigationBtn(String fromMonth, String toMonth) {
        if (Integer.parseInt(fromMonth) > Integer.parseInt(toMonth)) {
            return getPrevMonthBtn();
        } else {
            return getNextMonthBtn();
        }
    }
}
