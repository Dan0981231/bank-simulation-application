package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import com.cydeo.mapper.AccountMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {

private final AccountRepository accountRepository;
private final AccountMapper accountMapper;
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        //we need to create the account object
        AccountDTO accountDTO1 = new AccountDTO();
        //save into the database(repository)
        AccountDTO saved = accountRepository.save(accountDTO1);
        //return the object created
    }


    @Override
    public List<AccountDTO> listOfAccounts() {
        // we need to get all accounts from repository
    List<Account> accountList = accountRepository.findAll();

    return accountList.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        // find the account belongs the id
       AccountDTO accountDTO =  accountRepository.findById(id);

        // set status to deleted

        accountDTO.setAccountStatus(AccountStatus.DELETED);
    }

    @Override
    public void activateAccount(Long id) {
        // find the account belongs the id
        AccountDTO accountDTO =  accountRepository.findById(id);

        // set status to Active

        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public AccountDTO findById(Long id) {

        return accountRepository.findById(id);
    }


}
