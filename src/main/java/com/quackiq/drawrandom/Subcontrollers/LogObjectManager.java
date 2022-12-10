package com.quackiq.drawrandom.Subcontrollers;

import javafx.scene.control.ListView;
import org.apache.logging.log4j.LogManager;

/**
 * This class is used to manage the log list view
 *
 */
public class LogObjectManager {
    private ListView<String> logWindow;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogManager.class);

    public LogObjectManager(ListView<String> logWindow) {
        this.logWindow = logWindow;
    }
}
