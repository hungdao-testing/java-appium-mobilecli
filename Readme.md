# Mobile Automation #

## 1. Business Requirement

The mobile application is developed by React-native framework (v.7), simulating a few small businesses:

### Phase 1: Checkout business

The Check-out process has 3 serial steps

- Personal information

- Payment information

- Summary information

Noting that the `date-of-birth` field has different format in Android and iOS.

### Phase 2: Notification (TBD)


## 2. Setup

The automation framework is using:

- Maven lib: builder tool

- TestNg: test unit framework

- Appium: Mobile UI (`XCUITest and UiAutomator2` drivers) attaching `wait-for, device-farm` plugins.

- Allure: Reporter

The test script is following `Page-Object-Model`

The list of test cases are placed in `xml` file under `test/source/*.xml`.

## 3. Execution

The script is executed in

### Phase 1: In-parallel between `test` tag in `xml` file

#### Phase 1.1: Run test-suite without starting appium server first.

The script is run for `android` and `ios` platforms (2 simulator/emulator at the same time).

At root folder, running command: `mvn clean test -Dsuite="mobile-testng"`

#### Phase 1.2: Run test-suite with `appium-device-farm` => all test execution live-streaming to device-farm dashboard

Notes: Make sure the local appium already installed [`appium-device-farm`](https://devicefarm.org/setup/)

1. At root folder, starting appium server along with json config file:

 `appium --config "src/test/resources/mobile/.appiumrc.json"`

2. Update the `port` and `host` for `MobileBaseTest` as corresponding values in `.appiumrc.json` (node `server` > port and address)


3. running command: `mvn clean test -Dsuite="mobile-testng"` to run all test scripts


### Phase 2: Test distribution (TBD)