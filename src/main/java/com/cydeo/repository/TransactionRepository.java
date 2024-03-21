package com.cydeo.repository;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<>();

    public static List<Transaction> findAll() {
        return transactionList;
    }


    public Transaction save(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;

    }


}