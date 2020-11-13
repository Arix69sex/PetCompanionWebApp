package com.acme.petcompanion;

import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.UserService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import com.acme.petcompanion.service.UserServiceImpl;
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
public class UserServiceImplIntegrationTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetUserByEmail With Valid Email Then Returns User")
    public void whenGetUserByEmailWithValidEmailThenReturnsUser() {
        //Arrange
        String email = "roger.estrada@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("Software#2020");
        user.setPremium(true);

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        //Act
        User foundUser = userService.getUserByEmail(email);

        //Assert
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("When GetUserByEmail With Invalid Email Then Returns Resource Not Found Exception")
    public void whenGetUserByEmailWithInvalidEmailThenReturnsResourceNotFoundException() {
        //Arrange
        String email = "petcompanion@gmail.com";
        String template = "Resource %s not found for %s with value %s";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "User", "Email", email);

        //Act
        Throwable exception = catchThrowable(() -> {
            User foundUser = userService.getUserByEmail(email);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
