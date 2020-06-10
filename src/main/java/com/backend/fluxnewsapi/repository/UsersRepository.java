package com.backend.fluxnewsapi.repository;

import com.backend.fluxnewsapi.exceptions.RessourceException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.fluxnewsapi.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {
    //List<ArticleUser> findByArticleUsers(int id);
    User findByEmail(String email);
    User findByUsername(String username) throws RessourceException;
}
