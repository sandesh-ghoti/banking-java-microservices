package org.eazybytes.accounts.service.Impl;

import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.eazybytes.accounts.constants.AccountConstants;
import org.eazybytes.accounts.dto.AccountDto;
import org.eazybytes.accounts.dto.CustomerDto;
import org.eazybytes.accounts.entity.Account;
import org.eazybytes.accounts.entity.Customer;
import org.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import org.eazybytes.accounts.exception.ResourceNotFoundException;
import org.eazybytes.accounts.mapper.AccountsMapper;
import org.eazybytes.accounts.mapper.CustomerMapper;
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
    Customer customer = CustomerMapper.toCustomer(customerDto, new Customer());
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
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    Account account =
        accountRepository
            .findByCustomerId(customer.getCustomerId())
            .orElseThrow(
                () -> {
                  return new ResourceNotFoundException("Account", "customerid", mobileNumber);
                });
    AccountDto accountDto = AccountsMapper.toAccountDto(account, new AccountDto());
    CustomerDto customerDto = CustomerMapper.toCustomerDto(customer, new CustomerDto());
    customerDto.setAccountsDto(accountDto);
    return customerDto;
  }

  /**
   * @param customerDto - Input customerDto Object with updates
   * @return boolean indicating if the update of Account details is successful or not
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountDto accountDto = customerDto.getAccountsDto();
    if (accountDto != null) {

      Account account =
          accountRepository
              .findById(accountDto.getAccountNumber())
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Account", "accountNumber", accountDto.getAccountNumber().toString()));
      account = accountRepository.save(AccountsMapper.toAccount(accountDto, account));
      Long customerId = account.getCustomerId();
      Customer customer =
          customerRepository
              .findById(account.getCustomerId())
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Customer", "customerId", customerId.toString()));
      customerRepository.save(CustomerMapper.toCustomer(customerDto, customer));

      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of Account details is successful or not
   */
  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer =
        customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    accountRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.delete(customer);
    return true;
  }
}
