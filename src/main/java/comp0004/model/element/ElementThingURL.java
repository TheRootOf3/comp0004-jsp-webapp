package comp0004.model.element;

public class ElementThingURL implements Thing {
    private String content;
    private final int ID;
    private final Element parent;

    public ElementThingURL(String content, int ID, Element parent){
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
        return "url";
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
