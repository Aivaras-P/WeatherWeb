package lt.viko.eif.tpetrov.Pirmas.model.weatherapi;

import java.util.List;

/**
 * Represents the moon phase and astronomical data returned by the WeatherAPI.
 */
public class MoonPhaseResponse {
    private List<Day> days;

    public List<Day> getDays() { return days; }

    public static class Day {
        private String datetime;
        private double moonphase;
        private String sunrise;
        private String sunset;

        public String getDatetime() { return datetime; }
        public double getMoonphase() { return moonphase; }
        public String getSunrise() { return sunrise; }
        public String getSunset() { return sunset; }
    }
}

