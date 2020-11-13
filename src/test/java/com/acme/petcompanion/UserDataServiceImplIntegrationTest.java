package com.acme.petcompanion;

import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.model.UserData;
import com.acme.petcompanion.domain.repository.UserDataRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.UserDataService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import com.acme.petcompanion.service.UserDataServiceImpl;
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
public class UserDataServiceImplIntegrationTest {
    @MockBean
    private UserDataRepository userDataRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserDataService userDataService;

    @TestConfiguration
    static class UserDataServiceImplTestConfiguration {
        @Bean
        public UserDataService userDataService() {
            return new UserDataServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetUserDataByName With Valid Name Then Returns UserData")
    public void whenGetUserDataByNameWithValidNameThenReturnsUserData() {
        //Arrange
        String name = "Roger";
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setLastName("Estrada");
        userData.setScoreOwner(5.0f);
        userData.setScoreProvider(4.5f);
        String email = "roger.estrada@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("Software#2020");
        user.setPremium(true);
        userData.setUser(user);

        when(userDataRepository.findByName(name))
                .thenReturn(Optional.of(userData));

        //Act
        UserData foundUserData = userDataService.getUserDataByName(name);

        //Assert
        assertThat(foundUserData.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When GetUserDataByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetUserDataByNameWithInvalidNameThenReturnsResourceNotFoundException() {
        //Arrange
        String name = "roger.estrada@gmail.com";
        String template = "Resource %s not found for %s with value %s";

        when(userDataRepository.findByName(name))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "UserData", "Name", name);

        //Act
        Throwable exception = catchThrowable(() -> {
            UserData foundUserData = userDataService.getUserDataByName(name);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When GetUserDataByLastName With Valid LastName Then Returns UserData")
    public void whenGetUserDataByLastNameWithValidLastNameThenReturnsUserData() {
        //Arrange
        String lastName = "Estrada";
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName("Roger");
        userData.setLastName(lastName);
        userData.setScoreOwner(5.0f);
        userData.setScoreProvider(4.5f);
        String email = "roger.estrada@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("Software#2020");
        user.setPremium(true);
        userData.setUser(user);

        when(userDataRepository.findByLastName(lastName))
                .thenReturn(Optional.of(userData));

        //Act
        UserData foundUserData = userDataService.getUserDataByLastName(lastName);

        //Assert
        assertThat(foundUserData.getLastName()).isEqualTo(lastName);
    }

    @Test
    @DisplayName("When GetUserDataByLastName With Invalid LastName Then Returns Resource Not Found Exception")
    public void whenGetUserDataByLastNameWithInvalidLastNameThenReturnsResourceNotFoundException() {
        //Arrange
        String lastName = "Estrada";
        String template = "Resource %s not found for %s with value %s";

        when(userDataRepository.findByLastName(lastName))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "UserData", "LastName", lastName);

        //Act
        Throwable exception = catchThrowable(() -> {
            UserData foundUserData = userDataService.getUserDataByLastName(lastName);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When GetUserDataByScoreProvider With Valid ScoreOwner Then Returns UserData")
    public void whenGetUserDataByScoreProviderWithValidScoreOwnerThenReturnsUserData() {
        //Arrange
        float scoreOwner = 5.0f;
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName("Roger");
        userData.setLastName("Estrada");
        userData.setScoreOwner(scoreOwner);
        userData.setScoreProvider(4.5f);
        String email = "roger.estrada@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("Software#2020");
        user.setPremium(true);
        userData.setUser(user);

        when(userDataRepository.findByScoreOwner(scoreOwner))
                .thenReturn(Optional.of(userData));

        //Act
        UserData foundUserData = userDataService.getUserDataByScoreOwner(scoreOwner);

        //Assert
        assertThat(foundUserData.getScoreOwner()).isEqualTo(scoreOwner);
    }

    @Test
    @DisplayName("When GetUserDataByScoreProvider With Invalid ScoreOwner Then Returns Resource Not Found Exception")
    public void whenGetUserDataByScoreProviderWithInvalidScoreOwnerThenReturnsResourceNotFoundException() {
        //Arrange
        float scoreOwner = 5.0f;
        String template = "Resource %s not found for %s with value %s";

        when(userDataRepository.findByScoreOwner(scoreOwner))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "UserData", "ScoreOwner", scoreOwner);

        //Act
        Throwable exception = catchThrowable(() -> {
            UserData foundUserData = userDataService.getUserDataByScoreOwner(scoreOwner);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When GetUserDataByScoreProvider With Valid ScoreProvider Then Returns UserData")
    public void whenGetUserDataByScoreProviderWithValidScoreProviderThenReturnsUserData() {
        //Arrange
        float scoreProvider = 4.5f;
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName("Roger");
        userData.setLastName("Estrada");
        userData.setScoreOwner(5.0f);
        userData.setScoreProvider(scoreProvider );
        String email = "roger.estrada@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("Software#2020");
        user.setPremium(true);
        userData.setUser(user);

        when(userDataRepository.findByScoreProvider(scoreProvider))
                .thenReturn(Optional.of(userData));

        //Act
        UserData foundUserData = userDataService.getUserDataByScoreProvider(scoreProvider);

        //Assert
        assertThat(foundUserData.getScoreProvider()).isEqualTo(scoreProvider);
    }

    @Test
    @DisplayName("When GetUserDataByScoreProvider With Invalid ScoreProvider Then Returns Resource Not Found Exception")
    public void whenGetUserDataByScoreProviderWithInvalidScoreProviderThenReturnsResourceNotFoundException() {
        //Arrange
        float scoreProvider = 4.5f;
        String template = "Resource %s not found for %s with value %s";

        when(userDataRepository.findByScoreProvider(scoreProvider))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "UserData", "ScoreProvider", scoreProvider);

        //Act
        Throwable exception = catchThrowable(() -> {
            UserData foundUserData = userDataService.getUserDataByScoreProvider(scoreProvider);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}