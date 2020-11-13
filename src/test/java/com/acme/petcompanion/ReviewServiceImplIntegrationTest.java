package com.acme.petcompanion;

import com.acme.petcompanion.domain.model.Review;
import com.acme.petcompanion.domain.model.Service;
import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.repository.ReviewRepository;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.ReviewService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import com.acme.petcompanion.service.ReviewServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReviewServiceImplIntegrationTest {
    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private ServiceRepository serviceRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ReviewService reviewReview;

    @TestConfiguration
    static class ReviewReviewImplTestConfiguration {
        @Bean
        public ReviewService reviewReview() {
            return new ReviewServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetReviewByTitle With Valid Title Then Returns Review")
    public void whenGetReviewByTitleWithValidTitleThenReturnsReview() {
        //Arrange
        String title = "Highly recommended";
        Review review = new Review();
        review.setId(1L);
        review.setTitle(title);
        review.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");

        //Service
        Service service = new Service();
        service.setId(1L);
        service.setTitle(title);
        service.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");
        service.setReviewScore(4.8f);
        service.setServiceType("Pet Care");
        service.setAddress("Av. Lorem 1563");
        //User provider
        User provider = new User();
        provider.setId(1L);
        provider.setEmail("fabian.gomez@gmail.com");
        provider.setPassword("engineering#2020");
        provider.setPremium(true);
        service.setUser(provider);
        review.setService(service);

        //User owner
        User owner = new User();
        owner.setId(1L);
        owner.setEmail("roger.estrada@gmail.com");
        owner.setPassword("Software#2020");
        owner.setPremium(true);

        review.setAuthor(owner);

        when(reviewRepository.findByTitle(title))
                .thenReturn(Optional.of(review));

        //Act
        Review foundReview = reviewReview.getReviewByTitle(title);

        //Assert
        assertThat(foundReview.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("When GetReviewByTitle With Invalid Title Then Returns Resource Not Found Exception")
    public void whenGetReviewByTitleWithInvalidTitleThenReturnsResourceNotFoundException() {
        //Arrange
        String title = "Highly recommended";
        String template = "Resource %s not found for %s with value %s";

        when(reviewRepository.findByTitle(title))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Review", "Title", title);

        //Act
        Throwable exception = catchThrowable(() -> {
            Review foundReview = reviewReview.getReviewByTitle(title);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
