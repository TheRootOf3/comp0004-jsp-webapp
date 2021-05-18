package uk.ac.ucl.model;

import uk.ac.ucl.model.element.*;

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

    public void addNewListToList(String label, int listID){
        Element elementList = new ElementList(label, ++this.topID, this.elementHashMap.get(listID), "list");
        ((ElementList) this.elementHashMap.get(listID)).addElement(elementList);
        this.elementHashMap.put(this.topID, elementList);
    }

    public void addNewItemToList(String label, int listID){
        Element elementItem = new ElementList(label, ++this.topID, this.elementHashMap.get(listID), "item");
        ((ElementList) this.elementHashMap.get(listID)).addElement(elementItem);
        this.elementHashMap.put(this.topID, elementItem);
    }

    public void deleteElementFromList(int elementID, int listID){
        System.out.println(elementID + " " + listID);
        if (this.elementHashMap.get(elementID).getType().equals("item")) {
            ((ElementList) this.elementHashMap.get(listID)).deleteElement(elementID);
            this.elementHashMap.remove(elementID);

        }
        else {
//            TODO make recursive deletion
            ((ElementList) this.elementHashMap.get(listID)).deleteElement(elementID);
            this.elementHashMap.remove(elementID);
        }
    }

    public void addNewThingToItem(String type, String content, int itemID){
        Element thing = null;
        if (type.equals("text"))
            thing = new ElementThingText(content, ++this.topID, this.elementHashMap.get(itemID));
        else if (type.equals("url"))
            thing = new ElementThingURL(content, ++this.topID, this.elementHashMap.get(itemID));

        if (thing != null) {
            ((ElementList)this.elementHashMap.get(itemID)).addElement(thing);
            this.elementHashMap.put(this.topID, thing);
        }
    }

    public void editThing(String newContent, int thingID){
        ((Thing) this.elementHashMap.get(thingID)).editContent(newContent);
    }



    public Element getElement(int elementID){
        return this.elementHashMap.get(elementID);
    }


//    For debugging
    public HashMap<Integer, Element> getElementHashMap(){
        return this.elementHashMap;
    }


}
