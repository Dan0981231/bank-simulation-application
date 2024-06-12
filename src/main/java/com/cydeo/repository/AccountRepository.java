package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.dto.AccountDTO;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


//    public static List<AccountDTO> accountDTOList = new ArrayList<>();
//
//    public static List<AccountDTO> findAll() {
//        return accountDTOList;
//    }
//
//    public AccountDTO save(AccountDTO accountDTO){
//        accountDTOList.add(accountDTO);
//        return accountDTO;
//    }
//
//    @SneakyThrows
//    public AccountDTO findById(Long id) {
//        // Task
//        // Complete this method that finds the account inside the account list
//        // if accountId is not available throw RecordNotFoundException
//
//        return accountDTOList.stream().filter(account -> account.getId().equals(id))
//                .findAny().orElseThrow(()-> new RecordNotFoundException("Account doesn't exist in database"));
//
//    }
}
