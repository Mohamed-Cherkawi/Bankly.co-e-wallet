package org.ewallet.wallet.service.interfaces;

import org.ewallet.wallet.dto.WalletDto;

import java.util.List;

public interface WalletServiceInter {
    List<WalletDto> getAllWallets();
    WalletDto getWalletByUuid(String reference);
    WalletDto createWallet(WalletDto walletDto);
    WalletDto updateWallet(WalletDto walletDto);
    void addToBalance();
    void subtractFromBalance();
}