package com.acme.petcompanion.domain.service;

import com.acme.petcompanion.domain.model.Transacction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransacctionService {

    Transacction getTransacctionsById(Long transacctionId, Pageable pageable);

    Page<Transacction> getAllTransacctionsByRecieverId (Long userId, Pageable pageable);

    Page<Transacction> getAllTransacctionsByPayerId (Long userId, Pageable pageable);

    Transacction createTransacction(Long payerId, Long recieverId, Transacction transacction);


}
