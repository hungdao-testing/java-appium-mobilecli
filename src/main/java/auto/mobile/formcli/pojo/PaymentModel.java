package auto.mobile.formcli.pojo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentModel {
    private String cardName;
    private String expiredDate;
    private String cvv;

    @Builder.Default
    private boolean saveCard = true;
}
