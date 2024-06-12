package com.cydeo;

import com.cydeo.dto.AccountDTO;
import com.cydeo.enums.AccountType;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication {

    public static void main(String[] args) {
      ApplicationContext container = SpringApplication.run(BankSimulationApplication.class, args);
      // get account and transaction service beans
        //get account and transaction service beans
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);

      //  create 2 accounts sender and receiver
//        AccountDTO sender = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING, 1L);
//        AccountDTO receiver = accountService.createNewAccount(BigDecimal.valueOf(30), new Date(), AccountType.CHECKING, 2L);
//      AccountDTO sender1 = accountService.createNewAccount(BigDecimal.valueOf(200), new Date(), AccountType.CHECKING, 123L);
//      AccountDTO receiver1 = accountService.createNewAccount(BigDecimal.valueOf(900), new Date(), AccountType.SAVING, 221L);
        AccountDTO sender2 = null;

//        accountService.listOfAccounts().forEach(System.out::println);
//
//        transactionService.makeTransfer(sender,receiver,new BigDecimal(10),new Date(),"Transaction 1");
//
//        System.out.println(transactionService.findAllTransaction().get(0));
//
//        accountService.listOfAccounts().forEach(System.out::println);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
