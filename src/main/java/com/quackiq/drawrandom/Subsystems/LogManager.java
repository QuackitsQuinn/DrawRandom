package com.quackiq.drawrandom.Subsystems;

import com.quackiq.drawrandom.logger.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.HashMap;

public class LogManager extends Subsystem{
    private ListView<String> OutputLog;
    private Logger logger = new Logger(LogManager.class.getName());
    private ObservableList<String> LogEntries = logger.getRootLogger().getEntriesAsString();

    public LogManager(HashMap<String, Object> UiComponents) {
        //noinspection unchecked - We know that the OutputLog is a ListView<String>
        OutputLog = (ListView<String>) UiComponents.get("OutputLog");
        OutputLog.setItems(logger.getRootLogger().getEntriesAsString());
        logger.info("Log manager initialized");
    }
}
