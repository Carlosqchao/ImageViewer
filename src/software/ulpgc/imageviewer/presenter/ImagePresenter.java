package software.ulpgc.imageviewer.presenter;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.view.ImageDisplay.*;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((Shift) this::shift);
        this.display.on((Released) this::released);
    }

    public void show(Image image) {
        this.image = image;
        repaint();
    }
    private void shift(int offset) {
        display.clear();

        display.paint(image.id(), offset);

        if (offset < 0) {
            display.paint(image.next().id(), display.getWidth() + offset);
        }

        if (offset > 0) {
            display.paint(image.prev().id(), offset - display.getWidth());
        }
    }






    private void released(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2) {
            image = offset > 0 ? image.prev() : image.next();
            display.show(image);
        }
        repaint();
    }



    private void repaint() {
        if (image == null) return;
        this.display.clear();
        this.display.paint(image.id(), 0);
    }

    public Image getImage() {
        return image;
    }
}
