package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository <Review,Long> {
    Page<Review> findAllByAuthorId(Long userId, Pageable pageable);
    Page<Review> findAllByServiceId(Long serviceId, Pageable pageable);
    Review findByAuthorIdAndServiceId(Long userId, Long serviceId);
    Optional<Review> findByTitle(String title);
}
