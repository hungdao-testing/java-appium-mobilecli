package auto.mobile.formcliatd.constants;

public enum PointWay {
    FROM("from"),
    TO("to");

    private final String pointWay;

    PointWay(String pointWay) {
        this.pointWay = pointWay;
    }

    public String getPointWay() {
        return this.pointWay;
    }
}
