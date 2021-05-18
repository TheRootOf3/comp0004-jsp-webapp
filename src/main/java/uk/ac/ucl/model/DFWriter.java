package uk.ac.ucl.model;

import uk.ac.ucl.model.element.Element;
import uk.ac.ucl.model.element.Thing;

import java.io.FileWriter;
import java.io.IOException;

public class DFWriter {
    private final DataFrame dataFrame;
    private final String dir;

    public DFWriter(DataFrame dataframe, String dir){
        this.dataFrame = dataframe;
        this.dir = dir;
    }

    public void saveToCSV() throws IOException {
        FileWriter fw = new FileWriter(this.dir+"db.csv");

        fw.write(createHeader());
        for (Element element : this.dataFrame.getElementHashMap().values()){
            if (element.getParent() != null) {
                fw.write(createLineEntry(element));
                if (element instanceof Thing) {
                    TXTWriter.saveToTXT(this.dir+"content/"+element.getID() + ".txt", ((Thing) element).getContent());
                }
            }
        }
        fw.close();
    }

    private String createHeader(){
        return "ID,type,label,parent\n";
    }

    private String createLineEntry(Element element){
        String entry;
        entry = element.getID() +","+element.getType()+",\""+element.getLabel()+"\","+element.getParent().getID()+"\n";
        return entry;
    }

}
