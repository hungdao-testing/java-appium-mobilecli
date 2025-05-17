package auto.mobile.formcli.constants;

public enum MobilePlatform {
    ANDROID("android"),
    IOS("ios"),
    TV("tv");

    private  String mobilePlatform;
    MobilePlatform(String mobilePlatform){
        this.mobilePlatform = mobilePlatform;
    }
}
