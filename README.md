# Website QA Automation Project

This project demonstrates automated web testing using Playwright with Java and Gradle, using TestNG as the test runner.

## Setup

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd website-qa-automation
    ```

2.  **Build the project:**
    ```bash
    ./gradlew build
    ```

3.  **Run tests:**
    ```bash
    ./gradlew test
    ```

## Project Structure

*   `src/test/java/com/example/qa/automation/base`: Contains the `BaseTest` class for Playwright setup.
*   `src/test/java/com/example/qa/automation/pages`: Contains Page Object Model classes.
*   `src/test/java/com/example/qa/automation/tests`: Contains test classes.
*   `build.gradle`: Gradle build file with dependencies and task configurations.

## Dependencies

*   [Playwright](https://playwright.dev/java/)
*   [TestNG](https://testng.org/doc/)

## Browser Note
During the creation of this project, the OpenClaw browser control service was unavailable. The tests are written assuming the content of `https://example.com`. If you encounter issues, please ensure your Playwright browser drivers are installed (e.g., run `npx playwright install` or `mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"` from the project directory after setting up the project).
