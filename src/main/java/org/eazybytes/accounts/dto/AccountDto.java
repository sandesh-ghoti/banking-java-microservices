package org.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountDto {
  @NotEmpty(message = "accountNumber cannot be null")
  private Long accountNumber;

  @NotEmpty(message = "accountNumber cannot be null")
  private String accountType;

  @NotEmpty(message = "branchAddress cannot be null")
  private String branchAddress;
}
