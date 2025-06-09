package lt.viko.eif.tpetrov.Pirmas.model.openweather;

import java.util.List;

/**
 * Represents the 7-day weather forecast data from the OpenWeather API.
 */
public class ForecastResponse {

    /**
     * A list of daily weather forecast entries.
     */
    private List<Daily> daily;

    /**
     * Returns the list of daily forecasts.
     *
     * @return list of {@link Daily}
     */
    public List<Daily> getDaily() {
        return daily;
    }

    /**
     * Represents daily forecast data including temperature, wind, humidity, and weather description.
     */
    public static class Daily {
        private Temp temp;
        private double wind_speed;
        private int humidity;
        private List<Weather> weather;

        /**
         * Returns the temperature data.
         *
         * @return {@link Temp}
         */
        public Temp getTemp() {
            return temp;
        }

        /**
         * Returns the wind speed.
         *
         * @return wind speed in m/s
         */
        public double getWind_speed() {
            return wind_speed;
        }

        /**
         * Returns the humidity level.
         *
         * @return humidity in percent
         */
        public int getHumidity() {
            return humidity;
        }

        /**
         * Returns the list of weather descriptions.
         *
         * @return list of {@link Weather}
         */
        public List<Weather> getWeather() {
            return weather;
        }
    }

    /**
     * Represents daily temperature readings.
     */
    public static class Temp {
        private double day;
        private double night;

        /**
         * Returns the day temperature.
         *
         * @return temperature in degrees
         */
        public double getDay() {
            return day;
        }

        /**
         * Returns the night temperature.
         *
         * @return temperature in degrees
         */
        public double getNight() {
            return night;
        }
    }

    /**
     * Represents a brief description of the weather condition.
     */
    public static class Weather {
        private String description;

        /**
         * Returns the textual description of the weather.
         *
         * @return weather description
         */
        public String getDescription() {
            return description;
        }
    }
}
