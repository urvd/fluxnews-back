package com.backend.fluxnewsapi.services.newsapi;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class ArticleDataParser {
    protected enum DataType{
        AUTHOR("author"),SOURCE_NAME("name"),TITLE("tile"),
        DESCRIPTION("description"),IMAGE_URL("urlToImage"),URL("url"),
        CONTENT("content"),PUBLISH_DATE("publishedAt");
        public String name;
        DataType(String name) {
            this.name = name;
        }
    }
    protected static String parse(JSONObject objson, DataType dataType) throws JSONException {
        if(objson.isNull(dataType.name)) return null;
        else{
            final String object = (String) objson.get(dataType.name);
            if(object.isEmpty() || object.isBlank()) return  null;
            return object;
        }
    }
}
