package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {
       // we need to create the account object
        Account account = Account.builder().id(UUID.randomUUID())
                .userId(userId).balance(balance).accountType(accountType).creationDate(createDate)
                .build();
        // save into database
        // return the object created
        Account saved = accountRepository.save(account);
        return saved;
    }

    @Override
    public List<Account> listOfAccounts() {
        // we need to get all accounts from repository

        return AccountRepository.findAll();
    }
}