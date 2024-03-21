package com.cydeo.repository;

import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<Account> accountList = new ArrayList<>();

    public static List<Account> findAll() {
        return accountList;
    }

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    @SneakyThrows
    public Account findById(UUID id) {
        // Task
        // Complete this method that finds the account inside the account list
        // if accountId is not available throw RecordNotFoundException

        return accountList.stream().filter(account -> account.getId().equals(id))
                .findAny().orElseThrow(()-> new RecordNotFoundException("Account doesn't exist in database"));

    }
}
