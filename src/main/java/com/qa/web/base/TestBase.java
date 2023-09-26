package com.qa.web.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.web.testReport.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.output.WriterOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.qa.web.utils.TestUtil.IMPLICITLY_WAIT;
import static com.qa.web.utils.TestUtil.Page_Load_Timeout;

public class TestBase {


    public static WebDriver driver;
    static Properties properties;

    protected static ExtentReports extent;
    protected static ExtentTest extentLog;
    protected static String testName = "";
    protected static StringWriter requestWriter;
    protected static PrintStream requestCapture;
    protected String methodName;
    public static PrintStream REQUESTCAPTURE;

    @BeforeTest
    public void setUp() throws IOException {
        extent = ExtentManager.getExtentReport();
    }

    public TestBase() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/qa/web/config/config.properties");
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException IO) {
            IO.printStackTrace();
        }

    }

    public static void Initialization() {
        String browser = properties.getProperty("browser");

        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equals("ff")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Page_Load_Timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));
    }

    public String customReport(String message) {
        String format = "<b class='exception' style='display:block; cursor:pointer; user-select:none' onclick='($(\".exception\").click(function(){ $(this).next().toggle()}))'>"
                + message + "</b>" + "<pre style='display:none'></pre>";
        return format;
    }


    @BeforeMethod
    public void getMethodName(Method m) {
        methodName = m.getName();
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
        REQUESTCAPTURE = requestCapture;
        String packageName = this.getClass().getPackage().getName();
        testName = this.getClass().getSimpleName() + " : " + m.getName();
        extentLog = extent.createTest(testName, m.getAnnotation(Test.class).description())
                .assignCategory(packageName.substring(packageName.lastIndexOf(".") + 1));

    }

    @AfterMethod
    public void reportsUpdate(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentLog.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentLog.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLog.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
            extentLog.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentLog.log(Status.PASS,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
        }
    }

    @AfterTest
    public void afterSuite(ITestContext context) {
        System.out.println("in after suite************************************************");
        extent.flush();

    }


}
