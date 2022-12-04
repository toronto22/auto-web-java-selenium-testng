# Web app selenium junit4 autotest
This is a Web Application's automation test script that following 
page object model approach. The project support Allure report and CICD with Jenkins.

# Prerequisite
- JDK 1.8 or higher is installed 
- Maven is installed (The Highest version is recommended)
- IntelliJ (Recommended)
# Get started 

## Execute test on local

The `chromedriver.exe`, `geckodriver.exe` and `msedgedriver.exe` may be old version, and it 
may not match with our current browser version. So we need to download and use the matched version
(It is usually the newest version). To download those drivers we can access following links:
- Chrome driver: [ChromeDriver.exe](https://chromedriver.chromium.org/downloads)
- Firefox driver: [Geckodriver.exe](https://github.com/mozilla/geckodriver/releases)
- Edge driver: [msedgedriver.exe](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)

After download new driver we can execute test with commandline `mvn clean verify`

To run test in headless mode or other browser we can edit the configuration on `appsettings.json`

- Change `IsHeadless` variable to `true` will make script run on headless mode
- Change `Browser` variable to `Chrome`, `Firefox` or `Edge` to run script on selected browser.

## Execute test with Selenium Grid
To execute test with Selenium Grid, we have to install that one by follow the article:
- [Selenium Grid - Getting Started](https://toronto22.github.io/selenium_grid_getting_started/)

When we have Selenium Grid is install, we can open `appsettings.json` file to edit configuration that will make
the project execute test with Selenium Grid:
- Change `IsSeleniumGrid` variable to `true` value,that will make project execute tests using Selenium Grid.
- Update `GridHubUri` variable to our Selenium Grid URI by replace `{your-ip-address}` by our current IP address. (Example: `http://172.29.64.1:4444/wd/hub`) 
- Update `Browser` variable to the browser we want to use (Make sure our Selenium Grid support that browser).

# Pending issue
- Cannot change the severity of test cases, therefore all the test cases will have default severity is `Normal`
