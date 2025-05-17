package auto.mobile.formcli.config.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSDriverImpl implements IMobileDriver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public AppiumDriver createDriver(DesiredCapabilities capabilities) {
        XCUITestOptions options = new XCUITestOptions();
        capabilities.asMap().forEach(options::setCapability);
        logger.info("[Local] Create IOS driver with option: {}", options);
        return new IOSDriver(options);
    }

    @Override
    public AppiumDriver createDriver(String remoteUrl, DesiredCapabilities capabilities) {
        XCUITestOptions options = new XCUITestOptions();
        capabilities.asMap().forEach(options::setCapability);

        try {
            logger.info("[Remote] Create IOS driver with option: {} - at url:  {}", options, remoteUrl);
            return new IOSDriver(new URL(remoteUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
