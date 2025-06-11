package lt.viko.eif.tpetrov.Pirmas.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WeatherTestHelper {
    private static WebDriver driver;

    public static void openWeatherPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:8085");
    }

    public static void searchCity(String city) {
        WebElement input = driver.findElement(By.id("citySearch"));
        input.clear();
        input.sendKeys(city);
        driver.findElement(By.id("searchBtn")).click();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}