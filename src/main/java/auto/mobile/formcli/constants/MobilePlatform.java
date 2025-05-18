package auto.mobile.formcli.constants;

public enum MobilePlatform {
    ANDROID("android"),
    IOS("ios"),
    TV("tv");

    private final String mobilePlatform;
    MobilePlatform(String mobilePlatform){
        this.mobilePlatform = mobilePlatform;
    }

    public String getValue() {
        return this.mobilePlatform.trim().toLowerCase();
    }
}
