package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.model.Review;
import com.acme.petcompanion.domain.repository.ReviewRepository;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.ReviewService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Review> getAllReviews (Pageable pageable){
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Page<Review> getAllReviewsByServiceId (Long serviceId, Pageable pageable){
        return reviewRepository.findAllByServiceId(serviceId,pageable);
    }

    @Override
    public Page<Review> getAllReviewsByUserId(Long serviceId, Pageable pageable){
        return reviewRepository.findAllByAuthorId(serviceId,pageable);
    }

    @Override
    public Review getReviewByUserIdAndServiceId (Long userId, Long serviceId){
        return reviewRepository.findByAuthorIdAndServiceId(userId, serviceId);
    }

    @Override
    public Review getReviewById (Long reviewId){
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review", "Id", reviewId));
    }

    @Override
    public Review createReview (Long userId, Long serviceId, Review reviewDetails){
        /*if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
        Service service = user
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service", "Id", serviceId));
        if (!user)
            throw new ResourceNotFoundException("User", "Id", userId);*/
        return null;
    }

    public ResponseEntity<?> deleteReview(Long userId, Long serviceId, Long reviewId){
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return reviewRepository.findById(reviewId).map(review -> {
            reviewRepository.delete(review);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Review", "Id", reviewId));
    }
}
