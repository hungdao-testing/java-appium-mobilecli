package common.testrail;

public enum TestResultStatus {
    Passed(1),
    Blocked(2),
    Untested(3),
    Retest(4),
    Failed(5);
    private int statusId;

    TestResultStatus(int statusId){
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}
