package auto.mobile.formcli.screenFragment;

import auto.mobile.formcli.constants.AppConstant;
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

public class AndroidCountryDropdown extends MobileBaseScreen {

    private final By byCountryDropdown =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.view.ViewGroup\").instance(15)");

    private final By byCountryScrollElement = AppiumBy.id("com.formscli:id/select_dialog_listview");
    private final By byCountrOptionsInScrollableElement =
            AppiumBy.className("android.widget.CheckedTextView");

    public AndroidCountryDropdown(AppiumDriver driver) {
        super(driver);
    }

    @Step(value = "Open country dropdown")
    public void openCountryInAndroid() {
        WebElement countryDropdown = this.driver.findElement(byCountryDropdown);
        logger.info("Open country dropdown in Android ");
        countryDropdown.click();
    }

    @Step(value = "Select country")
    public void scrollToCountryInAndroid(String countryName) {

        ScrollDirection direction = ScrollDirection.UP;
        WebElement scrollableCountryList = this.driver.findElement(byCountryScrollElement);

        List<WebElement> countryOptions =
                scrollableCountryList.findElements(byCountrOptionsInScrollableElement);

        if (!countryOptions.get(0).getText().trim().contains(AppConstant.DEFAULT_COUNTRY_PLACEHOLDER)) {
            direction =
                    MobileActionUtils.setDirectionToScroll(countryOptions.get(0).getText(), countryName);
        }

        Map<PointWay, Point> pointMap =
                MobileActionUtils.setPointsToScroll(direction, scrollableCountryList);

        while (true) {

            if (countryOptions.stream().map(WebElement::getText).toList().contains(countryName)) {
                countryOptions.stream()
                        .filter(el -> el.getText().equalsIgnoreCase(countryName))
                        .findFirst()
                        .ifPresent(WebElement::click);
                break;
            }

            logger.info(
                    "Scrolling the country dropdown in Android by coordinate (from - to):  {} - {}",
                    pointMap.get(PointWay.FROM),
                    pointMap.get(PointWay.TO));

            MobileActionUtils.scrollToSelect(pointMap.get(PointWay.FROM), pointMap.get(PointWay.TO));

            countryOptions = scrollableCountryList.findElements(byCountrOptionsInScrollableElement);
        }
    }
}
