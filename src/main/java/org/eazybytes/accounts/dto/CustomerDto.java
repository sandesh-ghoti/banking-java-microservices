package org.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
  @NotEmpty(message = "email cannot be null")
  @Email
  private String email;

  @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
  @NotEmpty(message = "name cannot be null")
  private String name;

  @Pattern(regexp = "(^$|[6-9][0-9]{9})", message = "Mobile number must be valid 10 digits")
  private String mobileNumber;

  private AccountDto accountsDto;
}
