package main;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.ExtentReportManager;

public class mainTest {
    public Logger logger;
    protected WebDriver driver;
    protected BasePage basePage;
    protected ExtentReportManager extentReportManager;

    // Static flag to initialize report only once
    private static boolean reportInitialized = false;

    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting test setup...");

        // Initialize ExtentReportManager only once (static check)
        if (!reportInitialized) {
            extentReportManager = new ExtentReportManager();
            extentReportManager.initializeReport("./reports/ExtentReport.html");
            logger.info("✓ ExtentReportManager initialized");
            reportInitialized = true;
        } else {
            extentReportManager = new ExtentReportManager();
            logger.info("✓ Using existing ExtentReportManager instance");
        }

        // Start Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("✓ Chrome browser launched and maximized");

        // Set driver for screenshots
        extentReportManager.setDriver(driver);

        // Initialize BasePage
        basePage = new BasePage();
        basePage.setDriver(driver);
        logger.info("✓ BasePage initialized with WebDriver");
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();
            logger.info(">>> Test started: " + testName);

            // Create test in ExtentReport
            extentReportManager.createTest(testName);
            extentReportManager.logInfo("Test execution started");

        } catch (Exception e) {
            logger.error("Error in BeforeMethod: " + e.getMessage());
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            String testName = result.getMethod().getMethodName();

            if (result.getStatus() == ITestResult.SUCCESS) {
                logger.info("✓ Test PASSED: " + testName);
                extentReportManager.logPass("Test passed successfully");

            } else if (result.getStatus() == ITestResult.FAILURE) {
                logger.error("✗ Test FAILED: " + testName);
                String failureMessage = result.getThrowable() != null ?
                        result.getThrowable().getMessage() : "Unknown error";
                extentReportManager.logFail("Test failed: " + failureMessage);

                // Optionally attach screenshot on failure
                extentReportManager.attachScreenshot(testName + "_failure");

            } else if (result.getStatus() == ITestResult.SKIP) {
                logger.warn("⊘ Test SKIPPED: " + testName);
                extentReportManager.logSkip("Test was skipped");
            }

        } catch (Exception e) {
            logger.error("Error in AfterMethod: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        logger.info("Starting test teardown...");

        try {
            if (driver != null) {
                driver.quit();
                logger.info("✓ Browser closed successfully");
            }
        } catch (Exception e) {
            logger.error("Error closing browser: " + e.getMessage());
        }

        logger.info("Test teardown completed");
    }

    // This runs AFTER ALL test classes are finished
    @AfterSuite
    public void flushReportAfterAllTests() {
        logger.info("=== Flushing report after ALL tests ===");

        try {
            if (extentReportManager != null) {
                extentReportManager.flushReport();
                logger.info("✓ ExtentReport generated successfully");
                logger.info("✓ Report location: ./reports/");
            }
        } catch (Exception e) {
            logger.error("Error flushing report: " + e.getMessage());
        }
    }
}