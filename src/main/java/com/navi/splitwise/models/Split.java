package com.navi.splitwise.models;

import lombok.Data;

/**
 * @author Abhinav Tripathi 15/03/21
 */
@Data
public class Split {
    private User paidToUser;
    private double amount;

    public Split(User paidToUser, double amount) {
        this.paidToUser = paidToUser;
        this.amount = amount;
    }
}
