package net.oussama.ebankingbackend.repositroy;

import net.oussama.ebankingbackend.entites.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountOperationRepositroy extends JpaRepository<AccountOperation,Long> {
     List<AccountOperation> findByAccountId(String account);
     Page<AccountOperation> findByAccountId(String account, Pageable pageable);
}
