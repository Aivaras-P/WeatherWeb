package lt.viko.eif.tpetrov.Pirmas.controller;

import lt.viko.eif.tpetrov.Pirmas.model.CombinedWeatherResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.AirQualityResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.CurrentWeatherResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.ForecastResponse;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.MoonPhaseResponse;
import lt.viko.eif.tpetrov.Pirmas.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that handles weather-related API endpoints.
 * <p>
 * This controller provides an endpoint for retrieving a combined weather report
 * that includes current weather conditions, moon phase, 7-day forecast,
 * and air quality index (AQI) for a given city.
 * </p>
 *
 * <p>
 * Endpoint path: {@code /api/weather/{city}} <br>
 * Example request: {@code GET /api/weather/London}
 * </p>
 *
 * <p>
 * This controller uses {@link WeatherService} to fetch and aggregate data
 * from multiple external weather APIs.
 * </p>
 *
 * @author tpetrov
 */
@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*") // Enables CORS for all domains; adapt for production
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Constructs a new instance of {@code WeatherController}.
     *
     * @param weatherService the service responsible for retrieving weather data
     */
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Retrieves a combined weather response for the specified city.
     * <p>
     * This endpoint gathers the following data points:
     * <ul>
     *     <li>Current weather from OpenWeatherMap</li>
     *     <li>Moon phase, sunrise, and sunset from Visual Crossing</li>
     *     <li>7-day weather forecast from OpenWeatherMap</li>
     *     <li>Air quality index from Visual Crossing</li>
     * </ul>
     * </p>
     *
     * @param city the name of the city (e.g., "Vilnius", "London")
     * @return a {@link ResponseEntity} containing a {@link CombinedWeatherResponse} if successful,
     *         or {@code 404 NOT FOUND} if the city is invalid or not found
     */
    @GetMapping("/{city}")
    public ResponseEntity<CombinedWeatherResponse> getWeather(@PathVariable String city) {
        CurrentWeatherResponse current = weatherService.getCurrentWeather(city);
        if (current == null || current.getCoord() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        double lat = current.getCoord().getLat();
        double lon = current.getCoord().getLon();

        MoonPhaseResponse moon = weatherService.getMoonPhase(city);
        ForecastResponse forecast = weatherService.getSevenDayForecast(lat, lon);
        AirQualityResponse air = weatherService.getAirQuality(lat, lon);

        CombinedWeatherResponse combined = new CombinedWeatherResponse(current, moon, forecast, air);
        return ResponseEntity.ok(combined);
    }
}
