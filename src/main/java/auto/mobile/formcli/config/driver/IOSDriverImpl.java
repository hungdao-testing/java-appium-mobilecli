package auto.mobile.formcli.config.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IOSDriverImpl implements DriverInstance {

    private AppiumDriver driver = null;

    @Override
    public AppiumDriver createInstance(AppiumDriverLocalService service, DesiredCapabilities desiredCaps) {
        driver = new IOSDriver(service, desiredCaps);
        return driver;
    }

    @Override
    public AppiumDriver createInstance(DesiredCapabilities desiredCaps) {
        return null;
    }
}
