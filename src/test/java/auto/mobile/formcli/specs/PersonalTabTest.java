package auto.mobile.formcli.specs;


import auto.mobile.formcli.config.AppiumDriverManager;
import auto.mobile.formcli.pojo.DobPojo;
import auto.mobile.formcli.screenObject.HomeScreen;
import auto.mobile.formcli.screenObject.checkout.PersonalTab;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@Feature("Checkout")
@Tag("@personal-tab")
@Tag("@checkout")
public class PersonalTabTest extends MobileBaseTest {

    private PersonalTab personalTab;

    @BeforeClass(description = "Click on [Checkout] to load Personal Tab")
    public void setUpHomeScreen() {

        HomeScreen homeScreen = new HomeScreen(AppiumDriverManager.getAppiumDriver());
        Assert.assertTrue(homeScreen.isAt());
        Allure.step("Open Personal tab", homeScreen::openCheckout);

        personalTab = new PersonalTab(AppiumDriverManager.getAppiumDriver());
        Allure.step(
                "Verify user could open Personal Tab",
                () -> {
                    Assert.assertTrue(personalTab.isAt());
                });
    }

    @Test(description = "Verify user could setup info for personal tab")
    public void verifyUserCouldSetUpInfo() {

        String fullName = "David Cooper";
        String address = "789 Street-food Saigon Vietnam";
        String city = "Newyork";
        String postCode = "7000";
        String phone = "0792725253";
        List<String> countryInfo = List.of("Albania", "AL");
//    String dob = "15/07/2020";
        DobPojo dob = DobPojo.builder().year("2020").day("15").month("7").build();

        personalTab.setFullName(fullName);
        personalTab.setAddress(address);
        personalTab.setCity(city);
        personalTab.setPostCode(postCode);
        personalTab.setPhone(phone);
        personalTab.setCountry(countryInfo.get(0));

        personalTab.setDob(dob);

        Allure.step(
                "Assert input value in Full Name",
                () -> {
                    Assert.assertEquals(personalTab.getFullName(), fullName);
                });

        Allure.step(
                "Assert input value in Address",
                () -> {
                    Assert.assertEquals(personalTab.getAddress(), address);
                });

        Allure.step(
                "Assert input value in city",
                () -> {
                    Assert.assertEquals(personalTab.getCity(), city);
                });

        Allure.step(
                "Assert input value in postCode",
                () -> {
                    Assert.assertEquals(personalTab.getPostCode(), postCode);
                });

        Allure.step(
                "Assert input value in phone",
                () -> {
                    Assert.assertEquals(personalTab.getPhone(), phone);
                });

        Allure.step(
                "Assert input value in country",
                () -> {
                    Assert.assertEquals(personalTab.getCountryCode(), countryInfo.get(1));
                });

        Allure.step(
                "Assert input value in dob",
                () -> {
                    Assert.assertEquals(personalTab.getDob().toString(), dob.toString());
                });
    }

    @Test(description = "Verify error messages in Personal tab")
    @Tag("@errorMessages")
    public void verifyErrorMessages() {

        personalTab.clickNext();

        Allure.step(
                "Assert error message for Full Name missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.fullNameErrorField),
                            "Full name must be required");
                });

        Allure.step(
                "Assert error message for Address missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.addressErrorField), "Required");
                });

        Allure.step(
                "Assert error message for city missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.cityErrorField), "Required");
                });

        Allure.step(
                "Assert error message for postCode missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.postCodeErrorField), "Required");
                });

        Allure.step(
                "Assert error message for phone missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.phoneErrorField), "Required");
                });

        Allure.step(
                "Assert error message for country missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.countryErrorField), "Required");
                });

        Allure.step(
                "Assert error message for dob missing",
                () -> {
                    Assert.assertEquals(
                            personalTab.getContentOfErrorField(personalTab.dobErrorField), "Required");
                });
    }
}
