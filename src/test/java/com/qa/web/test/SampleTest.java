package com.qa.web.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SampleTest {

    @Test
    public void actionClassImplementation() {

        WebDriver driver;

        //Instantiate the Webdriver
        WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
        //Creating an Actions class object
       // Actions action = new Actions(driver);

        driver.get("https://www.google.com/");

        //action.moveTo


    }


}



















