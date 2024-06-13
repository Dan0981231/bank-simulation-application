package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.entity.Account;
import com.cydeo.entity.Transaction;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.UnderConstructionException;
import com.cydeo.mapper.TransactionMapper;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @SneakyThrows
    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        if(!underConstruction) {
        /*
            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance to make transfer ?
            if both accounts are checking, if not, one of them saving, it needs to be same userId
         */
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

         /*
            after all validations are completed, and money is transferred, we need to create Transaction object save/return it.
          */
            TransactionDTO transactionDTO = new TransactionDTO(amount,message,sender,receiver,creationDate);

            //save into db and return it
            transactionRepository.save(transactionMapper.convertToEntity(transactionDTO));
            return transactionDTO;

        }else {
            throw new UnderConstructionException("App is under construction, please try again later");
        }
    }

    @SneakyThrows
    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if(checkSenderBalance(sender, amount)){
            // update sender and receiver
            // 100 - 80 = 20
            sender.setBalance(sender.getBalance().subtract(amount));
            // 50 + 80 = 130
            receiver.setBalance(receiver.getBalance().add(amount));

            AccountDTO senderAc = accountService.findById(sender.getId());
            senderAc.setBalance(sender.getBalance());
            accountService.updateAccount(senderAc);

            AccountDTO receiverAc = accountService.findById(receiver.getId());
            receiverAc.setBalance(receiverAc.getBalance());
            accountService.updateAccount(receiverAc);
            /**
             * get a dto from database for both sender and receiver, update balance and save it
             * create account service updateAccount method and use it for saving
             */
        }else{
            // throw exception BalanceNotSufficientException
            throw new BalanceNotSufficientException("Balance is not enough");
        }
    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        // verify sender has enough balance to make a transfer
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0;
    }

    @SneakyThrows
    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        // write if statement if 1 account is saving and user and sender or receiver is not the same. Throw AccountOwnershipException

        if (sender.getAccountType().equals(AccountType.SAVING)||receiver.getAccountType().equals(AccountType.SAVING)&&!sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnershipException("If one of the account is saving, user should not be the same for sender and receiver");
        }
    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {

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

        // verify if account exists in Database or not

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(Long id) {
        accountService.findById(id);

    }

    @Override
    public List<TransactionDTO> findAllTransaction() {

        return transactionRepository.findAll().stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> lastTenTransactions() {
       // get last 10 transactions
        // write a query to get a result of last 10 transactions
        // convert to dto and return
       List<Transaction> last10Transactions = transactionRepository.findLast10Transaction();
       return last10Transactions.stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> finTransactionListByAccountId(Long id) {

        return transactionRepository.findAllByReceiver_IdOrSender_Id(id, id).stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }
}
