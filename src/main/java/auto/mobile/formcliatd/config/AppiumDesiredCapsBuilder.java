package auto.mobile.formcliatd.config;

import auto.mobile.formcli.config.MobileCapEnum;
import auto.mobile.formcli.config.MobileCapPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumDesiredCapsBuilder {
    private static final Logger logger = LogManager.getLogger();

    public static DesiredCapabilities setMobileCaps(MobileCapPojo mobileCaps) {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(auto.mobile.formcli.config.MobileCapEnum.PLATFORM_NAME.getCapName(), mobileCaps.getPlatformName());
        dc.setCapability(auto.mobile.formcli.config.MobileCapEnum.PLATFORM_VERSION.getCapName(), mobileCaps.getPlatformVersion());
        dc.setCapability(auto.mobile.formcli.config.MobileCapEnum.DEVICE_NAME.getCapName(), mobileCaps.getDeviceName());
        dc.setCapability(auto.mobile.formcli.config.MobileCapEnum.AUTOMATION_NAME.getCapName(), mobileCaps.getAutomationName());
        dc.setCapability("appium:noReset", false);
        dc.setCapability(auto.mobile.formcli.config.MobileCapEnum.APP_FILE.getCapName(), mobileCaps.getPathToApp());
        dc.setCapability(MobileCapEnum.MAX_TYPING.getCapName(), mobileCaps.getTypingFrequency());

        logger.info("Set mobile capabilities {} ", mobileCaps.toString());
        return dc;
    }


}
