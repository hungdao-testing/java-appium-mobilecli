package auto.mobile.formcli.config;

import auto.mobile.formcli.pojo.config.AppiumServerConfig;
import auto.mobile.formcli.utils.JsonConverter;

public class ConfigurationManager {

    private static final String appiumConfFile = "src/test/resources/mobile/.appiumrc.json";

    private static AppiumServerConfig getAppiumServerConfig() {
        return JsonConverter.parseJsonAs(appiumConfFile, AppiumServerConfig.class);
    }

    public static String getHost() {
        return getAppiumServerConfig().getAddress();
    }

    public static int getPort() {
        return getAppiumServerConfig().getPort();
    }
}
