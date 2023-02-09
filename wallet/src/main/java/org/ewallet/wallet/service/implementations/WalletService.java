package org.ewallet.wallet.service.implementations;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewallet.wallet.dto.WalletDto;
import org.ewallet.wallet.entity.Wallet;
import org.ewallet.wallet.repository.WalletRepository;
import org.ewallet.wallet.service.interfaces.WalletServiceInter;
import org.ewallet.wallet.util.EntityMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService implements WalletServiceInter {
    private final WalletRepository walletRepository;

    @Override
    public List<WalletDto> getAllWallets() {
         return walletRepository.findAll().stream()
                 .map(EntityMapping::walletToWalletDto)
                 .toList();
    }

    @Override
    public WalletDto getWalletByUuid(String uuid) {
        Optional<Wallet> wallet = walletRepository.getByUuid(uuid);
        if(wallet.isPresent())
            return EntityMapping.walletToWalletDto(wallet.get());

        return null;
    }

    @Override @Transactional
    public WalletDto createWallet(WalletDto walletDto) {
        Wallet wallet = EntityMapping.walletDtoToWallet(walletDto);

        log.info("test test",wallet);

        wallet.setUuid(UUID.randomUUID().toString());
        wallet.setCreationDate(LocalDateTime.now(ZoneId.of("Africa/Casablanca")));

        try {
            walletRepository.save(wallet);
        }catch (Exception e){
            return null;
        }

        log.info("A new wallet has been created {}",wallet);

        return EntityMapping.walletToWalletDto(wallet);
    }

    @Override
    public WalletDto updateWallet(WalletDto walletDto) {
        return null;
    }

    @Override
    public void addToBalance() {
        // To Be Created later
    }

    @Override
    public void subtractFromBalance() {
        // To Be Created later
    }
}