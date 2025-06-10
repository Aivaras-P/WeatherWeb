package lt.viko.eif.tpetrov.Pirmas.model.openweather;

import java.util.List;

/**
 * Represents the current weather data received from the OpenWeather API.
 * <p>
 * Contains information about coordinates, temperature, wind, and general weather description.
 * </p>
 */
public class CurrentWeatherResponse {

    /**
     * The geographical coordinates of the location.
     */
    private Coord coord;

    /**
     * The main weather data, including temperature and feels-like temperature.
     */
    private Main main;

    /**
     * The wind data, including speed.
     */
    private Wind wind;

    /**
     * List of weather conditions, such as descriptions.
     */
    private List<Weather> weather;

    /**
     * Returns the coordinates of the location.
     *
     * @return an instance of {@link Coord}
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Returns the main weather data.
     *
     * @return an instance of {@link Main}
     */
    public Main getMain() {
        return main;
    }

    /**
     * Returns the wind data.
     *
     * @return an instance of {@link Wind}
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Returns the list of weather descriptions.
     *
     * @return a list of {@link Weather}
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * Represents the main temperature data.
     */
    public static class Main {
        /**
         * Current temperature.
         */
        private double temp;

        /**
         * Feels-like temperature.
         */
        private double feels_like;

        /**
         * Returns the current temperature.
         *
         * @return temperature in degrees
         */
        public double getTemp() {
            return temp;
        }

        /**
         * Returns the feels-like temperature.
         *
         * @return feels-like temperature in degrees
         */
        public double getFeels_like() {
            return feels_like;
        }
    }

    /**
     * Represents wind data.
     */
    public static class Wind {
        /**
         * Wind speed.
         */
        private double speed;

        /**
         * Returns the wind speed.
         *
         * @return speed in m/s
         */
        public double getSpeed() {
            return speed;
        }
    }

    /**
     * Represents a weather condition description.
     */
    public static class Weather {
        /**
         * Textual description of the weather (e.g., "clear sky").
         */
        private String description;

        /**
         * Returns the weather description.
         *
         * @return a short description of the weather
         */
        public String getDescription() {
            return description;
        }
    }

    /**
     * Represents geographic coordinates.
     */
    public static class Coord {
        /**
         * Longitude of the location.
         */
        private double lon;

        /**
         * Latitude of the location.
         */
        private double lat;

        /**
         * Returns the longitude.
         *
         * @return longitude value
         */
        public double getLon() {
            return lon;
        }

        /**
         * Returns the latitude.
         *
         * @return latitude value
         */
        public double getLat() {
            return lat;
        }
    }
}
