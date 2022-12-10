package com.quackiq.drawrandom;

import org.apache.logging.log4j.Level;

import java.net.URL;
import java.util.List;

public class Constants {
    public static final Integer DEFAULT_WIDTH = 600;
    public static final Integer DEFAULT_HEIGHT = 400;
    public static final URL COLOR_PALETTE_URL = Utils.newURL("http://colormind.io/api/");
    public static final URL COLOR_PALETTE_LIST_URL = Utils.newURL("http://colormind.io/list/");
    public static final List<String> COLOR_PALETTES = ColorPalette.getPalettes();
    public static final Level LOG_LEVEL = Level.INFO;
    public static final String LOG_PATTERN =
            "%highlight{%d [%t] %-5level: %msg%n%throwable} %level{WARN=Warning, DEBUG=Debug, ERROR=Error, TRACE=Trace, INFO=Info} from %logger{1} in %thread at %date{HH:mm:ss.SSS} %n%msg%n%n";
}
