package com.qa.web.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class MoveToElementTest {

    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();

        //Instantiate the Webdriver
        WebDriver driver = new ChromeDriver();
        //Creating an Actions class object
        Actions action = new Actions(driver);
        action.perform();
        Action act = action.build();
        action.perform();
        driver.get("https://www.lambdatest.com/");


        assertEquals(driver.getTitle(), "Next-Generation Mobile Apps and Cross Browser Testing Cloud | LambdaTest");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


        //locator of the Resources menu
        WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/nav[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]"));


        //mouse hover the Resources element
        action.moveToElement(element).build().perform();

        driver.findElement(By.xpath("//h3[normalize-space()='Blog']")).click();
        //specify the locator for the element Blog and click

        assertEquals(driver.getCurrentUrl(), "https://www.lambdatest.com/blog/");
        System.out.println("Current URL is "+driver.getCurrentUrl() );

        //verify the page title after navigating to the Blog section

        assertEquals(driver.getTitle(), "LambdaTest Blogs");
        System.out.println("Current Title is "+driver.getTitle() );

        driver.close();
    }

}






















