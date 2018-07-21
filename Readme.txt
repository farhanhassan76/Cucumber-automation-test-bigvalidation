Hi,

I have completed my automation work regarding the smoke test scripts of a Big Data application. This is a maven project which runs on Cucumber JUnit. I would like to share some of the information regarding it. 

All the automation scripts regarding smoke test queries and their provided reports are completed. The "Sum" or the "flow_value" is extracted from the UI and are matched against the provided database queries on runtime. The "Assert Equals" function is applied to address the mismatch between the Expected (database) and Actual (UI) values.

To run this project you have to follow few simple things as prerequisites;

1. Configure JDK 1.7 or above on the machine
2. Configure Apache Maven on machine (latest version)
3. Place the project folder on any drive, for example "D:\\",
4. Open command prompt and go to the project directory, for example "D:\\automation-test".
5. Write "mvn test" command and press enter, so that your tests will begin.
6. Write "mvn install -Dcucumber.options ="classpath:com/Cucumber/Currency.feature" to run a specific feature file from command line.

Also, the code structure on high level is discussed as under,

    The following main Java files are kept in the "src/test/java/com/Cucumber"
        PageStepDefs.Java: This is the Step Definition file for the main cucumber test scripts to run on.
        PostgresqlDB.Java: This is the file for database connection and to return the result set of the query.
        ReadPropertiesFile.Java: This file reads the "sqlConfig.properties" file placed in the "src/test/resources" folder.
        RunTest.Java: This is the main cucumber file which runs all the test cases (feature) files configured in the class. One thing to mention regarding it is the choice of running the amount or type of test cases. It is easier for the user to comment the test cases in this file which are not required to be run.
    Also, all the 41 test cases (feature) files are placed in the "src/test/resources/com/Cucumber" folder. The feature files are configured on the "http://10.0.0.55/MicroStrategy/asp/Main.aspx" portal on "\\Shared Reports > QA_Testing" folder. If in future there is need to change the test environment, then all the X-Path configuration settings would have to be changed in all the 41 feature files.
    The "sqlConfig.properties" file is placed in the "src/test/resources" folder, this file is containing "endDate", "month" and "year" values which are being parameterized with SQL queries. The user can easily change/update these values before running the test and without going in to the code. 
    The auto-generated reports are placed at "target/surefire-reports" in the form of ".txt" and ".xml" files. These reports are updated/generated each time when the test is run.


Please let me know in case of any question or ambiguity.


Thanks.