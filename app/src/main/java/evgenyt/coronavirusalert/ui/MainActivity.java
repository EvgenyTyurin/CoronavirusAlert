package evgenyt.coronavirusalert.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import evgenyt.coronavirusalert.R;
import evgenyt.coronavirusalert.data.DataStorage;
import evgenyt.coronavirusalert.data.VirusCase;
import evgenyt.coronavirusalert.utils.GPSTracker;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init location
        TextView locationTextView = findViewById(R.id.text1);
        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation() && gps.getLatitude() != 0){
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses =
                        geocoder.getFromLocation(gps.getLatitude(), gps.getLongitude() , 1);
                locationTextView.setText(addresses.get(0).getCountryName() + ", " +
                        addresses.get(0).getLocality());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            locationTextView.setText("Please, set geolocation permission to application");
        }
        // Init virus data
        DataStorage dataStorage = DataStorage.getInstance();
        List<String> alertList = new ArrayList<>();
        for (VirusCase virusCase : dataStorage.getVirusCases()) {
            alertList.add(virusCase.getDate().toString() + ":" +
                    virusCase.getNewPatients());
        }
        alertList.add("empty");
        final ListAdapter arrayAdapter = new ArrayAdapter<>(this,
                R.layout.list_item, alertList);
        ListView alertListView = findViewById(R.id.alert_list);
        alertListView.setAdapter(arrayAdapter);
    }
}
