package com.acme.petcompanion;

import com.acme.petcompanion.domain.model.Plan;
import com.acme.petcompanion.domain.repository.PlanRepository;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.service.PlanService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import com.acme.petcompanion.service.PlanServiceImpl;
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
public class PlanServiceImplIntegrationTest {
    @MockBean
    private PlanRepository planRepository;

    @MockBean
    private ServiceRepository serviceRepository;

    @Autowired
    private PlanService planService;

    @TestConfiguration
    static class PlanServiceImplTestConfiguration {
        @Bean
        public PlanService planService() {
            return new PlanServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetPlanByName With Valid Name Then Returns Plan")
    public void whenGetPlanByNameWithValidNameThenReturnsPlan() {
        //Arrange
        String name = "Premium";
        Plan plan = new Plan();
        plan.setId(1L);
        plan.setName(name);
        plan.setDescription("This is a premium plan that allows you to " +
                "publish services, " +
                "purchase services, " +
                "remove ads, " +
                "automatically purchase a service and " +
                "make your ads appear first.");
        plan.setPrice(9.99F);

        when(planRepository.findByName(name))
                .thenReturn(Optional.of(plan));

        //Act
        Plan foundPlan = planService.getPlanByName(name);

        //Assert
        assertThat(foundPlan.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When GetPlanByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetPlanByNameWithInvalidNameThenReturnsResourceNotFoundException() {
        //Arrange
        String name = "Premium";
        String template = "Resource %s not found for %s with value %s";

        when(planRepository.findByName(name))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Plan", "Name", name);

        //Act
        Throwable exception = catchThrowable(() -> {
            Plan foundPlan = planService.getPlanByName(name);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
