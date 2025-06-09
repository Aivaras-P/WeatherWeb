package lt.viko.eif.tpetrov.Pirmas.service;

import jakarta.annotation.PostConstruct;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.AirQualityResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.CurrentWeatherResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.ForecastResponse;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.MoonPhaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * Service for retrieving weather-related data such as current weather,
 * 7-day forecast, moon phase, and air quality using external APIs
 * (OpenWeatherMap and Visual Crossing).
 */
@Service
public class WeatherService {

    /**
     * OpenWeatherMap API key, injected from application.properties.
     */
    @Value("${openweather.api.key}")
    private String openWeatherKey;

    /**
     * Visual Crossing API key, injected from application.properties.
     */
    @Value("${visualcrossing.api.key}")
    private String visualCrossingApiKey;

    /**
     * HTTP client used for sending requests to external APIs.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieves current weather information for a given city.
     *
     * @param city the name of the city
     * @return {@link CurrentWeatherResponse} containing weather data
     */
    public CurrentWeatherResponse getCurrentWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + openWeatherKey + "&units=metric";
        return restTemplate.getForObject(url, CurrentWeatherResponse.class);
    }

    /**
     * Retrieves the moon phase, sunrise, and sunset times for the current date
     * in the specified city using the Visual Crossing API.
     *
     * @param city the name of the city
     * @return {@link MoonPhaseResponse} with moon and sun data
     */
    public MoonPhaseResponse getMoonPhase(String city) {
        String date = LocalDate.now().toString();
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + city + "/" + date + "/" + date
                + "?key=" + visualCrossingApiKey
                + "&elements=sunrise,sunset,moonphase";

        return restTemplate.getForObject(url, MoonPhaseResponse.class);
    }

    /**
     * Retrieves a 7-day weather forecast for the specified geographic coordinates.
     *
     * @param lat latitude of the location
     * @param lon longitude of the location
     * @return {@link ForecastResponse} containing forecast data
     */
    public ForecastResponse getSevenDayForecast(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + lat + "&lon=" + lon + "&exclude=current,minutely,hourly,alerts&appid=" + openWeatherKey + "&units=metric";
        return restTemplate.getForObject(url, ForecastResponse.class);
    }

    /**
     * Retrieves the air quality index (AQI) for the given coordinates
     * using the Visual Crossing API.
     *
     * @param lat latitude of the location
     * @param lon longitude of the location
     * @return {@link AirQualityResponse} with AQI data
     */
    public AirQualityResponse getAirQuality(double lat, double lon) {
        String date = LocalDate.now().toString(); // Cia reikejo string'a padaryti

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + lat + "," + lon
                + "/" + date
                + "?key=" + visualCrossingApiKey
                + "&include=obs,alerts"
                + "&elements=aqi";

        return restTemplate.getForObject(url, AirQualityResponse.class);
    }

    /*
    // Optional method for printing trimmed API keys to console.
    @PostConstruct
    public void init() {
        visualCrossingApiKey = visualCrossingApiKey.trim();
        openWeatherKey = openWeatherKey.trim();
        System.out.println("OpenWeather Key:" + openWeatherKey);
        System.out.println("WeatherAPI Key:" + visualCrossingApiKey);
    }
    */
}
