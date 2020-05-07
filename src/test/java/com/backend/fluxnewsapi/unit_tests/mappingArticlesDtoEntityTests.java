package com.backend.fluxnewsapi.unit_tests;

import com.backend.fluxnewsapi.dtos.EntityDtoMap;
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
    EntityDtoMap<Article, ArticleDto> entityDtoMapArticle;
    @InjectMocks
    EntityDtoMap<Article, UserDto> entityDtoMapNotGood;

    @BeforeEach
    void setUp(){
        entityDtoMapArticle = new EntityDtoMap<>();
        entityDtoMapNotGood = new EntityDtoMap<>();
    }

    @Test
    public void mappingArticleDtoConvertToEntityTest() throws MyMappingException {
        //given
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(0);
        articleDto.setAuthor("pascal");
        articleDto.setContent("rctfvygbhnjok,lmlnjbhvgcfxfygnipo;poui,");
        articleDto.setTitle("title news");
        articleDto.setDescription("vyuiklkbbjlnk,m");

        //when
        Article article = entityDtoMapArticle.convertToEntity(articleDto,Article.class);

        //Then
        assertThat(article.getId()).isEqualTo(articleDto.getId());
        assertThat(article.getAuthor()).isEqualTo(articleDto.getAuthor());
        assertThat(article.getContent()).isEqualTo(articleDto.getContent());
        assertThat(article.getTitle()).isEqualTo(articleDto.getTitle());
        assertThat(article.getDescription()).isEqualTo(articleDto.getDescription());
    }
    @Test
    public void mappingArticleConvertToDtoTest() throws MyMappingException {
        //given
        Article article = new Article();
        article.setId(0);
        article.setAuthor("pascal");
        article.setContent("rctfvygbhnjok,lmlnjbhvgcfxfygnipo;poui,");
        article.setTitle("title news");
        article.setDescription("vyuiklkbbjlnk,m");

        //when
        ArticleDto articleDto = entityDtoMapArticle.convertToDto(article,ArticleDto.class);

        //Then
        assertThat(article.getId()).isEqualTo(articleDto.getId());
        assertThat(article.getAuthor()).isEqualTo(articleDto.getAuthor());
        assertThat(article.getContent()).isEqualTo(articleDto.getContent());
        assertThat(article.getTitle()).isEqualTo(articleDto.getTitle());
        assertThat(article.getDescription()).isEqualTo(articleDto.getDescription());
    }
    @Test
    public void mappingArticleConvertToDtoThrowExceptionTest() throws MyMappingException {
        //given
        Article article = new Article();
        article.setId(0);
        article.setAuthor("pascal");
        article.setContent("rctfvygbhnjok,lmlnjbhvgcfxfygnipo;poui,");
        article.setTitle("title news");
        article.setDescription("vyuiklkbbjlnk,m");

        try {
            //when
            UserDto userDto = entityDtoMapNotGood.convertToDto(article, UserDto.class);
        }catch (Exception e) {
            //Then
            org.junit.jupiter.api.Assertions.assertTrue(e instanceof  MyMappingException);
        }
    }
}
