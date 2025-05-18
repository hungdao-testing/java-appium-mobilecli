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

The test script is following `Page-Object-Model`

The list of test cases are placed in `xml` file under `test/source/*.xml`.

## 3. Execution

#### Run test-suite at appium-remote server

##### Precondition: 

- Install appium server along with Android and IOS drivers

- Then `element-wait` plugin

##### Steps:

At remote server (as the `address` in file `src/test/resources/appium.server.json`), open CLI and start appium 

```jshelllanguage
appium server --use-plugins=element-wait -p 5024 -a 0.0.0.0
```

At client server (or your computer): 

running command: `mvn clean test -Dsuite="mobile-testng"`

## 4. Report

At root folder, running 2 commands:

```jshelllanguage
 allure generate .\target\allure-results\ --clean
```

```jshelllanguage
 allure open .\allure-report\
```

## 5. Issues:
If the `android.cap` and `ios.cap` specifies `systemPort` and `wdaLocalPort` , the script would be failed
=> On the way to investigate
