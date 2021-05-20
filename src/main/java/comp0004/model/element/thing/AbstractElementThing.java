package comp0004.model.element.thing;


import comp0004.model.element.Element;

public abstract class AbstractElementThing implements Element, Thing {
    String content;
    private final int ID;
    private final Element parent;

    public AbstractElementThing(String content, int ID, Element parent) {
        this.content = content;
        this.ID = ID;
        this.parent = parent;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public abstract String getLabel();

    @Override
    public Element getParent() {
        return this.parent;
    }

    @Override
    public String getType() {
        return getLabel();
    }

    public void editContent(String newContent) {
        this.content = newContent;
    }

    public String getContent() {
        return this.content;
    }

}
