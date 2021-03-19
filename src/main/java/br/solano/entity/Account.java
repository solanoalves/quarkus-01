package br.solano.entity;

public class Account {
	private Integer accountId;
	public Account(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
