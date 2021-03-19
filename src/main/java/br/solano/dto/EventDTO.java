package br.solano.dto;

public class EventDTO {
    private String type;
    private String origin;
    private String destination;
    private Integer amount;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
