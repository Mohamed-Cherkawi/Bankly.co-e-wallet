package org.ewallet.transaction.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewallet.transaction.client.apis.WalletApis;
import org.ewallet.transaction.dto.TransactionDto;
import org.ewallet.transaction.dto.WalletDto;
import org.ewallet.transaction.entity.Transaction;
import org.ewallet.transaction.repository.TransactionRepository;
import org.ewallet.transaction.service.interfaces.TransactionServiceInter;
import org.ewallet.transaction.util.EntityMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionService implements TransactionServiceInter {
    private final TransactionRepository transactionRepository;
    private final WalletApis walletApis;

    @Override @Transactional
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

    private boolean deposit(WalletDto walletDto, Double amount) {
        TransactionDto transaction = createTransaction(TransactionDto
                .builder()
                .type("DEPOSIT")
                .amount(Double.toString(amount))
                .operatorReference(walletDto.getOwnerReference())
                .build());

        log.info("check filled transaction data {}",transaction);

        if(transaction == null)
            return false;

        amount = amount + Double.parseDouble(walletDto.getBalance());
        walletDto.setBalance(Double.toString(amount));

        log.info("Updated Balance Wallet : {}",walletDto);
        try {
            if(walletApis.updateWalletBalanceApi(walletDto).getStatusCode().toString().startsWith("200"))
                log.info("Updated Balance Wallet : {}",walletApis.updateWalletBalanceApi(walletDto).getBody());
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean withdrawal(WalletDto walletDto, Double amount) {
        return false;
    }

    @Override
    public String makeOperation(String operationType, TransactionDto transactionDto) {
        try {
            if( walletApis.getSingleWalletByOwnerReferenceApi(transactionDto.getOperatorReference()).getStatusCode().toString().startsWith("200") ){
                WalletDto walletDto = EntityMapping
                        .ObjectToWalletDto(walletApis.getSingleWalletByOwnerReferenceApi(transactionDto.getOperatorReference()).getBody());
                log.info("from test {}",walletDto);
                Double amount = isAmountValid(transactionDto.getAmount());

                if(amount != null){
                    operationType = operationType.toLowerCase();

                    boolean isOperationSucceed = false;

                    if(operationType.equals("deposit"))
                        isOperationSucceed = deposit(walletDto, amount);
                    else if(operationType.equals("withdrawal"))
                        isOperationSucceed = withdrawal(walletDto,amount);


                    return isOperationSucceed
                            ? "The " + operationType.toLowerCase() + " Operation Was Successfully Made"
                            : "The Operation Didn't Complete Something Went Wrong";
                } else
                    return "Something Wrong With The Provided Amount";
            }
        } catch (Exception e){
            return "The Provided Owner Reference does not exist";
        }
        return "";
    }

    private boolean isBalanceCompatible(WalletDto wallet, Double amount) {
            return true;
        }

    private Double isAmountValid(String amount){
        if(Pattern.matches("^0+$",amount))
                return null;
        try {
            return Double.parseDouble(amount);
        }catch (Exception e){
            return null;
        }
    }
}