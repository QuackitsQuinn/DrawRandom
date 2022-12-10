package com.quackiq.drawrandom;

import javafx.scene.paint.Color;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.List;

public class ColorPalette {
    public static List<String> getPalettes() {
        try {
            HttpURLConnection connection = (HttpURLConnection) Constants.COLOR_PALETTE_LIST_URL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();
            return Utils.getResult(connection);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Color> getPalette(String model) {
        try {
            Content data = Request.Post(Constants.COLOR_PALETTE_URL.toString())
                    .bodyString("{\"model\":\"" + model + "\"}", ContentType.APPLICATION_JSON)
                    .execute().returnContent();
            JSONObject json = new JSONObject(data.toString());
            List<Object> colors = json.getJSONArray("result").toList();
            return Utils.getColors(colors);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
