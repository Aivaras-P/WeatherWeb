package lt.viko.eif.tpetrov.Pirmas.definitions;

import io.cucumber.java.en.Then;
import lt.viko.eif.tpetrov.Pirmas.helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TemperatureSteps {

    WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(10));

    @Then("the temperature value should be visible and not \"--\"")
    public void temperatureShouldBeVisible() {
        wait.until(driver -> {
            String temp = driver.findElement(By.cssSelector(".temperature .speed-value")).getText().trim();
            return !temp.equals("--");
        });

        String tempText = DriverHelper.driver.findElement(By.cssSelector(".temperature .speed-value")).getText().trim();
        System.out.println("Temperature: " + tempText);
        assertFalse("Temperature should not be '--'", tempText.equals("--"));
    }

    @Then("the feels like temperature should be visible")
    public void feelsLikeShouldBeVisible() {
        WebDriverWait wait = new WebDriverWait(DriverHelper.driver, Duration.ofSeconds(15));

        WebElement feelsLikeElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feels-like")));

        boolean loaded = wait.until(driver -> {
            String text = feelsLikeElem.getText().trim();
            System.out.println("Feels like: " + text);
            return !text.isEmpty() && !text.equals("--");
        });

        assertFalse("Feels like temperature should not be empty or '--'", !loaded);
    }


    @Then("the current weather description should be visible")
    public void weatherDescriptionShouldBeVisible() {
        WebElement descElem = wait.until(driver ->
                driver.findElement(By.id("weather-description")));
        String text = descElem.getText().trim();
        System.out.println("Weather description: " + text);
        assertFalse("Weather description should not be empty", text.isEmpty());
    }

    @Then("the wind speed should be visible")
    public void windSpeedShouldBeVisible() {
        WebElement windElem = wait.until(driver ->
                driver.findElement(By.cssSelector(".wind-speed .speed-value")));
        String text = windElem.getText().trim();
        System.out.println("Wind speed: " + text);
        assertTrue("Wind speed should contain km/h", text.contains("km/h"));
    }
    @Then("the sunrise time should be visible")
    public void sunriseShouldBeVisible() {
        WebElement sunriseElem = wait.until(driver ->
                driver.findElement(By.cssSelector(".sunrise .sun-value")));
        String text = sunriseElem.getText().trim();
        System.out.println("Sunrise: " + text);
        assertFalse("Sunrise should not be empty", text.isEmpty() || text.equals("--"));
    }

    @Then("the sunset time should be visible")
    public void sunsetShouldBeVisible() {
        WebElement sunsetElem = wait.until(driver ->
                driver.findElement(By.cssSelector(".sunset .sunset-value")));
        String text = sunsetElem.getText().trim();
        System.out.println("Sunset: " + text);
        assertFalse("Sunset should not be empty", text.isEmpty() || text.equals("--"));
    }

    @Then("the air quality should be visible")
    public void airQualityShouldBeVisible() {
        WebElement airElem = wait.until(driver ->
                driver.findElement(By.cssSelector(".air-quality .quality-value")));
        String text = airElem.getText().trim();
        System.out.println("Air Quality: " + text);
        assertFalse("Air quality should not be empty", text.isEmpty() || text.equals("--") || text.equals("N/A"));
    }

}
