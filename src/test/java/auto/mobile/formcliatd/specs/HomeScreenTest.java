package auto.mobile.formcliatd.specs;



import auto.mobile.formcliatd.screenObject.HomeScreen;

import com.appium.manager.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;


@Feature("Home Screen")
public class HomeScreenTest  {

    private HomeScreen homeScreen;

    @BeforeMethod(description = "Setup home screen information")
    public void setUpHomeScreen() {
        AppiumDriver driver = AppiumDriverManager.getDriver();
        homeScreen = new HomeScreen(driver);
    }

//    @AfterMethod
    public void teardown(){
        AppiumDriverManager.getDriver().quit();
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
