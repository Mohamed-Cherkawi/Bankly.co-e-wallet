package org.ewallet.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString @Setter @Getter
public class TransactionDto implements Serializable {
    private String uuid;
    private String type;
    private String createdAt;
    private String amount;
    private String operatorReference;
}