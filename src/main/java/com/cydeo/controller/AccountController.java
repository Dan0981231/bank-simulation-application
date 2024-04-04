package com.cydeo.controller;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private String createFormPage(Model model){

        // we need to provide empty account object

    model.addAttribute("account", Account.builder().build());

    // provide account type enum info to fill the dropdown options


    return "account/create-account";
}
}
