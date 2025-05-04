package auto.mobile.formcli.screenFragment;

import auto.mobile.formcli.constants.AppConstant;
import auto.mobile.formcli.constants.PointWay;
import auto.mobile.formcli.constants.ScrollDirection;
import auto.mobile.formcli.screenObject.MobileBaseScreen;
import auto.mobile.formcli.utils.MobileActionUtils;
import auto.mobile.formcli.utils.MobileElementUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.Objects;

public class IOSCountryDropdown extends MobileBaseScreen {

    private final By byCountryDropdown = AppiumBy.id("ios_touchable_wrapper");
    private final By byCountryScrollElement = AppiumBy.className("XCUIElementTypePickerWheel");
    private final By byOutSideAreaOfCountryScrollableElement = AppiumBy.accessibilityId("ios_modal_top");

    public IOSCountryDropdown(AppiumDriver driver) {
        super(driver);
    }

    @Step(value = "Open country dropdown")
    public void openCountryInIOS() {

        WebElement countryDropdown = this.driver.findElement(byCountryDropdown);
        int x = MobileElementUtils.getRightCornerPositionOf(countryDropdown) - 5;
        int y = MobileElementUtils.getBottomCornerPositionOf(countryDropdown) - 5;

        logger.info("Open country dropdown by touching coordinate x: {}, y: {}", x, y);
        MobileActionUtils.touchOnCoordinate(x, y);
    }

    @Step(value = "Scroll to select country")
    public void scrollToCountryInIOS(String countryName) {

        ScrollDirection direction = ScrollDirection.UP;

        if (!Objects.requireNonNull(getCountryScrollElement().getDomAttribute("value"))
                .trim()
                .contains(AppConstant.DEFAULT_COUNTRY_PLACEHOLDER)) {
            direction =
                    MobileActionUtils.setDirectionToScroll(getCurrentCountryOptionInScroll(), countryName);
        }

        Map<PointWay, Point> pointMap =
                MobileActionUtils.setPointsToScroll(direction, getCountryScrollElement());

        while (true) {

            if (Objects.requireNonNull(getCurrentCountryOptionInScroll()).equalsIgnoreCase(countryName)) {
                getCountryScrollElement().sendKeys(countryName);
                break;
            }

            logger.info(
                    "Scrolling the country dropdown by coordinate (from - to):  {} - {}",
                    pointMap.get(PointWay.FROM),
                    pointMap.get(PointWay.TO));

            MobileActionUtils.scrollToSelect(pointMap.get(PointWay.FROM), pointMap.get(PointWay.TO));
        }
    }

    @Step(value = "Touch [Done] button")
    public void touchAcceptDropdownInIOS() {
        WebElement doneButton = driver.findElement(byOutSideAreaOfCountryScrollableElement);
        logger.info("Touch outside the dropdown dialog");
        doneButton.click();
    }

    private WebElement getCountryScrollElement() {
        return this.driver.findElement(byCountryScrollElement);
    }

    private String getCurrentCountryOptionInScroll() {
        return Objects.requireNonNull(getCountryScrollElement().getDomAttribute("value")).trim();
    }
}
