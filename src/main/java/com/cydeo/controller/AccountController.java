package com.cydeo.controller;
import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
//@RequestMapping("/index")
public class AccountController {

    // write a method to return index.html including account list information
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    private String getIndexPage(Model model){

        model.addAttribute("accountList", accountService.listOfAccounts());

        return "account/index";
    }

    @GetMapping("/create-form")
    private String createFormPage(Model model) {

        // we need to provide empty account object

        model.addAttribute("account", Account.builder().build());

        // provide account type enum info to fill the dropdown options
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

 /*
create a method to capture information from ui
print them on a console
trigger create new account method
createAccount based on the user input,
once user is created return back to the index page

 */

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") Account account, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("accountTypes", AccountType.values());
            return "account/create-account";
        }

        System.out.println(account);
        Account newAccount = accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());
        System.out.println("newAccount = " + newAccount);
        return "redirect:/index";
    }

@GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id){
        // print on the console
    System.out.println(id);

    // we need to find account with this account id and change the status to deleted

    accountService.deleteAccount(id);

    return "redirect:/index";
}

    @GetMapping("/activate/{id}")
    public String ActivateAccount(@PathVariable("id") UUID id){
        // print on the console
        System.out.println(id);

        // we need to find account with this account id and change the status to active

        accountService.activateAccount(id);

        return "redirect:/index";
    }



}
