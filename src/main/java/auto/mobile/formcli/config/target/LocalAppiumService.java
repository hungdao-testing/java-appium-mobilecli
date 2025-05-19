package auto.mobile.formcli.config.target;

import auto.mobile.formcli.pojo.AppiumServerPojo;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class LocalAppiumService {

    public static AppiumDriverLocalService buildAppiumService(AppiumServerPojo appiumServerInfo, DesiredCapabilities capabilities) {
        return new AppiumServiceBuilder()
                .withCapabilities(capabilities)
                .usingPort(Integer.parseInt(appiumServerInfo.getPort()))
                .withIPAddress(appiumServerInfo.getAddress())
                .withLogFile(new File(appiumServerInfo.getLogPath()))
                .build();
    }
}
