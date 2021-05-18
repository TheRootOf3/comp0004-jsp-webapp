package uk.ac.ucl.model;

import uk.ac.ucl.model.element.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataFrame {

    private ElementList mainList;
    private HashMap<Integer, Element> elementHashMap;
    private int topID;

    public DataFrame(){
        this.topID = 0;
        this.elementHashMap = new HashMap<>();
        this.mainList = new ElementList("Main list", this.topID, null, "list");
        this.elementHashMap.put(0, this.mainList);
    }

    public void addNewListToList(String label, int listID, int ID){
        if (ID == -1)
            ID = ++this.topID;
        Element elementList = new ElementList(label, ID, this.elementHashMap.get(listID), "list");
        ((ElementList) this.elementHashMap.get(listID)).addElement(elementList);
        this.elementHashMap.put(ID, elementList);
    }

    public void addNewItemToList(String label, int listID, int ID){
        if (ID == -1)
            ID = ++this.topID;
        Element elementItem = new ElementList(label, ID, this.elementHashMap.get(listID), "item");
        ((ElementList) this.elementHashMap.get(listID)).addElement(elementItem);
        this.elementHashMap.put(ID, elementItem);
    }

//    public void deleteElementFromList(int elementID, int listID){
//        System.out.println(elementID + " " + listID);
//        if (this.elementHashMap.get(elementID) instanceof Thing) {
//            ((ElementList) this.elementHashMap.get(listID)).deleteElement(elementID);
//            this.elementHashMap.remove(elementID);
//        }
//        else {
//            ((ElementList) this.elementHashMap.get(listID)).deleteElement(elementID);
//            this.elementHashMap.remove(elementID);
//        }
//    }

    public void deleteElementFromListCollect(int elementID, int listID){
        ArrayList<Pair<Integer>> collected = new ArrayList<>();
        collectItemsToRemove(collected, elementID, listID);
//        Elements deletion
        for (Pair<Integer> pair : collected) {
            System.out.println(pair.get1() + " " + pair.get2());
            ((ElementList) this.elementHashMap.get(pair.get2())).deleteElement(pair.get1());
            this.elementHashMap.remove(pair.get1());
        };
    }

//      Recursive collection of elements to delete
    private void collectItemsToRemove(ArrayList<Pair<Integer>> collected, int elementID, int listID){
        if (!(this.elementHashMap.get(elementID) instanceof Thing)) {
            for (Element element : ((ElementList) this.getElementHashMap().get(elementID)).getElementList()) {
                collectItemsToRemove(collected, element.getID(), elementID);
            }
        }
        collected.add(new Pair<>(elementID, listID));
    }

    public void addNewThingToItem(String type, String content, int itemID, int ID){
        if (ID == -1)
            ID = ++this.topID;
        Element thing = null;
        if (type.equals("text"))
            thing = new ElementThingText(content, ID, this.elementHashMap.get(itemID));
        else if (type.equals("url"))
            thing = new ElementThingURL(content, ID, this.elementHashMap.get(itemID));

        if (thing != null) {
            ((ElementList)this.elementHashMap.get(itemID)).addElement(thing);
            this.elementHashMap.put(ID, thing);
        }
    }

    public void editThing(String newContent, int thingID){
        ((Thing) this.elementHashMap.get(thingID)).editContent(newContent);
    }

    public void renameElement(String newLabel, int elementID){
        ((ElementList)this.elementHashMap.get(elementID)).changeLabel(newLabel);
    }

    public void saveAll() throws IOException{
        DFWriter dfWriter = new DFWriter(this, "./data/");
        dfWriter.saveToCSV();
    }



    public Element getElement(int elementID){
        return this.elementHashMap.get(elementID);
    }

    public void setTopID(int topID){
        this.topID = topID;
    }


//    For debugging
    public HashMap<Integer, Element> getElementHashMap(){
        return this.elementHashMap;
    }


}
