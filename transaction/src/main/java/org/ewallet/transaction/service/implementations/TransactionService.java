package org.ewallet.transaction.service.implementations;

import lombok.RequiredArgsConstructor;
import org.ewallet.transaction.dto.TransactionDto;
import org.ewallet.transaction.entity.Transaction;
import org.ewallet.transaction.repository.TransactionRepository;
import org.ewallet.transaction.service.interfaces.TransactionServiceInter;
import org.ewallet.transaction.util.EntityMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService implements TransactionServiceInter {
    private final TransactionRepository transactionRepository;


    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = EntityMapping.transactionDtoToTransaction(transactionDto);
        transaction.setUuid(UUID.randomUUID().toString());
        transaction.setCreatedAt(LocalDateTime.now(ZoneId.of("Africa/Casablanca")));

        try {
            transactionRepository.save(transaction);
        } catch (Exception e){
            return null;
        }

        return EntityMapping.transactionToTransactionDto(transaction);
    }

    @Override
    public void deposit() {
        // This two methods will be filled later
    }

    @Override
    public void withdrawal() {
        // This two methods will be filled later
    }
}
