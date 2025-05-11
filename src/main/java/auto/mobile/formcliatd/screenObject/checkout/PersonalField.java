package auto.mobile.formcliatd.screenObject.checkout;

public enum PersonalField {
    FULL_NAME("fullName"),
    ADDRESS("address"),
    CITY("city"),
    POSTCODE("postCode"),
    COUNTRY("country"),
    PHONE_NUMBER("phoneNumber"),
    DATE_OF_BIRTH("dateOfBirth");

    private final String fieldName;

    PersonalField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getName() {
        return this.fieldName;
    }
}
