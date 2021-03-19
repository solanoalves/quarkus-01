package br.solano.entity;

public class AccountBalance {
    private Account account;
    private Integer total;
    public AccountBalance(Account account, Integer total) {
        this.account = account;
        this.total = total;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getTotal() {
        return total;
    }
}
