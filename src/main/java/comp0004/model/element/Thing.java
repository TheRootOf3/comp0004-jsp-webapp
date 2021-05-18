package comp0004.model.element;

public interface Thing extends Element{
    void editContent(String newContent);
    String getContent();
}
