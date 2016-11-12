package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by izzetince on 30/10/2016.
 *
 * DTO
 *
 */

public class TicketAddForm {
    @NotEmpty
    @Size(min=2, max=50)
    private String itemType;

    @NotNull
    private int amount = 1; //default

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
