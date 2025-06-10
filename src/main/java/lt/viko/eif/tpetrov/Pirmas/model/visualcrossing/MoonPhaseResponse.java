package lt.viko.eif.tpetrov.Pirmas.model.visualcrossing;

import java.util.List;

public class MoonPhaseResponse {
    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public static class Day {
        private String datetime;
        private Double moonphase;
        private String sunrise;
        private String sunset;

        public String getDatetime() {
            return datetime;
        }

        public Double getMoonphase() {
            return moonphase;
        }

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }
    }
}
