package auto.mobile.formcli.specs;


import auto.mobile.formcli.config.driver.FwDriverManager;
import auto.mobile.formcli.pojo.DobPojo;
import auto.mobile.formcli.pojo.PaymentModel;
import auto.mobile.formcli.pojo.PersonalModel;
import auto.mobile.formcli.screenObject.HomeScreen;
import auto.mobile.formcli.screenObject.checkout.*;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SummaryTabTest extends MobileBaseTest {

    public PersonalModel personalModel;
    public PaymentModel paymentModel;
    SummaryTab summaryTab;

    @BeforeClass(description = "Setup Summary tab")
    public void setUp() {

        Allure.step(
                "Setup personal info",
                () -> {
                    personalModel =
                            PersonalModel.builder()
                                    .fullName("David Cooper")
                                    .address("789 Street-food Saigon Vietnam")
                                    .city("Newyork")
                                    .postCode("7000")
                                    .phoneNumber("0792725253")
                                    .country("Albania")
                                    .countryCode("AL")
                                    .dob(DobPojo.builder().day("26").month("5").year("2024").build())
                                    .build();

                    HomeScreen homeScreen = new HomeScreen(FwDriverManager.getAppiumDriver());
                    Assert.assertTrue(homeScreen.isAt());
                    homeScreen.openCheckout();

                    PersonalTab personalTab = new PersonalTab(FwDriverManager.getAppiumDriver());
                    personalTab.setFullName(personalModel.getFullName());
                    personalTab.setAddress(personalModel.getAddress());
                    personalTab.setCity(personalModel.getCity());
                    personalTab.setPostCode(personalModel.getPostCode());
                    personalTab.setPhone(personalModel.getCountry());
                    personalTab.setCountry(personalModel.getCountry());
                    personalTab.setDob(personalModel.getDob());
                    personalTab.clickNext();
                });

        Allure.step(
                "Setup payment info",
                () -> {
                    paymentModel =
                            PaymentModel.builder()
                                    .cardName("01234343411223")
                                    .expiredDate("12/45")
                                    .cvv("456")
                                    .saveCard(true)
                                    .build();

                    PaymentTab paymentTab = new PaymentTab(FwDriverManager.getAppiumDriver());
                    paymentTab.setCardNumber(paymentModel.getCardName());
                    paymentTab.setExpiredDate(paymentModel.getExpiredDate());
                    paymentTab.setCvv(paymentModel.getCvv());
                    paymentTab.enableSaveCard(paymentModel.isSaveCard());
                    paymentTab.submit();
                });

        summaryTab = new SummaryTab(FwDriverManager.getAppiumDriver(), personalModel, paymentModel);
        Assert.assertTrue(summaryTab.isAt());
    }

    @Test(description = "User could see the summary information")
    public void userCouldSeeSummaryInformationSetup() {

        Allure.step(
                "Assert personal card load correct information",
                () -> {
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.FULL_NAME),
                            this.personalModel.getFullName());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.ADDRESS),
                            this.personalModel.getAddress());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.CITY), this.personalModel.getCity());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.POSTCODE),
                            this.personalModel.getPostCode());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.PHONE_NUMBER),
                            this.personalModel.getPhoneNumber());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.COUNTRY),
                            this.personalModel.getCountryCode());

                    String dateInCard = summaryTab.getValueOfPersonalCard(PersonalField.DATE_OF_BIRTH);
                    Assert.assertEquals(
                            dateInCard,
                            personalModel.getDob().toString());


                });

        Allure.step(
                "Assert payment card load correct information",
                () -> {
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.CARD_NUMBER),
                            this.paymentModel.getCardName());
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.EXPIRE_DATE),
                            this.paymentModel.getExpiredDate());
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.CVV), this.paymentModel.getCvv());
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.SAVE_CARD),
                            String.valueOf(this.paymentModel.isSaveCard()));
                });
    }

    @Test(description = "User could edit personal and payment information")
    public void userCouldEditInformation() {

        summaryTab.editPersonalCard();
        personalModel =
                PersonalModel.builder()
                        .fullName("Patrick Clever")
                        .address("123 Berlin")
                        .city("Berlin")
                        .postCode("8000")
                        .phoneNumber("079865671212")
                        .country("Algeria")
                        .countryCode("DZ")
                        .dob(DobPojo.builder().day("10").month("12").year("2025").build())
                        .build();
        paymentModel =
                PaymentModel.builder()
                        .cardName("1232323234343")
                        .expiredDate("12/12")
                        .cvv("222")
                        .saveCard(false)
                        .build();

        Allure.step(
                "Edit  personal info",
                () -> {
                    PersonalTab personalTab = new PersonalTab(FwDriverManager.getAppiumDriver());
                    personalTab.setFullName(personalModel.getFullName());
                    personalTab.setAddress(personalModel.getAddress());
                    personalTab.setCity(personalModel.getCity());
                    personalTab.setPostCode(personalModel.getPostCode());
                    personalTab.setPhone(personalModel.getPhoneNumber());
                    personalTab.setCountry(personalModel.getCountry());
                    personalTab.setDob(personalModel.getDob());
                    personalTab.clickNext();
                });

        Allure.step(
                "Edit payment info",
                () -> {
                    PaymentTab paymentTab = new PaymentTab(FwDriverManager.getAppiumDriver());
                    paymentTab.setCardNumber(paymentModel.getCardName());
                    paymentTab.setExpiredDate(paymentModel.getExpiredDate());
                    paymentTab.setCvv(paymentModel.getCvv());
                    paymentTab.enableSaveCard(paymentModel.isSaveCard());
                    paymentTab.submit();
                });

        summaryTab = new SummaryTab(FwDriverManager.getAppiumDriver(), personalModel, paymentModel);
        Assert.assertTrue(summaryTab.isAt());
        Allure.step(
                "Assert personal card load correct information",
                () -> {
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.FULL_NAME),
                            this.personalModel.getFullName());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.ADDRESS),
                            this.personalModel.getAddress());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.CITY), this.personalModel.getCity());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.POSTCODE),
                            this.personalModel.getPostCode());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.PHONE_NUMBER),
                            this.personalModel.getPhoneNumber());
                    Assert.assertEquals(
                            summaryTab.getValueOfPersonalCard(PersonalField.COUNTRY),
                            this.personalModel.getCountryCode());

                    String dateInCard = summaryTab.getValueOfPersonalCard(PersonalField.DATE_OF_BIRTH);
                    Assert.assertEquals(
                            dateInCard,
                            personalModel.getDob().toString());
                });

        Allure.step(
                "Assert payment card load correct information",
                () -> {
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.CARD_NUMBER),
                            this.paymentModel.getCardName());
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.EXPIRE_DATE),
                            this.paymentModel.getExpiredDate());
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.CVV), this.paymentModel.getCvv());
                    Assert.assertEquals(
                            summaryTab.getValueOfPaymentCard(PaymentField.SAVE_CARD),
                            String.valueOf(this.paymentModel.isSaveCard()));
                });
    }
}
