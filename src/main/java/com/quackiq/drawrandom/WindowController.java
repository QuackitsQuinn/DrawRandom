package com.quackiq.drawrandom;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;

public class WindowController {
    @FXML
    private Spinner<Integer> LineCount;
    @FXML
    private Spinner<Integer> MaxLineSeg;
    @FXML
    private ChoiceBox<String> ColorPalettes;
    @FXML
    private ProgressBar DrawProgress;
    @FXML
    private ListView<String> OutputLog;
    @FXML
    private Button Draw;

    private static final  logger;

    public WindowController() {

    }
    public void ResetAll() {
        logger.info("Resetting all values");
        ColorPalettes.setItems(FXCollections.observableArrayList(ColorPalette.getPalettes()));
        ColorPalettes.setValue("default");
        DrawProgress.setProgress(0);
        OutputLog.getItems().clear();

    }
    public void InitSpinners() {
        logger.info("Initializing spinners");
        LineCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        MaxLineSeg.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
    }
    public void InitLogWindow() {
        logger.info("Initializing log window");
        logger.warn("Things may break");

    }
    @FXML
    protected void initialize() {
        InitSpinners();
        InitLogWindow();
        ResetAll();
    }
}

