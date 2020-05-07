package com.backend.fluxnewsapi.dtos.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class Articlesfetched {
    String status;  //possible value = "ok"
    int totalResults;
    List<ArticleDto> articles;
}
