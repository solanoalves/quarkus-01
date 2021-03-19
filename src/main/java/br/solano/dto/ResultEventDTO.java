package br.solano.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResultEventDTO {
    private AccountBalanceDTO origin;
    private AccountBalanceDTO destination;
    public AccountBalanceDTO getOrigin() {
        return origin;
    }
    public void setOrigin(AccountBalanceDTO origin) {
        this.origin = origin;
    }
    public AccountBalanceDTO getDestination() {
        return destination;
    }
    public void setDestination(AccountBalanceDTO destination) {
        this.destination = destination;
    }
}
