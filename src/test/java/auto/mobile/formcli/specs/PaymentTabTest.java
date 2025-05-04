package auto.mobile.formcli.specs;


import auto.mobile.formcli.config.AppiumDriverManager;
import auto.mobile.formcli.pojo.DobPojo;
import auto.mobile.formcli.screenObject.HomeScreen;
import auto.mobile.formcli.screenObject.checkout.PaymentTab;
import auto.mobile.formcli.screenObject.checkout.PersonalTab;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PaymentTabTest extends MobileBaseTest {

    PaymentTab paymentTab;

    @BeforeClass(description = "Setup precondition data for payment tab")
    public void setUp() {
        String fullName = "David Cooper";
        String address = "789 Street-food Saigon Vietnam";
        String city = "Newyork";
        String postCode = "7000";
        String phone = "0792725253";
        List<String> countryInfo = List.of("Albania", "AL");
        DobPojo dob = DobPojo.builder().day("15").month("7").year("2020").build();

        Allure.step(
                "Setup personal info",
                () -> {
                    HomeScreen homeScreen = new HomeScreen(AppiumDriverManager.getAppiumDriver());
                    Assert.assertTrue(homeScreen.isAt());
                    homeScreen.openCheckout();

                    PersonalTab personalTab = new PersonalTab(AppiumDriverManager.getAppiumDriver());
                    personalTab.setFullName(fullName);
                    personalTab.setAddress(address);
                    personalTab.setCity(city);
                    personalTab.setPostCode(postCode);
                    personalTab.setPhone(phone);
                    personalTab.setCountry(countryInfo.get(0));
                    personalTab.setDob(dob);
                    personalTab.clickNext();
                });

        paymentTab = new PaymentTab(AppiumDriverManager.getAppiumDriver());
        Assert.assertTrue(paymentTab.isAt());
    }

    @Test(description = "User could setup payment info")
    public void userCouldSetUpPayment() {
        String cardNumber = "01234343411223";
        String expiredDate = "12/45";
        String cvv = "456";
        boolean enableSaveCard = true;

        Allure.step(
                "Setup payment info",
                () -> {
                    paymentTab.setCardNumber(cardNumber);
                    paymentTab.setExpiredDate(expiredDate);
                    paymentTab.setCvv(cvv);
                    paymentTab.enableSaveCard(enableSaveCard);
                });

        Allure.step(
                "Assert input payment",
                () -> {
                    Assert.assertEquals(paymentTab.getCardNumber(), cardNumber);
                    Assert.assertEquals(paymentTab.getExpiredDate(), expiredDate);
                    Assert.assertEquals(paymentTab.getCvv(), cvv);
                    Assert.assertEquals(paymentTab.isSaveCardCheckboxEnable(), enableSaveCard);
                });
    }

    @Test(description = "Verify error messages for missing required fields")
    public void userCouldSeeErrorMessageForRequiredFields() {
        String cardNumber = "";
        String expiredDate = "";
        String cvv = "";
        boolean enableSaveCard = true;

        Allure.step(
                "Submit payment info",
                () -> {
                    paymentTab.setCardNumber(cardNumber);
                    paymentTab.setExpiredDate(expiredDate);
                    paymentTab.setCvv(cvv);
                    paymentTab.enableSaveCard(enableSaveCard);
                    paymentTab.submit();
                });

        Allure.step(
                "Assert error message",
                () -> {
                    Assert.assertEquals(
                            paymentTab.cardNumberErrorElement.getText(), "Please provide card number");
                    Assert.assertEquals(paymentTab.expiredDateErrorElement.getText(), "Required");
                    Assert.assertEquals(paymentTab.cvvErrorElement.getText(), "Please provide cvv number");
                });
    }

    @Test(description = "Verify error messages for validating format")
    public void userCouldSeeErrorMessageForValidatingFormat() {

        boolean enableSaveCard = true;

        Allure.step(
                "Submit payment info",
                () -> {
                    String cardNumber = "198002983434";
                    String expiredDate = "36/01";
                    String cvv = "3456";

                    paymentTab.setCardNumber(cardNumber);
                    paymentTab.setExpiredDate(expiredDate);
                    paymentTab.setCvv(cvv);
                    paymentTab.enableSaveCard(enableSaveCard);
                    paymentTab.submit();
                });

        Allure.step(
                "Assert error message",
                () -> {
                    Assert.assertEquals(
                            paymentTab.expiredDateErrorElement.getText(), "Please use MM/YY format");
                    Assert.assertEquals(
                            paymentTab.cvvErrorElement.getText(), "input should be less than 999");
                    Assert.assertEquals(paymentTab.isSaveCardCheckboxEnable(), enableSaveCard);
                });

        Allure.step(
                "Submit payment info",
                () -> {
                    String cardNumber = "9081981088934343";
                    String expiredDate = "12/12";
                    String cvv = "99";

                    paymentTab.setCardNumber(cardNumber);
                    paymentTab.setExpiredDate(expiredDate);
                    paymentTab.setCvv(cvv);
                    paymentTab.enableSaveCard(enableSaveCard);
                    paymentTab.submit();
                });

        Allure.step(
                "Assert error message",
                () -> {
                    Assert.assertEquals(
                            paymentTab.cvvErrorElement.getText(), "input should be greater than 100");
                });
    }
}
