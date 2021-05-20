package comp0004.model.element.thing;

import comp0004.model.element.Element;

public class ElementThingImage implements Thing {
    private String content;
    private final int ID;
    private final Element parent;

    public ElementThingImage(String content, int ID, Element parent) {
        this.content = content;
        this.ID = ID;
        this.parent = parent;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String getLabel() {
        return "image";
    }

    @Override
    public Element getParent() {
        return this.parent;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void editContent(String newContent) {
        this.content = newContent;
    }

    @Override
    public String getType() {
        return getLabel();
    }

}
