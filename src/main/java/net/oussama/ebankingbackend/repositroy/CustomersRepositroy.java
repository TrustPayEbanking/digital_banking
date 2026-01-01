package net.oussama.ebankingbackend.repositroy;

import net.oussama.ebankingbackend.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomersRepositroy extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c WHERE c.name like :kw ")
    List<Customer> serachcusomers(@Param("kw") String kw);
}
