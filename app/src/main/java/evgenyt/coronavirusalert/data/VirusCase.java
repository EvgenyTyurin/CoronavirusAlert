package evgenyt.coronavirusalert.data;

import java.time.LocalDate;

public class VirusCase implements Comparable{

    private final double latitude;
    private final double longitude;
    private final LocalDate date;
    private final int newPatients;
    private String place;
    private double distance;

    public VirusCase(double latitude,
                     double longitude,
                     LocalDate date,
                     int newPatients,
                     String place) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.newPatients = newPatients;
        this.place = place;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getNewPatients() {
        return newPatients;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (distance - ((VirusCase)o).distance);
    }
}
