package lt.viko.eif.tpetrov.Pirmas.model;

import lt.viko.eif.tpetrov.Pirmas.model.openweather.*;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.*;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.*;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.*;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.*;

/**
 * Combines weather data from multiple sources into a single response object.
 */
public class CombinedWeatherResponse {
    private CurrentWeatherResponse current;
    private MoonPhaseResponse moon;
    private ForecastResponse forecast;
    private ForecastResponse hourly;
    private AirQualityResponse air;
    private CityLocation[] suggestions;

    public CombinedWeatherResponse(CurrentWeatherResponse current, MoonPhaseResponse moon,
                                   ForecastResponse forecast, ForecastResponse hourly,
                                   AirQualityResponse air, CityLocation[] suggestions) {
        this.current = current;
        this.moon = moon;
        this.forecast = forecast;
        this.hourly = hourly;
        this.air = air;
        this.suggestions = suggestions;
    }

    public CurrentWeatherResponse getCurrent() {
        return current;
    }

    public MoonPhaseResponse getMoon() {
        return moon;
    }

    public ForecastResponse getForecast() {
        return forecast;
    }

    public ForecastResponse getHourly() {
        return hourly;
    }

    public AirQualityResponse getAir() {
        return air;
    }

    public CityLocation[] getSuggestions() {
        return suggestions;
    }
}
