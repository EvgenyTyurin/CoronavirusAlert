package evgenyt.coronavirusalert.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {

    private static DataStorage dataStorage;
    private List<VirusCase> virusCases = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private DataStorage() {

        try (BufferedReader br = new BufferedReader(new FileReader("cases.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                virusCases.add(
                        new VirusCase(
                            Long.valueOf(values[0]),
                            Long.valueOf(values[1]),
                            LocalDate.parse(values[2], formatter),
                            Integer.valueOf(values[3])
                        )
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataStorage getInstance() {
        if (dataStorage == null) {
            dataStorage = new DataStorage();
        }
        return dataStorage;
    }

    public List<VirusCase> getVirusCases() {
        return virusCases;
    }
}
