package auto.mobile.formcli.config.target;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BsAppiumService {
    private static final Logger logger = LogManager.getLogger();

    public static AppiumDriverLocalService buildAppiumService(DesiredCapabilities capabilities) {
        logger.info("Setting appium service for Browserstack");
        return new AppiumServiceBuilder()
                .withCapabilities(capabilities)
                .build();
    }
}
