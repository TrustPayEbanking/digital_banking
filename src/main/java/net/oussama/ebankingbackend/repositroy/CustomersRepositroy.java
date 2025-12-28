package net.oussama.ebankingbackend.repositroy;

import net.oussama.ebankingbackend.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepositroy extends JpaRepository<Customer,Long> {

}
