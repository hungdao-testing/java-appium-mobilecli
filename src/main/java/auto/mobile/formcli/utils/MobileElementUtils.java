package auto.mobile.formcli.utils;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

public class MobileElementUtils {

    private static Rectangle getRect(WebElement mobileElement) {
        return mobileElement.getRect();
    }

    public static int getWidthOf(WebElement mobileElement) {
        return getRect(mobileElement).getWidth();
    }

    public static int getHeightOf(WebElement mobileElement) {
        return getRect(mobileElement).getHeight();
    }

    public static int getLeftCornerPositionOf(WebElement mobileElement) {
        return getRect(mobileElement).getX();
    }

    public static int getTopCornerPositionOf(WebElement mobileElement) {
        return getRect(mobileElement).getY();
    }

    public static int getRightCornerPositionOf(WebElement mobileElement) {
        return getWidthOf(mobileElement) + getLeftCornerPositionOf(mobileElement);
    }

    public static int getBottomCornerPositionOf(WebElement mobileElement) {
        return getTopCornerPositionOf(mobileElement) + getHeightOf(mobileElement);
    }

    public static int getCenterX(WebElement mobileElement) {
        Rectangle rect = mobileElement.getRect();
        int x = rect.getX();
        return x + rect.width / 2;
    }

    public static int get90PercentY(WebElement mobileElement) {
        Rectangle rect = mobileElement.getRect();
        int y = rect.getY();
        return (int) (y + rect.height * 0.9);
    }

    public static int getPercentY(WebElement mobileElement, double percent) {
        Rectangle rect = mobileElement.getRect();
        int y = rect.getY();
        return (int) (y + rect.height * percent);
    }

    public static int getCenterY(WebElement mobileElement) {
        Rectangle rect = mobileElement.getRect();
        return rect.getY() + rect.getHeight() / 2;
    }
}
