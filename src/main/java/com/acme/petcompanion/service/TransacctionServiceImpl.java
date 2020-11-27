package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.aditionalClasses.TransactionValidator;
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
public class TransacctionServiceImpl implements TransacctionService {

    private TransactionValidator transactionValidator;

    @Autowired
    private TransacctionRepository transacctionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Transacction getTransacctionsById(Long transacctionId, Pageable pageable) {
        return transacctionRepository.findById(transacctionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "Id", transacctionId));
    }

    @Override
    public Page<Transacction> getAllTransacctionsByRecieverId(Long userId, Pageable pageable) {
        return transacctionRepository.findAllByPayerId(userId, pageable);
    }

    @Override
    public Page<Transacction> getAllTransacctionsByPayerId(Long userId, Pageable pageable) {
        return transacctionRepository.findAllByRecieverId(userId, pageable);
    }

    @Override
    public Transacction createTransacction(Long payerId, Long recieverId, Transacction transacction, float amount) {
        User payer = userRepository.findById(payerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", payerId));
        User reciever = userRepository.findById(recieverId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", recieverId));
        if (transactionValidator.test(transacction)){
            transacction.setPayer(payer);
            transacction.setReciever(reciever);
            transacction.setAmount(amount);
            return transacctionRepository.save(transacction);
        }
        return transacction;
    }

}
