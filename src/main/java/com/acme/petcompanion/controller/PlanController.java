package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Plan;
import com.acme.petcompanion.domain.model.UserData;
import com.acme.petcompanion.domain.service.PlanService;
import com.acme.petcompanion.domain.service.ServiceService;
import com.acme.petcompanion.resource.PlanResource;
import com.acme.petcompanion.resource.SavePlanResource;
import com.acme.petcompanion.resource.SaveUserDataResource;
import com.acme.petcompanion.resource.UserDataResource;
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
public class PlanController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PlanService planService;

    @Autowired
    private ServiceService serviceService;

    @Operation(summary = "Get All plans by ServiceId", description = "Get All plans by ServiceId", tags = {"plans"})
    @GetMapping("/services/{serviceId}/plans")
    public Page<PlanResource> getPlansByServiceId(@PathVariable Long serviceId, Pageable pageable){
        Page<Plan> planPage = planService.getAllPlansByServiceId(serviceId, pageable);
        List<PlanResource> resources = planPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get All plans by Id and ServiceId", description = "Get All plans by Id and ServiceId", tags = {"plans"})
    @GetMapping("/services/{serviceId}/plans/{planId}")
    public PlanResource getPlansByIdAndServiceId(@PathVariable Long planId, @PathVariable Long serviceId){
        return convertToResource(planService.getPlanByIdAndServiceId(planId,serviceId));
    }

    @Operation(summary = "Create Plan", description = "Create Plan of a Service", tags = {"plans"})
    @PostMapping("/services/{serviceId}/plans")
    public PlanResource createPlan(@Valid @RequestBody SavePlanResource resource, @PathVariable Long serviceId){
        Plan plan = convertToEntity(resource);
        return convertToResource(planService.createPlan(serviceId, plan));
    }

    @Operation(summary = "Update a Plan", description = "Update an existing Plan with given ServiceId", tags = {"plans"})
    @PutMapping("/services/{serviceId}/plans")
    public PlanResource updatePlan(@PathVariable Long serviceId, @RequestBody SavePlanResource resource) {
        Plan plan = convertToEntity(resource);
        return convertToResource(planService.updatePlan(serviceId, plan.getId(), plan));
    }

    @Operation(summary = "Delete a Plan", description = "Delete an existing Plan with given Id", tags = {"plans"})
    @DeleteMapping("/services/{serviceId}/plans/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long serviceId, @PathVariable Long planId, @RequestBody SavePlanResource resource) {
        return planService.deletePlan(serviceId, planId);
    }

    private Plan convertToEntity(SavePlanResource resource) {
        return mapper.map(resource, Plan.class);
    }

    private PlanResource convertToResource(Plan entity){ return mapper.map(entity, PlanResource.class); }
}
