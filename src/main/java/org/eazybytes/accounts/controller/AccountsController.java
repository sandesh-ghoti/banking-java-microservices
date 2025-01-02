package org.eazybytes.accounts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.eazybytes.accounts.constants.AccountConstants;
import org.eazybytes.accounts.dto.CustomerDto;
import org.eazybytes.accounts.dto.ErrorResponseDto;
import org.eazybytes.accounts.dto.ResponseDto;
import org.eazybytes.accounts.service.IAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD rest apis for accounts service",
    description = "here is all our account service endpoints")
@AllArgsConstructor
@RestController
@RequestMapping(
    path = "/api",
    produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

  private IAccountsService accountsService;

  @Operation(summary = "Create account REST api", description = "This api is to create new account")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "HTTP Created"),
    @ApiResponse(
        responseCode = "400",
        description = "HTTP BadRequest, required valid requestbody",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    @ApiResponse(
        responseCode = "409",
        description = "HTTP Conflict",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountConstants.MESSAGE_201, AccountConstants.STATUS_201, null));
  }

  @Operation(
      summary = "Get account REST api",
      description = "This api is to get account using mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP OK"),
    @ApiResponse(
        responseCode = "400",
        description = "HTTP BadRequest, required valid requestbody",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Notfound",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @GetMapping("/get")
  public ResponseEntity<ResponseDto> getAccount(
      @Valid
          @RequestParam
          @Pattern(regexp = "(^$|[6-9][0-9]{9})", message = "Mobile number must be valid 10 digits")
          String mobileNumber) {
    CustomerDto customerDto = accountsService.getAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            new ResponseDto(
                AccountConstants.MESSAGE_200, AccountConstants.STATUS_200, customerDto));
  }

  @Operation(
      summary = "Update account REST api",
      description = "This api is to update existing account")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP OK"),
    @ApiResponse(
        responseCode = "400",
        description = "HTTP BadRequest, required valid requestbody",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Notfound",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = accountsService.updateAccount(customerDto);
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            new ResponseDto(AccountConstants.MESSAGE_200, AccountConstants.STATUS_200, isUpdated));
  }

  @Operation(
      summary = "Delete account REST api",
      description = "This api is to delete existing account using mobile number")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "HTTP OK"),
    @ApiResponse(
        responseCode = "400",
        description = "HTTP BadRequest, required valid requestbody",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    @ApiResponse(
        responseCode = "404",
        description = "HTTP Notfound",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    @ApiResponse(
        responseCode = "500",
        description = "HTTP Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAccount(
      @Valid
          @RequestParam
          @Pattern(regexp = "(^$|[6-9][0-9]{9})", message = "Mobile number must be valid 10 digits")
          String mobileNumber) {
    boolean isDeleted = accountsService.deleteAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            new ResponseDto(AccountConstants.MESSAGE_200, AccountConstants.STATUS_200, isDeleted));
  }
}
