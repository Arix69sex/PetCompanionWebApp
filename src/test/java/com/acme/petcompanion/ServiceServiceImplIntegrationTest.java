package com.acme.petcompanion;

import com.acme.petcompanion.domain.model.Service;
import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.ServiceService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import com.acme.petcompanion.service.ServiceServiceImpl;
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
public class ServiceServiceImplIntegrationTest {
    @MockBean
    private ServiceRepository serviceRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ServiceService serviceService;

    @TestConfiguration
    static class ServiceServiceImplTestConfiguration {
        @Bean
        public ServiceService serviceService() {
            return new ServiceServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetServiceByTitle With Valid Title Then Returns Service")
    public void whenGetServiceByTitleWithValidTitleThenReturnsService() {
        //Arrange
        String title = "Service 1320";
        Service service = new Service();
        service.setId(1L);
        service.setTitle(title);
        service.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed eiusmod tempor incidunt ut labore et dolore magna aliqua.");
        service.setReviewScore(4.8f);
        service.setServiceType("Pet Care");
        service.setAddress("Av. Lorem 1563");
        User user = new User();
        user.setId(1L);
        user.setEmail("fabian.gomez@gmail.com");
        user.setPassword("engineering#2020");
        user.setPremium(true);
        service.setUser(user);

        when(serviceRepository.findByTitle(title))
                .thenReturn(Optional.of(service));

        //Act
        Service foundService = serviceService.getServiceByTitle(title);

        //Assert
        assertThat(foundService.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("When GetServiceByTitle With Invalid Title Then Returns Resource Not Found Exception")
    public void whenGetServiceByTitleWithInvalidTitleThenReturnsResourceNotFoundException() {
        //Arrange
        String title = "Service 1320";
        String template = "Resource %s not found for %s with value %s";

        when(serviceRepository.findByTitle(title))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Service", "Title", title);

        //Act
        Throwable exception = catchThrowable(() -> {
            Service foundService = serviceService.getServiceByTitle(title);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
