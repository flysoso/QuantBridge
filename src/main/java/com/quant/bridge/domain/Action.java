package com.quant.bridge.domain;

/**
 * Created by yuer on 2016/11/29.
 */
public class Action {
    private Double position;
    private String security;
    private String name;
    private Integer amount;
    private Double price;

    public Action(Double position, String security, String name, Integer amount, Double price) {
        this.position = position;
        this.security = security;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Action() {

    }

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
