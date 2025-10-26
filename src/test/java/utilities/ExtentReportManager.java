package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    // Static variables - shared across all test classes
    private static ExtentReports extent;
    private static ExtentTest test;
    private static WebDriver driver;
    private static String reportPath;

    // Synchronized method to prevent multiple report initialization
    public synchronized void initializeReport(String filePath) {
        // Only initialize if not already done
        if (extent == null) {
            // Create reports directory if it doesn't exist
            File reportDir = new File("./reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            // Create timestamp for unique report names
            String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            reportPath = "./reports/ExtentReport_" + timestamp + ".html";

            // Initialize Spark Reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Configure Reporter
            sparkReporter.config().setReportName("Complete Automation Test Report");
            sparkReporter.config().setDocumentTitle("All Test Results");
            sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Set system information
            extent.setSystemInfo("Test Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));

            System.out.println("✓ ExtentReport initialized: " + reportPath);
        }
    }

    public void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // Static method to get report instance
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            ExtentReportManager manager = new ExtentReportManager();
            manager.initializeReport("./reports/ExtentReport.html");
        }
        return extent;
    }

    public void createTest(String testName) {
        if (extent != null) {
            test = extent.createTest(testName);
        }
    }

    public static ExtentTest getTest() {
        return test;
    }

    public void logInfo(String message) {
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    public void logPass(String message) {
        if (test != null) {
            test.log(Status.PASS, message);
        }
    }

    public void logFail(String message) {
        if (test != null) {
            test.log(Status.FAIL, message);
        }
    }

    public void logWarning(String message) {
        if (test != null) {
            test.log(Status.WARNING, message);
        }
    }

    public void logSkip(String message) {
        if (test != null) {
            test.log(Status.SKIP, message);
        }
    }

    public void attachScreenshot(String screenshotName) {
        if (test != null && driver != null) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String destination = "./reports/screenshots/" + screenshotName + ".png";

                // Create screenshots directory if it doesn't exist
                new File("./reports/screenshots").mkdirs();

                Files.copy(screenshot.toPath(), Paths.get(destination));
                test.addScreenCaptureFromPath(destination);
            } catch (IOException e) {
                test.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    public synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("✓ ExtentReport generated successfully!");
            System.out.println("✓ Report location: " + reportPath);

            // Reset for next test run
            extent = null;
            test = null;
        }
    }
}