package auto.mobile.formcli.constants;

public enum MobileCapEnum {

    PLATFORM_NAME("appium:platformName"),
    PLATFORM_VERSION("appium:platformVersion"),
    AUTOMATION_NAME("appium:automationName"),
    DEVICE_NAME("appium:deviceName"),
    APP_FILE("appium:app"),
    MAX_TYPING("appium:maxTypingFrequency");

    private final String capName;

    MobileCapEnum(String capName) {
        this.capName = capName;
    }

    public String getCapName() {
        return this.capName;
    }
}
