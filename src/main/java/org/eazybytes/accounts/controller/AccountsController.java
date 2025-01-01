package org.eazybytes.accounts.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.eazybytes.accounts.constants.AccountConstants;
import org.eazybytes.accounts.dto.CustomerDto;
import org.eazybytes.accounts.dto.ResponseDto;
import org.eazybytes.accounts.service.IAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(
    path = "/api",
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

  private IAccountsService accountsService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountConstants.MESSAGE_201, AccountConstants.STATUS_201, null));
  }

  @GetMapping("/get")
  public ResponseEntity<ResponseDto> getAccount(@Valid @RequestParam String mobileNumber) {
    CustomerDto customerDto = accountsService.getAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            new ResponseDto(
                AccountConstants.MESSAGE_200, AccountConstants.STATUS_200, customerDto));
  }
}
