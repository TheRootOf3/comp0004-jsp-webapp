package comp0004.model.element.thing;

import comp0004.model.element.Element;

public interface Thing extends Element {
    void editContent(String newContent);
    String getContent();
}
