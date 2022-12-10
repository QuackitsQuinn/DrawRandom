package tests;

import com.quackiq.drawrandom.ColorPalette;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestColorPalette {
    @Test
    public void testGetColorPaletteList() {
        List<String> palettes = ColorPalette.getPalettes();
        System.out.println(palettes);
        assert palettes != null;
    }
    @Test
    public void testGetColorPalette() {
        List<Color> palette = ColorPalette.getPalette("default");
        for (Color color : palette) {
            System.out.println("r"+color.getRed()*255 + " b" + color.getGreen()*255 + " g" + color.getBlue()*255);
        }
        assert palette != null;
    }

}
