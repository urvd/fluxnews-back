package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<User,Integer> {
}
