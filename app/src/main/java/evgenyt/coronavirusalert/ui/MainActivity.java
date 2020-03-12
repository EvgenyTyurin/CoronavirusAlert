package evgenyt.coronavirusalert.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import evgenyt.coronavirusalert.R;
import evgenyt.coronavirusalert.data.DataStorage;
import evgenyt.coronavirusalert.data.VirusCase;
import evgenyt.coronavirusalert.utils.GPSTracker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init location
        TextView locationTextView = findViewById(R.id.text1);
        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation() && gps.getLatitude() != 0){
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses =
                        geocoder.getFromLocation(gps.getLatitude(),
                                gps.getLongitude() , 1);
                locationTextView.setText("Your location: " +
                        addresses.get(0).getCountryName() + ", " +
                        addresses.get(0).getLocality());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            locationTextView.setText("Your location: Unknown. Permit geolocation!");
        }
        // Init virus data
        DataStorage dataStorage = DataStorage.getInstance(this,
                gps.getLatitude(), gps.getLongitude());
        List<String> alertList = new ArrayList<>();
        for (VirusCase virusCase : dataStorage.getVirusCases()) {
            String place = virusCase.getPlace();
            if (place.equals("-"))
                try {
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(virusCase.getLatitude(),
                            virusCase.getLongitude() , 1);
                    place = addresses.get(0).getCountryName() + ", " +
                            addresses.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            alertList.add(virusCase.getDate().toString() + ", " +
                    place + ", " +
                    virusCase.getNewPatients() + " cases, " +
                    virusCase.getDistance() + " km.");
        }
        final ListAdapter arrayAdapter = new ArrayAdapter<>(this,
                R.layout.list_item, alertList);
        ListView alertListView = findViewById(R.id.alert_list);
        alertListView.setAdapter(arrayAdapter);
    }


}
