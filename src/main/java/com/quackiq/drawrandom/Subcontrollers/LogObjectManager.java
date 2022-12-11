package com.quackiq.drawrandom.Subcontrollers;

import javafx.scene.control.ListView;

/**
 * This class is used to manage the log list view
 *
 */
public class LogObjectManager {
    private ListView<String> logWindow;

    public LogObjectManager(ListView<String> logWindow) {
        this.logWindow = logWindow;
    }
}
