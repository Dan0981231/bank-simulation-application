package com.cydeo.repository;
import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<>();

    public static List<Transaction> findAll() {
        return transactionList;
    }


    public Transaction save(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;

    }


    public List<Transaction> findLastTenTransactions() {

        // write a stream that sort the transactions based on creation date and return 10 of them

        return transactionList.stream().
                sorted(Comparator.comparing(Transaction::getCreateDate).reversed()).
                limit(10).
                collect(Collectors.toList());
    }

    public List<Transaction> findTransactionListByAccountId(UUID id) {
// if account id is used either sender or receiver, return it
        return transactionList.stream().filter(transaction -> transaction.getSender().equals(id) ||
                        transaction.getReceiver().equals(id))
                .collect(Collectors.toList());

    }
    }
