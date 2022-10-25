# *Welcome to the SCG's Automation Project*
## This test framework consists of:
### Software Language: **Java**
### Automation Library: **Selenium**
### Package Manager: **Maven**
### Runner: **TestNG**
### API Testing: **RestAssured**
### Reporting Tools: **Allure Reports**, **Test Rail**

*You can work on the project with Windows/Mac/Linux systems.*
## Installation
### Mac / Linux based systems

Install homebrew via just pasting below line on terminal:

`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`

* Install Selenium via Homebrew (use Mac terminal):
  `brew install selenium-server`
* Install Java via Homebrew (use Mac terminal):
  `brew install java`
* Install Maven via Homebrew (use Mac terminal):
  `brew install maven`
* Install allure reports via Homebrew (use Mac terminal):
  `brew install allure`
* Download Xcode from App Store (This will enable Git on your Macbook)
* Clone the  [web-automation](https://github.com/elopage/web-automation) repository.
* Open the project on your IDE and let maven build scripts work to download all dependencies.
  _We use [IntelliJ IDEA](https://www.jetbrains.com/idea) as IDE. Please ask your team leader for JetBrains premium account._

### Windows PC

Some additional steps are required to run the code on Windows PC.

* Download [maven](https://maven.apache.org/download.cgi)
* Copy the folder under *C:\\Program Files*
* Download [allure reports](https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/)
* Extract allure commandline zip to "allure-commandline" folder
* On the Windows taskbar, right-click the Windows icon and select System.
* In the Settings window, under Related Settings, click Advanced system settings.
* On the Advanced tab, click Environment Variables.
* Click New to create a new environment variable.
* Add environment variable JAVA_HOME: *In example: `C:\Program Files\Java\jdk1.8.0_121`*
* Add environment variable for Maven as M2: `%M2_HOME%\bin`
* Add environment variable for Maven as M2_HOME: *In example: `C:\Program Files\apache-maven-3.8.6`*
* Add Path variable for Maven: *In example: `C:\Program Files\apache-maven-3.8.6\bin`*
* Add Path variable for Allure Reports: *In example  `C:\allure-commandline\allure-2.10.0\bin`*

For adding new dependencies to the project, visit [mvn repository](https://mvnrepository.com/) and add them under <Dependencies> tag of pom.xml

Use following command to run the desired test suite:
`mvn clean test -Dsuites={test_suite_name} -Dapp`

_You can find test suites under src/test/suites and add the relevant suite name to the command line code._
_You can find linter rules under /checkstyle.xml

### Current environments:
This section contains required information about environments

### Naming Conventions:

This section explains naming convention for methods, classes, tests and branches.

_Example_  
Contains examples
### Please refer to our coding guidelines:
This section defines or targets coding guidelines (might contain links to confluence)

### Reporting:
`allure_serve`,

`allure generate --clean --output your-result-folder` _(caution with clean command as it will remove all files in directory)_

_Have a nice time coding with us!_
