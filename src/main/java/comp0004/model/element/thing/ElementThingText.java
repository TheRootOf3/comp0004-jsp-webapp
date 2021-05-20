package comp0004.model.element.thing;

import comp0004.model.element.Element;

public class ElementThingText extends AbstractElementThing {

    public ElementThingText(String content, int ID, Element parent) {
        super(content, ID, parent);
    }

    @Override
    public String getLabel() {
        return "text";
    }

}
