package uk.ac.ucl.model;

import java.io.FileWriter;
import java.io.IOException;

public class TXTWriter {

    public static void saveToTXT(String filename, String content) throws IOException {
        FileWriter fw = new FileWriter(filename);
        fw.write(content);
        fw.close();
    }
}
