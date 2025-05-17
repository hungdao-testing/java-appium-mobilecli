package auto.mobile.formcli.config.driver;

import auto.mobile.formcli.constants.MobilePlatform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class FwDriverManager {

    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();


    public static AppiumDriver getAppiumDriver() {
        return driverThreadLocal.get();
    }

    public static void setAppiumDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }


    /**
     * Set up appium driver for local running
     * */
    public static void setAppiumDriver(MobilePlatform platform, DesiredCapabilities capabilities) {
        AppiumDriver driver = null;

        if (MobilePlatform.ANDROID == platform) {
            driver = new AndroidDriverImpl().createDriver(capabilities);
        } else if (MobilePlatform.IOS == platform) {
            driver = new IOSDriverImpl().createDriver(capabilities);
        }
        setAppiumDriver(driver);
    }

    /**
     * Set up appium driver for remote running
     * */
    public static void setAppiumDriver(String remoteUrl, MobilePlatform platform, DesiredCapabilities capabilities) {
        AppiumDriver driver = null;

        if (MobilePlatform.ANDROID == platform) {
            driver = new AndroidDriverImpl().createDriver(remoteUrl, capabilities);
        } else if (MobilePlatform.IOS == platform) {
            driver = new IOSDriverImpl().createDriver(remoteUrl, capabilities);
        }

        setAppiumDriver(driver);
    }

    public static void clearAppiumThread() {
        driverThreadLocal.remove();
    }


}
