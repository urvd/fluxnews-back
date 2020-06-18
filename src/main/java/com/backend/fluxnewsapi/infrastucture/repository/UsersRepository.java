package com.backend.fluxnewsapi.infrastucture.repository;

import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.fluxnewsapi.infrastucture.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {
    //List<ArticleUser> findByArticleUsers(int id);
    User findByEmail(String email);
    User findByUsername(String username) throws RessourceException;
}
