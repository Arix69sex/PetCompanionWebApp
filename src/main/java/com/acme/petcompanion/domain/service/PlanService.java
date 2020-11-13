package com.acme.petcompanion.domain.service;

import com.acme.petcompanion.domain.model.Message;
import com.acme.petcompanion.domain.model.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PlanService {
    Page<Plan> getAllPlansByServiceId (Long serviceId, Pageable pageable);

    Plan getPlanByIdAndServiceId (Long serviceId, Long planId);

    Plan createPlan (Long serviceId, Plan plan);

    Plan updatePlan (Long serviceId, Long planId, Plan planDetails);

    ResponseEntity<?> deletePlan(Long serviceId, Long planId);

    Plan getPlanByName(String name);
}
