package comp0004.model;

import comp0004.filedb.DMReader;
import comp0004.filedb.DMWriter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Model {
    private DataModel dataModel;
    private boolean autoSave;

    public Model() {
        this.dataModel = new DataModel();
        this.autoSave = true;
    }

    //    Load model and setTopID
    public void loadModelFromDir(String dir) throws IOException {
        DMReader DMReader = new DMReader(dir);
        DMReader.loadFromFile();
        this.dataModel = DMReader.getDataModel();
        this.dataModel.setTopID(Collections.max(this.dataModel.getElementHashMap().keySet()));
    }

    public void saveAll(boolean thingEdited) throws IOException {
        DMWriter DMWriter = new DMWriter(this.dataModel, "." + File.separator + "data" + File.separator);
        DMWriter.saveToCSV(thingEdited);
    }

    public void setAutoSave(boolean state) {
        this.autoSave = state;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public DataModel getDataModel() {
        return this.dataModel;
    }
}
