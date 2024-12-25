package com.example.project3.Service;

import com.example.project3.ApiResponce.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public List<Account> getMyAccount(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        return accountRepository.findAllByCustomer(customer);
    }

    public void addAccount(Integer customer_id, Account account) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void updateAccount(Integer customer_id, Integer account_id, Account account) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Account oldAccount = accountRepository.findAccountById(account_id);
        if (oldAccount == null) {
            throw new ApiException("Account not found");
        }
        if (customer == null) {
            throw new ApiException("customer not found");
        } else if (oldAccount.getCustomer().getId() != customer_id) {
            throw new ApiException("sorry you dont have authority");
        }
        oldAccount.setBalance(account.getBalance());
        oldAccount.setCustomer(customer);
        accountRepository.save(oldAccount);
    }


    public void activateAccount(Integer customerId, Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        Customer customer = customerRepository.findCustomerById(customerId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customer == null) {
            throw new ApiException("customer not found");
        } else if (account.getCustomer().getId() != customerId) {
            throw new ApiException("sorry you dont have authority");
        }
        account.setIsActive(true);
        accountRepository.save(account);


    }

    public void deleteAccount(Integer customer_id, Integer account_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account oldAccount = accountRepository.findAccountById(account_id);
        if (oldAccount == null) {
            throw new ApiException("Account not found");
        } else if (oldAccount.getCustomer().getId() != customer_id) {
            throw new ApiException("Customer id mismatch, Unauthorized action");
        }

        accountRepository.delete(oldAccount);

    }

    public Account viewAccountDetails(Integer customer_id,Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        if(account.getCustomer().getId()!=customer_id){
            throw new ApiException("Customer id mismatch,Unauthorized action");
        }

        return account;

    }


    public Account depositMoney(Integer customer_id,Integer account_id,double amount) {
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }

        if(account.getIsActive()!=true){
            throw new ApiException("Account must be Active");
        }

        if(account.getCustomer().getId()!= customer_id){
            throw new ApiException("Customer id mismatch,Unauthorized action");
        }
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdrawMoney( Integer customer_id,Integer account_id,double amount) {
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if(!account.getIsActive().equals(true)){
            throw new ApiException("Account must be Active");
        }
        if(account.getCustomer().getId()!= customer_id){
            throw new ApiException("Customer id mismatch,Unauthorized action");
        }
        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }



    public void transferFunds(Integer customer_id,Integer fromAccount_id, Integer toAccount_id,double amount) {
        Account fromAccount = accountRepository.findAccountById(fromAccount_id);
        Account toAccount = accountRepository.findAccountById(toAccount_id);
        if (fromAccount == null || toAccount == null) {
            throw new ApiException("Account not found");
        }
        if (fromAccount.getCustomer().getId() != customer_id) {
            throw new ApiException("Customer id mismatch,Unauthorized action");
        }
        if(!fromAccount.getIsActive().equals(true)||!toAccount.getIsActive().equals(true)){
            throw new ApiException("Account must be Active");
        }

        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }


    public void blockAccount(Integer customer_id, Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("Account not found");
        }

        if (!account.getCustomer().getId().equals(customer_id)) {
            throw new ApiException("You don't have permission to block this account");
        }
        if (Boolean.FALSE.equals(account.getIsActive())) {
            throw new ApiException("Account is already blocked");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }
}
