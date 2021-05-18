package uk.ac.ucl.model;

import java.io.FileWriter;
import java.io.IOException;

public class TXTWriter {
    private final String content;

    public TXTWriter(String content){
        this.content = content;
    }

    public void saveToTXT(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        fw.write(this.content);
        fw.close();
    }
}
