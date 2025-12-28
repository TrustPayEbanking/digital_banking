package net.oussama.ebankingbackend;

import net.oussama.ebankingbackend.entites.AccountOperation;
import net.oussama.ebankingbackend.entites.CurrentAccount;
import net.oussama.ebankingbackend.entites.Customer;
import net.oussama.ebankingbackend.entites.SavingAccount;
import net.oussama.ebankingbackend.enums.AccountStatus;
import net.oussama.ebankingbackend.enums.OperationType;
import net.oussama.ebankingbackend.repositroy.BankAccountOperationRepositroy;
import net.oussama.ebankingbackend.repositroy.BankAccountRepositroy;
import net.oussama.ebankingbackend.repositroy.CustomersRepositroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner init(CustomersRepositroy customersRepositroy,
                           BankAccountRepositroy bankaccountrepositroy,
                           BankAccountOperationRepositroy aBankaccountrepositroy

    ) {
        return args -> {
            Stream.of("Hasan","khadija","Oussama").forEach(
                    str -> {
                        Customer customer = new Customer();
                        customer.setName(str);
                        customer.setEmail(str+"@gmail.com");
                        customersRepositroy.save(customer);
                    }
            );
            customersRepositroy.findAll().forEach(customer ->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setDate(new Date());
                currentAccount.setCustomer(customer);
                currentAccount.setStatus(AccountStatus.ACTIVETED);
                currentAccount.setOverdraft(Math.random()*900);
                bankaccountrepositroy.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*900);
                savingAccount.setDate(new Date());
                savingAccount.setCustomer(customer);
                savingAccount.setStatus(AccountStatus.ACTIVETED);
                savingAccount.setInterestRate(5.5);
                bankaccountrepositroy.save(savingAccount);
            });
            List<AccountOperation> operations =bankaccountrepositroy
                    .findAll()
                    .stream()
                    .flatMap(account -> IntStream.range(0,10).mapToObj(i->{
                        AccountOperation accountOperation = new AccountOperation();
                        accountOperation.setAccount(account);
                        accountOperation.setAmount(Math.random()*900);
                        accountOperation.setDate(new Date());
                        accountOperation.setType(Math.random()>0.5?OperationType.CREDIT:OperationType.DEBIT);
                                        System.out.println("iteraoin"+i+accountOperation);
                        return accountOperation;
                    }
                    )
                    ).toList();
            aBankaccountrepositroy.saveAll(operations);
        };
    }


}
