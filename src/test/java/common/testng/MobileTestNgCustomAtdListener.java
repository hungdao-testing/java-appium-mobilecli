package common.testng;


import com.appium.manager.AppiumDriverManager;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;


public class MobileTestNgCustomAtdListener implements ITestListener {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public void onTestStart(ITestResult result) {
    logger.info("Test Started: {}", result.getName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    logger.info("Test Passed: {}", result.getName());
  }

  @Override
  public void onTestFailure(ITestResult result) {

    logger.error("Test Failed: {}", result.getName());
    logger.error("Exception: ", result.getThrowable());

    byte[] screenshot = ((TakesScreenshot) AppiumDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
  }

}
