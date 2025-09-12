
# Test Automation Framework

This is a Java-based Test Automation Framework.

It is designed to support data-driven testing, cross-browser execution, cloud execution on LambdaTest, and easy integration with CI/CD pipelines.The framework is lightweight, modular, and scalable, with clean reporting and logging support.


## 🚀 About Me
Hi, My Name is Pranjali Nirmal and I have 6 years of experience in Automation Testing using technologies like Selenium Webdriver,RestAssured,Appium.

My Major expertise is in Java Programming Language.


## Author

- [@Pranjali92](https://github.com/Pranjali92)
- EmailAddress: pranjalinirmal92@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Pranjali92)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/pranjali-nirmal-554bb3187/)


## Prerequisites

Before running the framework, make sure the following are set up on your system:

- **Java 11** installed and configured in the system environment variables.
- **Maven** is installed for build and dependency management.
- Download Link: https://maven.apache.org/download.cgi
- **LambdaTest** Account created for running tests on the cloud.


## Features
- **TestNG**: Used for test execution, management, and creating test suites.
- **Data-Driven Testing**: Implemented using CSV files, Gson for JSON parsing, and Apache POI for working with Excel files.
- **Faker Library**: Used for dynamic test data generation during test execution.
- **Headless Browser Execution**: Enables faster test runs by executing tests without opening a browser UI.
- **LambdaTest Integration**: Used for cloud-based cross-browser testing
- **Maven Surefire Plugin**: Allows running tests from CI/CD pipelines with runtime parameter configuration.
- **Extent Reports**: Generates custom, detailed HTML reports of test execution.
- **Log4j**: Used for log generation, with logs stored in the logs/ folder for tracking and debugging.


##  Technologies Used
- Java 11
- TestNG
- Maven
- OpenCSV
- Apache POI
- Gson
- Faker Library
- Extent Reports
- Log4j
- LambdaTest


## Setup Instructions

**Clone the Repository**:

```bash
    git clone https://github.com/Pranjali92/Test-Automation-Framework.git
  
    cd Test-Automation-Framework
```

**Running Tests on LambdaTest**:

```bash
     mvn test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false -X

```

**Running Tests on Chrome Browser on Local Machine in Headless Mode**:

```bash
     mvn test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true -X

```

## Reports and Logs
- Reports: Test execution results are generated as HTML reports using Extent Reports and saved at ./report.html. 

The report provides detailed information about each test case, including status along with screenshots for failed tests.

## Logs
-Test execution generates logs using Log4j, which are stored in the ./logs/ directory.


## Integrated the Project with Github Actions
This Automation Framework is Integaretd with github actions.The tests will be executed 11:30PM IST every single day.

The reports will be archieved in gh-pages branch.
You can view the html reports at https://pranjali92.github.io/Test-Automation-Framework/report.html

