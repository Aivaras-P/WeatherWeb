package lt.viko.eif.tpetrov.Pirmas.model.openweather;

import java.util.List;

/**
 * Represents the weather forecast data from the OpenWeather API.
 */
public class ForecastResponse {

    /**
     * A list of daily weather forecast entries.
     */
    private List<Daily> daily;

    /**
     * A list of hourly weather forecast entries.
     */
    private List<Hourly> hourly;

    public List<Daily> getDaily() {
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    /**
     * Represents daily forecast data including temperature, wind, humidity, and weather description.
     */
    public static class Daily {
        private Temp temp;
        private double wind_speed;
        private int humidity;
        private List<Weather> weather;

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public double getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(double wind_speed) {
            this.wind_speed = wind_speed;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }
    }

    /**
     * Represents hourly forecast data.
     */
    public static class Hourly {
        private long dt;
        private double temp;
        private double feels_like;
        private int humidity;
        private double wind_speed;
        private List<Weather> weather;

        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(double wind_speed) {
            this.wind_speed = wind_speed;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }
    }

    /**
     * Represents daily temperature readings.
     */
    public static class Temp {
        private double day;
        private double night;

        public double getDay() {
            return day;
        }

        public void setDay(double day) {
            this.day = day;
        }

        public double getNight() {
            return night;
        }

        public void setNight(double night) {
            this.night = night;
        }
    }

    /**
     * Represents a brief description of the weather condition.
     */
    public static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
