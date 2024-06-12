package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class TransactionController {

private final AccountService accountService;
private final TransactionService transactionService;
    @GetMapping("/make-transfer")
    private String getMakeTransfer(Model model) {

        // what we need to provide to make transfer happen
        // provide empty transaction object
        model.addAttribute("transaction", new TransactionDTO());

        // we need to provide list of all accounts
        model.addAttribute("accounts", accountService.listOfAccounts());

        // we need list of last 10 transactions to fill the table (complete the business logic)

        model.addAttribute("lastTransactions", transactionService.lastTenTransactions());

        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    private String makeTransfer(@Valid @ModelAttribute("transaction") TransactionDTO transactionDTO, BindingResult bindingResult, Model model) {

        // I have UUID of accounts but i need to provide account object
        // I need to find accounts based on id that i have and use a s a parameter to complete make transfer
        if (bindingResult.hasErrors()){
            model.addAttribute("accounts", accountService.listOfAccounts());
            model.addAttribute("lastTransactions", transactionService.lastTenTransactions());
            return "transaction/make-transfer";
        }

        AccountDTO sender = accountService.findById(transactionDTO.getSender().getId());
        AccountDTO receiver =  accountService.findById(transactionDTO.getReceiver().getId());

      transactionService.makeTransfer(sender, receiver, transactionDTO.getAmount(), new Date(), transactionDTO.getMessage());

        return "redirect:/make-transfer";
    }

    // Task
    // write a method that gets the account id from index.html and print it in a console
    //  work on index.html to trigger the end point\// transaction/{id}
    // return transaction/transactions page

    @GetMapping("/transaction/{id}")
    public String deleteAccount(@PathVariable("id") Long id, Model model){
        System.out.println(id);
        // get the list of transactions based on the id and return as a model attribute
        // findTransactionListByAccountId
        // service and repository
       List<TransactionDTO> transactionDTOListById = transactionService.finTransactionListByAccountId(id);
       model.addAttribute("transactions", transactionDTOListById);
        return "transaction/transactions";
    }

    // go to transaction html file based on the size of the transaction either show "No transaction yet", or transaction table

}
