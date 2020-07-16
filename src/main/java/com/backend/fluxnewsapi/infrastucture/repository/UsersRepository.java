package com.backend.fluxnewsapi.infrastucture.repository;

import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.infrastucture.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {
    com.backend.fluxnewsapi.infrastucture.models.User findByEmail(String email);
    User findByUsername(String username) throws RessourceException;
}
