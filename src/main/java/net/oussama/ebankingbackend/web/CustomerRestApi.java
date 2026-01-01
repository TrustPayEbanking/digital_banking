package net.oussama.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.oussama.ebankingbackend.Execption.CustomerNotFondExecption;
import net.oussama.ebankingbackend.dtos.CustomerDto;
import net.oussama.ebankingbackend.entites.Customer;
import net.oussama.ebankingbackend.repositroy.CustomersRepositroy;
import net.oussama.ebankingbackend.services.BanKAccountServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestApi {
    private final CustomersRepositroy customersRepositroy;
    private BanKAccountServices bankAccountServices;
     @GetMapping("/customers")
    public List<CustomerDto> customers(){
      return bankAccountServices.listCustomers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFondExecption {
         return bankAccountServices.get_Customers(id);
    }
    //requestbody il indique ou spring que on va recuper les donne sous forme json
    @PostMapping("/CreateCustomers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto request){
        return bankAccountServices.saveCustomer(request);
    }
    @PutMapping("/cutomers/{customers}")
    public CustomerDto updateCustomer(@PathVariable  Long id, @RequestBody CustomerDto customerDto) throws CustomerNotFondExecption {
        customerDto.setId(id);
        return bankAccountServices.updateCustomer(customerDto);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFondExecption {
        bankAccountServices.deletecutomers(id);
    }
    @GetMapping("/Customers/search")
    public List<CustomerDto> searchCustomer(@RequestParam(name = "keyword",defaultValue = "") String keyword){
          return bankAccountServices.search("%"+keyword+"%");
    }
}
