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
            StringBuilder data = new StringBuilder();
            data.append(reader.readLine());
            JSONObject json = new JSONObject(data.toString());
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
        List<Color> colorList= new ArrayList<>();
        for (Object color : colors) {
            List<Integer> rgb = (List<Integer>) color;
            colorList.add(Color.rgb(rgb.get(0), rgb.get(1), rgb.get(2)));
        }
        return colorList;
    }
}
