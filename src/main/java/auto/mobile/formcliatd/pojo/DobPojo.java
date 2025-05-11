package auto.mobile.formcliatd.pojo;


import auto.mobile.formcli.constants.TestConstant;
import auto.mobile.formcli.utils.DateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.format.DateTimeFormat;

@Builder
@Getter
@Setter

public class DobPojo {
    private String day;
    private String month;
    private String year;

    public String toString() {
        String date = String.format("%s/%s/%s", day, month, year);
        return DateUtils.parseStringAsDatePattern(
                        date, DateTimeFormat.forPattern(TestConstant.DATE_PATTERN))
                .toString(DateTimeFormat.forPattern(TestConstant.DATE_PATTERN));
    }
}
