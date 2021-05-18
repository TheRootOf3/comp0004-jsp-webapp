package uk.ac.ucl.model.element;

public interface Element {
    int getID();
    String getLabel();
    Element getParent();
    String getType();
}
