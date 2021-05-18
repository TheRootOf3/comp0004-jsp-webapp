package uk.ac.ucl.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DFReader {
    private DataFrame dataFrame;

    public DFReader(){
        this.dataFrame = new DataFrame();
    }

    public void loadFromFile(String filename) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        String entry;
        if ((entry = f.readLine()) == null)
            throw new IOException("Empty File");

        while ((entry = f.readLine()) != null) {
            extractValues(entry);
//            TODO Read from file and create elements based on the order.
//            TODO read TXT content and create things
        }
        f.close();
    }

    private HashMap<String, String> extractValues(String entry){
        HashMap<String, String> elementValues = new HashMap<>();
        String[] splitEntry = entry.split(",");
        elementValues.put("ID", splitEntry[0]);
        elementValues.put("type", splitEntry[1]);
        elementValues.put("label", splitEntry[2]);
        elementValues.put("parent", splitEntry[3]);
        return elementValues;
    }

}
