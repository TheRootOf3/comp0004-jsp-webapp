package comp0004.model.element;

import java.util.ArrayList;

public class ElementList implements Element {

    private String label;
    private final ArrayList<Element> elementList;
    private final int ID;
    private final Element parent;
    private final String type;

    public ElementList(String label, int ID, Element parent, String type) {
        this.label = label;
        this.ID = ID;
        this.elementList = new ArrayList<>();
        this.parent = parent;
        this.type = type;
    }

    public void addElement(Element element) {
        this.elementList.add(element);
    }

    public void deleteElement(int elementID) {
        this.elementList.removeIf(element -> element.getID() == elementID);
    }

    public ArrayList<Element> getElementList() {
        return elementList;
    }

    public void changeLabel(String newLabel) {
        this.label = newLabel;
    }


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Element getParent() {
        return parent;
    }


    @Override
    public String getType() {
        return this.type;
    }
}
