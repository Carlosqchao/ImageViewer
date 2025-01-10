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
        return (_, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                assert files != null;
                return files[i].getAbsolutePath();
            }


            @Override
            public Image next() {
                assert files != null;
                return imageAt((i+1) % files.length);
            }

            @Override
            public Image randomnext(){
                Random random = new Random();
                assert files != null;
                return imageAt(random.nextInt(files.length));
            }

            @Override
            public Image prev() {
                assert files != null;
                return imageAt((i-1+ files.length) % files.length);
            }

        };
    }
}
