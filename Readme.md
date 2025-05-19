# Mobile Automation #

## 1. Introduction

This branch is built to setup how to connect to remote-appium server and run.

For business requirement, please refer to `main` branch.

## 2. Setup

The automation framework is using:

- Maven lib: builder tool
- TestNg: test unit framework
- Appium: Mobile UI (`XCUITest and UiAutomator2` drivers) attaching `element-wait` plugin.
- Allure: Reporter (must install Allure CLI in your computer to generate report)
- BrowserStack SDK

The test script is following `Page-Object-Model`

The list of test cases are placed in `xml` file under `test/source/*.xml`.

## 3. Execution

#### Run test-suite at appium-remote server



##### Steps:

running command: 

- Android platform

`mvn clean test -Dplatform=android -Dtarget=bs -Dsuite="mobile-testng"`


- IOS platform

`mvn clean test -Dplatform=ios -Dtarget=bs -Dsuite="mobile-testng"`


Notes: `-Dtarget=bs` guides framework to trigger `BsAppiumService` class to build appium service

## 4. Report

At root folder, running 2 commands:

```jshelllanguage
 allure generate .\target\allure-results\ --clean
```

```jshelllanguage
 allure open .\allure-report\
```

## 5. Issues:

