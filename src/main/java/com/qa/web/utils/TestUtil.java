package com.qa.web.utils;
import com.qa.web.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil extends TestBase {
    public static long Page_Load_Timeout = 500;
    public static long IMPLICITLY_WAIT = 500;
    public static long EXPLICITILY_WAIT = 30;

    public static void clickAt(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, EXPLICITILY_WAIT).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public static void switchframe() {
        driver.switchTo().frame(0);
    }

    public static void click(WebElement webelement) {
        webelement.click();
    }

    public static void type(WebElement element, String value) {
        element.sendKeys(value);
    }

}
