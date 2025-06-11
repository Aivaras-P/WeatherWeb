package lt.viko.eif.tpetrov.Pirmas.definitions;

import io.cucumber.java.en.Then;
import lt.viko.eif.tpetrov.Pirmas.helper.DriverHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertFalse;

public class ForecastSteps {

    WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(15));

    @Then("the forecast temperature should be visible for each of the next 6 days")
    public void forecastTemperatureShouldBeVisible() {
        List<WebElement> buttons = wait.until(driver ->
                driver.findElements(By.cssSelector(".day-btn")));

        assertFalse("Expected at least 7 day buttons", buttons.size() < 7);

        for (int i = 1; i <= 6; i++) {
            WebElement button = buttons.get(i);

            ((JavascriptExecutor) DriverHelper.driver).executeScript("arguments[0].scrollIntoView(true);", button);
            ((JavascriptExecutor) DriverHelper.driver).executeScript("arguments[0].click();", button);

            boolean updated = wait.until(driver -> {
                String temp = driver.findElement(By.cssSelector(".temperature .speed-value")).getText().trim();
                return !temp.equals("--") && !temp.isEmpty();
            });

            String tempText = DriverHelper.driver.findElement(By.cssSelector(".temperature .speed-value")).getText().trim();
            System.out.println("Day " + i + " temperature: " + tempText);
            assertFalse("Temperature on day " + i + " should not be '--'", tempText.equals("--"));
        }
    }

    @Then("the average night temperature should be visible for each of the next 6 days")
    public void nightTemperatureShouldBeVisible() {
        List<WebElement> buttons = wait.until(driver ->
                driver.findElements(By.cssSelector(".day-btn")));

        assertFalse("Expected at least 7 day buttons", buttons.size() < 7);

        for (int i = 1; i <= 6; i++) {
            WebElement button = buttons.get(i);

            ((JavascriptExecutor) DriverHelper.driver).executeScript("arguments[0].scrollIntoView(true);", button);
            ((JavascriptExecutor) DriverHelper.driver).executeScript("arguments[0].click();", button);

            // Palaukiam, kol naktinės temperatūros blokas taps matomas
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("night-temp-container")));

            WebElement nightTempElement = DriverHelper.driver.findElement(By.cssSelector("#night-temp"));
            String nightTemp = nightTempElement.getText().trim();

            System.out.println("Day " + i + " night temperature: " + nightTemp);
            assertFalse("Night temperature on day " + i + " should not be empty or '--'", nightTemp.isEmpty() || nightTemp.equals("--"));
        }
    }
}
