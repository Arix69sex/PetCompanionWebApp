package com.acme.petcompanion.domain.service;

import com.acme.petcompanion.domain.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ServiceService {

    Page<Service> getAllServices (Pageable pageable);

    Page<Service> getAllServicesByUserId (Long userId, Pageable pageable);

    Service getServiceById (Long serviceIc);

    Service createService (Long userId, Service service);

    Service updateService (Long userId, Long serviceId, Service serviceRequest);

    ResponseEntity<?> deleteService(Long userId, Long serviceId);

}
