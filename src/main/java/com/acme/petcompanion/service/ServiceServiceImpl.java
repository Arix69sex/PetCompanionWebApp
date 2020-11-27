package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.repository.ServiceRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.ServiceService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.acme.petcompanion.domain.model.Service;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Service> getAllServices (Pageable pageable){
        return serviceRepository.findAll(pageable);
    }


    @Override
    public Page<Service> getAllServicesByUserId (Long userId, Pageable pageable){
        return serviceRepository.findByUserId(userId, pageable);
    }

    @Override
    public Service getServiceById (Long serviceId){
        return serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public Service createService (Long userId, Service service){
        return userRepository.findById(userId).map(user -> {
            service.setUser(user);
            return serviceRepository.save(service);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
    }

    @Override
    public Service updateService (Long userId, Long serviceId, Service serviceRequest){
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return serviceRepository.findById(serviceId).map(service -> {
            service.setTitle(serviceRequest.getTitle());
            service.setDescription(serviceRequest.getDescription());
            service.setReviewScore(serviceRequest.getReviewScore());
            service.setServiceType(serviceRequest.getServiceType());
            service.setAddress(serviceRequest.getAddress());
            return serviceRepository.save(service);
        }).orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public ResponseEntity<?> deleteService(Long userId, Long serviceId){
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return serviceRepository.findById(serviceId).map(service -> {
            serviceRepository.delete(service);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public Service getServiceByTitle(String title){
        return serviceRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "Title", title));
    }
}
