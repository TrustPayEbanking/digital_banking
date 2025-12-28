package net.oussama.ebankingbackend.entites;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor @NoArgsConstructor
public class Customer {
    @Id
    private Long Id;
    private String name;
    private String LastName;
    private String Email;
    private List<BankAccount> BankAccounts;
}
