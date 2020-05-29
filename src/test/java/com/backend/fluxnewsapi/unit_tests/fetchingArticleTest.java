package com.backend.fluxnewsapi.unit_tests;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONException;

public class fetchingArticleTest {

    private  final static String JSON_DATA_SAMPLE = "{\"status\":\"ok\",\"totalResults\":38,\"articles\":[{"+
            "\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},"+
            "\"author\":\"Simon Cullen, CNN\","+
            "\"title\":\"England and Wales coronavirus death toll 41% higher than UK government's daily figures - CNN\","+
            "\"description\":\"The true death toll from coronavirus in England and Wales up to April 10 was about 41%"+
            "higher than the UK government's daily update, according to data released by the country's Office of"+
            "National Statistics (ONS).\","+
            "\"url\":\"https://www.cnn.com/2020/04/21/uk/uk-death-toll-coronavirus-statistics-gbr-intl/index.html\","+
            "\"urlToImage\":\"https://cdn.cnn.com/cnnnext/dam/assets/200414114642-01-uk-health-worker-0324-super-tease.jpg\","+
            "\"publishedAt\":\"2020-04-21T14:36:46Z\","+
            "\"content\":null}]}";

    @Test
    public void testArticleFetch() throws JSONException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        /*JSONObject json = new JSONObject(JSON_DATA_SAMPLE);
        //NewsApiFetch newsApiFetch = new NewsApiFetch();

        Articlesfetched articlesfetched = new Articlesfetched();
        Method method = NewsApiFetch.class.getDeclaredMethod("convertFrom", JSONObject.class);
        method.setAccessible(true);
        articlesfetched = (Articlesfetched) method.invoke(JSONObject.class, new JSONObject(JSON_DATA_SAMPLE));

        Assertions.assertThat(articlesfetched.getStatus()).isEqualTo("ok");
        Assertions.assertThat(articlesfetched.getTotalResults()).isEqualTo(38);
        Assertions.assertThat(articlesfetched.getArticles().get(0).getAuthor()).isEqualTo("Simon Cullen, CNN");*/
    }
}
