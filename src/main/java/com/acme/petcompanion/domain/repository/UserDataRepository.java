package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    UserData findByUserId(Long userId);
}
