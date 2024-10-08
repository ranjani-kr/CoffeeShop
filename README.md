
# Web Automation E2E Test 
https://web-playground.ultralesson.com/


## Automation Steps : 
-  **Go to website**
-  **Search product Jeans and verify the list**
-  **Add first product**
-  **Go to cart and verify cart**
-  **Create account and enter shipping information**
-  **Continue to payment enter card details and paynow**
-  **Verify orderconfirmation**
-  

## Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **WebDriverManager**: To manage and run tests in different browsers automatically.
- **Lombok**: To reduce boilerplate code with annotations like `@Data`, `@Builder`, etc.

## Table of Contents

- [Installation](#installation)
- [Running the Tests](#running-the-tests)
- [Project Structure](#project-structure)

## Installation

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 8+**
- **Gradle** (Optional, if you prefer using the command line)
- **An IDE** (such as IntelliJ IDEA or Eclipse)

### Cloning the Repository

To set up the project locally, follow these steps:

1. **Open your terminal**:
   - On macOS or Linux, use the Terminal app.
   - On Windows, you can use Command Prompt, Git Bash, or any terminal emulator.

2. **Navigate to the directory where you want to clone the project**:
   - Use the `cd` command to change directories. For example:
   
     ```bash
     cd path/to/your/directory
     ```

3. **Clone the repository**:

   ```bash
   git clone git@github.com:ranjani-kr/CoffeeShop.git

4. **Navigate into the cloned directory:**
    ```bash
   cd yourrepository

   
### Dependencies
The project uses Gradle to manage dependencies. Once the repository is cloned, Gradle will automatically download the necessary dependencies.

1. Open the project in your preferred IDE (IntelliJ IDEA recommended).
2. Wait for Gradle to download all the required jars and libraries.

## Running the Tests

You can run the E2E tests using two methods: via an IDE or the command line.

### Option 1: Using an IDE
1. Open the project in your IDE
2. Wait for Gradle to sync and download all necessary dependencies**
3. Navigate to the src/test/java directory.
4. Open the desired test file (e.g., BuyProductTest.java).
5. Click the Run button in your IDE to execute the test.

### Option 2: Using the Command Line
1. Open a terminal or command prompt**
2. **Navigate into the cloned directory:**
    ```bash
   cd yourrepository
3. **Navigate into the cloned directory:**
    ```bash
   ./gradlew test

## Project Structure

- CoffeeShop/
  - src/
    - main/
      - java/
        - org/example/
          - drivers/ (WebDriver setup and configuration classes)
          - models/ (Models using Lombok)
          - pages/ (Page Object Model (POM) classes for each web page)
    - test/
      - java/
        - org/example/tests/ (TestNG test cases)
    - resources/ (Test resources, e.g., data files)
  - build.gradle (Gradle build configuration)
  - README.md (Project documentation)

