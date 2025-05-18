package auto.mobile.formcli.specs;


import auto.mobile.formcli.config.driver.FwDesiredCapsBuilder;
import auto.mobile.formcli.config.driver.FwDriverManager;
import auto.mobile.formcli.constants.MobilePlatform;
import auto.mobile.formcli.pojo.AppiumServerPojo;
import auto.mobile.formcli.pojo.MobileCapPojo;
import auto.mobile.formcli.utils.JsonConverter;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.Objects;

public class MobileBaseTest {


    private static final String APPIUM_PLUGIN_ELEMENT_WAIT = "element-wait";
    private static final ThreadLocal<AppiumDriverLocalService> appiumServiceThread =
            new ThreadLocal<>();
    private final Logger logger = LogManager.getLogger();

    @BeforeTest(description = "Start appium server")
    @Parameters({"platform"})
    public void startAppiumServer(String platform) {
        logger.info("Setup appium server for platform {}", platform);
        setAppiumService(platform);
//        appiumServiceThread.get().start();
    }

    @AfterTest(description = "Stop appium driver and server")
    public void stopAppiumServer() {
        FwDriverManager.clearAppiumThread();
    }

    @BeforeClass(description = "Setup mobile driver")
    @Parameters({"platform"})
    public void setUpMobileDriver(String platform) {
        logger.info("Setup appium driver for platform {}", platform);
        FwDriverManager.setAppiumDriver(appiumServiceThread.get(),
                Objects.equals(platform, MobilePlatform.IOS.getValue()) ? MobilePlatform.IOS : MobilePlatform.ANDROID,
                setUpCapability(platform));
    }

    @AfterClass(description = "Shutdown mobile driver")
    @Parameters({"platform"})
    public void tearDownMobileDriver(String platform) {
        if (FwDriverManager.getAppiumDriver() != null) {
            logger.info("Shutdown appium driver");
            FwDriverManager.getAppiumDriver().quit();
        }
    }

    private void setAppiumService(String platform) {
        AppiumServerPojo appiumServerConfig = setUpAppiumServerInfo();
        appiumServiceThread.set(
                new AppiumServiceBuilder()
                        .withCapabilities(setUpCapability(platform))
//                        .withArgument(GeneralServerFlag.USE_PLUGINS, APPIUM_PLUGIN_ELEMENT_WAIT)
                        .usingPort(Integer.parseInt(appiumServerConfig.getPort()))
                        .withIPAddress(appiumServerConfig.getAddress())
                        .withLogFile(new File(appiumServerConfig.getLogPath()))
                        .build());
    }

    private DesiredCapabilities setUpCapability(String platform) {
        MobileCapPojo mobileCaps =
                JsonConverter.parseJsonAs(
                        String.format("src/test/resources/mobile/caps/%s.cap.json", platform),
                        MobileCapPojo.class);
        return FwDesiredCapsBuilder.setMobileCaps(mobileCaps);
    }

    private AppiumServerPojo setUpAppiumServerInfo() {
        return JsonConverter.parseJsonAs(
                "src/test/resources/appium.server.json",
                AppiumServerPojo.class);
    }
}
