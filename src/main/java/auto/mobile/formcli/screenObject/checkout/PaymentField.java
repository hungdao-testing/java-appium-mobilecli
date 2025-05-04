package auto.mobile.formcli.screenObject.checkout;

public enum PaymentField {
    CARD_NUMBER("cardNumber"),
    EXPIRE_DATE("expireDate"),
    CVV("cvv"),
    SAVE_CARD("saveCard");

    private final String fieldName;

    PaymentField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getName() {
        return this.fieldName;
    }
}
