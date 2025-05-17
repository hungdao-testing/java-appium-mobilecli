package auto.mobile.formcli.config.driver;

import auto.mobile.formcli.constants.MobileCapEnum;
import auto.mobile.formcli.pojo.MobileCapPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FwDesiredCapsBuilder {
    private static final Logger logger = LogManager.getLogger();

    public static DesiredCapabilities setMobileCaps(MobileCapPojo mobileCaps) {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapEnum.PLATFORM_NAME.getCapName(), mobileCaps.getPlatformName());
        dc.setCapability(MobileCapEnum.PLATFORM_VERSION.getCapName(), mobileCaps.getPlatformVersion());
        dc.setCapability(MobileCapEnum.DEVICE_NAME.getCapName(), mobileCaps.getDeviceName());
        dc.setCapability(MobileCapEnum.AUTOMATION_NAME.getCapName(), mobileCaps.getAutomationName());
        dc.setCapability("appium:noReset", false);
        dc.setCapability(MobileCapEnum.APP_FILE.getCapName(), mobileCaps.getPathToApp());
        dc.setCapability(MobileCapEnum.MAX_TYPING.getCapName(), mobileCaps.getTypingFrequency());

        logger.info("Set mobile capabilities {} ", mobileCaps.toString());
        return dc;
    }


}
