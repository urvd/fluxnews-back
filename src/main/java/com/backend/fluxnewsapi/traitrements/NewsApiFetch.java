package com.backend.fluxnewsapi.traitrements;

import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.Articlesfetched;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsApiFetch {

    Log logger = LogFactory.getLog(NewsApiFetch.class);

    private static String URL = "http://newsapi.org/v2/top-headlines?" +
            "country=us&" +
            "apiKey=760f6bb3dd0e43f1bef41c8cfd1d110b";

    public Articlesfetched fetchArticles() throws IOException {
        try {
            return convertFrom(readUrl())/*convertToObject(readUrl())*/;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Articlesfetched convertFrom(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Articlesfetched articlesfetched = new Articlesfetched();
        articlesfetched.setStatus((String) jsonObject.get("status"));
        //articlesfetched.setTotalResults((int) jsonObject.get("totalResults"));

        JSONArray arrArticlesJson = jsonObject.getJSONArray("articles");
        List<ArticleDto> listArticles = new ArrayList<>();

        for (int i = 0; i < arrArticlesJson.length(); i++) {
            JSONObject jsonSource = (JSONObject) arrArticlesJson.getJSONObject(i);
            listArticles.add(
                    new ArticleDto(
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i).getJSONObject("source"),
                                    ArticleDataParser.DataType.SOURCE_NAME),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.AUTHOR),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.TITLE),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.DESCRIPTION),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.URL),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.IMAGE_URL),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.PUBLISH_DATE),
                            ArticleDataParser.parse(arrArticlesJson.getJSONObject(i), ArticleDataParser.DataType.CONTENT)
                    )
            );
            listArticles.get(i).setNumberid(i+1);
        }
        articlesfetched.setTotalResults(arrArticlesJson.length());
        articlesfetched.setArticles(listArticles);
        return articlesfetched;
    }
    private Articlesfetched convertToObject(String jsonString){
        Gson gson = new Gson();
        Articlesfetched articlesfetched= gson.fromJson(jsonString, Articlesfetched.class);
        return  articlesfetched;
    }

    private String readUrl() throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(URL);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    public Articlesfetched testConversion(String jsonString) throws JSONException {
        return convertFrom(jsonString);
    }

    public String runArticles() throws IOException {
        try {
            return readUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}