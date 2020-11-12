package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.model.Plan;
import com.acme.petcompanion.domain.repository.PlanRepository;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.service.PlanService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<Plan> getAllPlansByServiceId(Long serviceId, Pageable pageable) {
        return planRepository.findAllByServiceId(serviceId, pageable);
    }

    @Override
    public Plan getPlanByIdAndServiceId(Long planId, Long serviceId) {
        return planRepository.findByIdAndServiceId(planId, serviceId);
    }

    @Override
    public Plan createPlan(Long serviceId, Plan plan) {
        return serviceRepository.findById(serviceId).map(service -> {
            plan.setService(service);
            return planRepository.save(plan);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Service", "Id", serviceId));
    }

    @Override
    public Plan updatePlan(Long serviceId, Long planId, Plan planDetails) {
        if (!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", "Id", serviceId);
        return planRepository.findById(planId).map(plan -> {
            plan.setName(planDetails.getName());
            plan.setDescription(planDetails.getDescription());
            plan.setPrice(planDetails.getPrice());
            return planRepository.save(plan);
        }).orElseThrow(() -> new ResourceNotFoundException("Plan", "Id", planId));
    }

    @Override
    public ResponseEntity<?> deletePlan(Long serviceId, Long planId) {
        if (!serviceRepository.existsById(serviceId))
            throw new ResourceNotFoundException("Service", "Id", serviceId);
        return planRepository.findById(planId).map(plan -> {
            planRepository.delete(plan);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Plan", "Id", planId));
    }
}
