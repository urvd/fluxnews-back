package com.backend.fluxnewsapi.config;

import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.Articlesfetched;
import org.modelmapper.ModelMapper;
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

    private static String url= "http://newsapi.org/v2/top-headlines?" +
             "country=us&" +
             "apiKey=760f6bb3dd0e43f1bef41c8cfd1d110b";

    public NewsApiFetch(){}
    public Articlesfetched fetchArticles() throws IOException, JSONException {
        try {
            String jsonString = readUrl(url);
            return convertFrom(new JSONObject(jsonString));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String runArticles() throws IOException {
    	try {
			return readUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    private Articlesfetched convertFrom(JSONObject jsonObject) throws JSONException {
        Articlesfetched articlesfetched = new Articlesfetched();
        articlesfetched.setStatus((String)jsonObject.get("status"));
        articlesfetched.setTotalResults((int)jsonObject.get("totalResults"));
        ModelMapper modelMapper = new ModelMapper();
        JSONArray arrArticlesJson = jsonObject.getJSONArray("articles");
        List<ArticleDto> listArticles = new ArrayList<>();
        for(int i=0;i<arrArticlesJson.length();i++) {
            listArticles.add(new ArticleDto(arrArticlesJson.getJSONObject(i).getJSONObject("source").getString("name"),
                                    arrArticlesJson.getJSONObject(i).getString("author"),
                                    arrArticlesJson.getJSONObject(i).getString("title"),
                                    arrArticlesJson.getJSONObject(i).getString("description"),
                                    arrArticlesJson.getJSONObject(i).getString("url"),
                                    arrArticlesJson.getJSONObject(i).getString("urlToImage"),
                                    arrArticlesJson.getJSONObject(i).getString("publishedAt"),
                                    arrArticlesJson.getJSONObject(i).getString("content") ) );
        }
        articlesfetched.setArticles(listArticles);
        return articlesfetched;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
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
}
