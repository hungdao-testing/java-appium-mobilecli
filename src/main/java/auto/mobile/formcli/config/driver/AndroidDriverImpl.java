package auto.mobile.formcli.config.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverImpl implements IMobileDriver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public AppiumDriver createDriver(DesiredCapabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        capabilities.asMap().forEach(options::setCapability);
        logger.info("[Local] Create Android driver with option: {}", options);
        return new AndroidDriver(options);
    }

    @Override
    public AppiumDriver createDriver(String remoteUrl, DesiredCapabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        capabilities.asMap().forEach(options::setCapability);

        try {
            logger.info("[Remote] Create Android driver with option: {} - at url:  {}", options, remoteUrl);
            return new AndroidDriver(new URL(remoteUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
