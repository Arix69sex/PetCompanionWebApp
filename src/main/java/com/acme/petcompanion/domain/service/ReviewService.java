package com.acme.petcompanion.domain.service;

import com.acme.petcompanion.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    Page<Review> getAllReviews (Pageable pageable);

    Page<Review> getAllReviewsByServiceId (Long serviceId, Pageable pageable);

    Page<Review> getAllReviewsByUserId(Long serviceId, Pageable pageable);

    Review getReviewByUserIdAndServiceId (Long userId, Long serviceId);

    Review getReviewById (Long reviewId);

    Review createReview (Long userId, Long serviceId, Review reviewDetails);

    ResponseEntity<?> deleteReview(Long userId, Long serviceId, Long reviewId);

    Review getReviewByTitle (String title);
}
