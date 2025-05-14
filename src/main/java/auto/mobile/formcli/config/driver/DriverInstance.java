package auto.mobile.formcli.config.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverInstance {

    AppiumDriver createInstance(AppiumDriverLocalService service, DesiredCapabilities desiredCaps);

    AppiumDriver createInstance(DesiredCapabilities desiredCaps);
}
