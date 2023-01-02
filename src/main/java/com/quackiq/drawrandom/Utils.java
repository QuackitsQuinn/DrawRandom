package com.quackiq.drawrandom;

import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static URL newURL(String url) {
        try {
            return new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getResult(HttpURLConnection connection) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject json = new JSONObject(reader.readLine());
            JSONArray result = json.getJSONArray("result");
            List<Object> results = result.toList();
            List<String> strings = new ArrayList<>();
            for (Object o : results) {
                strings.add(o.toString());
            }
            return strings;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Color> getColors(List<Object> colors) {
        List<Color> colorList = new ArrayList<>();
        for (Object color : colors) {
            List<Integer> rgb = (List<Integer>) color;
            colorList.add(Color.rgb(rgb.get(0), rgb.get(1), rgb.get(2)));
        }
        return colorList;
    }

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static float getBrightness(Color color) {
        return (float) Math.sqrt(
                color.getRed() * color.getRed() * .241 +
                        color.getGreen() * color.getGreen() * .691 +
                        color.getBlue() * color.getBlue() * .068);
    }

    public static Color getBaseColor(List<Color> colors) {
        float brightness = 0;
        float darkness = 0;
        Color brightColor = null;
        Color darkColor = null;
        for (Color color : colors) {
            float colorBrightness = getBrightness(color);
            if (colorBrightness > brightness) {
                brightness = colorBrightness;
                brightColor = color;
            }
            if (colorBrightness < darkness) {
                darkness = colorBrightness;
                darkColor = color;
            }
        }
        if (brightness - darkness > 0.5) {
            return brightColor;
        } else {
            return darkColor;
        }
    }
}
