package comp0004.model.element.thing;

import comp0004.model.element.Element;

public class ElementThingURL extends AbstractElementThing implements Element {

    public ElementThingURL(String content, int ID, Element parent) {
        super(content, ID, parent);
    }

    @Override
    public String getLabel() {
        return "url";
    }

}
