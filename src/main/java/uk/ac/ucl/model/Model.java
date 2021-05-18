package uk.ac.ucl.model;

import java.io.File;
import java.util.List;

public class Model
{
  private final DataFrame dataFrame;

  public Model(){
    this.dataFrame = new DataFrame();
  }

  public void createDemoModel() {
    this.dataFrame.addNewListToList("list1", 0);
    this.dataFrame.addNewListToList("list2", 0);
    this.dataFrame.addNewListToList("list3", 0);
    this.dataFrame.addNewItemToList("element 1", 1);
    this.dataFrame.addNewItemToList("element 2", 2);
    this.dataFrame.addNewItemToList("element 3", 2);
    this.dataFrame.addNewThingToItem("text", "abcdfsfdfd", 4);
    this.dataFrame.addNewThingToItem("url", "https://www.google.com", 4);
  }

  public DataFrame getDataFrame() {
    return this.dataFrame;
  }
}
