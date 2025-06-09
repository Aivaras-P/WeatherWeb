package lt.viko.eif.tpetrov.Pirmas.model;

import lt.viko.eif.tpetrov.Pirmas.model.openweather.AirQualityResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.CurrentWeatherResponse;
import lt.viko.eif.tpetrov.Pirmas.model.openweather.ForecastResponse;
import lt.viko.eif.tpetrov.Pirmas.model.weatherapi.MoonPhaseResponse;

/**
 * Combines weather data from multiple sources into a single response object.
 * <p>
 * This includes:
 * <ul>
 *     <li>Current weather</li>
 *     <li>Moon phase and sunrise/sunset times</li>
 *     <li>7-day forecast</li>
 *     <li>Air quality index</li>
 * </ul>
 * </p>
 */
public class CombinedWeatherResponse {
    private CurrentWeatherResponse current;
    private MoonPhaseResponse moon;
    private ForecastResponse forecast;
    private AirQualityResponse air;

    /**
     * Constructs a combined weather response.
     *
     * @param current  the current weather data
     * @param moon     the moon and astronomy data
     * @param forecast the 7-day forecast data
     * @param air      the air quality data
     */
    public CombinedWeatherResponse(CurrentWeatherResponse current, MoonPhaseResponse moon,
                                   ForecastResponse forecast, AirQualityResponse air) {
        this.current = current;
        this.moon = moon;
        this.forecast = forecast;
        this.air = air;
    }

    /**
     * Returns the current weather data.
     *
     * @return {@link CurrentWeatherResponse}
     */
    public CurrentWeatherResponse getCurrent() {
        return current;
    }

    /**
     * Returns the moon and astronomy data.
     *
     * @return {@link MoonPhaseResponse}
     */
    public MoonPhaseResponse getMoon() {
        return moon;
    }

    /**
     * Returns the 7-day forecast data.
     *
     * @return {@link ForecastResponse}
     */
    public ForecastResponse getForecast() {
        return forecast;
    }

    /**
     * Returns the air quality data.
     *
     * @return {@link AirQualityResponse}
     */
    public AirQualityResponse getAir() {
        return air;
    }
}
