package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Transacction;
import com.acme.petcompanion.domain.service.TransacctionService;
import com.acme.petcompanion.resource.SaveTransacctionResource;
import com.acme.petcompanion.resource.TransacctionResource;
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
public class TransactionsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TransacctionService transacctionService;

    @Operation(summary = "Get Transaction By UserId", description = "Get transaction By UserId", tags = {"transactions"})
    @GetMapping("transactions/{transactionId}")
    public TransacctionResource getTransacctionsByUserId(
            @PathVariable Long transactionId) {
        return convertToResource(transacctionService.getTransacctionById(transactionId));
    }

    @Operation(summary = "Get Transactions received  By UserId", description = "Get transactions received By UserId", tags = {"transactions"})
    @GetMapping("users/{userId}/receivedTransactions")
    public Page<TransacctionResource> getAllTransacctionsByRecieverId(
            @PathVariable Long userId, Pageable pageable) {
        Page<Transacction> transactionPage = transacctionService.getAllTransacctionsByRecieverId(userId, pageable);
        List<TransacctionResource> resources = transactionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Transactions payed By UserId", description = "Get transactions payed By UserId", tags = {"transactions"})
    @GetMapping("users/{userId}/payedTransaction")
    public Page<TransacctionResource> getAllTransacctionsByPayerId(
            @PathVariable Long userId, Pageable pageable) {
        Page<Transacction> transactionPage = transacctionService.getAllTransacctionsByPayerId(userId, pageable);
        List<TransacctionResource> resources = transactionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Transaction", description = "Create a new Transaction", tags = {"transactions"})
    @PostMapping("users/{payerId}/{recieverId}/transactions")
    public TransacctionResource createTransaction(
            @PathVariable Long payerId,
            @PathVariable Long recieverId,
            @Valid @RequestBody SaveTransacctionResource resource) {
        return convertToResource(transacctionService.createTransaction(payerId, recieverId, convertToEntity(resource)));
    }

    private Transacction convertToEntity(SaveTransacctionResource resource) {
        return mapper.map(resource, Transacction.class);
    }

    private TransacctionResource convertToResource(Transacction entity) {
        return mapper.map(entity, TransacctionResource.class);
    }
}
