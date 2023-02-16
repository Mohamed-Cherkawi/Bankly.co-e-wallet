package org.ewallet.transaction.service.interfaces;

import org.ewallet.transaction.dto.TransactionDto;

public interface TransactionServiceInter {
    TransactionDto createTransaction(TransactionDto transactionDto);
    String makeOperation(String operationType, TransactionDto walletDto);
}