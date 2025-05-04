package auto.mobile.formcli.specs;


import auto.mobile.formcli.config.AppiumDriverManager;
import auto.mobile.formcli.screenObject.HomeScreen;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Home Screen")
public class HomeScreenTest extends MobileBaseTest {

    private HomeScreen homeScreen;

    @BeforeClass(description = "Setup home screen information")
    public void setUpHomeScreen() {
        homeScreen = new HomeScreen(AppiumDriverManager.getAppiumDriver());
    }

    @Test(description = "Verify user could go to Checkout screen")
    public void testUserCouldGoToCheckoutScreen() {
        Allure.step(
                "Assert home screen is loaded properly",
                () -> {
                    Assert.assertTrue(homeScreen.isAt());
                });

        Allure.step(
                "Assert user could open Checkout screen",
                () -> {
                    homeScreen.openCheckout();
                });
    }
}
