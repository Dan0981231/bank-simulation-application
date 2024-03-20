package com.cydeo.service.impl;

import com.cydeo.exception.BadRequestException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public Transaction makeTransfer(Account sender, String message, Date createDate, BigDecimal amount, Account receiver) {

        /**
         * if sender or receiver is null?
         * If sender and receiver is the same account
         * If sender has enough balance to make transfer
         * If both accounts are checking, if not and one is saving, it needs to be same user id
         */
       validateAccount(sender, receiver);



        // make transfer
        return null;
    }

    private void validateAccount(Account sender, Account receiver) {

        /**
         * if any of the account is null
         * if account ids are the same
         * if account exist in database
         */

        if(sender==null||receiver==null){
            throw new BadRequestException("Sender or receiver can't be null");
        }

        // If Accounts are the same throw the same exception with a message saying that accounts need to be different

        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Accounts can't be the same, and should be different then receiver account");
        }

    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
