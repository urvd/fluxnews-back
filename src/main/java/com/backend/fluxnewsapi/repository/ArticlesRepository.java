package com.backend.fluxnewsapi.repository;

import com.backend.fluxnewsapi.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository  extends JpaRepository<Article, Long> {

/*    @Query(
            value =
                    "insert into Users (name, age, email, status) values (:id, :author, :source, :url," +
                            ":url)",
            nativeQuery = true)
    void insertAll();*/

    Article findByNumberid(int numberid);
}
