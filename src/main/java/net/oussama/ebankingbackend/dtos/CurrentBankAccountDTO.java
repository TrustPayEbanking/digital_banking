package net.oussama.ebankingbackend.dtos;

import lombok.Data;
import net.oussama.ebankingbackend.enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDto {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customer;
    private double overdraft ;
}
