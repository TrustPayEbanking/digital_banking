package net.oussama.ebankingbackend.dtos;

import lombok.Data;

import java.util.List;
@Data
public class AccountHistroyDto {
    private String accountId;
    List<AccountOperationDto>   operations;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private double balance;
}
