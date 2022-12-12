package com.quackiq.drawrandom.logger;

import java.util.Date;

public class LogEntry {
    private String message;
    private LoggerConstants.Level level;
    private String loggerName;
    private Date timestamp = new Date();
    public LogEntry(String loggerName,String message, LoggerConstants.Level level) {
        this.loggerName = loggerName;
        this.message = message;
        this.level = level;
    }
    public String getMessage() {
        return message;
    }
    public LoggerConstants.Level getLevel() {
        return level;
    }
    public String getLoggerName() {
        return loggerName;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return "LogEntry [message=" + message + ", level=" + level + ", loggerName=" + loggerName + "]";
    }

}
