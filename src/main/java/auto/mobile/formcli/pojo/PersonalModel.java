package auto.mobile.formcli.pojo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonalModel {
    private String fullName;
    private String address;
    private String city;
    private String postCode;
    private String country;

    @Builder.Default
    private String countryCode = "";
    private String phoneNumber;
    private DobPojo dob;
}
