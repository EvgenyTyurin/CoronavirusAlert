package evgenyt.coronavirusalert.data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import evgenyt.coronavirusalert.utils.Utils;

public class DataStorage {

    private static DataStorage dataStorage;
    private List<VirusCase> virusCases = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private DataStorage(Context context, double latitude, double longitude) {

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("cases.csv")));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                virusCases.add(
                        new VirusCase(
                                Double.valueOf(values[0]),
                                Double.valueOf(values[1]),
                                LocalDate.parse(values[2], formatter),
                                Integer.valueOf(values[3]),
                                values[4]
                        )
                );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (VirusCase virusCase: virusCases) {
            virusCase.setDistance(
                    Math.round(Utils.distance(virusCase.getLatitude(),
                    virusCase.getLongitude(), latitude, longitude, "K")));
        }

        Collections.sort(virusCases);

    }

    public static DataStorage getInstance(Context context,
                                          double latitude,
                                          double longitude) {
        if (dataStorage == null) {
            dataStorage = new DataStorage(context, latitude, longitude);
        }
        return dataStorage;
    }

    public List<VirusCase> getVirusCases() {
        return virusCases;
    }
}
