package com.cydeo.service;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date createDate, String message);

    List<Transaction> findAllTransaction();

    List<Transaction> lastTenTransactions();

    List<Transaction> finTransactionListByAccountId(UUID id);
}
