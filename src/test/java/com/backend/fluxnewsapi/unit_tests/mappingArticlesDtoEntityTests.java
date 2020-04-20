package com.backend.fluxnewsapi.unit_tests;

import com.backend.fluxnewsapi.dtos.DataMap;
import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.UserDto;
import com.backend.fluxnewsapi.exceptions.MyMappingException;
import com.backend.fluxnewsapi.models.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class mappingArticlesDtoEntityTests {
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    DataMap<Article, ArticleDto> dataMapArticle;
    @InjectMocks
    DataMap<Article, UserDto> dataMapNotGood;

    @BeforeEach
    void setUp(){
        dataMapArticle = new DataMap<>();
        dataMapNotGood = new DataMap<>();
    }

    @Test
    public void mappingArticleDtoConvertToEntityTest() throws MyMappingException {
        //given
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(0);
        articleDto.setAuthor("pascal");
        articleDto.setContenu("rctfvygbhnjok,lmlnjbhvgcfxfygnipo;poui,");
        articleDto.setTilte("title news");
        articleDto.setDescription("vyuiklkbbjlnk,m");

        //when
        Article article = dataMapArticle.convertToEntity(articleDto,Article.class);

        //Then
        assertThat(article.getId()).isEqualTo(articleDto.getId());
        assertThat(article.getAuthor()).isEqualTo(articleDto.getAuthor());
        assertThat(article.getContenu()).isEqualTo(articleDto.getContenu());
        assertThat(article.getTilte()).isEqualTo(articleDto.getTilte());
        assertThat(article.getDescription()).isEqualTo(articleDto.getDescription());
    }
    @Test
    public void mappingArticleConvertToDtoTest() throws MyMappingException {
        //given
        Article article = new Article();
        article.setId(0);
        article.setAuthor("pascal");
        article.setContenu("rctfvygbhnjok,lmlnjbhvgcfxfygnipo;poui,");
        article.setTilte("title news");
        article.setDescription("vyuiklkbbjlnk,m");

        //when
        ArticleDto articleDto = dataMapArticle.convertToDto(article,ArticleDto.class);

        //Then
        assertThat(article.getId()).isEqualTo(articleDto.getId());
        assertThat(article.getAuthor()).isEqualTo(articleDto.getAuthor());
        assertThat(article.getContenu()).isEqualTo(articleDto.getContenu());
        assertThat(article.getTilte()).isEqualTo(articleDto.getTilte());
        assertThat(article.getDescription()).isEqualTo(articleDto.getDescription());
    }
    @Test
    public void mappingArticleConvertToDtoThrowExceptionTest() throws MyMappingException {
        //given
        Article article = new Article();
        article.setId(0);
        article.setAuthor("pascal");
        article.setContenu("rctfvygbhnjok,lmlnjbhvgcfxfygnipo;poui,");
        article.setTilte("title news");
        article.setDescription("vyuiklkbbjlnk,m");

        try {
            //when
            UserDto userDto = dataMapNotGood.convertToDto(article, UserDto.class);
        }catch (Exception e) {
            //Then
            org.junit.jupiter.api.Assertions.assertTrue(e instanceof  MyMappingException);
        }
    }
}
