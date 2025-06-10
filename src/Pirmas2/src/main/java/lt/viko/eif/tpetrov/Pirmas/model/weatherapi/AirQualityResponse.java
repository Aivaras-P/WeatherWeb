package lt.viko.eif.tpetrov.Pirmas.model.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the air quality response from WeatherAPI.
 * Adapted to work with WeatherAPI's JSON structure.
 */
public class AirQualityResponse {
    private Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    // Adapter methods to maintain compatibility with existing code
    public CurrentConditions getCurrentConditions() {
        if (current != null && current.getAirQuality() != null) {
            CurrentConditions conditions = new CurrentConditions();
            conditions.setAqi(current.getAirQuality().getUsEpaIndex());
            return conditions;
        }
        return null;
    }

    public List<Day> getDays() {
        // WeatherAPI current endpoint doesn't provide daily data
        // Return null or empty list to maintain compatibility
        return null;
    }

    /**
     * WeatherAPI current data structure
     */
    public static class Current {
        @JsonProperty("air_quality")
        private AirQuality airQuality;

        public AirQuality getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(AirQuality airQuality) {
            this.airQuality = airQuality;
        }
    }

    /**
     * WeatherAPI air quality data
     */
    public static class AirQuality {
        private double co;
        private double no2;
        private double o3;
        private double so2;
        @JsonProperty("pm2_5")
        private double pm2_5;
        private double pm10;
        @JsonProperty("us-epa-index")
        private int usEpaIndex;
        @JsonProperty("gb-defra-index")
        private int gbDefraIndex;

        // Getters and setters
        public double getCo() { return co; }
        public void setCo(double co) { this.co = co; }

        public double getNo2() { return no2; }
        public void setNo2(double no2) { this.no2 = no2; }

        public double getO3() { return o3; }
        public void setO3(double o3) { this.o3 = o3; }

        public double getSo2() { return so2; }
        public void setSo2(double so2) { this.so2 = so2; }

        public double getPm2_5() { return pm2_5; }
        public void setPm2_5(double pm2_5) { this.pm2_5 = pm2_5; }

        public double getPm10() { return pm10; }
        public void setPm10(double pm10) { this.pm10 = pm10; }

        public int getUsEpaIndex() { return usEpaIndex; }
        public void setUsEpaIndex(int usEpaIndex) { this.usEpaIndex = usEpaIndex; }

        public int getGbDefraIndex() { return gbDefraIndex; }
        public void setGbDefraIndex(int gbDefraIndex) { this.gbDefraIndex = gbDefraIndex; }
    }

    /**
     * Compatibility class - represents current air quality conditions.
     * Maintains compatibility with existing code that expects this structure.
     */
    public static class CurrentConditions {
        private int aqi;

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }
    }

    /**
     * Compatibility class - represents daily weather data including AQI.
     * Note: WeatherAPI current endpoint doesn't provide daily forecasts.
     */
    public static class Day {
        private String datetime;
        private int aqi;

        public String getDatetime() {
            return datetime;
        }

        public int getAqi() {
            return aqi;
        }
    }
}