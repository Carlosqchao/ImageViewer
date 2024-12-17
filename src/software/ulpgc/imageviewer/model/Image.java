package software.ulpgc.imageviewer.model;

public interface Image {
    String name();

    int id();
    Image next();

    Image randomnext();

    Image prev();

}
