package auto.mobile.formcli.pojo.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Delegate;


@Getter
public class AppiumServerConfig {
    @JsonProperty("server")
    @Delegate
    private Server server;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
class Server {
    @JsonProperty("address")
    public String address;

    @JsonProperty("port")
    public int port;

}