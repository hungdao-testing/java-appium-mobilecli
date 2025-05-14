package auto.mobile.formcli.config;

import auto.mobile.formcli.config.driver.AndroidDriverImpl;
import auto.mobile.formcli.config.driver.IOSDriverImpl;
import auto.mobile.formcli.constants.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;


public final class AppiumDriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    public static AppiumDriver getAppiumDriver() {
        return driverThreadLocal.get();
    }

    public static void setAppiumDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void setAppiumDriver(String platform, DesiredCapabilities desiredCapabilities) {
        if (Objects.equals(platform, MobilePlatform.ANDROID.getPlatform())) {
            driverThreadLocal.set(new AndroidDriverImpl().createInstance(desiredCapabilities));
        } else if (Objects.equals(platform, MobilePlatform.IOS.getPlatform())) {
            driverThreadLocal.set(new IOSDriverImpl().createInstance(desiredCapabilities));
        }
    }

    public static void setAppiumDriver(AppiumDriverLocalService localService, String platform, DesiredCapabilities desiredCapabilities) {
        if (Objects.equals(platform, MobilePlatform.ANDROID.getPlatform())) {
            setAppiumDriver(new AndroidDriverImpl().createInstance(localService, desiredCapabilities));
        } else if (Objects.equals(platform, MobilePlatform.IOS.getPlatform())) {
            setAppiumDriver(new IOSDriverImpl().createInstance(localService, desiredCapabilities));
        }

    }

    public static void clearAppiumThread() {
        driverThreadLocal.remove();
    }


}
