package comp0004.filedb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TXTReader {

    //    Simply read file content
    public static String loadFromFile(String filename) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringBuilder content = new StringBuilder();
        String line;
        if ((line = f.readLine()) == null)
            return content.toString();
        content.append(line);
        while ((line = f.readLine()) != null)
            content.append(line);
        f.close();

        return content.toString();
    }
}
