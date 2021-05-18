package uk.ac.ucl.model;

import uk.ac.ucl.model.element.Element;
import uk.ac.ucl.model.element.Thing;

import java.io.FileWriter;
import java.io.IOException;

public class DFWriter {
    private final DataFrame dataFrame;

    public DFWriter(DataFrame dataframe){
        this.dataFrame = dataframe;
    }

    public void saveToCSV(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);

        fw.write(createHeader());
        for (Element element : this.dataFrame.getElementHashMap().values()){
            if (element.getParent() != null) {
                fw.write(createLineEntry(element));
                if (element instanceof Thing) {
                    TXTWriter txtWriter = new TXTWriter(((Thing) element).getContent());
                    txtWriter.saveToTXT("./data/content/"+element.getID() + ".txt");
                }
            }
        }
        fw.close();
    }

    private String createHeader(){
        return "ID,type,label,parent,content\n";
    }

    private String createLineEntry(Element element){
        String entry;
        entry = element.getID() +",\""+element.getType()+"\",\""+element.getLabel()+"\","+element.getParent().getID()+"\n";
        return entry;
    }

}
