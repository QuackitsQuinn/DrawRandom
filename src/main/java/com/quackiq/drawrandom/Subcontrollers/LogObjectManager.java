package com.quackiq.drawrandom.Subcontrollers;

import com.quackiq.drawrandom.logger.Logger;

import java.util.HashMap;

/**
 * This class is used to manage the log list view
 *
 */
public class LogObjectManager {
    private Object logWindow;
    private Logger logger = new Logger(LogObjectManager.class.getName());

    public LogObjectManager(HashMap<String, Object> uiComponents) {
        logWindow = uiComponents.get("OutputLog");
        logger.info("Log window object set");
    }
}
