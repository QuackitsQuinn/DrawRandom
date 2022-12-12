package com.quackiq.drawrandom.logger;

import static com.quackiq.drawrandom.Utils.getRandomInt;

public class LoggerConstants {
    public enum Level {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    }
    public static final RootLogger ROOT_LOGGER = new RootLogger(Level.INFO);
    public static final int IDENTIFIER = getRandomInt(0, 1000000);


}
