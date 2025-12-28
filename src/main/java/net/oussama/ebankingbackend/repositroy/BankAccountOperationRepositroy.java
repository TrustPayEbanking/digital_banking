package net.oussama.ebankingbackend.repositroy;

import net.oussama.ebankingbackend.entites.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountOperationRepositroy extends JpaRepository<AccountOperation,Long> {
}
