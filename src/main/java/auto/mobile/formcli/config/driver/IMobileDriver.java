package auto.mobile.formcli.config.driver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface IMobileDriver {

     AppiumDriver createDriver(DesiredCapabilities capabilities);
     AppiumDriver createDriver(String remoteUrl, DesiredCapabilities capabilities);

}
