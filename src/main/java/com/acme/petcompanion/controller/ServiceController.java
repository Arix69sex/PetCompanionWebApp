package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Service;
import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.service.ServiceService;
import com.acme.petcompanion.resource.ServiceResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public Page<ServiceResource> getAllServices(Pageable pageable){
        Page<Service> servicePage = serviceService.getAllServices(pageable);
        List<ServiceResource> resources = servicePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create a StudyCenter", description = "Create a new StudyCenter", tags = {"studyCenters"})
    @PostMapping("/studyCenters")
    public ServiceResource createService(@Valid @RequestBody ServiceResource resource, Long userId){
        Service service = convertToEntity(resource);
        return convertToResource(serviceService.createService(userId,service));
    }

    private Service convertToEntity(ServiceResource resource) {
        return mapper.map(resource, Service.class);
    }

    private ServiceResource convertToResource(Service entity){
        return mapper.map(entity, ServiceResource.class);
    }
}
