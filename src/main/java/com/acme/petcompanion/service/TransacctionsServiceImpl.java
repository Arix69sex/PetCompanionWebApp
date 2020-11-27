package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.model.Transacction;
import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.repository.TransacctionRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.TransacctionService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransacctionsServiceImpl implements TransacctionService {

    @Autowired
    private TransacctionRepository transacctionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Transacction getTransacctionById(Long transacctionId) {
        return transacctionRepository.findById(transacctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "Id", transacctionId));
    }

    @Override
    public Page<Transacction> getAllTransacctionsByRecieverId(Long userId, Pageable pageable) {
        return transacctionRepository.findAllByRecieverId(userId, pageable);
    }

    @Override
    public Page<Transacction> getAllTransacctionsByPayerId(Long userId, Pageable pageable) {
        return transacctionRepository.findAllByPayerId(userId, pageable);
    }

    @Override
    public Transacction createTransaction(Long payerId, Long recieverId, Transacction transaction) {
        User payer = userRepository.findById(payerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", payerId));
        User reciever = userRepository.findById(recieverId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", recieverId));
        transaction.setPayer(payer);
        transaction.setReciever(reciever);
        return transacctionRepository.save(transaction);
    }

}
