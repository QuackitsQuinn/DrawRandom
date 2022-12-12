package com.quackiq.drawrandom;

import java.net.URL;
import java.util.List;

public class Constants {
    public static final Integer DEFAULT_WIDTH = 600;
    public static final Integer DEFAULT_HEIGHT = 400;
    public static final URL COLOR_PALETTE_URL = Utils.newURL("http://colormind.io/api/");
    public static final URL COLOR_PALETTE_LIST_URL = Utils.newURL("http://colormind.io/list/");
    public static final List<String> COLOR_PALETTES = ColorPalette.getPalettes();
}
