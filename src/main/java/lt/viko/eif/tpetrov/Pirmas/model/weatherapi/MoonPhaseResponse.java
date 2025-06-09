package lt.viko.eif.tpetrov.Pirmas.model.weatherapi;

/**
 * Represents the moon phase and astronomical data returned by the WeatherAPI.
 */
public class MoonPhaseResponse {

    private Astronomy astronomy;

    /**
     * Returns the astronomy information.
     *
     * @return {@link Astronomy}
     */
    public Astronomy getAstronomy() {
        return astronomy;
    }

    /**
     * Contains astronomical data such as moon phase, sunrise, and sunset.
     */
    public static class Astronomy {
        private Astro astro;

        /**
         * Returns the astro data.
         *
         * @return {@link Astro}
         */
        public Astro getAstro() {
            return astro;
        }
    }

    /**
     * Represents moon phase and sun timing information.
     */
    public static class Astro {
        private String moon_phase;
        private String sunrise;
        private String sunset;

        /**
         * Returns the moon phase (e.g., "Waning Crescent").
         *
         * @return moon phase description
         */
        public String getMoon_phase() {
            return moon_phase;
        }

        /**
         * Returns the sunrise time.
         *
         * @return sunrise time in format "HH:mm"
         */
        public String getSunrise() {
            return sunrise;
        }

        /**
         * Returns the sunset time.
         *
         * @return sunset time in format "HH:mm"
         */
        public String getSunset() {
            return sunset;
        }
    }
}
