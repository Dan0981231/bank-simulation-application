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
    public String getMakeTransfer(Model model){

        //what we need to provide to make transfer happen
        //we need to provide empty transaction object
        model.addAttribute("transactionDTO", new TransactionDTO());
        //we need to provide list of all accounts
        model.addAttribute("accounts",accountService.listAllActiveAccounts());
        //we need list of last 10 transactions to fill the table(business logic is missing)
        model.addAttribute("lastTransactions",transactionService.lastTenTransactions());

        return "transaction/make-transfer";
    }

    //write a post method that takes transaction object from the UI
    //complete the transfer and return the same page
    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transactionDTO") TransactionDTO transactionDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

            model.addAttribute("accounts",accountService.listAllActiveAccounts());
            model.addAttribute("lastTransactions",transactionService.lastTenTransactions());

            return "transaction/make-transfer";
        }
        //I have UUID of accounts but I need to provide account object.
        //I need to find the Accounts based on the ID that I have and use a parameter to complete make transfer
        AccountDTO sender = accountService.findById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.findById(transactionDTO.getReceiver().getId());
        transactionService.makeTransfer(sender,receiver, transactionDTO.getAmount(),new Date(), transactionDTO.getMessage());

        return "redirect:/make-transfer";
    }
    //TASK
    //write a method that gets the account id from index.html and print on the console
    //(work on index.html and here)
    //transaction/{id}
    //return transaction/transactions page
    @GetMapping("/transaction/{id}")
    public String getTransactionList(@PathVariable("id") Long id, Model model){
        System.out.println(id);
        //TASK
        //get the list of transactions based on the id and return as a model attribute
        //findTransactionListById
        List<TransactionDTO> transactionDTOListById = transactionService.finTransactionListByAccountId(id);
        model.addAttribute("transactions", transactionDTOListById);
        //(service and repository)
        return "transaction/transactions";
    }
    //go to transactions.html
    //based on the size of the transactions either show "no transactions yet" or transactions table
















}