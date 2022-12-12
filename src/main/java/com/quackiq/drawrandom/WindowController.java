package com.quackiq.drawrandom;

import com.quackiq.drawrandom.Subcontrollers.LogObjectManager;
import com.quackiq.drawrandom.logger.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;

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
    private HashMap<String, Object> UiComponents = new HashMap<>();

    private static final Logger logger = new Logger(WindowController.class.getName());

    public WindowController() {
        UiComponents.put("LineCount", LineCount);
        UiComponents.put("MaxLineSeg", MaxLineSeg);
        UiComponents.put("ColorPalettes", ColorPalettes);
        UiComponents.put("DrawProgress", DrawProgress);
        UiComponents.put("OutputLog", OutputLog);
        UiComponents.put("Draw", Draw);

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
       new LogObjectManager(UiComponents);

    }
    @FXML
    protected void initialize() {
        InitSpinners();
        InitLogWindow();
        ResetAll();
    }
}

