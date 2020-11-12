package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
