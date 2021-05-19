package comp0004.filedb;

import comp0004.model.DataFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
//        Omit the first line since it contains headers.

        while ((entry = f.readLine()) != null) {
            createElement(entry);
        }
        f.close();
    }

    private void createElement(String entry) throws IOException{
//        0 - id, 1 - type, 2 - label, 3 - parentID
        String[] splitEntry = entry.split(",");
        int elementID = Integer.parseInt(splitEntry[0]);
        String type = splitEntry[1];
        String label = splitEntry[2].substring(1,splitEntry[2].length()-1);
        int parentID = Integer.parseInt(splitEntry[3]);
        switch (type){
            case "list":
            case "item":
                this.dataFrame.addNewElementToList(label, parentID, elementID, type);
                break;
            case "text":
            case "url":
            case "image":
                String content = TXTReader.loadFromFile(this.dir+"content"+ File.separator+elementID+".txt"); //if thing -> read from file
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
