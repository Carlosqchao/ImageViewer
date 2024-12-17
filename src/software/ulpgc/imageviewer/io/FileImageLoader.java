package software.ulpgc.imageviewer.io;

import software.ulpgc.imageviewer.model.Image;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] files;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg", ".png",".jpeg");
    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return files[i].getAbsolutePath();
            }

            @Override
            public int id() {
                return i;
            }

            @Override
            public Image next() {
                return imageAt((i+1) % files.length);
            }

            @Override
            public Image randomnext(){
                Random random = new Random();
                return imageAt(random.nextInt(files.length));
            }

            @Override
            public Image prev() {
                return imageAt((i-1+ files.length) % files.length);
            }
        };
    }
}
