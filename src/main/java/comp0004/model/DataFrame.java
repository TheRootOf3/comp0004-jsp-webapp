package comp0004.model;

import comp0004.model.element.Element;
import comp0004.model.element.ElementList;
import comp0004.model.element.thing.ElementThingImage;
import comp0004.model.element.thing.ElementThingText;
import comp0004.model.element.thing.ElementThingURL;
import comp0004.model.element.thing.Thing;
import comp0004.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DataFrame {

    private final HashMap<Integer, Element> elementHashMap;
    private int topID;

    public DataFrame() {
        this.topID = 0;
        this.elementHashMap = new HashMap<>();
        ElementList mainList = new ElementList("Main list", this.topID, null, "list"); // main list of lists
        this.elementHashMap.put(0, mainList);
    }

    // if ID == -1, then take the next free ID. Otherwise, load id from file.
    public void addNewElementToList(String label, int listID, int ID, String type) {
        if (ID == -1)
            ID = ++this.topID;
        Element elementList = new ElementList(label, ID, this.elementHashMap.get(listID), type);
        ((ElementList) this.elementHashMap.get(listID)).addElement(elementList);
        this.elementHashMap.put(ID, elementList);
    }

    //    delete by firstly traversing all elements, then creating a list of items to remove, then remove them.
    public void deleteElementFromListCollect(int elementID, int listID) {
        ArrayList<Pair<Integer>> collected = new ArrayList<>();
        collectItemsToRemove(collected, elementID, listID);
//        Elements deletion
        for (Pair<Integer> pair : collected) {
            ((ElementList) this.elementHashMap.get(pair.get2())).deleteElement(pair.get1());
            this.elementHashMap.remove(pair.get1());
        }
        updateTopID();
    }

    //      Recursive collection of elements to delete
    private void collectItemsToRemove(ArrayList<Pair<Integer>> collected, int elementID, int listID) {
        if (!(this.elementHashMap.get(elementID) instanceof Thing)) {
            for (Element element : ((ElementList) this.elementHashMap.get(elementID)).getElementList()) {
                collectItemsToRemove(collected, element.getID(), elementID);
            }
        }
        collected.add(new Pair<>(elementID, listID));
    }

    //   When deleting you may remove elements with topID, so update it with the max id of remaining elements.
    private void updateTopID() {
        setTopID(Collections.max(this.elementHashMap.keySet()));
    }

    // if ID == -1, then take the next free ID. Otherwise, load id from file.
    public void addNewThingToItem(String type, String content, int itemID, int ID) {
        if (ID == -1)
            ID = ++this.topID;
        Element thing = switch (type) {
            case "text" -> new ElementThingText(content, ID, this.elementHashMap.get(itemID));
            case "url" -> new ElementThingURL(content, ID, this.elementHashMap.get(itemID));
            case "image" -> new ElementThingImage(content, ID, this.elementHashMap.get(itemID));
            default -> null;
        };

        if (thing != null) {
            ((ElementList) this.elementHashMap.get(itemID)).addElement(thing);
            this.elementHashMap.put(ID, thing);
        }
    }

    public void editThing(String newContent, int thingID) {
        ((Thing) this.elementHashMap.get(thingID)).editContent(newContent);
    }

    public void renameElement(String newLabel, int elementID) {
        ((ElementList) this.elementHashMap.get(elementID)).changeLabel(newLabel);
    }

    //    Search in the elementHashMap for elements containing the keyword.
    public ArrayList<ArrayList<ElementList>> searchInElementLabels(String labelSearch) {
        ArrayList<ArrayList<ElementList>> traces = new ArrayList<>();
        for (Element element : this.elementHashMap.values()) {
            if (element instanceof ElementList && element.getLabel().contains(labelSearch)) {
                traces.add(getElementTrace((ElementList) element));
            }
        }
        return traces;
    }

    //    For an element, return its trace up to the main list (ID=0)
    private ArrayList<ElementList> getElementTrace(ElementList element) {
        ArrayList<ElementList> traceElementList = new ArrayList<>();
        ElementList currentElement = element;
        while (currentElement != null) {
            traceElementList.add(currentElement);
            currentElement = (ElementList) currentElement.getParent();
        }

        return traceElementList;
    }

    public Element getElement(int elementID) {
        return this.elementHashMap.get(elementID);
    }

    public void setTopID(int topID) {
        this.topID = topID;
    }

    public HashMap<Integer, Element> getElementHashMap() {
        return this.elementHashMap;
    }
}
