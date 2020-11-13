package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.Service;
import com.acme.petcompanion.domain.model.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Page<Service> findByUserId (Long userId, Pageable pageable);
    Optional<Service> findByTitle(String title);
}
