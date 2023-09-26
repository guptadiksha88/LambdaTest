package com.qa.web.testReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;

public class ExtentManager {
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest extentLog;

	final static String current_Platform = System.getProperty("os.name");

	public static ExtentReports getExtentReport() throws IOException {
		return setExtentReport();
	}

	private static ExtentReports setExtentReport() throws IOException {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + "AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", current_Platform);
		htmlReporter.config().setDocumentTitle("Automation-Report");
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.DARK);

		return extent;
	}
}
