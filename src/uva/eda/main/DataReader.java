package uva.eda.main;

import java.io.*;
import java.util.ArrayList;

public class DataReader {
    private final String directory;
    private ArrayList<Persona> data;

    public DataReader(String directory) {
        this.directory = directory;
    }

    public ArrayList<Persona> readData() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(directory)));
            int entryCount = Integer.parseInt(reader.readLine());
            data = new ArrayList<>(entryCount);
            long t0, t1;
            t0 = System.nanoTime();
            Persona temp;
            for (int i = 0; i < entryCount; i++) {
                temp = new Persona();
                temp.parseData(reader.readLine());
                temp.setName(reader.readLine());
                data.add(temp);
            }
            t1 = System.nanoTime() - t0;
            System.out.println("Time reading data: " + t1 * 1e-9 + "sec");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String[] splitDateInput(String dateInterval) {
       return dateInterval.split(" ");
    }
}
