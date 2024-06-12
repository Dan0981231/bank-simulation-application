package com.cydeo.service;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date createDate, String message);

    List<TransactionDTO> findAllTransaction();

    List<TransactionDTO> lastTenTransactions();

    List<TransactionDTO> finTransactionListByAccountId(Long id);
}
