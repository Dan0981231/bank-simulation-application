package com.cydeo.service.impl;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public Transaction makeTransfer(Account sender, String message, Date createDate, BigDecimal amount, Account receiver) {
        return null;
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
