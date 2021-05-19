package comp0004.model;

import java.io.IOException;
import java.util.Collections;

public class Model
{
  private DataFrame dataFrame;
  private boolean autoSave;

  public Model(){
    this.dataFrame = new DataFrame();
    this.autoSave = true;
  }

  public void createDemoModel() {
    this.dataFrame.addNewListToList("list1", 0, -1);
    this.dataFrame.addNewListToList("list2", 0, -1);
    this.dataFrame.addNewListToList("list3", 0, -1);
    this.dataFrame.addNewItemToList("element 1", 1, -1);
    this.dataFrame.addNewItemToList("element 2", 2, -1);
    this.dataFrame.addNewItemToList("element 3", 2, -1);
    this.dataFrame.addNewThingToItem("text", "abcdfsfdfd", 4, -1);
    this.dataFrame.addNewThingToItem("url", "https://www.google.com", 4, -1);
  }

  public void loadModelFromDir(String dir) throws IOException{
      DFReader dfReader = new DFReader(dir);
      dfReader.loadFromFile();
      this.dataFrame = dfReader.getDataFrame();
      this.dataFrame.setTopID(Collections.max(this.dataFrame.getElementHashMap().keySet()));
//      System.out.println(Collections.max(this.dataFrame.getElementHashMap().keySet()));
  }

  public void setAutoSave(boolean state){
      this.autoSave = state;
  }

    public boolean isAutoSave() {
        return autoSave;
    }

    public DataFrame getDataFrame() {
    return this.dataFrame;
  }
}
