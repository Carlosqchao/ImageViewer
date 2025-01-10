package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.control.NextImageCommand;
import software.ulpgc.imageviewer.control.PreviousImageCommand;
import software.ulpgc.imageviewer.control.RandomImageCommand;
import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.presenter.ImagePresenter;

import javax.swing.*;
import java.io.File;
import java.nio.file.Paths;

import static software.ulpgc.imageviewer.io.IconDownloader.download;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image());
        frame.getImageDisplay().show(image());
        frame.add("Prev", new PreviousImageCommand(presenter));
        frame.add("Next", new NextImageCommand(presenter));
        frame.add("Random", new RandomImageCommand(presenter));
        File path = new File("");
        String Path = path.getAbsolutePath();
        String imageUrlInput= "https://img.icons8.com/?size=100&id=ooYry2iVu6Gr&format=png&color=000000";
        String imageOutputPath = Path+"\\icon.png";
        download(imageUrlInput, imageOutputPath);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        frame.setVisible(true);
    }

    private static Image image() {
        String username = System.getProperty("user.home");
        String picturesPath = Paths.get(username, "Pictures").toString();
        return new FileImageLoader(new File(picturesPath)).load();
    }

}
