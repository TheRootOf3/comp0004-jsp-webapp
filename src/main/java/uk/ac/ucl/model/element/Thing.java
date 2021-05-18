package uk.ac.ucl.model.element;

public interface Thing extends Element{
    void editContent(String newContent);
    String getContent();
}
