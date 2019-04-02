package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonSerializer {
    private final Gson gson;

    public JsonSerializer() {
        gson = new GsonBuilder().create();
    }

    public <T> String toJson(T object) {
        return gson.toJson(object);
    }

    public <T> T toObject(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }
}
