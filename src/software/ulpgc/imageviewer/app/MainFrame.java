package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.control.Command;
import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame()  {
        this.commands = new HashMap<>();
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createImageDisplay());
        add(createToolbar(), BorderLayout.SOUTH);
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("Prev"));
        panel.add(createButton("Next"));
        panel.add(createButton("Random"));
        return panel;
    }
    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(_ -> commands.get(label).execute());
        return button;
    }
    public void add(String name, Command command) {
        commands.put(name, command);
    }
}
