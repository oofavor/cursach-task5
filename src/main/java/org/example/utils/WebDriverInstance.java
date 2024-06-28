package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverInstance {
    public static WebDriver driver = new ChromeDriver();

    public static WebDriver getInstance() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void waitTillAppear(By element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitTillVisible(By element) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public static void waitTillInvisible(By element) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    public static void scrollTo(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
