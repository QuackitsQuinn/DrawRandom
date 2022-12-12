package com.quackiq.drawrandom.logger;

public class LogLayout {
    private static String patternLayout = "%level from %logger at %hr:%mn:%sc : %message";

    public LogLayout() {

    }
    public LogLayout(String patternLayout) {
        this.patternLayout = patternLayout;
    }
    public String format(LogEntry entry) {
        String formatted = patternLayout;
        formatted = formatted.replace("%level", entry.getLevel().toString());
        formatted = formatted.replace("%logger", entry.getLoggerName());
        formatted = formatted.replace("%hr", String.valueOf(entry.getTimestamp().getHours()));
        formatted = formatted.replace("%mn", String.valueOf(entry.getTimestamp().getMinutes()));
        formatted = formatted.replace("%sc", String.valueOf(entry.getTimestamp().getSeconds()));
        formatted = formatted.replace("%message", entry.getMessage());
        return formatted;
    }
    public static String getDefaultPatternLayout() {
        return patternLayout;
    }


}
