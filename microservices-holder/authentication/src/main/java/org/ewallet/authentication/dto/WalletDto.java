package org.ewallet.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class WalletDto {
    private String uuid ;
    private String name ;
    private String balance ;
    private String creationDate ;
    private String ownerReference;
}