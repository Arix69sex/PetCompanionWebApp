package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Review;
import com.acme.petcompanion.domain.service.ReviewService;
import com.acme.petcompanion.resource.ReviewResource;
import com.acme.petcompanion.resource.SaveReviewResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReviewsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Get Reviews By UserId", description = "Get Reviews By UserId", tags = {"reviews"})
    @GetMapping("users/{userId}/reviews}")
    public Page<ReviewResource> getAllReviewsByUserId(
        @PathVariable Long userId, Pageable pageable) {
        Page<Review> reviewPage = reviewService.getAllReviewsByUserId(userId, pageable);
        List<ReviewResource> resources = reviewPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
/*
    @Operation(summary = "Create Review", description = "Create a new Review", tags = {"reviews"})
    @PostMapping("users/{userId}/services/{serviceId}/reviews")
    public ReviewResource createReview(
            @PathVariable Long userId,
            @PathVariable Long serviceId,
            @Valid @RequestBody SaveReviewResource resource) {
        return convertToResource(reviewService.createReview(userId, serviceId, convertToEntity(resource)));
    }
*/
    @Operation(summary = "Update Review", description = "Update Review with given Id", tags = {"reviews"})
    @PutMapping("users/{userId}/services/{serviceId}/reviews/{reviewId}")
    public ReviewResource updateReview(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "serviceId") Long serviceId,
            @PathVariable(value = "reviewId") Long reviewId,
            @Valid @RequestBody SaveReviewResource resource) {
        return convertToResource(reviewService.updateReview(userId, serviceId, reviewId, convertToEntity(resource)));
    }

    @Operation(summary = "delete Review", description = "delete Review with given Id", tags = {"reviews"})
    @DeleteMapping("users/{userId}/services/{serviceId}/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "serviceId") Long serviceId,
            @PathVariable(value = "reviewId") Long reviewId) {
        return reviewService.deleteReview(userId, serviceId, reviewId);
    }

    private Review convertToEntity(SaveReviewResource resource) {
        return mapper.map(resource, Review.class);
    }

    private ReviewResource convertToResource(Review entity) {
        return mapper.map(entity, ReviewResource.class);
    }
}