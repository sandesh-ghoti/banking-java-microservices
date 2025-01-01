package org.eazybytes.accounts.service.Impl;

import static org.eazybytes.accounts.mapper.CustomerMapper.toCustomer;

import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.eazybytes.accounts.constants.AccountConstants;
import org.eazybytes.accounts.dto.CustomerDto;
import org.eazybytes.accounts.entity.Account;
import org.eazybytes.accounts.entity.Customer;
import org.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import org.eazybytes.accounts.repository.AccountRepository;
import org.eazybytes.accounts.repository.CustomerRepository;
import org.eazybytes.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService {

  private AccountRepository accountRepository;
  private CustomerRepository customerRepository;

  /**
   * @param customerDto - CustomerDto Object
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = toCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already exists with given mobile number " + customerDto.getMobileNumber());
    }
    Customer savedCustomer = customerRepository.save(customer);
    accountRepository.save(createNewAccount(savedCustomer));
    return;
  }

  /**
   * @param customer - Customer Object
   * @return the new account details
   */
  private Account createNewAccount(Customer customer) {
    Account newAccount = new Account();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountConstants.SAVINGS);
    newAccount.setBranchAddress(AccountConstants.ADDRESS);
    return newAccount;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Accounts Details based on a given mobileNumber
   */
  @Override
  public CustomerDto getAccount(String mobileNumber) {
    return null;
  }

  /**
   * @param customerDto - Input customerDto Object with updates
   * @return boolean indicating if the update of Account details is successful or not
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    return false;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of Account details is successful or not
   */
  @Override
  public boolean deleteAccount(String mobileNumber) {
    return false;
  }
}
