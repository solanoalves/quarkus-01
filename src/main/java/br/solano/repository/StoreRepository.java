package br.solano.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import br.solano.entity.Account;
import br.solano.entity.AccountBalance;

@ApplicationScoped
public class StoreRepository {
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, AccountBalance> accountBalances = new HashMap<>();
    
    public StoreRepository() {
        populate();
    }
    
    public Account getAccountById(Integer accountId) {
        return accounts.get(accountId);
    }
    
    public void saveAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }
    
    public AccountBalance getAccountBalance(Integer accountId) {
        return accountBalances.get(accountId);
    }
    
    public void saveAccountBalance(AccountBalance accountBalance) {
        accountBalances.put(accountBalance.getAccount().getAccountId(), accountBalance);
    }
    
    public void populate () {
        Account account;
        AccountBalance balance;
        
        accounts.clear();
        accountBalances.clear();
        
        account = new Account(300);
        balance = new AccountBalance(account, 0);
        
        accounts.put(account.getAccountId(), account);
        accountBalances.put(account.getAccountId(), balance);
    }
}
