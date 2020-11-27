package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Service;
import com.acme.petcompanion.domain.model.UserData;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.service.ServiceService;
import com.acme.petcompanion.domain.service.UserService;
import com.acme.petcompanion.resource.SaveServiceResource;
import com.acme.petcompanion.resource.SaveUserDataResource;
import com.acme.petcompanion.resource.ServiceResource;
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
public class ServicesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ServiceService serviceService;


    @Operation(summary = "Get all the Services", description = "Get all the Services", tags = {"services"})
    @GetMapping("/users/services")
    public Page<ServiceResource> getAllServices(Pageable pageable){
        Page<Service> servicePage = serviceService.getAllServices(pageable);
        List<ServiceResource> resources = servicePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get all the Services by UserId", description = "Get all the Services by UserId", tags = {"services"})
    @GetMapping("/users/{userId}/services")
    public Page<ServiceResource> getAllServicesByUserId(@PathVariable Long userId, Pageable pageable){
        Page<Service> servicePage = serviceService.getAllServicesByUserId(userId, pageable);
        List<ServiceResource> resources = servicePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get a new Service by its Id", description = "Get a new Service by its Id", tags = {"services"})
    @GetMapping("/services")
    public ServiceResource getServiceById(Long serviceId){
       return convertToResource(serviceService.getServiceById(serviceId));
    }

    @Operation(summary = "Create a Service", description = "Create a new Service", tags = {"services"})
    @PostMapping("/users/{userId}/services")
    public ServiceResource createService(@PathVariable Long userId, @Valid @RequestBody SaveServiceResource resource){
        Service service = convertToEntity(resource);
        return convertToResource(serviceService.createService(userId,service));
    }

    @Operation(summary = "Update a Service", description = "Update an existing Service with given UserId", tags = {"services"})
    @PutMapping("/users/{userId}/services")
    public ServiceResource updateService(@PathVariable Long userId, @Valid @RequestBody SaveServiceResource resource) {
        Service service = convertToEntity(resource);
        return convertToResource(serviceService.updateService(userId, service.getId(), service));
    }

    @Operation(summary = "Delete a Service", description = "Delete an existing Service with given Id", tags = {"services"})
    @DeleteMapping("/users/{userId}/services/{serviceId}")
    public ResponseEntity<?> deleteService(@PathVariable Long userId, @PathVariable Long serviceId) {
        return serviceService.deleteService(userId, serviceId);
    }

    private Service convertToEntity(SaveServiceResource resource) {
        return mapper.map(resource, Service.class);
    }

    private ServiceResource convertToResource(Service entity){
        return mapper.map(entity, ServiceResource.class);
    }
}
