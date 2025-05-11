package auto.mobile.formcliatd.config;

import io.appium.java_client.AppiumDriver;

public final class AppiumDriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    public static AppiumDriver getAppiumDriver() {
        return driverThreadLocal.get();
    }

    public static void setAppiumDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void clearAppiumThread() {
        driverThreadLocal.remove();
    }
}
