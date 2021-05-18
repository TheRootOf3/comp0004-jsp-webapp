package comp0004.model.element;

public interface Element {
    int getID();
    String getLabel();
    Element getParent();
    String getType();
}
