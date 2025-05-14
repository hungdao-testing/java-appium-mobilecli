package auto.mobile.formcli.constants;

import lombok.Getter;

public enum MobilePlatform {
    IOS("ios"),
    ANDROID("android");

    @Getter
    public String platform;

    MobilePlatform(String platform) {
        this.platform = platform;
    }
}
