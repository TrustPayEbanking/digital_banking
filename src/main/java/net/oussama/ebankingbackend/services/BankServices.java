package net.oussama.ebankingbackend.services;

import jakarta.transaction.Transactional;
import net.oussama.ebankingbackend.entites.BankAccount;
import net.oussama.ebankingbackend.entites.CurrentAccount;
import net.oussama.ebankingbackend.entites.SavingAccount;
import net.oussama.ebankingbackend.repositroy.BankAccountRepositroy;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankServices{
    private final BankAccountRepositroy bankAccountRepositroy;

    public BankServices(BankAccountRepositroy bankAccountRepositroy) {
        this.bankAccountRepositroy = bankAccountRepositroy;
    }

    public void consulter(){
        BankAccount bankAccount = bankAccountRepositroy.findById("8d3cf00e-6862-4d9a-bd8b-aa585f79d40f").orElse(null);
        System.out.println("*****************");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getDate());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCustomer().getName());
        if(bankAccount instanceof CurrentAccount){
            System.out.println(((CurrentAccount)bankAccount).getOverdraft());
        }else if(bankAccount instanceof SavingAccount) {
            System.out.println(((SavingAccount)bankAccount).getInterestRate());
        }
        bankAccount.getOperations().forEach(operation -> {
            System.out.println(operation.getType());
            System.out.println(operation.getAmount());
            System.out.println(operation.getDate());

        });
    }
}
