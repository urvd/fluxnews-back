package com.backend.fluxnewsapi;

import com.backend.fluxnewsapi.dtos.models.Articlesfetched;
import com.backend.fluxnewsapi.traitrements.NewsApiFetch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.configurationprocessor.json.JSONException;

public class fetchingArticleTest {

    private  final static String JSON_DATA_SAMPLE = "{\"status\":\"ok\",\"totalResults\":1,\"articles\":[{"+
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
    @InjectMocks
    NewsApiFetch newsApiFetch;
/*    @BeforeAll
    public  void set(){

    }*/
    @Test
    public void testArticleFetch() throws JSONException {
        NewsApiFetch newsApiFetch = new NewsApiFetch();
        //when
        Articlesfetched articlesfetched = newsApiFetch.testConversion(JSON_DATA_SAMPLE);
        //then
        Assertions.assertThat(articlesfetched.getStatus()).isEqualTo("ok");
        Assertions.assertThat(articlesfetched.getTotalResults()).isEqualTo(1);
        Assertions.assertThat(articlesfetched.getArticles().get(0).getAuthor()).isEqualTo("Simon Cullen, CNN");
        Assertions.assertThat(articlesfetched.getArticles().get(0).getSource()).isEqualTo("CNN");
    }
}
