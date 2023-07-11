# Web app selenium TestNG autotest
This is a Web Application's automation test script that following 
page object model approach. The project support Allure report and CICD with Jenkins.

# Prerequisite
- JDK 1.8 or higher is installed 
- Maven is installed (The Highest version is recommended)
- IntelliJ (Recommended)
# Get started 

## Execute test on 

To run test in headless mode or other browser we can edit the configuration on `appsettings.json`

- Change `IsHeadless` variable to `true` will make script run on headless mode
- Change `Browser` variable to `Chrome`, `Firefox` or `Edge` to run script on selected browser.
- Change `IsSeleniumGrid` variable to `false` to run test on local

After download new driver we can execute test with commandline `mvn clean verify`


## Execute test with Selenium Grid
To execute test with Selenium Grid, we have to install that one by follow the article:
- [Selenium Grid - Getting Started](https://toronto22.github.io/selenium_grid_getting_started/)

When we have Selenium Grid is install, we can open `appsettings.json` file to edit configuration that will make
the project execute test with Selenium Grid:
- Change `IsSeleniumGrid` variable to `true` value,that will make project execute tests using Selenium Grid.
- Update `GridHubUri` variable to our Selenium Grid URI when we already have Selenium Grid is set up and have Selenium Grid URI. If we have Selenium Grid on local Docker, we can keep use `localhost` value.
- Update `Browser` variable to the browser we want to use (Make sure our Selenium Grid support that browser).

## Generate report

After execute test with the command `mvn clean verify` we will get the allure results in folder `target/allure-results`, to show the allure report
we can use the command:

```js
`mvn allure:serve`
```

that command will be show the allure report in html format on our local computer.

## Apply CICD with Jenkins Pipeline
If the Jenkins is not installed, we can install it following [Jenkins - Installation](https://toronto22.github.io/jenkins_installation/)

To create Jenkins Pipeline for our project, we follow those steps:
- Access Jenkins Dashboard page
- Click `New item`
- Enter `valid name`, select `pipeline option` and click `ok`
- In Project Configure page we scroll down to Pipeline section and select `Definition` field with `Pipeline Script from SCM` value
- Select `SCM` field to `Git` value 
- Input our project git repository in to `Repository URL`
- Select the valid Credential of our `Github account` //TODO How to add new GitHub credential to jenkins 
- Select `Branch Specifier` field with the branch you want to apply pipeline
- With `Script Path` we can keep the `Jenkinsfile` value or change it if we move the Jenkinsfile to other folder
- Click on `Save`
# Pending issue
- Cannot run it on local firefox

## To kill drivers
taskkill -F -IM chromedriver.exe
