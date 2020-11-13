package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    UserData findByUserId(Long userId);

    Optional<UserData> findByName(String name);

    Optional<UserData> findByLastName(String lastName);

    Optional<UserData> findByScoreOwner(float scoreOwner);

    Optional<UserData> findByScoreProvider(float scoreProvider);
}
