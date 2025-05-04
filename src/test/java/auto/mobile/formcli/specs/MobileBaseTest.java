package auto.mobile.formcli.specs;

import auto.mobile.formcli.config.AppiumDesiredCapsBuilder;
import auto.mobile.formcli.config.AppiumDriverManager;
import auto.mobile.formcli.config.MobileCapPojo;
import auto.mobile.formcli.utils.JsonConverter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;

public class MobileBaseTest {

    private static final String APPIUM_PLUGIN_ELEMENT_WAIT = "element-wait";
    private static final ThreadLocal<AppiumDriverLocalService> appiumServiceThread =
            new ThreadLocal<>();
    private final Logger logger = LogManager.getLogger();

    @BeforeTest(description = "Start appium server")
    @Parameters({"platform"})
    public void startAppiumServer(String platform) {
        logger.info("Start appium server in platform {}", platform);
        setAppiumService(platform);
        appiumServiceThread.get().start();
    }

    @AfterTest(description = "Stop appium driver and server")
    public void stopAppiumServer() {
        AppiumDriverManager.clearAppiumThread();
    }

    @BeforeClass(description = "Setup mobile driver")
    @Parameters({"platform"})
    public void setUpMobileDriver(String platform) {
        logger.info("Setup appium driver for platform {}", platform);
        AppiumDriver driver = new AppiumDriver(appiumServiceThread.get(), setUpCapability(platform));
        AppiumDriverManager.setAppiumDriver(driver);
    }

    @AfterClass(description = "Shutdown mobile driver")
    @Parameters({"platform"})
    public void tearDownMobileDriver(String platform) {
        logger.info("Shutdown appium driver");
        if (AppiumDriverManager.getAppiumDriver() != null) {
            AppiumDriverManager.getAppiumDriver().quit();
        }
    }

    private void setAppiumService(String platform) {
        appiumServiceThread.set(
                new AppiumServiceBuilder()
                        .withCapabilities(setUpCapability(platform))
                        .withArgument(GeneralServerFlag.USE_PLUGINS, APPIUM_PLUGIN_ELEMENT_WAIT)
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                        .usingAnyFreePort()
                        .withLogFile(new File("logs/appium.log"))
                        .build());
    }

    private DesiredCapabilities setUpCapability(String platform) {
        MobileCapPojo mobileCaps =
                JsonConverter.parseJsonAs(
                        String.format("src/test/resources/mobile/caps/%s.cap.json", platform),
                        MobileCapPojo.class);
        return AppiumDesiredCapsBuilder.setMobileCaps(mobileCaps);
    }
}
