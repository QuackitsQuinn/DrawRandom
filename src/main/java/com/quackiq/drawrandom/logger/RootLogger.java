package com.quackiq.drawrandom.logger;

import com.quackiq.drawrandom.logger.LoggerConstants.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Root logger class
 * All loggers should point to a root logger
 */
public class RootLogger {
    private Level level;
    private List<LogEntry> entries = new java.util.ArrayList<LogEntry>();
    private ObservableList<String> formattedEntries = FXCollections.observableArrayList();
    private LogLayout layout = new LogLayout();
    public RootLogger(Level level) {
        this.level = level;;
    }
    public void append(LogEntry entry) {
        if (entry.getLevel().ordinal() >= level.ordinal()) {
            entries.add(entry);
            String formatted = layout.format(entry);
            formattedEntries.add(formatted);
            System.out.println(formatted);
        }
    }
    public void setLevel(Level level) {
        this.level = level;
    }
    public List<LogEntry> getEntries() {
        return entries;
    }
    public ObservableList<String> getEntriesAsString() {
        return formattedEntries;
    }
    public void setLayout(LogLayout layout) {
        this.layout = layout;
    }
    public void clear() {
        entries.clear();
        formattedEntries.clear();
    }
    public void reformat() {
        formattedEntries.clear();
        for (LogEntry entry : entries) {
            formattedEntries.add(layout.format(entry));
        }
    }
}
