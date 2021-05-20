package comp0004.model;

import comp0004.filedb.DFReader;
import comp0004.filedb.DFWriter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Model {
    private DataFrame dataFrame;
    private boolean autoSave;

    public Model() {
        this.dataFrame = new DataFrame();
        this.autoSave = true;
    }

    public void loadModelFromDir(String dir) throws IOException {
        DFReader dfReader = new DFReader(dir);
        dfReader.loadFromFile();
        this.dataFrame = dfReader.getDataFrame();
        this.dataFrame.setTopID(Collections.max(this.dataFrame.getElementHashMap().keySet()));
    }

    public void saveAll(boolean thingEdited) throws IOException {
        DFWriter dfWriter = new DFWriter(this.dataFrame, "." + File.separator + "data" + File.separator);
        dfWriter.saveToCSV(thingEdited);
    }

    public void setAutoSave(boolean state) {
        this.autoSave = state;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public DataFrame getDataFrame() {
        return this.dataFrame;
    }
}
