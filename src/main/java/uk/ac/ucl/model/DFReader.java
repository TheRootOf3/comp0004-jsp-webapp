package uk.ac.ucl.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DFReader {
    private final DataFrame dataFrame;
    private final String dir;

    public DFReader(String dir){
        this.dataFrame = new DataFrame();
        this.dir = dir;
    }

    public void loadFromFile() throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(this.dir+"db.csv"));
        String entry;
        if ((entry = f.readLine()) == null)
            throw new IOException("Empty File");

        while ((entry = f.readLine()) != null) {
            createElement(entry);
        }
        f.close();
    }

    private void createElement(String entry) throws IOException{
        String[] splitEntry = entry.split(",");
        for (String a : splitEntry){
            System.out.println(a);
        }
        int elementID = Integer.parseInt(splitEntry[0]);
        String type = splitEntry[1];
        String label = splitEntry[2].substring(1,splitEntry[2].length()-1);
        int parentID = Integer.parseInt(splitEntry[3]);
        switch (type){
            case "list":
                this.dataFrame.addNewListToList(label, parentID, elementID);
                break;
            case "item":
                this.dataFrame.addNewItemToList(label, parentID, elementID);
                break;
            case "text":
            case "url":
                String content = TXTReader.loadFromFile(this.dir+"content/"+elementID+".txt");
                this.dataFrame.addNewThingToItem(type, content, parentID, elementID);
                break;
            default:
                break;
        }
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }
}
