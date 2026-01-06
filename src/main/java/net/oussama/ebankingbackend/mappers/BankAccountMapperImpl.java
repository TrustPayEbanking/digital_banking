package net.oussama.ebankingbackend.mappers;

import net.oussama.ebankingbackend.dtos.AccountOperationDto;
import net.oussama.ebankingbackend.dtos.CurrentBankAccountDTO;
import net.oussama.ebankingbackend.dtos.CustomerDto;
import net.oussama.ebankingbackend.dtos.SavingBankAccountDTO;
import net.oussama.ebankingbackend.entites.AccountOperation;
import net.oussama.ebankingbackend.entites.CurrentAccount;
import net.oussama.ebankingbackend.entites.Customer;
import net.oussama.ebankingbackend.entites.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDto fromCustomer(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
    public Customer fromCustomerDto(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }
    public SavingBankAccountDTO fromsavingbankaccount(SavingAccount SavingAccount) {
     SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
     BeanUtils.copyProperties(SavingAccount, savingBankAccountDTO);
     savingBankAccountDTO.setCustomer(fromCustomer(SavingAccount.getCustomer()));
     savingBankAccountDTO.setType("SA");
     return savingBankAccountDTO;
    }
    public SavingAccount fromrSavingAccountDto(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomerDto(savingBankAccountDTO.getCustomer()));
        return savingAccount;
    }
    public CurrentBankAccountDTO fromcurrentbankaccounr(CurrentAccount  currentAccount) {
       CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
       BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
       currentBankAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));
       currentBankAccountDTO.setType("CUR");
       return currentBankAccountDTO;
    }
    public CurrentAccount fromcurrentbankaccountDto(CurrentBankAccountDTO currentBankAccountDTO) {
       CurrentAccount currentAccount = new CurrentAccount();
       BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
       currentAccount.setCustomer(fromCustomerDto(currentBankAccountDTO.getCustomer()));
       return currentAccount;
    }
    public AccountOperationDto fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDto accountOperationDto = new AccountOperationDto();
        BeanUtils.copyProperties(accountOperation,accountOperationDto);
        return accountOperationDto;
    }

}
