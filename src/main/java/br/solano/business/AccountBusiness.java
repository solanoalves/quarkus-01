package br.solano.business;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.solano.dto.AccountBalanceDTO;
import br.solano.dto.EventDTO;
import br.solano.dto.ResultEventDTO;
import br.solano.entity.Account;
import br.solano.entity.AccountBalance;
import br.solano.exception.AccountNotFoundException;
import br.solano.repository.StoreRepository;
import br.solano.type.EventType;

@Dependent
public class AccountBusiness {
	
	@Inject
	StoreRepository storeRepository;
	
	public ResultEventDTO processAccountEvent(EventDTO eventDTO) throws AccountNotFoundException {
		ResultEventDTO resultEventDTO = null;
		switch(EventType.valueOf(eventDTO.getType().toUpperCase())) {
			case DEPOSIT:
				resultEventDTO = depositIntoAccount(eventDTO.getDestination(), eventDTO.getAmount());
				break;
			case TRANSFER:
				resultEventDTO = transferToAccount(eventDTO.getOrigin(), eventDTO.getDestination(), eventDTO.getAmount());
				break;
			case WITHDRAW:
				resultEventDTO = withdrawFromAccount(eventDTO.getOrigin(), eventDTO.getAmount());
				break;
			default:
				break;
		}
		return resultEventDTO;
	}
	
	private void createAccount(String destination, Integer amount) {
		Account account = new Account(Integer.valueOf(destination));
		AccountBalance accountBalance = new AccountBalance(account, amount);
		
		storeRepository.saveAccount(account);
		storeRepository.saveAccountBalance(accountBalance);
	}
	
	private ResultEventDTO depositIntoAccount(String destination, Integer amount) {
		AccountBalance accountBalance = storeRepository.getAccountBalance(Integer.valueOf(destination));
		
		if(accountBalance == null) {
			createAccount(destination, amount);
			accountBalance = storeRepository.getAccountBalance(Integer.valueOf(destination));
		} else {
			accountBalance.setTotal(accountBalance.getTotal() + amount);
		}
		
		return convertToResultEventDTO(null, accountBalance);
	}
	
	private ResultEventDTO withdrawFromAccount(String origin, Integer amount) throws AccountNotFoundException {
		AccountBalance accountBalance = storeRepository.getAccountBalance(Integer.valueOf(origin));
		
		if(accountBalance == null) {
			throw new AccountNotFoundException();
		} else {
			accountBalance.setTotal(accountBalance.getTotal() - amount);
		}
		
		return convertToResultEventDTO(accountBalance, null);
	}
	
	private ResultEventDTO transferToAccount(String origin, String destination, Integer amount) throws AccountNotFoundException {
		AccountBalance originAccount = storeRepository.getAccountBalance(Integer.valueOf(origin));
		AccountBalance destinationAccount = storeRepository.getAccountBalance(Integer.valueOf(destination));
		
		if(originAccount == null) {
			throw new AccountNotFoundException();
		} else {
			destinationAccount.setTotal(destinationAccount.getTotal() + amount);
			originAccount.setTotal(originAccount.getTotal() - amount);
		}
		
		return convertToResultEventDTO(originAccount, destinationAccount);
	}
	
	public Integer getBalanceFromAccount(Integer accountId) throws AccountNotFoundException {
		AccountBalance accountBalance = storeRepository.getAccountBalance(accountId);
		
		if(accountBalance == null)
			throw new AccountNotFoundException();
		
		return accountBalance != null ? accountBalance.getTotal() : null;
	}
	
	private ResultEventDTO convertToResultEventDTO(AccountBalance originAccount, AccountBalance destinationAccount) {
		AccountBalanceDTO originAccountDTO = null, destinationAccountDTO = null;
		
		if(originAccount != null) {
			originAccountDTO = new AccountBalanceDTO();
			originAccountDTO.setId(originAccount.getAccount().getAccountId().toString());
			originAccountDTO.setBalance(originAccount.getTotal());
		}
		if(destinationAccount != null) {
			destinationAccountDTO = new AccountBalanceDTO();
			destinationAccountDTO.setId(destinationAccount.getAccount().getAccountId().toString());
			destinationAccountDTO.setBalance(destinationAccount.getTotal());
		}
		
		ResultEventDTO resultEventDTO = new ResultEventDTO();
		resultEventDTO.setOrigin(originAccountDTO);
		resultEventDTO.setDestination(destinationAccountDTO);
		
		return resultEventDTO;
	}
}
