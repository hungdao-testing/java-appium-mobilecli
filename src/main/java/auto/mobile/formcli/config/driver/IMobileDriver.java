package auto.mobile.formcli.config.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface IMobileDriver {

     AppiumDriver createDriver(DesiredCapabilities capabilities);
     AppiumDriver createDriver(String remoteUrl, DesiredCapabilities capabilities);

    AppiumDriver createDriver(AppiumDriverLocalService service, DesiredCapabilities capabilities);

}
