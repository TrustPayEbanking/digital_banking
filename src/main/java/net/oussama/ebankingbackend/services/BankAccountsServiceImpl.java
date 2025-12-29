package net.oussama.ebankingbackend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.oussama.ebankingbackend.Execption.BalanceSoldeinsuffisantExecption;
import net.oussama.ebankingbackend.Execption.BankAccountNotfoundExecption;
import net.oussama.ebankingbackend.Execption.CustomerNotFondExecption;
import net.oussama.ebankingbackend.entites.*;
import net.oussama.ebankingbackend.enums.OperationType;
import net.oussama.ebankingbackend.repositroy.BankAccountOperationRepositroy;
import net.oussama.ebankingbackend.repositroy.BankAccountRepositroy;
import net.oussama.ebankingbackend.repositroy.CustomersRepositroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountsServiceImpl implements BanKAccountServices{

    private CustomersRepositroy customersRepositroy;
    private BankAccountRepositroy bankAccountRepositroy;
    private BankAccountOperationRepositroy bankAccountOperationRepositro;
    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer");
        return ((Customer)customersRepositroy.save(customer));
    }

    @Override
    public CurrentAccount saveBankCurrentAccount(double SoldeIntial, double overDraft, Long CustomerId) throws  CustomerNotFondExecption {
        Customer customer = customersRepositroy.findById(CustomerId).orElseThrow(()->new CustomerNotFondExecption("Customer not found"));

        log.info("Saving bank account");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setDate(new Date());
        currentAccount.setBalance(SoldeIntial);
        currentAccount.setOverdraft(overDraft);
        currentAccount.setCustomer(customer);

        return ((CurrentAccount)bankAccountRepositroy.save(currentAccount));
    }

    @Override
    public SavingAccount saveBankSavingAccount(double SoldeIntial, double interestRate, Long CustomerId) throws CustomerNotFondExecption {
        Customer customer =customersRepositroy.findById(CustomerId).orElseThrow(()->new CustomerNotFondExecption("Customer not found"));
        log.info("Saving bank account");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setDate(new Date());
        savingAccount.setBalance(SoldeIntial);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        return ((SavingAccount)bankAccountRepositroy.save(savingAccount));
    }

    @Override
    public List<Customer> listCustomers() {
        return customersRepositroy.findAll();
    }

    @Override
    public BankAccount getBankAccount(String Id) throws BankAccountNotfoundExecption {
        return bankAccountRepositroy.findById(Id).orElseThrow(()->new BankAccountNotfoundExecption("erreur leur de trouve le banke account"));
    }

    @Override
    public void debitAccount(String acountId, double amount, String description) throws BankAccountNotfoundExecption ,BalanceSoldeinsuffisantExecption{
        BankAccount bankAccount=getBankAccount(acountId);
        if(bankAccount.getBalance()<amount){
            throw new BalanceSoldeinsuffisantExecption("solde no insuffusant pour retire l'argent");
        }
        AccountOperation accountOperation= new  AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setDate(new Date());
        accountOperation.setAccount(bankAccount);
        bankAccountOperationRepositro.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepositroy.save(bankAccount);
    }

    @Override
    public void creditAccount(String acountId, double amount, String description) throws BankAccountNotfoundExecption {
        BankAccount bankAccount=getBankAccount(acountId);
        AccountOperation accountOperation= new  AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setDate(new Date());
        accountOperation.setAccount(bankAccount);
        bankAccountOperationRepositro.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepositroy.save(bankAccount);
    }

    @Override
    public void virementAccount(String acountIdSource, String accountIddestination, double amount) throws BankAccountNotfoundExecption {
       debitAccount(acountIdSource,amount,"transfere");
       creditAccount(acountIdSource,amount,"transfere");
    }
}
