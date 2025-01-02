package org.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountDto {

  @Schema(description = "10 digit Account number of user", example = "1562394089")
  @NotEmpty(message = "accountNumber cannot be null")
  private Long accountNumber;

  @Schema(description = "Account type of account", example = "test@test.com")
  @NotEmpty(message = "accountType cannot be null")
  private String accountType;

  @Schema(description = "branch address of user home branch", example = "123 Abc place")
  @NotEmpty(message = "branchAddress cannot be null")
  private String branchAddress;
}
