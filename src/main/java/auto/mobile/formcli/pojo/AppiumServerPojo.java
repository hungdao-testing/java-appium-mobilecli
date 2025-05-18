package auto.mobile.formcli.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AppiumServerPojo {
    @JsonProperty("address")
    private String address;

    @JsonProperty("port")
    private String port;

    @JsonProperty("logPath")
    private String logPath;
}
