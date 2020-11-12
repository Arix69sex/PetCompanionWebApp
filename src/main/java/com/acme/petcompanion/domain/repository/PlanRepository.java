package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Page<Plan> findAllByServiceId(Long serviceId, Pageable pageable);

    Plan findByIdAndServiceId(Long planId, Long serviceId);
}
