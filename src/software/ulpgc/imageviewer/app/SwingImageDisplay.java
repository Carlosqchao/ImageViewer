package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.view.ViewPort;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;

    private Image image;
    private BufferedImage bitmap;
    private final List<Paint> paints = new ArrayList<>();
    private final Map<String,BufferedImage> gallery = new HashMap<>();

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int currentOffset = e.getX() - initShift;
                shift.offset(currentOffset);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };
    }




    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.id());
        this.repaint();
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Paint paint : paints) {
            checkSaved(paint);
            bitmap = gallery.get(paint.id());
            ViewPort fitted = getViewPort();
            int x = (this.getWidth()-fitted.width())/2;
            int y = (this.getHeight()- fitted.height())/2;

            g.drawImage(bitmap,x+ paint.offset,y,fitted.width(),fitted.height(),null);
        }
    }

    private ViewPort getViewPort() {
        ViewPort viewPort = ViewPort.ofSize(this.getWidth(), this.getHeight());
        return viewPort.fit(this.getWidth(), this.getHeight());
    }

    private void checkSaved(Paint paint) {
        if (!gallery.containsKey(paint.id())){
            gallery.put(paint.id(),load(paint.id()));
        }
    }


    @Override
    public void paint(String id, int offset) {
        paints.add(new Paint(id, offset));
        repaint();
    }

    @Override
    public void clear() {
        paints.clear();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    @Override
    public Image image() {
        return image;
    }



    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private record Paint(String id, int offset) {
    }

}
