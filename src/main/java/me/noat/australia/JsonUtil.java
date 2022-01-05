package me.noat.australia;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonUtil {
    public static String parse(String url,  String object) throws InvalidUsernameException, IOException {
        String aaaaaa = "ERROR";
        HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
        request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new BufferedReader(new InputStreamReader(request.getInputStream())));
        if (request.getResponseCode() == 400) {
            throw new InvalidUsernameException();
        }
        JsonObject rootobj = root.getAsJsonObject();
        System.out.println(object);
        aaaaaa = rootobj.get(object).getAsString();
        return aaaaaa;
    }

    public static String parseArray(String url, String array, String object) {
        String omg = "ERROR";
        try {
        HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
        request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        request.connect();
        JsonElement jelement = new JsonParser().parse(new BufferedReader(new InputStreamReader(request.getInputStream())));
        JsonObject  jobject = jelement.getAsJsonObject();
        omg = jobject.getAsJsonObject(array).get(object).toString();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return omg;
    }

    public static String getUUIDFromName(String userName) {
        String omg = "ERROR";
        try {
            HttpURLConnection request = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/"+userName).openConnection();
            request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new BufferedReader(new InputStreamReader(request.getInputStream())));
            JsonObject rootobj = root.getAsJsonObject();
            omg  = rootobj.get("id").getAsString();
            omg = new StringBuilder(omg).insert(omg.length()-12, "-").insert(omg.length()-16, "-").insert(omg.length()-20,"-").insert(omg.length()-24, "-").toString();
            System.out.println(omg);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return omg;
    }
}
