package com.quackiq.drawrandom.Subsystems;

import com.quackiq.drawrandom.ColorPalette;
import com.quackiq.drawrandom.logger.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.util.HashMap;

public class InputManager {
    private final Spinner<Integer> segmentSpinner;
    private final Spinner<Integer> LineCountSpinner;
    private final ChoiceBox<String> ColorPaletteBox;
    private final Logger logger = new Logger(InputManager.class.getName());

    public InputManager(HashMap<String, Object> UiComponents) {
        //noinspection unchecked - We know that the segmentSpinner is a Spinner<Integer>
        segmentSpinner = (Spinner<Integer>) UiComponents.get("MaxLineSeg");
        //noinspection unchecked - We know that the LineCountSpinner is a Spinner<Integer>
        LineCountSpinner = (Spinner<Integer>) UiComponents.get("LineCount");
        //noinspection unchecked - We know that the ColorPaletteBox is a ChoiceBox<String>
        ColorPaletteBox = (ChoiceBox<String>) UiComponents.get("ColorPalettes");
        logger.info("Initializing inputs...");
        InitSpinners();
        InitColorPaletteBox();
        logger.info("Done initializing inputs");
    }

    public void InitSpinners() {
        logger.info("Initializing spinners");
        segmentSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 5));
        LineCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 10));
        logger.info("Done");
    }

    public void InitColorPaletteBox() {
        logger.info("Initializing ColorPaletteBox");
        ObservableList<String> ColorPalettes = FXCollections.observableArrayList(ColorPalette.getPalettes());
        ColorPaletteBox.getItems().setAll(ColorPalettes);
        ColorPaletteBox.setValue(ColorPaletteBox.getItems().get(0));
        logger.info("Done");
    }

    public int getSegmentSpinnerValue() {
        return segmentSpinner.getValue();
    }

    public int getLineCountSpinnerValue() {
        return LineCountSpinner.getValue();
    }

    public String getColorPaletteVal() {
        return ColorPaletteBox.getValue();
    }
}

