package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.Transacction;
import com.acme.petcompanion.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacctionRepository extends JpaRepository<Transacction,Long> {
    Page<Transacction> findAllByRecieverId (Long payerId, Pageable pageable);
    Page<Transacction> findAllByPayerId (Long payerId, Pageable pageable);
}
