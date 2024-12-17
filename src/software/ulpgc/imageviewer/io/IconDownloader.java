package software.ulpgc.imageviewer.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.lang.StringTemplate.STR;

public class IconDownloader {

    public static void download(String resourceUrl, String outputPath) {
        try {
            URL url = new URL(resourceUrl);
            InputStream inputStream = url.openStream();
            Path path = Paths.get(outputPath);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException e) {
            System.err.println(STR."Error downloading: \{e.getMessage()}");
        }
    }
}
