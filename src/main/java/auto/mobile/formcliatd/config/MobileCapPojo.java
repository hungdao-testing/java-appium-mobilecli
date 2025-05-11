package auto.mobile.formcliatd.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MobileCapPojo {

    @JsonProperty("appium:platformName")
    private String platformName;

    @JsonProperty("appium:automationName")
    private String automationName;

    @JsonProperty("appium:deviceName")
    private String deviceName;

    @JsonProperty("appium:newCommandTimeout")
    private int commandTimeout;

    @JsonProperty("appium:platformVersion")
    private String platformVersion;

    @JsonProperty("appium:noReset")
    private boolean noReset;

    @JsonProperty("appium:maxTypingFrequency")
    private int typingFrequency;

    @JsonProperty("appium:app")
    private String pathToApp;
}
