# Project Title: Automation on Selenium-TestNG for dailyFinance
# Project Summary

This project focuses on automating the testing process for key functionalities of the DailyFinance platform. The primary objectives include user registration, profile updates, cost management, and verifying dashboard data. By employing the Page Object Model (POM) design pattern, the tests are structured for better maintainability and scalability.

## Key Features:
1. **User Registration:**  
   Automated test cases for registering users with:
   - All fields completed.
   - Only mandatory fields filled.
   - Missing mandatory fields (to validate error handling).
   
   User data is saved into a JSON array for later verification.

2. **Admin Dashboard Verification:**  
   Automated tests to log in as an admin and validate that the last registered user appears on the dashboard. The tests print and assert the first name, email, and phone number against the saved JSON data.

3. **Profile Update:**  
   Tests for logging in with the last registered user and updating their profile image.

4. **Cost/Expenditure Management:**  
   A CSV file with 5 rows of test data is used to add multiple items and associated costs. The test loops through the CSV and:
   - Adds the items with their respective details.
   - Calculates and asserts the total cost against the expected sum.

5. **Item Search:**  
   Automated test to search for an item by name and assert that the total cost matches the price of the searched item.

## Test Suites:
- **Regression Suite:**  
  Comprehensive suite covering all test cases.
  
- **Smoke Suite:**  
  Focused on critical functionality, executing only test cases related to cost management and item search.

## Reporting:  
Allure framework is used to generate detailed reports for the regression suite, providing insights into test execution and results.

The project is designed to ensure high-quality, reliable automation testing with a clear structure and comprehensive reporting.

## Tools and Technologies Used

1. **Selenium WebDriver**  
2. **TestNG**  
3. **Gradle**  
4. **Page Object Model (POM) Design Pattern**  
5. **CSV Parser**  
6. **Simple JSON Library**  
7. **Allure Framework**  
8. **TestNG Test Suites**  
9. **IntelliJ IDEA**

## How to Run 

1. **Clone the Project**  
   Clone the project repository from the source control (e.g., GitHub).

2. **Open the Project in IntelliJ IDEA**
   - Open IntelliJ IDEA.
   - Navigate to `File > Open`.
   - Select the cloned project folder and expand it.
   - Choose "Open as Project."

3. **Run Test Suites**
   - To run the regression suite, execute the following command in the terminal:
     ```bash
     gradle clean test -PsuiteName="regressionSuite.xml"
     ```
   - To run the smoke suite, execute the following command:
     ```bash
     gradle clean test -PsuiteName="smokeSuite.xml"
     ```

4. **Generate Allure Report**
   - After test execution, generate the Allure report by running:
     ```bash
     allure generate allure-results --clean
     ```
   - Serve the report locally by running:
     ```bash
     allure serve allure-results
     ```

## Allure Reports 
![TestNG Suite](https://github.com/user-attachments/assets/ecdc7a9a-2c73-43b1-9b98-09e5d4375c3b)

![TestNG behaviour](https://github.com/user-attachments/assets/05d5494c-30d6-4a8d-ad10-d9cbabc329ce)

## Regression Suite Video 
https://github.com/user-attachments/assets/aeab77d3-e089-4cfc-b33f-35d9d6cf940a





