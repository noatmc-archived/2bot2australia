import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonUtil {
    public static String parse(String url, String object) {
        String result = "";
        try {
            HttpURLConnection request = (HttpURLConnection)new URL(url).openConnection();
            request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            request.connect();
            JsonElement root = JsonParser.parseReader(new BufferedReader(new InputStreamReader(request.getInputStream())));
            JsonObject rootobj = root.getAsJsonObject();
            result = rootobj.get(object).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String parseArray(String url, String array, String object) {
        String result = "";
        try {
            HttpURLConnection request = (HttpURLConnection)new URL(url).openConnection();
            request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            request.connect();
            JsonElement jelement = JsonParser.parseReader(new BufferedReader(new InputStreamReader(request.getInputStream())));
            JsonObject jobject = jelement.getAsJsonObject();
            result = jobject.getAsJsonObject(array).get(object).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getUUIDFromName(String userName) {
        String result = "";
        try {
            HttpURLConnection request = (HttpURLConnection)new URL("https://api.mojang.com/users/profiles/minecraft/" + userName).openConnection();
            request.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            request.connect();
            JsonElement root = JsonParser.parseReader(new BufferedReader(new InputStreamReader(request.getInputStream())));
            JsonObject rootobj = root.getAsJsonObject();
            result = rootobj.get("id").getAsString();
            result = new StringBuilder(result).insert(result.length() - 12, "-").insert(result.length() - 16, "-").insert(result.length() - 20, "-").insert(result.length() - 24, "-").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}