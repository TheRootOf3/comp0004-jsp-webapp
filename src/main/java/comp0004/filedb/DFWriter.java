package comp0004.filedb;

import comp0004.model.DataFrame;
import comp0004.model.element.Element;
import comp0004.model.element.thing.Thing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DFWriter {
    private final DataFrame dataFrame;
    private final String dir;

    public DFWriter(DataFrame dataframe, String dir){
        this.dataFrame = dataframe;
        this.dir = dir;
    }

    public void saveToCSV(boolean thingEdited) throws IOException {
        if (thingEdited)
            cleanContent();

        FileWriter fw = new FileWriter(this.dir+"db.csv");

        fw.write(createHeader());
        for (Element element : this.dataFrame.getElementHashMap().values()){
            if (element.getParent() != null) {
                fw.write(createLineEntry(element));
                if (element instanceof Thing) {
                    TXTWriter.saveToTXT(this.dir+"content"+File.separator+element.getID() + ".txt", ((Thing) element).getContent());
                }
            }
        }
        fw.close();
    }

    private void cleanContent(){
        File[] fileList = new File(dir+"content").listFiles();
        if (fileList != null){
        for(File file: fileList)
            if (!file.isDirectory())
                file.delete();
        }
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
