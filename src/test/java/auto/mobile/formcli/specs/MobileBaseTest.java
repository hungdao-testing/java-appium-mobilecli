package auto.mobile.formcli.specs;

import auto.mobile.formcli.config.AppiumDesiredCapsBuilder;
import auto.mobile.formcli.config.AppiumDriverManager;
import auto.mobile.formcli.config.ConfigurationManager;
import auto.mobile.formcli.pojo.config.MobileCapPojo;
import auto.mobile.formcli.utils.JsonConverter;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class MobileBaseTest {


    private static final ThreadLocal<AppiumDriverLocalService> appiumServiceThread =
            new ThreadLocal<>();
    private final Logger logger = LogManager.getLogger();

    @BeforeTest(description = "Start appium server")
    @Parameters({"platform"})
    public void startAppiumServer(String platform) throws URISyntaxException, MalformedURLException {
        logger.info("Start appium server with defined configuration");
        setAppiumLocalService();
//        appiumServiceThread.get().start();
    }

    @AfterTest(description = "Stop appium driver and server")
    public void stopAppiumServer() {
        AppiumDriverManager.clearAppiumThread();
        appiumServiceThread.get().stop();
    }

    @BeforeClass(description = "Setup mobile driver")
    @Parameters({"platform"})
    public void setUpMobileDriver(String platform) {
        logger.info("Setup appium driver for platform {}", platform);
        AppiumDriverManager.setAppiumDriver(appiumServiceThread.get(), platform, setUpCapability(platform));

    }

    @AfterClass(description = "Shutdown mobile driver")
    @Parameters({"platform"})
    public void tearDownMobileDriver(String platform) {
        logger.info("Shutdown appium driver");
        if (AppiumDriverManager.getAppiumDriver() != null) {
            AppiumDriverManager.getAppiumDriver().quit();
        }
    }

    private void setAppiumLocalService() {
        appiumServiceThread.set(
                new AppiumServiceBuilder()
                        .withIPAddress(ConfigurationManager.getHost())
                        .usingPort(ConfigurationManager.getPort())
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
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
