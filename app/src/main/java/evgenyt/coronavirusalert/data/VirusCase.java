package evgenyt.coronavirusalert.data;

import java.time.LocalDate;

public class VirusCase {

    private final long latitude;
    private final long longitude;
    private final LocalDate date;
    private final int newPatients;

    public VirusCase(long latitude, long longitude, LocalDate date, int newPatients) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.newPatients = newPatients;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public int getNewPatients() {
        return newPatients;
    }

    public LocalDate getDate() {
        return date;
    }
}
