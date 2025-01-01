package org.eazybytes.accounts.service;

import org.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {
  /**
   * Create new account for provided user details 1. check user must be new 2. create user 3. create
   * account
   *
   * @param customerDto - CustomerDto Object
   */
  void createAccount(CustomerDto customerDto);

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Accounts Details based on a given mobileNumber
   */
  CustomerDto getAccount(String mobileNumber);

  /**
   * @param customerDto - Input customerDto Object with updates
   * @return boolean indicating if the update of Account details is successful or not
   */
  boolean updateAccount(CustomerDto customerDto);

  /**
   * @param mobileNumber - Input Mobile Number
   * @return boolean indicating if the delete of Account details is successful or not
   */
  boolean deleteAccount(String mobileNumber);
}
