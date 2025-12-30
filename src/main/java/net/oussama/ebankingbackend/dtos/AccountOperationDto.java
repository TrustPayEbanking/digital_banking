package net.oussama.ebankingbackend.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import net.oussama.ebankingbackend.entites.BankAccount;
import net.oussama.ebankingbackend.enums.OperationType;

import java.util.Date;
@Data
public class AccountOperationDto {
    private Long Id;
    private Date date;
    private double amount;
    private OperationType type;
    private String description;
}
