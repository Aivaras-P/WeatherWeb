package lt.viko.eif.tpetrov.Pirmas.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import lt.viko.eif.tpetrov.Pirmas.helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class WeatherSteps {

    @Given("the user opens the weather page")
    public void openWeatherPage() {
        WebDriverManager.chromedriver().setup();
        DriverHelper.driver = new ChromeDriver();
        DriverHelper.driver.get("http://localhost:8085");
    }

    @When("the user searches for {string}")
    public void searchCity(String city) {
        WebElement input = DriverHelper.driver.findElement(By.id("citySearch"));
        input.sendKeys(city);
        DriverHelper.driver.findElement(By.id("searchBtn")).click();
    }

    @Then("the hourly chart should be visible at the bottom")
    public void verifyChartAtBottom() {
        WebElement chart = DriverHelper.driver.findElement(By.id("hourlyChart"));
        WebElement body = DriverHelper.driver.findElement(By.tagName("body"));

        int chartY = chart.getLocation().getY();
        int bodyHeight = body.getSize().getHeight();

        assertTrue("Chart should be near the bottom", chartY > bodyHeight * 0.6);

        DriverHelper.driver.quit();
    }
}
