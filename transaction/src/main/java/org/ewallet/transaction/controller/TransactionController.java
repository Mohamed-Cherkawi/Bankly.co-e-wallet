package org.ewallet.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.ewallet.transaction.dto.TransactionDto;
import org.ewallet.transaction.service.interfaces.TransactionServiceInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction/api/v1")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionServiceInter transactionService;

    @PostMapping("/adding")
    public ResponseEntity<Object> saveTransactionApi(@RequestBody TransactionDto transactionDto){
        TransactionDto transaction = transactionService.createTransaction(transactionDto);

        return (transaction != null)
                ? ResponseEntity.ok(transaction)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong , try again");
    }
}