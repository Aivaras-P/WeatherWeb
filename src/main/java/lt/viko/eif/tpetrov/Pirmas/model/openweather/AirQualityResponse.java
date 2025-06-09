package lt.viko.eif.tpetrov.Pirmas.model.openweather;

/**
 * Represents the air quality response received from an external weather API.
 * <p>
 * This class holds information about the current air quality index (AQI).
 * </p>
 */
public class AirQualityResponse {

    /**
     * Object representing the current air quality conditions.
     */
    private CurrentConditions currentConditions;

    /**
     * Returns the current air quality conditions.
     *
     * @return an instance of {@link CurrentConditions}
     */
    public CurrentConditions getCurrentConditions() {
        return currentConditions;
    }

    /**
     * Represents the current air quality conditions.
     */
    public static class CurrentConditions {

        /**
         * Air Quality Index (AQI) value.
         */
        private int aqi;

        /**
         * Returns the Air Quality Index (AQI).
         *
         * @return the AQI value
         */
        public int getAqi() {
            return aqi;
        }

        /**
         * Sets the Air Quality Index (AQI).
         *
         * @param aqi the AQI value to set
         */
        public void setAqi(int aqi) {
            this.aqi = aqi;
        }
    }
}
