package com.cydeo.service;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction makeTransfer(Account sender, String message, Date createDate, BigDecimal amount, Account receiver);

    List<Transaction> findAllTransaction();
}
