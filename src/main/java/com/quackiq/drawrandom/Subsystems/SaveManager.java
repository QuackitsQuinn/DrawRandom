package com.quackiq.drawrandom.Subsystems;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SaveManager {
    private final Canvas Cv;
    private final SnapshotParameters Sp = new SnapshotParameters();

    public SaveManager(HashMap<String, Object> UiComponents, HashMap<String, Object> Subsystems) {
        //noinspection unchecked - We know that the Canvas is a Canvas
        Cv = (Canvas) UiComponents.get("Canvas");
    }

    public void Save() {
        Image img = Cv.snapshot(Sp, new WritableImage((int) Cv.getWidth(), (int) Cv.getHeight()));
        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File ouput = fileChooser.showSaveDialog(Cv.getScene().getWindow()); // TODO: fix +[CATransaction synchronize] called within transaction
        if (ouput != null) {
            try {
                //noinspection ResultOfMethodCallIgnored - We don't care if the file already exists
                ouput.createNewFile();
                javax.imageio.ImageIO.write(bImage, "png", ouput);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

}
