package com.acme.petcompanion.domain.aditionalClasses;

import com.acme.petcompanion.domain.model.Review;
import com.acme.petcompanion.domain.repository.ReviewRepository;
import library.StrategyPattern.ReviewAverage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ServiceReviewAverage implements ReviewAverage {

    @Override
    public float getAverage(){
        return 0;
    }

    public float getAverage(ReviewRepository reviewRepository, Long serviceId){
        float total = 0;
        Page<Review> reviewsPage = reviewRepository.findAllByServiceId(serviceId, Pageable.unpaged());
        List<Review> reviews = reviewsPage.getContent();
        for (int i = 0; i < reviews.size(); i++){
            total = total + reviews.get(i).getScore();
        }
        return total/reviews.size();
    }
}
