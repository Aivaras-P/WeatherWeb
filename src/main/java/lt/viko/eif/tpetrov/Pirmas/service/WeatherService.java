package lt.viko.eif.tpetrov.Pirmas.service;

import lt.viko.eif.tpetrov.Pirmas.model.openweather.*;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * Service for retrieving weather-related data such as current weather,
 * 7-day forecast, moon phase, and air quality using external APIs
 * (OpenWeatherMap WeatherApi and Visual Crossing).
 */
@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String openWeatherKey;

    @Value("${visualcrossing.api.key}")
    private String visualCrossingApiKey;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public CurrentWeatherResponse getCurrentWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + openWeatherKey + "&units=metric";
        return restTemplate.getForObject(url, CurrentWeatherResponse.class);
    }

    public MoonPhaseResponse getMoonPhase(String city) {
        String startDate = LocalDate.now().toString();
        String endDate = LocalDate.now().plusDays(6).toString();

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + city + "/" + startDate + "/" + endDate
                + "?key=" + visualCrossingApiKey
                + "&elements=datetime,moonphase,sunrise,sunset";

        return restTemplate.getForObject(url, MoonPhaseResponse.class);
    }


    public ForecastResponse getSevenDayForecast(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + lat + "&lon=" + lon + "&exclude=current,minutely,hourly,alerts&appid=" + openWeatherKey + "&units=metric";
        return restTemplate.getForObject(url, ForecastResponse.class);
    }


    public ForecastResponse getTodayHourlyForecast(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/3.0/onecall?lat=" + lat + "&lon=" + lon
                + "&exclude=current,minutely,daily,alerts&appid=" + openWeatherKey + "&units=metric";
        return restTemplate.getForObject(url, ForecastResponse.class);
    }


    public AirQualityResponse getAirQuality(double lat, double lon) {
        String url = "http://api.weatherapi.com/v1/current.json"
                + "?key=" + weatherApiKey
                + "&q=" + lat + "," + lon
                + "&aqi=yes";

        return restTemplate.getForObject(url, AirQualityResponse.class);
    }


    public CityLocation[] getCityAutocomplete(String query) {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + query + "&limit=5&appid=" + openWeatherKey;
        return restTemplate.getForObject(url, CityLocation[].class);
    }
}


