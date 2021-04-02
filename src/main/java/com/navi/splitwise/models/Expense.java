package com.navi.splitwise.models;

import lombok.Data;

import java.util.List;

/**
 * @author Abhinav Tripathi 15/03/21
 */
@Data
public class Expense {
    private Long id;
    private double amount;
    private User paidByUser;
    private List<Split> splits;

    public Expense(double amount, User paidByUser, List<Split> splits) {
        this.amount = amount;
        this.paidByUser = paidByUser;
        this.splits = splits;
    }
}
