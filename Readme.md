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
- Appium: Mobile UI (`XCUITest and UiAutomator2` drivers) attaching `wait-for` add-on.
- Allure: Reporter

The test script is following `Page-Object-Model`

The list of test cases are placed in `xml` file under `test/source/*.xml`.

## 3. Execution

The script is executed in

### Phase 1: In-parallel between `test` tag in `xml` file

The script is run for `android` and `ios` platforms (2 simluator/emulator at the same time).

At root folder, running command: `mvn clean test -Dsuite="mobile-testng"`

### Phase 2: Test distribution (TBD)