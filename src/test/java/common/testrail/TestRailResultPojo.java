package common.testrail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
public class TestRailResultPojo {
    public int case_id;
    public int status_id;
    public String comment;
    public String elapsed;
    public String environment;
    public String defects;
    public int custom_failure_severity;
    @Builder.Default public List<String> attachment_ids = new ArrayList<>();
}
