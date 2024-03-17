package com.cydeo.repository;

import com.cydeo.model.Account;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    public static List<Account> accountList = new ArrayList<>();

    public static List<Account> findAll() {
        return accountList;
    }

    public Account save(Account account){
        accountList.add(account);
        return account;
    }
}
