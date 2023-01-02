package com.quackiq.drawrandom;

import com.quackiq.drawrandom.Subsystems.InputManager;
import com.quackiq.drawrandom.Subsystems.LogManager;
import com.quackiq.drawrandom.Subsystems.MainCanvas;
import com.quackiq.drawrandom.Subsystems.SaveManager;
import com.quackiq.drawrandom.logger.Logger;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
    @FXML
    private Canvas Canvas;
    @FXML
    private Pane DrawPane;
    @FXML
    private Button Save;
    private final HashMap<String, Object> UiComponents = new HashMap<>();
    private final HashMap<String, Object> Subsystems = new HashMap<>();

    private static final Logger logger = new Logger(WindowController.class.getName());

    public WindowController() {
    }

    public void InitLogWindow() {
        logger.info("Initializing log window");
        Subsystems.put("LogManager", new LogManager(UiComponents));

    }

    public void InitDrawCanvas() {
        logger.info("Initializing draw canvas");
        Subsystems.put("MainCanvas", new MainCanvas(UiComponents, Subsystems));
    }

    public void InitUiComponents() {
        logger.info("Initializing UI components");
        UiComponents.put("OutputLog", OutputLog);
        UiComponents.put("DrawProgress", DrawProgress);
        UiComponents.put("LineCount", LineCount);
        UiComponents.put("MaxLineSeg", MaxLineSeg);
        UiComponents.put("ColorPalettes", ColorPalettes);
        UiComponents.put("Draw", Draw);
        UiComponents.put("Canvas", Canvas);
        UiComponents.put("DrawPane", DrawPane);
        UiComponents.put("Save", Save);
    }

    public void InitInputs() {
        Subsystems.put("InputManager", new InputManager(UiComponents));
    }

    public void InitSaveButton() {
        logger.info("Initializing save button");
        Subsystems.put("SaveManager", new SaveManager(UiComponents, Subsystems));
    }

    public void Save() {
        ((SaveManager) Subsystems.get("SaveManager")).Save();
    }

    @FXML
    protected void initialize() {
        InitUiComponents();
        InitLogWindow();
        InitInputs();
        InitDrawCanvas();
        InitSaveButton();

    }

    @FXML
    public void Draw() {
        logger.info("Drawing...");
        ((MainCanvas) Subsystems.get("MainCanvas")).draw();
    }
}

