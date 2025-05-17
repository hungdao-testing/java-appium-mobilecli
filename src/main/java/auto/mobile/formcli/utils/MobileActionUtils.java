package auto.mobile.formcli.utils;


import auto.mobile.formcli.config.driver.FwDriverManager;
import auto.mobile.formcli.constants.PointWay;
import auto.mobile.formcli.constants.ScrollDirection;
import auto.mobile.formcli.constants.TestConstant;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MobileActionUtils {

    private static final Logger logger = LogManager.getLogger();

    /**
     * The method does action `touch-by-input-coordination`
     *
     * @param x the horizontal coordination
     * @param y the vertical coordination
     */
    @Step(value = "Touch by coordination")
    public static void touchOnCoordinate(int x, int y) {
        PointerInput touchInput = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequenceAction = new Sequence(touchInput, 0);

        sequenceAction.addAction(
                touchInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        sequenceAction.addAction(touchInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        FwDriverManager.getAppiumDriver().perform(List.of(sequenceAction));
    }

    /**
     * The method does action `scroll` from startPoint -> to endPoint in the view-port of the mobile device
     *
     * @param startPoint the point to start scroll
     * @param endPoint   the target to scroll to
     */
    @Step(value = "Scrolling from point1 -> point2")
    public static void scrollToSelect(Point startPoint, Point endPoint) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);

        scroll.addAction(
                finger.createPointerMove(
                        Duration.ZERO, PointerInput.Origin.viewport(), startPoint.getX(), startPoint.getY()));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(500),
                        PointerInput.Origin.viewport(),
                        endPoint.getX(),
                        endPoint.getY()));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        FwDriverManager.getAppiumDriver().perform(List.of(scroll));
    }

    /**
     * The method helps to find scroll-direction (UP/DOWN) from the `basedValue` to the `targetValue`
     *
     * @param basedValue  the based value
     * @param targetValue the target value
     * @return ScrollDirection
     * @see ScrollDirection
     */
    @Step(value = "Setting scroll-direction by comparing input values")
    public static ScrollDirection setDirectionToScroll(
            String basedValue, String targetValue) {
        List<String> sortedArr =
                Stream.of(basedValue, targetValue).sorted(String::compareTo).toList();
        if (sortedArr.get(0).equalsIgnoreCase(targetValue)) {
            logger.info("The scroll direction from top -> bottom");
            return ScrollDirection.DOWN;
        } else {
            logger.info("The scroll direction from bottom -> up");
            return ScrollDirection.UP;
        }
    }

    public static Map<PointWay, Point> setPointsToScroll(
            ScrollDirection scrollDirection, WebElement scrollableElement) {
        Point startPoint;
        Point endPoint;
        Map<PointWay, Point> pointMap = new HashMap<>();

        if (scrollDirection
                .getScrollDirection()
                .equalsIgnoreCase(ScrollDirection.UP.getScrollDirection())) {
            startPoint =
                    new Point(
                            MobileElementUtils.getCenterX(scrollableElement),
                            MobileElementUtils.getPercentY(
                                    scrollableElement, TestConstant.DEFAULT_SCROLL_OFFSET));
            endPoint =
                    new Point(
                            MobileElementUtils.getCenterX(scrollableElement),
                            MobileElementUtils.getCenterY(scrollableElement));
        } else {
            startPoint =
                    new Point(
                            MobileElementUtils.getCenterX(scrollableElement),
                            MobileElementUtils.getCenterY(scrollableElement));
            endPoint =
                    new Point(
                            MobileElementUtils.getCenterX(scrollableElement),
                            MobileElementUtils.getPercentY(
                                    scrollableElement, TestConstant.DEFAULT_SCROLL_OFFSET));
        }

        pointMap.put(PointWay.FROM, startPoint);
        pointMap.put(PointWay.TO, endPoint);
        return pointMap;
    }
}
