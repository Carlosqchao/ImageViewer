package software.ulpgc.imageviewer.view;

import software.ulpgc.imageviewer.model.Image;

public interface ImageDisplay {
    void paint(String id, int offset);
    int getWidth();
    void clear();
    void on(Shift shift);
    void on(Released released);

    Image image();
    void show(Image next);

    interface Shift {
        Shift Null = offset -> {};
        void offset(int offset);
    }
    interface Released {
        Released Null = offset -> {};
        void offset(int offset);
    }
}
