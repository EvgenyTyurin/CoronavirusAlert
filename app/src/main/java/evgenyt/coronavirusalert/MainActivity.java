package evgenyt.coronavirusalert;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text1);
        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            gps.getLatitude(); // returns latitude
            gps.getLongitude();
            textView.setText("Latitude:" + gps.getLatitude() + " Longitude:" + gps.getLongitude() );
        }
        /*
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            textView.setText(location.toString());
                        } else {
                            textView.setText("Location is null");
                        }
                    }
                });

         */
    }
}
