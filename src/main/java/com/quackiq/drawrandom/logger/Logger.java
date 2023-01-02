package com.quackiq.drawrandom.logger;

public class Logger {
    private final String name;

    public Logger(String name) {
    this.name = name;
    }

    public void trace(String message) {
        LogEntry entry = new LogEntry(name, message, LoggerConstants.Level.TRACE);
        LoggerConstants.ROOT_LOGGER.append(entry);
    }
    public void debug(String message) {
        LogEntry entry = new LogEntry(name, message, LoggerConstants.Level.DEBUG);
        LoggerConstants.ROOT_LOGGER.append(entry);
    }
    public void info(String message) {
        LogEntry entry = new LogEntry(name, message, LoggerConstants.Level.INFO);
        LoggerConstants.ROOT_LOGGER.append(entry);
    }
    public void warn(String message) {
        LogEntry entry = new LogEntry(name, message, LoggerConstants.Level.WARN);
        LoggerConstants.ROOT_LOGGER.append(entry);
    }
    public void error(String message) {
        LogEntry entry = new LogEntry(name, message, LoggerConstants.Level.ERROR);
        LoggerConstants.ROOT_LOGGER.append(entry);
    }
    public void fatal(String message) {
        LogEntry entry = new LogEntry(name, message, LoggerConstants.Level.FATAL);
        LoggerConstants.ROOT_LOGGER.append(entry);
    }

    public RootLogger getRootLogger() {
        return LoggerConstants.ROOT_LOGGER;
    }


}
