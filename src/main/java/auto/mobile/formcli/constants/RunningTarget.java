package auto.mobile.formcli.constants;

public enum RunningTarget {

    LOCAL("local"),
    BS("browser-stack");

    private String target;
    RunningTarget(String target){
        this.target = target;
    }
}
