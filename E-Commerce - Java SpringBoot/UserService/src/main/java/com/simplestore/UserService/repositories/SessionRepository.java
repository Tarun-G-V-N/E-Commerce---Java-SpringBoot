package com.simplestore.UserService.repositories;

import com.simplestore.UserService.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByUser_IdAndToken(long userId, String token);
    List<Session> findAllByUser_Id(long userId);
}
