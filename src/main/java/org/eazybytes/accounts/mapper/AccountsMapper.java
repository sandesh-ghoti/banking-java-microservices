package org.eazybytes.accounts.mapper;

import org.eazybytes.accounts.dto.AccountDto;
import org.eazybytes.accounts.entity.Account;

public class AccountsMapper {

    public static AccountDto toAccountDto(Account account, AccountDto accountDto) {

        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }

    public static Account toAccount(AccountDto accountDto, Account account) {

        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }
}
