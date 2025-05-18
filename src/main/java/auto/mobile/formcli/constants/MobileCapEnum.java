package auto.mobile.formcli.constants;

import lombok.Getter;

@Getter
public enum MobileCapEnum {

    PLATFORM_NAME("appium:platformName"),
    PLATFORM_VERSION("appium:platformVersion"),
    AUTOMATION_NAME("appium:automationName"),
    DEVICE_NAME("appium:deviceName"),
    APP_FILE("appium:app"),
    MAX_TYPING("appium:maxTypingFrequency"),
    ANDROID_SYSTEM_PORT("systemPort"),
    IOS_WDA_PORT("wdaLocalPort");

    private final String capName;

    MobileCapEnum(String capName) {
        this.capName = capName;
    }

}
