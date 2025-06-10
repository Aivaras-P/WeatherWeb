package lt.viko.eif.tpetrov.Pirmas.controller;

import lt.viko.eif.tpetrov.Pirmas.model.*;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.*;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.*;
import lt.viko.eif.tpetrov.Pirmas.service.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST controller that handles weather-related API endpoints.
 */
@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<EntityModel<CombinedWeatherResponse>> getWeather(@PathVariable String city) {
        CurrentWeatherResponse current = weatherService.getCurrentWeather(city);
        if (current == null || current.getCoord() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        double lat = current.getCoord().getLat();
        double lon = current.getCoord().getLon();

        MoonPhaseResponse moon = weatherService.getMoonPhase(city);
        ForecastResponse forecast = weatherService.getSevenDayForecast(lat, lon);
        ForecastResponse hourly = weatherService.getTodayHourlyForecast(lat, lon);
        AirQualityResponse air = weatherService.getAirQuality(lat, lon);
        CityLocation[] suggestions = weatherService.getCityAutocomplete(city);

        CombinedWeatherResponse combined = new CombinedWeatherResponse(
                current, moon, forecast, hourly, air, suggestions
        );

        // Create HATEOAS links
        EntityModel<CombinedWeatherResponse> model = EntityModel.of(combined);

        Link selfLink = linkTo(methodOn(WeatherController.class).getWeather(city)).withSelfRel();
        Link forecastLink = linkTo(methodOn(WeatherController.class).getWeather(city)).withRel("7-day-forecast");
        Link airQualityLink = linkTo(methodOn(WeatherController.class).getWeather(city)).withRel("air-quality");
        Link moonPhaseLink = linkTo(methodOn(WeatherController.class).getWeather(city)).withRel("moon-phase");

        model.add(selfLink, forecastLink, airQualityLink, moonPhaseLink);

        return ResponseEntity.ok(model);
    }
}
