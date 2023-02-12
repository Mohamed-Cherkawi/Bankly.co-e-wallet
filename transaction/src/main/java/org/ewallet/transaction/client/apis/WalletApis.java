package org.ewallet.transaction.client.apis;

import org.ewallet.transaction.dto.WalletDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ewallet-transaction-service-v1")
public interface WalletApis {
    @GetMapping("/fetching/all")
    ResponseEntity<List<WalletDto>> getAllWalletsApi();
    @GetMapping("/fetching/single/ref/{uuid}")
    ResponseEntity<Object> getSingleWalletByReferenceApi(@PathVariable("uuid") String reference);
    @PostMapping("/adding")
    ResponseEntity<Object> saveWalletApi(@RequestBody WalletDto walletDto);
}