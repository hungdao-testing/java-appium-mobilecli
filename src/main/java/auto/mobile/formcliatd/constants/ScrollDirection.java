package auto.mobile.formcliatd.constants;

public enum ScrollDirection {
    UP("up"),
    DOWN("down");

    private final String scrollDirection;

    ScrollDirection(String scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    public String getScrollDirection() {
        return this.scrollDirection;
    }
}
