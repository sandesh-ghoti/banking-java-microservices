package org.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
  @Schema(description = "Email of user", example = "jonhdoe@test.com")
  @NotEmpty(message = "email cannot be null")
  @Email
  private String email;

  @Schema(description = "Name of user", example = "John Doe")
  @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
  @NotEmpty(message = "name cannot be null")
  private String name;

  @Schema(description = "Mobile number of user", example = "9876543210")
  @Pattern(regexp = "(^$|[6-9][0-9]{9})", message = "Mobile number must be valid 10 digits")
  @NotEmpty(message = "mobile number cannot be null")
  private String mobileNumber;

  @Schema(description = "Account details of the Customer")
  private AccountDto accountsDto;
}
