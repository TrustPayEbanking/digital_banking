package net.oussama.ebankingbackend.repositroy;

import net.oussama.ebankingbackend.entites.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepositroy extends JpaRepository<BankAccount,String> {
}
